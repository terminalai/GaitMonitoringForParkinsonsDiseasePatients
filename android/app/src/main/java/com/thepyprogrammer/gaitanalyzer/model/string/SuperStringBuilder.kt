package com.thepyprogrammer.gaitanalyzer.model.string

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
    ) {
        builder.append(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end)
    }

    fun writeln(vararg objects: Any?, sep: String = "", end: String = "", prefix: String = "", postfix: String = "") {
        builder.append(objects.joinToString(separator = sep, prefix = prefix, postfix = postfix) + end + "\n")
    }

    fun writelns(vararg objects: Any?, sep: String = "", end: String = "", prefix: String = "", postfix: String = "") {
        builder.append(objects.joinToString(separator = sep + "\n", prefix = prefix, postfix = postfix) + end)
    }

    fun writeElement(vararg objects: Any?, tag: String = "p", sep: String = "", end: String = "\n") {
        builder.append(objects.joinToString(separator = sep, prefix = "<$tag>", postfix = "</$tag>") + end)
    }

    override fun toString() = builder.toString()

}