package com.thepyprogrammer.gaitanalyzer.ui.auth

import android.app.Activity
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
import com.thepyprogrammer.gaitanalyzer.model.view.modifications.afterTextChanged
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.Util
import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.main.MainActivity
import java.util.*


class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel
    lateinit var nric: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var login: Button
    private lateinit var loading: ProgressBar
    private lateinit var nricLayout: TextInputLayout



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)


        nric = root.findViewById(R.id.nricInput)
        password = root.findViewById(R.id.passwordInput)
        login = root.findViewById(R.id.login)
        //esc = root.findViewById(R.id.escape)
        loading = root.findViewById(R.id.loading)
        nricLayout = root.findViewById(R.id.nricInputLayout)


        /**View Model**/
        viewModel = activity?.let { ViewModelProvider(it).get(AuthViewModel::class.java) }!!

        val nameObserver = Observer<String>{ newNric ->
            nric.setText(newNric)
        }

        val passwordObserver = Observer<String> { newPassword ->
            password.setText(newPassword)
        }

        viewModel.pName.observe(requireActivity(), nameObserver)
        viewModel.password.observe(requireActivity(), passwordObserver)

        nric.afterTextChanged {
//            viewModel.NRIC.value = it
            if(it.trim().isNotEmpty())
                nricLayout.error = null
            else if (it.trim().length != 9)
                nricLayout.error = "NRIC Length should be ${nricLayout.counterMaxLength}"
            else if (!Util.checkNRIC(it.trim().toUpperCase(Locale.ROOT)))
                nricLayout.error = "NRIC format is inaccurate"
            else
                nricLayout.error = null
        }
//
//        password.afterTextChanged {
//            viewModel.password.value = it
//        }

        login.setOnClickListener {
            viewModel.pName.value = nric.text.toString().trim().toUpperCase(Locale.ROOT)
            viewModel.password.value = password.text.toString().trim()
            loading.visibility = View.VISIBLE
            viewModel.login()

            // FirebaseUtil.retrieveImage(requireActivity())


        }

        //esc.setOnClickListener {
        //    FirebaseUtil.user = VaccinatedUser(
        //        "S1234567D",
        //        "Prannay Gupta",
        //        Timestamp.now(),
        //        "helloWorld"
        //    )
        //    loading.visibility = View.GONE
//
        //    startActivityForResult(Intent(activity, MainActivity::class.java).also {
        //        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //    }, 0)
//
        //    activity?.setResult(Activity.RESULT_OK)
//
        //    //Complete and destroy login activity once successful
        //    activity?.finish()
        //}

        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**View Model**/
        viewModel = activity?.let { ViewModelProvider(it).get(AuthViewModel::class.java) }!!

        val nricObserver = Observer<String> { newNric ->
            nric.setText(newNric)
        }
        val passwordObserver = Observer<String> { newPassword ->
            password.setText(newPassword)
        }

        viewModel.pName.observe(requireActivity(), nricObserver)
        viewModel.password.observe(requireActivity(), passwordObserver)

        val resultObserver =
            Observer<User> {
                when {
                    viewModel.userResult.value?.password == "old" -> {}
                    viewModel.userResult.value?.password == "" || viewModel.userResult.value?.password == "3" -> {
                        nric.setText("")
                        password.setText("")
                        loading.visibility = View.GONE
                        val sb =
                            view?.let { it1 -> Snackbar.make(it1, "Invalid ID and Password", Snackbar.LENGTH_LONG) }
                        sb?.show()
                    }
                    else -> {
                        FirebaseUtil.user = viewModel.userResult.value

                        Log.d("TAG", "Data is Correct second!")
                        loading.visibility = View.GONE
                        startActivityForResult(Intent(activity, MainActivity::class.java).also {
                            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }, 0)

                        activity?.setResult(Activity.RESULT_OK)

                        //Complete and destroy login activity once successful
                        activity?.finish()
                    }
                }
            }
        viewModel.userResult.observe(viewLifecycleOwner,resultObserver)
    }

}

