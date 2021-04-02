package com.thepyprogrammer.gaitanalyzer.model.account

import com.google.firebase.Timestamp
import com.thepyprogrammer.greenpass.model.Util
import java.io.Serializable

data class VaccinatedUser(
        val nric: String,
        val fullName: String,
        val dateOfVaccine: Timestamp,
        val password: String
) : Serializable {
        override fun toString(): String {
                return "$nric\n$fullName\n"+ Util.format.format(dateOfVaccine.toDate())+"\n$password"
        }
}
