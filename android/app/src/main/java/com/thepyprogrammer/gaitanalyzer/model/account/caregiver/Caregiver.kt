package com.thepyprogrammer.gaitanalyzer.model.account.caregiver

import com.thepyprogrammer.gaitanalyzer.model.account.base.User

data class Caregiver(
        override val name: String,
        override val password: String
) : User(name, password, "caregiver")