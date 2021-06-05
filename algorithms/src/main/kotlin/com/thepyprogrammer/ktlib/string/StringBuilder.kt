package com.thepyprogrammer.ktlib.string

fun StringBuilder.write(
        vararg objects: Any?,
        sep: String = "",
        end: String = "",
        prefix: String = "",
        postfix: String = ""
) = run {
    append(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end)
    this
}

fun StringBuilder.writeln(vararg objects: Any?, sep: String = "", end: String = "", prefix: String = "", postfix: String = "") = run {
    append(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end + "\n")
    this
}

fun StringBuilder.writelns(vararg objects: Any?, sep: String = "", end: String = "", prefix: String = "", postfix: String = "") = run {
    append(objects.joinToString(separator = sep + "\n", prefix = prefix, postfix = postfix) + end)
    this
}

fun StringBuilder.writeElement(vararg objects: Any?, tag: String = "p", sep: String = "", end: String = "\n") = run {
    append(objects.joinToString(separator = sep, prefix = "<$tag>", postfix = "</$tag>") + end)
    this
}

fun stringBuild(
        vararg objects: Any?,
        sep: String = "",
        end: String = "",
        prefix: String = "",
        postfix: String = ""
) = StringBuilder(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end)