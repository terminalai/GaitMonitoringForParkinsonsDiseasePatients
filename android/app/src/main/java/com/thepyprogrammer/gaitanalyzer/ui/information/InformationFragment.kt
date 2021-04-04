package com.thepyprogrammer.gaitanalyzer.ui.information

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentInformationBinding
import com.thepyprogrammer.gaitanalyzer.ui.main.MainViewModel


class InformationFragment : Fragment() {

    private var viewModel: MainViewModel? = null

    private var _binding: FragmentInformationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        binding.button.setOnClickListener {
            Navigation.findNavController(binding.button).navigate(R.id.nav_profile)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        binding.invalidateAll()
        Log.d("TAG", viewModel?.pName?.value.toString())
    }
}