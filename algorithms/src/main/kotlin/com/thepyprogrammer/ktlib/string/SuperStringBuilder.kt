package com.thepyprogrammer.ktlib.string

class SuperStringBuilder(val builder: StringBuilder = StringBuilder()) {
    constructor(
            vararg objects: Any?,
            sep: String = "",
            end: String = "",
            prefix: String = "",
            postfix: String = ""
    ) : this(StringBuilder(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end))

    fun write(
            vararg objects: Any?,
            sep: String = "",
            end: String = "",
            prefix: String = "",
            postfix: String = ""
    ) = run {
        builder.append(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end)
        this
    }

    fun writeln(vararg objects: Any?, sep: String = "", end: String = "", prefix: String = "", postfix: String = "") = run {
        builder.append(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end + "\n")
        this
    }

    fun writelns(vararg objects: Any?, sep: String = "", end: String = "", prefix: String = "", postfix: String = "") = run {
        builder.append(objects.joinToString(separator = sep + "\n", prefix = prefix, postfix = postfix) + end)
        this
    }

    fun writeElement(vararg objects: Any?, tag: String = "p", sep: String = "", end: String = "\n") = run {
        builder.append(objects.joinToString(separator = sep, prefix = "<$tag>", postfix = "</$tag>") + end)
        this
    }

    override fun toString() = builder.toString()

}