package com.thepyprogrammer.gaitanalyzer.ui.video

import android.app.PictureInPictureParams
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Rational
import android.view.*
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
        desc.text =
                """The Olympic Games is a quadrennial international multi-sport event celebrated as a global sports festival by people all over the world. The Olympic Games are held in both the summer and winter, with the ultimate goal of cultivating people and world peace through sports. The Games of the XXIX Olympiad held in Beijing in 2008 saw athletes from 204 countries and regions participate. London hosted the 2012 Olympics, commemorating the 30th Olympic Games.
            
            This year, Japan will be hosting Olympics 2021 in Tokyo. The Tokyo 2020 Olympic Games will feature a record 33 competitions and 339 events held across 42 competition venues.    
        """.trimIndent()
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
            TextToSpeech(this, null).getTTS(this, desc.text.toString())
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
}