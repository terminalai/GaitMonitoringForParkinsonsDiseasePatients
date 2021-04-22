package com.thepyprogrammer.gaitanalyzer.model.account

import java.io.Serializable

open class User(
        open val name: String,
        open val password: String,
        val type: String,
        open val id: String
) : Serializable {
    override fun toString(): String {
        return "$name\n$password\n$type"
    }

    constructor(name: String, password: String, type: String): this(name, password, type, "$name$password$type")

    companion object {
        fun empty() = User("", "", "patient", "")
    }

    open fun getFileName(): String {
        return ""
    }
}