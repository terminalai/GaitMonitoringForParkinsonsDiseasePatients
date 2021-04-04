package com.thepyprogrammer.gaitanalyzer.model.string

fun String.isWhitespace(): Boolean = Regex("\\w*").matches(this)
fun String.isNumeric(): Boolean = Regex("[\\d]*(.[\\d]*)?").matches(this)