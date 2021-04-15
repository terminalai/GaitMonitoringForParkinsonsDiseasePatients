package com.thepyprogrammer.gaitanalyzer.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentHomeBinding
import com.thepyprogrammer.gaitanalyzer.model.data.HomeCardInfo
import com.thepyprogrammer.gaitanalyzer.ui.main.MainViewModel
import com.thepyprogrammer.gaitanalyzer.ui.main.home.adapter.HomeAdapter

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

        val homeAdapter = HomeAdapter(this,
                mutableListOf(
                        HomeCardInfo("HELLO", "krvjrovirvrinfeidvgrtiefgtrhfrhehiuehrifdehourfgwheufhreofrhfirhfirerfhtihoreghrihtrvhorugherfhrhfreogerifohtrghiore")
                )
        )
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        binding.mainViewModel = mainViewModel
        binding.lifecycleOwner = this

        binding.homeFeed.adapter = homeAdapter
        binding.homeFeed.layoutManager = LinearLayoutManager(activity)

        return root
    }
}