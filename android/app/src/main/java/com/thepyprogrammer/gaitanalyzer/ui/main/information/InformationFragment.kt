package com.thepyprogrammer.gaitanalyzer.ui.main.information

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentInformationBinding
import com.thepyprogrammer.gaitanalyzer.ui.main.MainViewModel


class InformationFragment : Fragment() {

    private var viewModel: MainViewModel? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentInformationBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformationBinding.inflate(inflater, container, false)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }
}