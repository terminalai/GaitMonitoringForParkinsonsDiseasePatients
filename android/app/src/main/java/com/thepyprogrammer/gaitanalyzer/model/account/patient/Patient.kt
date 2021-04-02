package com.thepyprogrammer.gaitanalyzer.model.account.patient

import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.account.caregiver.Caregiver

data class Patient(
    override val name: String,
    override val password: String,
    val caregiver: Caregiver
) : User(name, password, "patient")