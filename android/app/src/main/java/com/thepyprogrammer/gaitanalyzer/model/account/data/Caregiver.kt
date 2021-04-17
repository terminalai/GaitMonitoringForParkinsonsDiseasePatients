package com.thepyprogrammer.gaitanalyzer.model.account.data

data class Caregiver(
    override val name: String,
    override val password: String
) : User(name, password, "caregiver") {
    override fun toString(): String {
        return "$name\n$password\n$type"
    }
}