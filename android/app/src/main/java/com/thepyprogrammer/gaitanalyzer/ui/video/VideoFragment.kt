package com.thepyprogrammer.gaitanalyzer.ui.video

import android.app.PictureInPictureParams
import android.graphics.PixelFormat
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.Rational
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.MediaController
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.databinding.FragmentVideoBinding
import com.thepyprogrammer.gaitanalyzer.model.configurations.getTTS
import com.thepyprogrammer.gaitanalyzer.model.configurations.io.getUriFromRaw
import com.thepyprogrammer.gaitanalyzer.view.web.*
import java.util.*


class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        val view = binding.root

        val sc = Scanner(resources.openRawResource(R.raw.github_markdown))
        val scBuilder = StringBuilder()
        while (sc.hasNext()) {
            scBuilder.append(sc.nextLine() + "\n")
        }

        with(binding.hub) {
            webViewClient = GitHubWebViewClient(requireActivity())
            settings.javaScriptEnabled = true
            addJavascriptInterface(WebAppInterface(requireActivity()), "Android")
            webChromeClient = WebBrowserClient()
            loadUrl("file:///android_asset/www/index.html")
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        conFigVideo()
    }

    private fun conFigVideo() {
        requireActivity().window.setFormat(PixelFormat.TRANSLUCENT)
        binding.videoView.setVideoURI(getUriFromRaw(requireContext(), R.raw.gaitmonitoring)) // placeholder
        binding.videoView.start()

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)

        binding.videoView.setOnPreparedListener {
            Log.i(
                "VIDEO",
                "Duration = " + it.duration
            )
        }

        binding.videoView.setPlayPauseListener {
            Log.d("VIDEO", if(it) "Play!" else "Pause!")
            binding.playPauseBtn.setImageResource(if(it) R.drawable.ic_play else R.drawable.ic_play)
        }

        binding.playPauseBtn.setOnClickListener {
            Log.d("VIDEO", if(binding.videoView.toggle()) "Play!" else "Pause!")
        }


        binding.ttsButton.setOnClickListener {
            TextToSpeech(requireContext(), null).getTTS(requireContext(), VideoActivity.text.toString())
        }

        binding.pip.setOnClickListener { enterPIP() }
    }

    override fun onPictureInPictureModeChanged(isPictureinPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isPictureinPictureMode)

        if (isPictureinPictureMode) {
            requireActivity().actionBar?.hide()
            binding.buttonLayout.visibility = View.GONE
            binding.scrollingView.visibility = View.GONE
        } else {
            requireActivity().actionBar?.show()
            binding.buttonLayout.visibility = View.VISIBLE
            binding.scrollingView.visibility = View.VISIBLE
        }

    }

    private fun enterPIP() {
        val rational = Rational(binding.videoView.width, binding.videoView.height)
        val params = PictureInPictureParams.Builder().setAspectRatio(rational).build()
        binding.videoView.setMediaController(null)
        requireActivity().enterPictureInPictureMode(params)
    }
}