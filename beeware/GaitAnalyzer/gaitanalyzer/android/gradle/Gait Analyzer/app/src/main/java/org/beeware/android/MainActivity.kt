package org.beeware.android

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.system.ErrnoException
import android.system.Os
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.thepyprogrammer.gaitanalyzer.R
import org.beeware.rubicon.Python
import java.io.*
import java.nio.charset.StandardCharsets
import java.util.zip.ZipInputStream

class MainActivity : AppCompatActivity() {
    @Throws(IOException::class)
    private fun ensureDir(dir: File): File {
        if (dir.exists()) {
            return dir
        }
        val mkdirResult = dir.mkdirs()
        if (!mkdirResult) {
            throw IOException("Unable to make directory " + dir.absolutePath)
        }
        return dir
    }

    // `stdlib` is used as PYTHONHOME.

    // We cache the stdlib by checking the contents of this file.

    // Put `user_code` into paths so we can unpack the assets into it.

    // `app` and `app_packages` store user code and user code dependencies,
    // respectively. These paths exist within the `python/` assets tree.
    @get:Throws(IOException::class)
    private val pythonPaths: Map<String, File>
        get() {
            val base = File(applicationContext.filesDir.absolutePath + "/python/")

            // `stdlib` is used as PYTHONHOME.
            val stdlib = File(base.absolutePath + "/stdlib/")
            ensureDir(stdlib)

            return hashMapOf(
                    "stdlib" to stdlib,
                    // We cache the stdlib by checking the contents of this file.
                    "stdlib-last-filename" to File(base.absolutePath + "/stdlib.last-filename"),
                    // Put `user_code` into paths so we can unpack the assets into it.
                    "user_code" to File(base.absolutePath + "/user_code/"),
                    // `app` and `app_packages` store user code and user code dependencies,
                    // respectively. These paths exist within the `python/` assets tree.
                    "app" to File(base.absolutePath + "/user_code/app/"),
                    "app_packages" to File(base.absolutePath + "/user_code/app_packages/")
            )
        }

    @Throws(IOException::class)
    private fun unpackPython(paths: Map<String, File>) {
        // Try to find `lastUpdateTime` on disk; compare it to actual `lastUpdateTime` from package manager.
        // https://developer.android.com/reference/android/content/pm/PackageInfo.html#lastUpdateTime
        val context = this.applicationContext
        val lastUpdateTimeFile = File(context.cacheDir, "last-update-time")
        var storedLastUpdateTime: String? = null
        var actualLastUpdateTime: String? = null
        if (lastUpdateTimeFile.exists()) {
            val reader = BufferedReader(InputStreamReader(FileInputStream(lastUpdateTimeFile), StandardCharsets.UTF_8))
            storedLastUpdateTime = reader.readLine()
        }
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            actualLastUpdateTime = packageInfo.lastUpdateTime.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, "Unable to find package; using default actualLastUpdateTime")
        }
        if (storedLastUpdateTime != null && storedLastUpdateTime == actualLastUpdateTime) {
            Log.d(TAG, "unpackPython() complete: Exiting early due to lastUpdateTime match: $storedLastUpdateTime")
            return
        }
        val myAbi = Build.SUPPORTED_ABIS[0]
        val pythonHome = paths["stdlib"]

        // Get list of assets under the stdlib/ directory, filtering for our ABI.
        val stdlibAssets = this.assets.list("stdlib")
        var pythonHomeZipFilename: String? = null
        val abiZipSuffix = "$myAbi.zip"
        for (i in stdlibAssets!!.indices) {
            val asset = stdlibAssets[i]
            if (asset.startsWith("pythonhome.") && asset.endsWith(abiZipSuffix)) {
                pythonHomeZipFilename = "stdlib/$asset"
                break
            }
        }
        // Unpack stdlib, except if it's missing, abort; and if we already unpacked a
        // file of the same name, then skip it. That way, the filename can serve as
        // a cache identifier.
        if (pythonHomeZipFilename == null) {
            throw RuntimeException("Unable to find file matching pythonhome.* and " +
                    abiZipSuffix)
        }
        val stdlibLastFilenamePath = paths["stdlib-last-filename"]
        var cacheOk = false
        if (stdlibLastFilenamePath!!.exists()) {
            val reader = BufferedReader(InputStreamReader(FileInputStream(stdlibLastFilenamePath), StandardCharsets.UTF_8))
            val stdlibLastFilename = reader.readLine()
            if (stdlibLastFilename == pythonHomeZipFilename) {
                cacheOk = true
            }
        }
        if (cacheOk) {
            Log.d(TAG, "Skipping unpack of Python stdlib due to cache hit on $pythonHomeZipFilename")
        } else {
            Log.d(TAG, "Unpacking Python stdlib due to cache miss on $pythonHomeZipFilename")
            unzipTo(ZipInputStream(this.assets.open(pythonHomeZipFilename)), pythonHome!!)
            val writer = BufferedWriter(OutputStreamWriter(FileOutputStream(stdlibLastFilenamePath), StandardCharsets.UTF_8))
            writer.write(pythonHomeZipFilename, 0, pythonHomeZipFilename.length)
            writer.close()
        }
        val userCodeDir = paths["user_code"]
        Log.d(TAG, "Unpacking Python assets to base dir " + userCodeDir!!.absolutePath)
        unpackAssetPrefix(assets, "python", userCodeDir)
        if (actualLastUpdateTime != null) {
            Log.d(TAG, "Replacing old lastUpdateTime = $storedLastUpdateTime with actualLastUpdateTime = $actualLastUpdateTime")
            val timeWriter = BufferedWriter(OutputStreamWriter(FileOutputStream(lastUpdateTimeFile), StandardCharsets.UTF_8))
            timeWriter.write(actualLastUpdateTime, 0, actualLastUpdateTime.length)
            timeWriter.close()
        }
        Log.d(TAG, "unpackPython() complete")
    }

    @Throws(IOException::class, ErrnoException::class)
    private fun setPythonEnvVars(pythonHome: String) {
        Log.d(TAG, "setPythonEnvVars() start")
        Log.v(TAG, "pythonHome=$pythonHome")
        val applicationContext = this.applicationContext
        val cacheDir = applicationContext.cacheDir

        // Set stdout and stderr to be unbuffered. We are overriding stdout/stderr and would
        // prefer to avoid delays.
        Os.setenv("PYTHONUNBUFFERED", "1", true)

        // Tell rubicon-java's Python code where to find the C library, to access it via ctypes.
        Os.setenv("RUBICON_LIBRARY", this.applicationInfo.nativeLibraryDir + "/librubicon.so", true)
        Os.setenv("TMPDIR", cacheDir.absolutePath, true)
        Os.setenv("LD_LIBRARY_PATH", this.applicationInfo.nativeLibraryDir, true)
        Os.setenv("PYTHONHOME", pythonHome, true)
        Os.setenv("ACTIVITY_CLASS_NAME", "org/beeware/android/MainActivity", true)
        Log.d(TAG, "setPythonEnvVars() complete")
    }

    @Throws(Exception::class)
    private fun startPython(paths: Map<String, File>) {
        unpackPython(paths)
        val pythonHome = paths["stdlib"]!!.absolutePath
        setPythonEnvVars(pythonHome)
        Log.d(TAG, "Computing Python version.")
        // Compute Python version number so that we can make sure it's first on sys.path when we
        // configure sys.path with Python.init().
        val libpythons = File(this.applicationInfo.nativeLibraryDir).list { _, s -> s.startsWith("libpython") && s.endsWith(".so") }
        if (libpythons?.isEmpty() == true) {
            throw Exception("Unable to compute Python version")
        }
        val pythonVersion = libpythons[0].replace("libpython", "").replace("m*.so".toRegex(), "")
        Log.d(TAG, "Computed Python version: $pythonVersion")

        // `app` is the last item in the sysPath list.
        val sysPath = ("$pythonHome/lib/python$pythonVersion/" + ":"
                + paths["app_packages"]!!.absolutePath + ":" + paths["app"]!!.absolutePath)
        if (Python.init(pythonHome, sysPath, null) != 0) {
            throw Exception("Unable to start Python interpreter.")
        }
        Log.d(TAG, "Python.init() complete")

        // Run the app's main module, similar to `python -m`.
        Log.d(TAG, "Python.run() start")
        Python.run("gaitanalyzer", arrayOfNulls(0))
        Log.d(TAG, "Python.run() end")
        Log.d(TAG, "startPython() end")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() start")
        captureStdoutStderr()
        Log.d(TAG, "onCreate(): captured stdout/stderr")
        // Change away from the splash screen theme to the app theme.
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this)
        this.setContentView(layout)
        singletonThis = this
        try {
            val paths = pythonPaths
            startPython(paths)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to create Python app", e)
            return
        }
        Log.d(TAG, "user code onCreate() start")
        pythonApp!!.onCreate()
        Log.d(TAG, "user code onCreate() complete")
        Log.d(TAG, "onCreate() complete")
    }

    override fun onStart() {
        Log.d(TAG, "onStart() start")
        super.onStart()
        pythonApp?.onStart()
        Log.d(TAG, "onStart() complete")
    }

    override fun onResume() {
        Log.d(TAG, "onResume() start")
        super.onResume()
        pythonApp?.onResume()
        Log.d(TAG, "onResume() complete")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult() start")
        super.onActivityResult(requestCode, resultCode, data)
        pythonApp?.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult() complete")
    }

    private external fun captureStdoutStderr(): Boolean

    companion object {
        private var pythonApp: IPythonApp? = null

        /**
         * This method is called by `app.__main__` over JNI in Python when the BeeWare
         * app launches.
         *
         * @param app
         */
        fun setPythonApp(app: IPythonApp?) {
            pythonApp = app
        }

        /**
         * We store the MainActivity instance on the *class* so that we can easily
         * access it from Python.
         */
        var singletonThis: MainActivity? = null

        init {
            System.loadLibrary("native-lib")
        }

        // To profile app launch, use `adb -s MainActivity`; look for "onCreate() start" and "onResume() completed".
        private const val TAG = "MainActivity"
    }
}