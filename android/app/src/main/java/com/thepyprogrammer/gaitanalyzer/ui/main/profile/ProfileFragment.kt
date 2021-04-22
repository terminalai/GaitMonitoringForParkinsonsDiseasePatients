package com.thepyprogrammer.gaitanalyzer.ui.main.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentProfileBinding
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.image.ImageClickListener
import com.thepyprogrammer.gaitanalyzer.ui.main.MainViewModel
import com.thepyprogrammer.ktlib.io.KFile
import java.io.File
import java.util.*

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    private lateinit var imageInfoFile: File
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        loadImage()
    }

    override fun onResume() {
        super.onResume()
        loadImage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
        imageInfoFile = File(activity?.filesDir, "profileImageURI.txt")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding.imageView.setOnClickListener(ImageClickListener(this))

        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            binding.name.text = newName
        }
        viewModel.pName.observe(viewLifecycleOwner, nameObserver)
        viewModel.pName.value = FirebaseUtil.user?.name
    }


    private fun readData(): String {
        if (!imageInfoFile.exists()) {
            return ""
        }
        try {
            val scanner = Scanner(imageInfoFile)
            val string = StringBuilder(scanner.nextLine())

            while (scanner.hasNextLine())
                string.append("\n" + scanner.nextLine())


            scanner.close()
            return string.toString()
        } catch (e: NoSuchElementException) {
            return ""
        }
    }

    private fun loadImage() {
        val string: String = readData()
            if (string.isNotEmpty()) {
                binding.imageView.setImageURI(Uri.parse(string))
            } else {
                binding.imageView.setImageResource(R.drawable.face_trans)
            }
    }


}