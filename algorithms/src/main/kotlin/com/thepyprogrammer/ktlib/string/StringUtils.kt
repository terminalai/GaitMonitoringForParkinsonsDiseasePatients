package com.thepyprogrammer.ktlib.string

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

fun String.isPalindrome() = (length <= 1) || equals(StringBuilder(this).reverse().toString())

fun String.toTitleCase(): String {
    if (isNullOrBlank()) {
        return this
    }

    return split(" ").map { word ->
        word.foldIndexed("") { index, working, char ->
            val nextChar = if (index == 0) char.toUpperCase() else char.toLowerCase()
            "$working$nextChar"
        }
    }.reduceIndexed { index, working, word ->
        if (index > 0) "$working $word" else word
    }
}

fun String.nameToLogin(): String {
    if (isNullOrBlank()) {
        return this
    }
    var working = ""
    toCharArray().forEach { char ->
        if (char.isLetterOrDigit()) {
            working += char.toLowerCase()
        } else if (char.isWhitespace() and !working.endsWith(".")) {
            working += "."
        }
    }
    return working
}

fun String.removeRandomZeroes() = run {
    var str = this.toString()
    if (str.contains(".")) {
        while (str.get(length - 1) == '0') {
            str = str.substring(0, str.length - 1)
        }
        if (str[str.length - 1] == '.') {
            str = str.substring(0, str.length - 1)
        }
    }
    str
}

fun Int.pad(zeros: Int) = "%0${zeros}d".format(this)

