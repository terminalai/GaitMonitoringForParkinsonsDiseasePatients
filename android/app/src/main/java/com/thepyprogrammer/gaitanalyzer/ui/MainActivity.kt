package com.thepyprogrammer.gaitanalyzer.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.KeyEvent
import android.view.WindowManager
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.ActivityMainBinding
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.auth.AuthViewModel
import com.thepyprogrammer.gaitanalyzer.ui.main.home.HomeViewModel
import com.thepyprogrammer.gaitanalyzer.ui.onboarding.OnboardingFragment
import com.thepyprogrammer.ktlib.io.KFile
import com.thepyprogrammer.ktlib.randInt
import com.thepyprogrammer.ktlib.toEpoch
import java.io.File
import java.io.PrintWriter
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private lateinit var homeViewModel: HomeViewModel

    lateinit var wakeLock: PowerManager.WakeLock

    lateinit var sensorManager: SensorManager

    lateinit var accelerometer: Sensor
    lateinit var gyroscope: Sensor

    lateinit var accListener: SensorEventListener
    lateinit var gyroListener: SensorEventListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        FirebaseUtil.retrieveFreeze(this) {}

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.parent_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.filesDir.value = filesDir

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    }


    val currentFragment: Fragment
        get() = (
                supportFragmentManager
                    .findFragmentById(R.id.parent_nav_host_fragment)?.childFragmentManager?.fragments?.get(
                        0
                    )
                )!!


    fun logout(): Boolean {

        val freezesFile = File(filesDir, "freezes.txt")
        FirebaseUtil.uploadFreeze(freezesFile) {
            for(filename in arrayOf("freezes", "accs", "gyros", "accountDetails", "profileImageURI")) {
                val file = File(filesDir, "$filename.txt")
                if(!file.exists()) file.createNewFile()
                val fileWriter = KFile.write(file)
                fileWriter.close()
            }

            FirebaseUtil.user = null

            val authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
            authViewModel.pName.value = ""
            authViewModel.password.value = ""
            authViewModel.error.setValue("DON'T LOGIN", "")
            authViewModel.userResult.value = null

            navController.navigate(R.id.action_nav_main_to_nav_identification)
        }
        return true

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        try {
            val fragments = supportFragmentManager.fragments
            for (fragment in fragments) {
                if (!fragment.isVisible) continue
                if (fragment is OnboardingFragment && fragment.onBackPressed()) {
                    return true
                }
            }

            val hub = findViewById<WebView>(R.id.hub)
            if (keyCode == KeyEvent.KEYCODE_BACK && hub.canGoBack()) {
                hub.goBack()
                return true
            }
        } catch (ex: Exception) {
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        val freezesFile = File(filesDir, "freezes.txt")
        FirebaseUtil.uploadFreeze(freezesFile)
    }

    @SuppressLint("BatteryLife")
    fun setScreenOn() {
        val pm = getSystemService(POWER_SERVICE) as PowerManager
        if (!pm.isIgnoringBatteryOptimizations(packageName)) {
            val intent = Intent().apply {
                action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                data = Uri.parse("package: $packageName")
            }
            startActivity(intent)
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        wakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
                    acquire()
                }
            }
    }

    fun setScreenOff() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        wakeLock.release()
    }

}