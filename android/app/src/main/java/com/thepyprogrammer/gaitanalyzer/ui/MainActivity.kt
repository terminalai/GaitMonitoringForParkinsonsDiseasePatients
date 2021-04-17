package com.thepyprogrammer.gaitanalyzer.ui

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.ActivityMainBinding
import com.thepyprogrammer.gaitanalyzer.model.account.firebase.FirebaseUtil
import java.io.File
import java.io.PrintWriter


class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
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
        val accountDetails = File(filesDir, "accountDetails.txt")
        if (accountDetails.exists())
            PrintWriter(accountDetails).close()

        FirebaseUtil.user = null

        navController.navigate(R.id.action_nav_main_to_nav_identification)
        return true
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        try {
            val hub = findViewById<WebView>(R.id.hub)
            if (keyCode == KeyEvent.KEYCODE_BACK && hub.canGoBack()) {
                hub.goBack()
                return true
            }
        } catch (ex: Exception) {
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

}