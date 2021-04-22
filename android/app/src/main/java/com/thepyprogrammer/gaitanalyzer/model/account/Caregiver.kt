package com.thepyprogrammer.gaitanalyzer.model.account

data class Caregiver(
    override val name: String,
    override val password: String,
    override val id: String,
    var patient: Patient? = null
) : User(name, password, "caregiver", id) {
    override fun toString(): String {
        return "$name\n$password\n$type"
    }

    override fun getFileName(): String {
        return super.getFileName()+patient?.id
    }
}