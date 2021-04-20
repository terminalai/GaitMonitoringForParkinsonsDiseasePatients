package com.thepyprogrammer.androidlib.bluetooth

interface BLEControllerListener {
    fun BLEControllerConnected()
    fun BLEControllerDisconnected()
    fun BLEDeviceFound(name: String?, address: String?)
}