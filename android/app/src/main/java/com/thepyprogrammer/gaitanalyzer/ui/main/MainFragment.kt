package com.thepyprogrammer.gaitanalyzer.ui.main

import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ImageSpan
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentMainBinding
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import com.thepyprogrammer.gaitanalyzer.ui.image.ImageClickListener
import com.thepyprogrammer.gaitanalyzer.ui.main.home.HomeFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.information.InformationFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.profile.ProfileFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.settings.SettingsFragment
import com.thepyprogrammer.gaitanalyzer.ui.main.aboutapp.AboutAppFragment
import com.thepyprogrammer.ktlib.io.KFile
import com.thepyprogrammer.ktlib.randInt
import com.thepyprogrammer.ktlib.toEpoch
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.time.LocalDateTime
import java.util.*

class MainFragment : Fragment() {

    private var imageNavMenuView: CircleImageView? = null

    private var imageInfoFile: File? = null

    private lateinit var viewModel: MainViewModel

    private lateinit var nameView: TextView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    lateinit var binding: FragmentMainBinding
    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onStart() {
        super.onStart()
        loadImage()
    }

    override fun onResume() {
        super.onResume()
        loadImage()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

//        // TESTING CODE - PLEASE REMOVE ON ACTUAL USAGE
//        val time = mutableListOf<Long>()
//        for(day in 15..20) {
//            for(hour in 9..22) {
//                for (i in 1..randInt(1, 5)) {
//                    time.add(LocalDateTime.of(2021, 4, day, hour, 0, 0).toEpoch())
//                }
//            }
//        }
//
//        val freezesFile = File(activity?.filesDir, "freezes.txt")
//        if (!freezesFile.exists()) freezesFile.createNewFile()
//        val freezesWriter = KFile.write(freezesFile)
//        time.forEach { freezesWriter.out?.println(it) }
//        freezesWriter.close()

        FirebaseUtil.retrieveFreeze(activity as AppCompatActivity) {}

        imageInfoFile =
            File((activity as AppCompatActivity).filesDir, "profileImageURI.txt")

        val accountDetails =
            File((activity as AppCompatActivity).filesDir, "accountDetails.txt")
        if (!accountDetails.exists()) accountDetails.createNewFile()

        val pw = PrintWriter(accountDetails)
        pw.println(FirebaseUtil.user.toString())
        pw.close()


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_freeze, R.id.nav_settings
            ), binding.drawerLayout
        )


        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            requireActivity(),
            binding.drawerLayout,
            binding.toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        ) {}

        binding.drawerLayout.apply {
            setDrawerListener(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()
            drawerElevation = 20F //set drawer elevation (dimension)
        }

        loadImage()

        binding.navView.getHeaderView(0).apply {
            nameView = findViewById(R.id.nameView)
            imageNavMenuView = findViewById<CircleImageView>(R.id.imageView).also {
                it.setOnClickListener(ImageClickListener(requireActivity()))
            }
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val nameObserver = Observer<String> { newName ->
            nameView.text = newName
        }

        viewModel.pName.observe(activity as AppCompatActivity, nameObserver)

        viewModel.pName.value = FirebaseUtil.user?.name

        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navController = nestedNavHostFragment?.navController!!.apply {
            setupActionBarWithNavController(
                activity as AppCompatActivity,
                this,
                appBarConfiguration
            )
            binding.navView.setupWithNavController(this)
        }

        binding.bottomNavigation.apply {
            setupWithNavController(navController)
            background = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_settings -> navigateToSettings()

            R.id.action_logout -> (activity as MainActivity).logout()

            R.id.information -> navigateToVideo()

            R.id.action_video -> navigateToInformation()

            R.id.action_me -> navigateToAboutMe()

            else -> false
        }


    private val currentFragment: Fragment?
        get() = (
                requireActivity().supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment)?.childFragmentManager?.fragments?.get(
                        0
                    )
                )

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

    private fun navigateToVideo() =
        if ((currentFragment !is AboutAppFragment)) {
            val navController = findNavController(binding.navHostFragment)
            navController.navigate(R.id.nav_video)
            true
        } else false

    private fun navigateToAboutMe() =
        if ((currentFragment !is AboutAppFragment)) {
            val navController = findNavController(binding.navHostFragment)
            navController.navigate(R.id.nav_aboutme)
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
        if (imageInfoFile?.exists() == false) {
            return ""
        }
        return try {
            val scanner = Scanner(imageInfoFile)
            val string = StringBuilder(scanner.nextLine())

            while (scanner.hasNextLine())
                string.append("\n" + scanner.nextLine())


            scanner.close()
            string.toString()
        } catch (e: NoSuchElementException) {
            ""
        }
    }

    private fun loadImage() {
        val string: String = readData()
        try {
            if (string.isNotEmpty()) {
                imageNavMenuView?.setImageURI(Uri.parse(string))
            } else {
                imageNavMenuView?.setImageResource(R.drawable.face)
            }
        } catch(e: FileNotFoundException) {
            imageNavMenuView?.setImageResource(R.drawable.face)
        }
    }

    override fun onDetach() {
        val freezesFile = File(requireActivity().filesDir, "freezes.txt")
        FirebaseUtil.uploadFreeze(freezesFile)
        super.onDetach()
    }

    override fun onDestroy() {
        val freezesFile = File(requireActivity().filesDir, "freezes.txt")
        FirebaseUtil.uploadFreeze(freezesFile)
        super.onDestroy()
    }

}