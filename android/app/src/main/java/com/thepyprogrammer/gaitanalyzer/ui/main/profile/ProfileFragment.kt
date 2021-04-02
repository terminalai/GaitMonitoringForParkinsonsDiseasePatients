package com.thepyprogrammer.gaitanalyzer.ui.main.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.Util
import com.thepyprogrammer.gaitanalyzer.ui.image.ImageClickListener
import com.thepyprogrammer.gaitanalyzer.ui.main.MainViewModel
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ProfileFragment : Fragment() {
    var circleImageView: CircleImageView? = null
    var imageInfoFile: File? = null
    lateinit var nameTextView: TextView
    private lateinit var NRICTextView: TextView
    lateinit var emailTextView: TextView
    lateinit var dateTextView: TextView
    lateinit var button: Button
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        return root
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
        viewModel = activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        //ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        nameTextView = view?.findViewById(R.id.name)!!


//        //testing code for viewmodel
//        button = view?.findViewById(R.id.button2)!!
//
//        button.setOnClickListener {
//            viewModel.pName.value = "This is a thing!"
//        }

        imageView.setOnClickListener(ImageClickListener(this))

        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            nameTextView.text = newName
        }
        viewModel.pName.observe(viewLifecycleOwner, nameObserver)
    }

    private fun readData(): String {
        if (!imageInfoFile!!.exists()) {
            return ""
        }
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
            imageView!!.setImageURI(Uri.parse(readData()))
        } else {
            imageView!!.setImageResource(R.drawable.face_trans)
        }
    }


}