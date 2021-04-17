package com.thepyprogrammer.gaitanalyzer.ui.main.freeze.freezeData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentSelectedBinding

class SelectedFragment : Fragment() {
    private lateinit var binding: FragmentSelectedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSelectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val completeViewModel =
            ViewModelProvider(requireActivity()).get(CompleteViewModel::class.java)
        binding.textView.text = "You selected Card ${completeViewModel.adapterPosition.value}"
    }
}