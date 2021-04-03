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
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import java.util.*


class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel
    lateinit var name: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var login: Button
    private lateinit var loading: ProgressBar
    private lateinit var nameLayout: TextInputLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)


        name = root.findViewById(R.id.nameInput)
        password = root.findViewById(R.id.passwordInput)
        login = root.findViewById(R.id.login)
        //esc = root.findViewById(R.id.escape)
        loading = root.findViewById(R.id.loading)
        nameLayout = root.findViewById(R.id.nameInputLayout)


        /**View Model**/
        viewModel = activity?.let { ViewModelProvider(it).get(AuthViewModel::class.java) }!!

        val nameObserver = Observer<String> { newNric ->
            name.setText(newNric)
        }

        val passwordObserver = Observer<String> { newPassword ->
            password.setText(newPassword)
        }

        viewModel.pName.observe(requireActivity(), nameObserver)
        viewModel.password.observe(requireActivity(), passwordObserver)

        login.setOnClickListener {
            viewModel.pName.value = name.text.toString().trim().toUpperCase(Locale.ROOT)
            viewModel.password.value = password.text.toString().trim()
            loading.visibility = View.VISIBLE
            viewModel.login()

        }

        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        /**View Model**/
        viewModel = activity?.let { ViewModelProvider(it).get(AuthViewModel::class.java) }!!

        val nameObserver = Observer<String> {
            name.setText(it)
        }
        val passwordObserver = Observer<String> {
            password.setText(it)
        }

        viewModel.pName.observe(requireActivity(), nameObserver)
        viewModel.password.observe(requireActivity(), passwordObserver)

        val resultObserver =
            Observer<User> {
                when (viewModel.userResult.value?.password) {
                    "old" -> {
                    }
                    "", "3" -> {
                        name.setText("")
                        password.setText("")
                        loading.visibility = View.GONE
                        val sb =
                            view?.let { it1 ->
                                Snackbar.make(
                                    it1,
                                    "Invalid ID and Password",
                                    Snackbar.LENGTH_LONG
                                )
                            }
                        sb?.show()
                    }
                    else -> {
                        FirebaseUtil.user = viewModel.userResult.value

                        Log.d("TAG", "Data is Correct second!")
                        loading.visibility = View.GONE
                        startActivityForResult(Intent(activity, MainActivity::class.java).also {
                            it.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }, 0)

                        activity?.setResult(Activity.RESULT_OK)

                        //Complete and destroy login activity once successful
                        activity?.finish()
                    }
                }
            }
        viewModel.userResult.observe(viewLifecycleOwner, resultObserver)
    }

}

