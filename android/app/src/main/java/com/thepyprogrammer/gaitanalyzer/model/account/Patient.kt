package com.thepyprogrammer.gaitanalyzer.model.account

data class Patient(
    override val name: String,
    override val password: String,
    override val id: String,
    var phone: String = "",
    var caregiver: Caregiver? = null
) : User(name, password, "patient", id) {
    override fun toString(): String {
        return "$name\n$password\n$type\n$phone"
    }

    override fun getFileName(): String {
        return super.getFileName()+id
    }
}