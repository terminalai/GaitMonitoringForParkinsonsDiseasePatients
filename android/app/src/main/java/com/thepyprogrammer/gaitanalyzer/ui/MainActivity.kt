package com.thepyprogrammer.gaitanalyzer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.parent_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }


    private val currentFragment: Fragment
        get() = (
                supportFragmentManager
                        .findFragmentById(R.id.parent_nav_host_fragment)?.childFragmentManager?.fragments?.get(
                                0
                        )
                )!!


    fun logout(): Boolean {
        // viewModel.logout(this)
        navController.navigate(R.id.action_nav_main_to_nav_auth)
        return true
    }

}