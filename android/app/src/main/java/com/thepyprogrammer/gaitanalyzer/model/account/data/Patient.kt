package com.thepyprogrammer.gaitanalyzer.model.account.data

data class Patient(
    override val name: String,
    override val password: String,
    var phone: String = ""
) : User(name, password, "patient") {
    override fun toString(): String {
        return "$name\n$password\n$type\n$phone"
    }
}