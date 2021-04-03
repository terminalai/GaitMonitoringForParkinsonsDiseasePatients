package com.thepyprogrammer.gaitanalyzer.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentMainBinding
import com.thepyprogrammer.gaitanalyzer.model.view.listener.OnShakeListener
import com.thepyprogrammer.gaitanalyzer.model.view.listener.OnSwipeTouchListener
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.gaitanalyzer.ui.auth.AuthAdapter
import com.thepyprogrammer.gaitanalyzer.ui.auth.AuthFragment
import com.thepyprogrammer.gaitanalyzer.ui.information.InformationFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.home.HomeFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.profile.ProfileFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.settings.SettingsFragment
import com.thepyprogrammer.gaitanalyzer.ui.video.VideoActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File
import java.util.*

class MainFragment: Fragment() {

    private var imageView: CircleImageView? = null
    private var imageNavMenuView: CircleImageView? = null

    private var imageInfoFile: File? = null

    private lateinit var viewModel: MainViewModel

    private lateinit var nameView: TextView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    /* put this into your activity class */
    private var mSensorManager: SensorManager? = null
    private lateinit var shakeListener: OnShakeListener

    private lateinit var binding: FragmentMainBinding
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onStart() {
        super.onStart()
        loadImage()
    }

    override fun onResume() {
        super.onResume()
        loadImage()
        mSensorManager?.registerListener(
            shakeListener,
            mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        mSensorManager?.unregisterListener(shakeListener)
        super.onPause()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        imageInfoFile = File((activity as AppCompatActivity).filesDir, "profileImageURI.txt")

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_list, R.id.nav_settings
            ), drawer_layout
        )

        navController = (activity as AppCompatActivity).findNavController(R.id.nav_host_fragment).apply {
            setupActionBarWithNavController(activity as AppCompatActivity, this, appBarConfiguration)
            nav_view.setupWithNavController(this)
        }

        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            requireActivity(),
            binding.drawerLayout,
            toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        ) {}

        binding.drawerLayout.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.drawerLayout.apply {
            setViewScale(GravityCompat.START, 0.9f) //set height scale for main view (0f to 1f)
            setViewElevation(
                GravityCompat.START,
                20F
            ) //set main view elevation when drawer open (dimension)
            setViewScrimColor(GravityCompat.START, Color.TRANSPARENT) //set drawer overlay color
            drawerElevation = 20F //set drawer elevation (dimension)
            setContrastThreshold(3F) //set maximum of contrast ratio between white text and background color.
            setRadius(GravityCompat.START, 25F) //set end container's corner radius (dimension)
        }

        fragmentContainer.setOnTouchListener(object : OnSwipeTouchListener(activity) {
            override fun onSwipeRight() {
                Log.v("detectSwipe", "Right Swipe Detected")
            }

            override fun onSwipeLeft() {
                Log.v("detectSwipe", "Left Swipe Detected")
                navigateToNext()
            }

            override fun onSwipeUp() {
                Log.v("detectSwipe", "Swipe Up Detected")
            }

            override fun onSwipeDown() {
                Log.v("detectSwipe", "Swipe Down Detected")
            }
        })

        shakeListener = object : OnShakeListener(requireActivity()) {
            override fun onShakeLeft() {
                navigateToPrevious()
            }

            override fun onShakeRight() {
                navigateToNext()
            }

        }

        loadImage()

        bottom_navigation.apply {
            setupWithNavController(navController)
            background = null
        }

        nav_view.getHeaderView(0).apply {
            nameView = findViewById(R.id.nameView)
        }

        /**View Model**/
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.pName.value = "yournameishere"


        val nameObserver = Observer<String> { newName ->
            nameView.text = newName
        }

        viewModel.pName.observe(activity as AppCompatActivity, nameObserver)



        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        menu.findItem(R.id.action_settings).apply {
            title = SpannableStringBuilder("* Settings").also {
                it.setSpan( // replace "*" with icon
                    ImageSpan(requireActivity(), R.drawable.ic_settings),
                    0,
                    1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        menu.findItem(R.id.information).apply {
            title = SpannableStringBuilder("* Information").also {
                it.setSpan( // replace "*" with icon
                    ImageSpan(requireActivity(), R.drawable.ic_info),
                    0,
                    1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_settings -> navigateToSettings()

            R.id.action_logout -> (activity as MainActivity).logout()


            R.id.information -> navigateToInformation()
            R.id.action_video -> {
                startActivity(Intent(requireActivity(), VideoActivity::class.java))
                true
            }
            else -> false
        }



    private val currentFragment: Fragment
        get() = (
                requireActivity().supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments?.get(
                        0
                    )
                )!!

    private fun navigateToHome() =
        if ((currentFragment !is ProfileFragment)) {
            val navController = findNavController(binding.navHostFragment)
            navController.navigate(R.id.nav_home)
            true
        } else false

    private fun navigateToProfile() =
        if ((currentFragment !is ProfileFragment)) {
            val navController = findNavController(binding.navHostFragment)
            navController.navigate(R.id.nav_profile)
            true
        } else false

    private fun navigateToSettings() =
        if ((currentFragment !is SettingsFragment)) {
            val navController = findNavController(binding.navHostFragment)
            navController.navigate(R.id.nav_settings)
            true
        } else false

    private fun navigateToInformation() =
        if ((currentFragment !is InformationFragment)) {
            val navController = findNavController(binding.navHostFragment)
            navController.navigate(R.id.nav_information)
            true
        } else false

    private fun navigateToNext() =
        when (currentFragment) {
            is SettingsFragment -> navigateToHome()
            is HomeFragment -> navigateToProfile()
            else -> navigateToSettings()
        }

    private fun navigateToPrevious() =
        when (currentFragment) {
            is SettingsFragment -> navigateToProfile()
            is HomeFragment -> navigateToSettings()
            else -> navigateToHome()
        }

    private fun readData(): String {
        if (!imageInfoFile!!.exists()) return ""

        val scanner = Scanner(imageInfoFile)
        val string = StringBuilder(scanner.nextLine())

        while (scanner.hasNextLine())
            string.append("\n" + scanner.nextLine())

        scanner.close()
        return string.toString()
    }

    private fun loadImage() {
        val string: String = readData()
        if (string.isNotEmpty()) {
            imageView?.setImageURI(Uri.parse(readData()))
            imageNavMenuView?.setImageURI(Uri.parse(readData()))
        } else {
            imageView?.setImageResource(R.drawable.face)
            imageNavMenuView?.setImageResource(R.drawable.face)
        }
    }
}