package com.thepyprogrammer.gaitanalyzer.ui.auth.identification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentIdentificationBinding
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil

class IdentificationFragment : Fragment() {

    private lateinit var binding: FragmentIdentificationBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentIdentificationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.caregiverButton.setOnClickListener {
            FirebaseUtil.type = "caregiver"
            Navigation.findNavController(view).navigate(R.id.nav_auth)
        }

        binding.patientButton.setOnClickListener {
            FirebaseUtil.type = "patient"
            Navigation.findNavController(view).navigate(R.id.nav_auth)
        }


        return view
    }
}