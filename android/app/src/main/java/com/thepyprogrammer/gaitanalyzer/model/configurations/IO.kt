package com.thepyprogrammer.gaitanalyzer.model.configurations

import android.net.Uri
import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun File.read() = String(Files.readAllBytes(Paths.get(absolutePath)))

fun File.readLines() = Files.readAllLines(Paths.get(absolutePath), StandardCharsets.UTF_8).toTypedArray()


fun Any.print(out: Writer) {
    when (out) {
        is PrintWriter -> out.println(toString())
        is BufferedWriter -> out.write(toString())
        is FileWriter -> out.write(toString())
    }
}

fun Any.print(outStream: OutputStream) {
    when (outStream) {
        is FileOutputStream -> outStream.write(toString().toByteArray())
        is DataOutputStream -> outStream.writeUTF(toString())
        is BufferedOutputStream -> DataOutputStream(outStream).writeUTF(toString())
        is PrintStream -> outStream.write(toString().toByteArray())
    }
}

fun Any.print(channel: FileChannel) {
    val strBytes = toString().toByteArray()
    val buffer: ByteBuffer = ByteBuffer.allocate(strBytes.size)
    buffer.put(strBytes)
    buffer.flip()
    channel.write(buffer)
}

fun Any.print(file: java.io.File) {
    val pw = PrintWriter(file)
    print(pw)
    pw.close()
}


fun Any.print(file: com.thepyprogrammer.gaitanalyzer.model.io.File) {
    if (file.out != null) file.out?.println(file) else print(
        com.thepyprogrammer.gaitanalyzer.model.io.File(
            file.absolutePath
        )
    )
}

fun Any.print(parent: com.thepyprogrammer.gaitanalyzer.model.io.File, child: String) {
    print(File(parent, child))
}
fun Any.print(parent: String, child: String) {
    print(File(parent, child))
}

fun Any.print(uri: Uri) {
    print(com.thepyprogrammer.gaitanalyzer.model.io.File(uri, 'w'))
}

fun input(inp: String = ""): String {
    print(inp)
    val sc = Scanner(System.`in`)
    val line = sc.nextLine()
    sc.close()
    return line
}
