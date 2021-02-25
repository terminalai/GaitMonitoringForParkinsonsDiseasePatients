package com.thepyprogrammer.greenpass.model.bluetooth

interface BLEControllerListener {
    fun BLEControllerConnected()
    fun BLEControllerDisconnected()
    fun BLEDeviceFound(name: String?, address: String?)
}