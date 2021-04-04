package com.thepyprogrammer.gaitanalyzer.ui.video

import android.app.PictureInPictureParams
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Rational
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.configurations.*
import com.thepyprogrammer.gaitanalyzer.model.io.getUriFromRaw
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



        desc.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }
        }



        val builder =
            StringBuilder("<!DOCTYPE html>\n<html>\n<body>\n<style>\n").append(scBuilder.toString())
                .append("\n</style>\n").append(text).append("</body>\n</html>")
        val content = builder.toString()
        desc.loadDataWithBaseURL(null, content, "text/html", "utf-8", null)

    }

    override fun onStart() {
        super.onStart()



        conFigVideo()
    }

    private fun conFigVideo() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        itemVideo.setVideoURI(getUriFromRaw(this, R.raw.gaitmonitoring)) // placeholder
        itemVideo.start()

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
        val text =
            """Parkinson’s disease (PD) is a neurodegenerative disorder that affects the dopamine producing neurons in the substantia nigra, an area of the brain, leading to shaking, stiffness and difficulty walking. Parkinson’s patients frequently exhibit the debilitating condition freezing of gait (FOG), which is when patients cannot move their feet forward despite the intention to walk. While the feet remain in place, the torso still has forward momentum, making falls very common. At the start, FOG can be triggered by stress, tight spaces or a sudden change in direction. As the disease progresses, this happens more frequently, a fact extremely detrimental to the patient’s health and mental well-being.
                    
                    
                    This study aims to compare all the ways of classifying FOG in PD patients and determine the best parameter to utilise while creating an algorithm for data analysis. It also aims to compare multiple machine learning models based on acceleration data from accelerometers placed on the thigh. Public datasets of PD patients will be analysed to extract the motion pattern of PD patients. A Freeze Index value is postulated and used to predict FOG based on these parameters. The comfort of the patient and the ease and accuracy in which the parameter can predict FOG will be taken into account in this study. Ultimately, a prototype that fulfils all these requirements will be made.
                """.trimIndent()
    }
}