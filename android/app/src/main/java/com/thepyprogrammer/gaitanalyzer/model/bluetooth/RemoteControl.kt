package com.thepyprogrammer.gaitanalyzer.model.bluetooth

class RemoteControl(private val bleController: BLEController) {
    private fun createControlWord(type: Byte, vararg args: Byte): ByteArray {
        val command = ByteArray(args.size + 3)
        command[0] = START
        command[1] = type
        command[2] = args.size.toByte()
        for (i in args.indices) command[i + 3] = args[i]
        return command
    }

    fun switchLED(on: Boolean) {
        bleController.sendData(createControlWord(LED_COMMAND, if (on) VALUE_ON else VALUE_OFF))
    }

    fun heartbeat() {
        bleController.sendData(createControlWord(HEARTBEAT))
    }

    companion object {
        private const val START: Byte = 0x1
        private const val HEARTBEAT: Byte = 0x2
        private const val LED_COMMAND: Byte = 0x4
        private const val VALUE_OFF: Byte = 0x0
        private const val VALUE_ON = 0xFF.toByte()
    }
}