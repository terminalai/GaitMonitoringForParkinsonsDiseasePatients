package com.thepyprogrammer.gaitanalyzer.ui.auth.setup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentRegisterBinding
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentSetupBinding
import com.thepyprogrammer.gaitanalyzer.model.account.data.Patient
import com.thepyprogrammer.gaitanalyzer.model.account.data.User
import com.thepyprogrammer.gaitanalyzer.model.account.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.gaitanalyzer.ui.auth.AuthViewModel

class SetupFragment : Fragment() {

    private lateinit var viewModel: SetupViewModel

    private lateinit var binding: FragmentSetupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetupBinding.inflate(inflater, container, false)
        val view = binding.root


        /**View Model**/
        viewModel = ViewModelProvider(requireActivity()).get(SetupViewModel::class.java)

        val nameObserver = Observer<String> { newName ->
            binding.nameInput.setText(newName)
        }

        val passwordObserver = Observer<String> { newPassword ->
            binding.passwordInput.setText(newPassword)
        }

        val phoneNumberObsever = Observer<String> { newPhoneNumber ->
            binding.phoneInput.setText(newPhoneNumber)
        }

        viewModel.pName.observe(requireActivity(), nameObserver)
        viewModel.password.observe(requireActivity(), passwordObserver)
        viewModel.phoneNumber.observe(requireActivity(), phoneNumberObsever)

        binding.continueButton.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            viewModel.pName.value = binding.nameInput.text.toString().trim()
            viewModel.password.value = binding.passwordInput.text.toString().trim()
            viewModel.phoneNumber.value = binding.phoneInput.text.toString().trim()
            viewModel.setup()
        }

        val resultObserver =
            Observer<User> {
                if (viewModel.error.value != "" && viewModel.error.value != "DON'T LOGIN") {
                    val sb = Snackbar.make(
                        view,
                        viewModel.error.value!!,
                        Snackbar.LENGTH_LONG
                    )
                    sb.show()
                } else if (viewModel.error.value != "DON'T LOGIN") {
                    binding.loading.visibility = View.GONE

                    (activity as MainActivity)
                        .navController
                        .navigate(
                            if(FirebaseUtil.user is Patient)
                                R.id.nav_setup
                            else
                                R.id.nav_main
                        )
                }
            }
        viewModel.userResult.observe(viewLifecycleOwner, resultObserver)

        return view
    }
}