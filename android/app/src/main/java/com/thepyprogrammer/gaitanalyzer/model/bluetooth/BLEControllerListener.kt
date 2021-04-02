package com.thepyprogrammer.gaitanalyzer.model.bluetooth

interface BLEControllerListener {
    fun BLEControllerConnected()
    fun BLEControllerDisconnected()
    fun BLEDeviceFound(name: String?, address: String?)
}