package com.thepyprogrammer.gaitanalyzer.model.account.data

import java.io.Serializable

open class User(
        open val name: String,
        open val password: String,
        val type: String
) : Serializable {
    override fun toString(): String {
        return "$name\n$password\n$type"
    }

    companion object {
        fun empty() = User("", "", "patient")
    }
}