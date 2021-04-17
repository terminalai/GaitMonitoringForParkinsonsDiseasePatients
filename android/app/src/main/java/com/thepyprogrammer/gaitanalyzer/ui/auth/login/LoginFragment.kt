package com.thepyprogrammer.gaitanalyzer.ui.auth.login

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
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentLoginBinding
import com.thepyprogrammer.gaitanalyzer.model.account.data.User
import com.thepyprogrammer.gaitanalyzer.model.account.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.gaitanalyzer.ui.auth.AuthViewModel


class LoginFragment : Fragment() {

    private lateinit var viewModel: AuthViewModel

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root


        /**View Model**/
        viewModel = activity?.let { ViewModelProvider(it).get(AuthViewModel::class.java) }!!

        val nameObserver = Observer<String> { newNric ->
            binding.nameInput.setText(newNric)
        }

        val passwordObserver = Observer<String> { newPassword ->
            binding.passwordInput.setText(newPassword)
        }

        viewModel.pName.observe(viewLifecycleOwner, nameObserver)
        viewModel.password.observe(viewLifecycleOwner, passwordObserver)

        binding.login.setOnClickListener {
            viewModel.pName.value = binding.nameInput.text.toString().trim()
            viewModel.password.value = binding.passwordInput.text.toString().trim()
            binding.loading.visibility = View.VISIBLE
            viewModel.login()
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
                    FirebaseUtil.user = viewModel.userResult.value
                    FirebaseUtil.user?.password?.let { it1 -> Log.d("TAG", it1) }
                    binding.loading.visibility = View.GONE
                    (activity as MainActivity).navController.navigate(R.id.nav_main)
                }
            }

        viewModel.userResult.observe(viewLifecycleOwner, resultObserver)

        return view
    }

}

