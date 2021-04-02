package com.thepyprogrammer.gaitanalyzer.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.thepyprogrammer.gaitanalyzer.R
import com.thepyprogrammer.gaitanalyzer.model.account.base.User
import com.thepyprogrammer.gaitanalyzer.model.firebase.FirebaseUtil
import com.thepyprogrammer.gaitanalyzer.ui.auth.AuthActivity
import com.thepyprogrammer.gaitanalyzer.ui.main.MainActivity
import java.io.File
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val backgroundImage: ImageView = findViewById(R.id.splash_screen_image)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        backgroundImage.startAnimation(slideAnimation)

        val accountDetails = File(filesDir, "accountDetails.txt")
        if (accountDetails.exists()) {
            val sc = Scanner(accountDetails)
            if (sc.hasNext()) {
                val nric = sc.nextLine()
                if (nric != "null") {
                    val name = sc.nextLine()
                    val password = sc.nextLine()
                    val type = sc.nextLine()
                    FirebaseUtil.user = User(
                        name, password, type
                    )
                    Handler().postDelayed({
                        val main = Intent(applicationContext, MainActivity::class.java)
                        startActivity(main)
                        finish()
                    }, SPLASH_TIME_OUT.toLong())
                    return
                }
            }
        }

        Handler().postDelayed({
            val auth = Intent(applicationContext, AuthActivity::class.java)
            startActivity(auth)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    override fun onResume() {
        super.onResume()
        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPause() {
        super.onPause()
        window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }
}