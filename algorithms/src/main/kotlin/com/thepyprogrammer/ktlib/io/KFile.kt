package com.thepyprogrammer.ktlib.io

import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintWriter
import java.net.MalformedURLException
import java.net.URI
import java.net.URL
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class KFile @JvmOverloads constructor(filename: String?, type: Char = 'r') : java.io.File(filename),
        Cloneable, AutoCloseable {
    var out: PrintWriter? = null
    var `in`: Scanner? = null
    var outstream: FileOutputStream? = null
    private var type = 0.toChar()

    constructor(file: java.io.File) : this(file.absolutePath)
    constructor(file: java.io.File, type: Char) : this(file.absolutePath, type)
    constructor(url: URL) : this(url.file)
    constructor(url: URL, type: Char) : this(url.file, type)
    constructor(uri: URI) : this(uri.path)
    constructor(uri: URI, type: Char) : this(uri.path, type)

    // Methods
    override fun getName(): String {
        return absolutePath
    }

    override fun getAbsoluteFile(): KFile {
        return KFile(super.getAbsoluteFile())
    }

    override fun getParentFile(): KFile {
        return KFile(super.getParentFile())
    }

    override fun getCanonicalFile(): KFile? {
        return try {
            KFile(super.getCanonicalFile())
        } catch (e: IOException) {
            null
        }
    }

    fun rename(dest: KFile?): Boolean {
        return renameTo(dest)
    }

    override fun listFiles(): Array<KFile?> {
        return convert(*listFiles())
    }

    val relativePath: String
        get() = KFile(System.getProperty("user.dir")).relativize(this)

    fun getRelativePath(directory: String?): String {
        return KFile(directory).relativize(this)
    }

    fun getRelativePath(directory: KFile): String {
        return directory.relativize(this)
    }

    operator fun hasNext(): Boolean {
        return `in`!!.hasNext()
    }

    fun count(substring: String): Int {
        val input = read()
        val words = input.split(substring.toRegex()).toTypedArray()
        return words.size - 1
    }

    fun count(substring: Char): Int {
        return count(substring.toString() + "")
    }

    fun count(substring: Int): Int {
        return count(substring.toString() + "")
    }

    fun count(substring: Double): Int {
        return count(substring.toString() + "")
    }

    fun count(substring: Float): Int {
        return count(substring.toString() + "")
    }

    fun split(delimeter: String): Array<String> {
        val input = read()
        return input.split(delimeter.toRegex()).toTypedArray()
    }

    fun read(): String {
        try {
            return String(Files.readAllBytes(Paths.get(absolutePath)))
        } catch (e: IOException) {
        }
        return ""
    }

    fun readLine(): String {
        if (`in` != null) {
            if (hasNext()) return `in`!!.nextLine() else {
                try {
                    val inp = Scanner(this)
                    return inp.nextLine()
                } catch (e: IOException) {
                }
            }
        }
        return ""
    }

    fun readLines(): Array<String> {
        var lines = emptyList<String>()
        try {
            lines = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8)
        } catch (e: IOException) {
        }
        return lines.toTypedArray()
    }

    override fun close() {
        try {
            `in`!!.close()
            scanners.remove(absolutePath)
        } catch (ex: NullPointerException) {
        }
        try {
            if (type == 'w') {
                out!!.close()
                overwriters.remove(
                        absolutePath
                )
            } else if (type == 'a') {
                out!!.close()
                appendwriters.remove(
                        absolutePath
                )
            }
        } catch (ex: NullPointerException) {
        }
    }

    fun nonExistent() = !exists()

    fun toGeneric(): java.io.File {
        close()
        return this
    }

    fun relativize(file: KFile): String {
        return relativize(file)
    }

    fun relativize(uri: URI?): String {
        return relativize(uri)
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        return if (other == null || other !is KFile) false else absolutePath == other.absolutePath
    }

    fun equals(other: KFile): Boolean {
        return absolutePath == other.absolutePath
    }

    public override fun clone(): KFile {
        return KFile(absolutePath)
    }

    operator fun compareTo(o: KFile?): Int {
        return super.compareTo(o)
    }

    companion object {
        // Ensuring only a singular in, out exists per file
        private val scanners = HashMap<String, Scanner>()
        private val appendwriters = HashMap<String, PrintWriter>()
        private val overwriters = HashMap<String, PrintWriter>()
        private val writeoutstreams = HashMap<String, FileOutputStream>()
        private val appendoutstreams = HashMap<String, FileOutputStream>()
        fun read(filename: String?): KFile {
            return KFile(filename)
        }

        fun read(file: KFile): KFile {
            return KFile(file)
        }

        fun read(file: java.io.File): KFile {
            return KFile(file)
        }

        @Throws(MalformedURLException::class)
        fun read(uri: URI): KFile {
            return KFile(uri)
        }

        fun read(url: URL): KFile {
            return KFile(url)
        }

        fun write(filename: String?): KFile {
            return KFile(filename, 'w')
        }

        fun write(file: KFile): KFile {
            return KFile(file, 'w')
        }

        fun write(file: java.io.File): KFile {
            return KFile(file, 'w')
        }

        @Throws(MalformedURLException::class)
        fun write(uri: URI): KFile {
            return KFile(uri, 'w')
        }

        fun write(url: URL): KFile {
            return KFile(url, 'w')
        }

        fun append(filename: String?): KFile {
            return KFile(filename, 'a')
        }

        fun append(file: KFile): KFile {
            return KFile(file, 'a')
        }

        fun append(file: java.io.File): KFile {
            return KFile(file, 'a')
        }

        @Throws(MalformedURLException::class)
        fun append(uri: URI): KFile {
            return KFile(uri, 'a')
        }

        fun append(url: URL): KFile {
            return KFile(url, 'a')
        }

        fun relative(absParent: KFile, vararg relativePaths: String?): String {
            var file = java.io.File(absParent.path)
            for (relativePath in relativePaths) {
                file = java.io.File(file, relativePath)
            }
            return file.absolutePath
        }


        fun convert(vararg files: java.io.File?): Array<KFile?> {
            return convert(listOf(*files))
        }

        fun convert(files: List<java.io.File?>): Array<KFile?> {
            val rearr = arrayOfNulls<KFile>(files.size)
            var i = 0
            for (file in files) {
                rearr[i] = file?.let { KFile(it) }
                i++
            }
            return rearr
        }

        fun readFrom(filePath: String?): String {
            val file = read(filePath)
            val read = file.read()
            file.close()
            return read
        }

        fun readFrom(ioFile: java.io.File): String {
            val file = read(ioFile)
            val read = file.read()
            file.close()
            return read
        }

        @Throws(IOException::class)
        fun writeTo(file: java.io.File?, text: String?) {
            val pw = PrintWriter(file)
            pw.println(text)
            pw.close()
        }
    }

    // Constructors
    init {
        try {
            if (!exists()) createNewFile()
        } catch (ex: IOException) {
        }
        this.type = type
        if (!isDirectory) {
            if ((type == 'r' || type == 'w' || type == 'a') && Files.isReadable(toPath())) {
                if (scanners.containsKey(
                                absolutePath
                        )
                ) `in` = scanners[absolutePath] else {
                    try {
                        `in` = Scanner(this)
                        scanners[absolutePath] = `in`!!
                    } catch (e: IOException) {
                    }
                }
            }
            if (type == 'w' && Files.isWritable(toPath())) {
                if (writeoutstreams.containsKey(
                                absolutePath
                        ) && overwriters.containsKey(
                                absolutePath
                        )
                ) {
                    outstream = writeoutstreams[absolutePath]
                    out = overwriters[absolutePath]
                } else {
                    try {
                        outstream = FileOutputStream(filename)
                        out = PrintWriter(outstream)
                        writeoutstreams[absolutePath] = outstream!!
                        overwriters[absolutePath] = out!!
                    } catch (e: IOException) {
                    }
                }
            } else if (type == 'a' && Files.isWritable(toPath())) {
                if (appendoutstreams.containsKey(
                                absolutePath
                        ) && appendwriters.containsKey(
                                absolutePath
                        )
                ) {
                    outstream = appendoutstreams[absolutePath]
                    out = appendwriters[absolutePath]
                } else {
                    try {
                        outstream = FileOutputStream(filename, true)
                        out = PrintWriter(outstream)
                        appendoutstreams[absolutePath] = outstream!!
                        appendwriters[absolutePath] = out!!
                    } catch (e: IOException) {
                    }
                }
            }
        }
    }
}