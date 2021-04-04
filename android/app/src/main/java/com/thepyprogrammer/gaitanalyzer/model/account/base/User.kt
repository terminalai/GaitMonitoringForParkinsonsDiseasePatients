package com.thepyprogrammer.gaitanalyzer.model.account.base

import java.io.Serializable

open class User(
        open val name: String,
        open val password: String,
        val type: String
) : Serializable {
    override fun toString(): String {
        return "$type($name, $password)"
    }

    companion object {
        fun empty() = User("", "", "patient")
    }
}