package com.thepyprogrammer.gaitanalyzer.model.account.patient

import com.thepyprogrammer.gaitanalyzer.model.account.base.User

data class Patient(
        override val name: String,
        override val password: String
) : User(name, password, "patient") {
    override fun toString(): String {
        return "$name\n$password\n$type"
    }
}