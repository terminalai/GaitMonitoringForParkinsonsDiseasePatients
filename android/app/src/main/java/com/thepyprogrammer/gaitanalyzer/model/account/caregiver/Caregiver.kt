package com.thepyprogrammer.gaitanalyzer.model.account.caregiver

import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.account.patient.Patient

data class Caregiver(
    override val name: String,
    override val password: String,
    val patient: Patient
) : User(name, password, "caregiver")