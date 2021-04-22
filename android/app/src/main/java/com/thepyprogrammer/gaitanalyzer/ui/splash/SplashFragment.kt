package com.thepyprogrammer.gaitanalyzer.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentSplashBinding
import com.thepyprogrammer.gaitanalyzer.model.account.Caregiver
import com.thepyprogrammer.gaitanalyzer.model.account.Patient
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.ktlib.crypto.AES
import java.io.File
import java.util.*


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val aes = AES()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        val slideAnimation = AnimationUtils.loadAnimation(activity, R.anim.side_slide)
        binding.splashScreenImage.startAnimation(slideAnimation)

        try {
            val accountDetails = File(requireActivity().filesDir, "accountDetails.txt")
            if (accountDetails.exists()) {
                val sc = Scanner(accountDetails)
                if (sc.hasNext()) {
                    val name = sc.nextLine()
                    if (name != null) {
                        val password = sc.nextLine()
                        val type = sc.nextLine()
                        if (type == "caregiver") FirebaseUtil.user = Caregiver(name, password, encrypt("$name$type$password"))
                        else if (type == "patient") {
                            val phone = sc.nextLine()
                            FirebaseUtil.user = Patient(name, password, encrypt("$name$type$password"), phone)
                        }
                        Handler().postDelayed({
                            Navigation.findNavController(binding.splashScreenImage)
                                .navigate(R.id.nav_main)
                        }, SPLASH_TIME_OUT.toLong())
                        return view
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("AUTH", e.stackTrace.toString())
        }

        val preferences = activity?.getSharedPreferences("preferences", Context.MODE_PRIVATE)


        Handler().postDelayed({
            Navigation.findNavController(binding.splashScreenImage)
                .navigate(
                    if (preferences?.getBoolean("onboardingDone", true) == true)
                        R.id.nav_onboarding
                    else
                        R.id.nav_identification
                )
        }, SPLASH_TIME_OUT.toLong())

        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }

    private fun encrypt(string: String): String {
        var encryptedCode = aes.encrypt(
                string,
                "GaitMonitoringAndAnalysisForParkinsonsDiseasePatients"
        )
        if (encryptedCode == null) encryptedCode = string
        encryptedCode.replace(Regex("[/\\\\]+"), "")
        return encryptedCode
    }
}