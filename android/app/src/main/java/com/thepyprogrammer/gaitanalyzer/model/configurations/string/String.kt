package com.thepyprogrammer.gaitanalyzer.model.configurations.string

import java.util.*

fun String.isWhitespace(): Boolean = Regex("\\w*").matches(this)
fun String.isNumeric(): Boolean = Regex("[\\d]*(.[\\d]*)?").matches(this)

fun String.isAlpha(): Boolean {
    val s = this.toLowerCase(Locale.ROOT)
    for (i in s) {
        if (!Character.isLetter(i)) return false
    }
    return true
}

fun String.isPalindrome() = (length <= 1) || equals(StringBuilder(this).reverse().toString());