package com.thepyprogrammer.gaitanalyzer.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.Util
import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.model.view.modifications.afterTextChanged
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*


class RegisterFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        val fullname = root.findViewById<TextInputEditText>(R.id.fullNameInput)
        val nric = root.findViewById<TextInputEditText>(R.id.nricInput)
        val password = root.findViewById<TextInputEditText>(R.id.passwordInput)
        val register = root.findViewById<Button>(R.id.register)
        val loading = root.findViewById<ProgressBar>(R.id.loading)
        val nricLayout = root.findViewById<TextInputLayout>(R.id.nricInputLayout)


        /**View Model**/
        viewModel = activity?.let { ViewModelProvider(it).get(AuthViewModel::class.java) }!!

        val nameObserver = Observer<String> { newName ->
            fullname.setText(newName)
        }

        val passwordObserver = Observer<String> { newPassword ->
            password.setText(newPassword)
        }

        viewModel.pName.observe(requireActivity(), nameObserver)
        viewModel.password.observe(requireActivity(), passwordObserver)

        nric.afterTextChanged {
//            viewModel.NRIC.value = it
            if (it.trim().isNotEmpty())
                nricLayout.error = null
            else if (it.trim().length != 9)
                nricLayout.error = "NRIC Length should be ${nricLayout.counterMaxLength}"
            else if (!Util.checkNRIC(it.trim().toUpperCase(Locale.ROOT)))
                nricLayout.error = "NRIC format is inaccurate"
            else
                nricLayout.error = null
        }

//
//        fullname.afterTextChanged {
//            viewModel.pName.value = it
//        }
//
//        nric.afterTextChanged {
//            viewModel.NRIC.value = it
//        }
//
//        password.afterTextChanged {
//            viewModel.password.value = it
//        }

        register.setOnClickListener {
            loading.visibility = View.VISIBLE
            viewModel.pName.value = fullNameInput.text.toString().trim()
            viewModel.password.value = passwordInput.text.toString()
            viewModel.register()
        }
        val resultObserver =
            Observer<User> {
                if (viewModel.userResult.value?.password != "old") {
                    when {
                        viewModel.userResult.value?.password == "" -> {
                            loading.visibility = View.GONE
                            val sb =
                                view?.let { it1 ->
                                    Snackbar.make(
                                        it1,
                                        "NRIC Already Registered!",
                                        Snackbar.LENGTH_LONG
                                    )
                                }
                            sb?.show()
                        }
                        viewModel.userResult.value?.password.equals("3") -> {
                            loading.visibility = View.GONE
                            val sb =
                                view?.let { it1 ->
                                    Snackbar.make(
                                        it1,
                                        "Password Length Too Short!",
                                        Snackbar.LENGTH_LONG
                                    )
                                }
                            sb?.show()
                        }
                        else -> {
                            FirebaseUtil.user = viewModel.userResult.value

                            viewModel.userResult.value?.password?.let { it1 -> Log.d("TAG", it1) }
                            loading.visibility = View.GONE
                            startActivity(Intent(activity, MainActivity::class.java))
                        }
                    }
                }
            }
        viewModel.userResult.observe(viewLifecycleOwner, resultObserver)

        return root
    }
}