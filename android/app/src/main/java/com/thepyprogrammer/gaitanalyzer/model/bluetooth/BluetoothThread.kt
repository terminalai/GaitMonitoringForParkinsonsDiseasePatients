package com.thepyprogrammer.gaitanalyzer.model.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.os.Handler
import android.os.Message
import android.util.Log
import java.io.InputStream
import java.io.OutputStream
import java.nio.charset.StandardCharsets
import java.util.*


/**
 * A thread that connects to a remote device over Bluetooth, and reads/writes data
 * using message Handlers. A delimiter character is used to parse messages from a stream,
 * and must be implemented on the other side of the connection as well. If the connection
 * fails, the thread exits.
 *
 * Usage:
 *
 * BluetoothThread t = BluetoothThread("00:06:66:66:33:89", new Handler() {
 * @Override
 * public void handleMessage(Message message) {
 * String msg = (String) message.obj;
 * do_something(msg);
 * }
 * });
 *
 * Handler writeHandler = t.getWriteHandler();
 * t.start();
 */
class BluetoothThread(address: String, handler: Handler) :
    Thread() {
    // MAC address of remote Bluetooth device
    private val address: String = address.toUpperCase(Locale.ROOT)

    // Bluetooth socket of active connection
    private var socket: BluetoothSocket? = null

    // Streams that we read from and write to
    private var outStream: OutputStream? = null
    private var inStream: InputStream? = null

    // Handlers used to pass data between threads
    private val readHandler: Handler = handler

    /**
     * Return the write handler for this connection. Messages received by this
     * handler will be written to the Bluetooth socket.
     */
    private val writeHandler: Handler

    // Buffer used to parse messages
    private var rx_buffer = ""

    /**
     * Connect to a remote Bluetooth socket, or throw an exception if it fails.
     */
    @Throws(Exception::class)
    private fun connect() {
        Log.i(TAG, "Attempting connection to $address...")

        // Get this device's Bluetooth adapter
        val adapter = BluetoothAdapter.getDefaultAdapter()
        if (adapter == null || !adapter.isEnabled) {
            throw Exception("Bluetooth adapter not found or not enabled!")
        }

        // Find the remote device
        val remoteDevice = adapter.getRemoteDevice(address)

        // Create a socket with the remote device using this protocol
        socket = remoteDevice.createRfcommSocketToServiceRecord(uuid)

        // Make sure Bluetooth adapter is not in discovery mode
        adapter.cancelDiscovery()

        // Connect to the socket
        socket?.connect()


        // Get input and output streams from the socket
        outStream = socket?.outputStream
        inStream = socket?.inputStream
        Log.i(TAG, "Connected successfully to $address.")
    }

    /**
     * Disconnect the streams and socket.
     */
    private fun disconnect() {
        if (inStream != null) {
            try {
                inStream!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (outStream != null) {
            try {
                outStream!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (socket != null) {
            try {
                socket!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Return data read from the socket, or a blank string.
     */
    private fun read(): String {
        var s = ""
        try {
            // Check if there are bytes available
            if (inStream!!.available() > 0) {

                // Read bytes into a buffer
                val inBuffer = ByteArray(1024)
                val bytesRead = inStream!!.read(inBuffer)

                // Convert read bytes into a string
                s = String(inBuffer, StandardCharsets.US_ASCII)
                s = s.substring(0, bytesRead)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Read failed!", e)
        }
        return s
    }

    /**
     * Write data to the socket.
     */
    private fun write(s: String) {
        var s = s
        try {
            // Add the delimiter
            s += DELIMITER

            // Convert to bytes and write
            outStream!!.write(s.toByteArray())
            Log.i(TAG, "[SENT] $s")
        } catch (e: Exception) {
            Log.e(TAG, "Write failed!", e)
        }
    }

    /**
     * Pass a message to the read handler.
     */
    private fun sendToReadHandler(s: String) {
        val msg = Message.obtain()
        msg.obj = s
        readHandler.sendMessage(msg)
        Log.i(TAG, "[RECV] $s")
    }

    /**
     * Send complete messages from the rx_buffer to the read handler.
     */
    private fun parseMessages() {

        // Find the first delimiter in the buffer
        val inx = rx_buffer.indexOf(DELIMITER)

        // If there is none, exit
        if (inx == -1) return

        // Get the complete message
        val s = rx_buffer.substring(0, inx)

        // Remove the message from the buffer
        rx_buffer = rx_buffer.substring(inx + 1)

        // Send to read handler
        sendToReadHandler(s)

        // Look for more complete messages
        parseMessages()
    }

    /**
     * Entry point when thread.start() is called.
     */
    override fun run() {

        // Attempt to connect and exit the thread if it failed
        try {
            connect()
            sendToReadHandler("CONNECTED")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to connect!", e)
            sendToReadHandler("CONNECTION FAILED")
            disconnect()
            return
        }

        // Loop continuously, reading data, until thread.interrupt() is called
        while (!this.isInterrupted) {

            // Make sure things haven't gone wrong
            if (inStream == null || outStream == null) {
                Log.e(TAG, "Lost bluetooth connection!")
                break
            }

            // Read data and add it to the buffer
            val s = read()
            if (s.isNotEmpty()) rx_buffer += s

            // Look for complete messages
            parseMessages()
        }

        // If thread is interrupted, close connections
        disconnect()
        sendToReadHandler("DISCONNECTED")
    }

    companion object {
        // Tag for logging
        private const val TAG = "BluetoothThread"

        // Delimiter used to separate messages
        private const val DELIMITER = '#'

        // UUID that specifies a protocol for generic bluetooth serial communication
        private val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    }

    /**
     * Constructor, takes in the MAC address of the remote Bluetooth device
     * and a Handler for received messages.
     *
     */
    init {
        writeHandler = object : Handler() {
            override fun handleMessage(message: Message) {
                write(message.obj as String)
            }
        }
    }
}