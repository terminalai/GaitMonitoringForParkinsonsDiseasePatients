package com.thepyprogrammer.gaitanalyzer.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentHomeBinding
import com.thepyprogrammer.gaitanalyzer.model.account.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.model.data.HomeCardInfo
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.gaitanalyzer.ui.image.ImageClickListener
import com.thepyprogrammer.gaitanalyzer.ui.main.MainViewModel
import com.thepyprogrammer.gaitanalyzer.ui.main.home.adapter.HomeAdapter
import com.thepyprogrammer.ktlib.io.KFile
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File

class HomeFragment : Fragment() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        mainViewModel =
            ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding.toggleWalk.setOnClickListener {
            (activity as MainActivity)
        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this
    }
}