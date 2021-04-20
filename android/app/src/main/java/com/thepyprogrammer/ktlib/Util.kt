package com.thepyprogrammer.ktlib

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatterBuilder

object Util {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    val dTF =
        DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd/MM/yyyy")
            .toFormatter()
}