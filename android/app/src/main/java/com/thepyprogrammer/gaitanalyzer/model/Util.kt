package com.thepyprogrammer.gaitanalyzer.model

import java.text.SimpleDateFormat

object Util {
    val nricRegex = Regex("[STFG]\\d{7}[A-Z]")
    val format = SimpleDateFormat("dd/MM/yyyy")

    fun checkNRIC(NRIC: String) = NRIC.matches(nricRegex)
}