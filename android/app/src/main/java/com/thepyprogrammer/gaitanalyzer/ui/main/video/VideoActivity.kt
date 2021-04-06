package com.thepyprogrammer.gaitanalyzer.ui.main.video

import android.app.PictureInPictureParams
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.Rational
import android.view.*
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.configurations.*
import com.thepyprogrammer.gaitanalyzer.model.configurations.io.getUriFromRaw
import com.thepyprogrammer.gaitanalyzer.view.web.GitHubWebViewClient
import com.thepyprogrammer.gaitanalyzer.view.web.WebAppInterface
import com.thepyprogrammer.gaitanalyzer.view.web.WebBrowserClient
import com.thepyprogrammer.gaitanalyzer.ui.MainActivity
import kotlinx.android.synthetic.main.activity_video.*
import java.util.*


class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        setSupportActionBar(toolbar)


        val sc = Scanner(resources.openRawResource(R.raw.github_markdown))
        val scBuilder = StringBuilder()
        while (sc.hasNext()) {
            scBuilder.append(sc.nextLine() + "\n")
        }

        with(desc) {
            webViewClient = GitHubWebViewClient(this@VideoActivity)
            settings.javaScriptEnabled = true
            addJavascriptInterface(WebAppInterface(this@VideoActivity), "Android")
            webChromeClient = WebBrowserClient()
            loadUrl("file:///android_asset/www/index.html")
        }
//        val builder =
//            SuperStringBuilder("<!DOCTYPE html>\n<html>\n<body>\n")
//                .writeElement(scBuilder.toString(), tag="style")
//                .writeln(text, "</body>\n</html>", sep="\n")
//        val content = builder.toString()
//        desc.loadDataWithBaseURL(null, content, "text/html", "utf-8", null)


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && desc.canGoBack()) {
            desc.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    override fun onStart() {
        super.onStart()

        conFigVideo()
    }

    private fun conFigVideo() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        itemVideo.setVideoURI(getUriFromRaw(this, R.raw.gaitmonitoring)) // placeholder
        itemVideo.start()

        val mediaController = MediaController(this)
        mediaController.setAnchorView(itemVideo);
        itemVideo.setMediaController(mediaController);

        itemVideo.setOnPreparedListener {
            Log.i(
                "VIDEO",
                "Duration = " + it.duration
            )


        }


        ttsButton.setOnClickListener {
            TextToSpeech(this, null).getTTS(this, text.toString())
        }

        pip.setOnClickListener { enterPIP() }
    }

    override fun onPictureInPictureModeChanged(isPictureinPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isPictureinPictureMode)

        if (isPictureinPictureMode) {
            toolbar.visibility = View.GONE
            buttonLayout.visibility = View.GONE
            scrollingView.visibility = View.GONE
        } else {
            toolbar.visibility = View.VISIBLE
            buttonLayout.visibility = View.VISIBLE
            scrollingView.visibility = View.VISIBLE
        }

    }

    private fun enterPIP() {
        val rational = Rational(itemVideo.width, itemVideo.height)
        val params = PictureInPictureParams.Builder().setAspectRatio(rational).build()
        itemVideo.setMediaController(null)
        enterPictureInPictureMode(params)
    }

    //Creates the back button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return super.onCreateOptionsMenu(menu)
    }

    //This helps press the button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this@VideoActivity, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val text =
            "Parkinson’s disease, or PD is a neurodegenerative disorder that affects the dopamine-producing neurons in the substantia nigra, an area of the brain, leading to shaking, stiffness and difficulty walking. Parkinson’s patients frequently exhibit the debilitating condition freezing of gait, or FOG, which is when patients cannot move their feet forward despite the intention to walk. While the feet remain in place, the torso still has forward momentum, making falls very common. At the start, FOG can be triggered by stress, tight spaces or a sudden change in direction. As the disease progresses, this happens more frequently, a fact extremely detrimental to the patient’s health and mental well-being. This study aims to compare all the ways of classifying FOG in PD patients and determine the best parameter to utilise while creating an algorithm for data analysis. It also aims to compare multiple machine learning models based on acceleration data from accelerometers placed on the thigh. Public datasets of PD patients will be analysed to extract the motion pattern of PD patients. A Freeze Index value is postulated and used to predict FOG based on these parameters. An algorithm was then developed to identify the most suitable parameter for the classification of FOG in PD patients. Multiple machine learning models were then compared based on acceleration data from accelerometers placed on the thigh. After analyzing, the most suitable parameters for classification are freezeY and freezeZ based on the acceleration data in the public datasets and the best model is the Linear Kernel model in terms of sensitivity. Furthermore, a prototype has been created using an Arduino Nano 33 BLE board. It can be implemented to test the performance of the identified most suitable parameters. This system has now been connected to this Android Application such that notifications can be sent to the caregiver’s phone to alert them to a fall."
    }
}