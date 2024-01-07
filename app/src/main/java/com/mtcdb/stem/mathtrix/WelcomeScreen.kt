package com.mtcdb.stem.mathtrix

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val layout = layoutInflater.inflate(R.layout.activity_welcome, null)
        layout.visibility = View.VISIBLE

        // Check if the welcome screen has been shown before
        if (!isWelcomeScreenShown()) {
            // If not shown, display the welcome screen
            layout.visibility = View.VISIBLE
            setWelcomeScreenShown()
        } else {
            // If already shown, navigate to the main activity or any other desired screen
            navigateToMainActivity()
        }

        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            navigateToMainActivity()
        }

        setFullScreen()

    }

    private fun isWelcomeScreenShown(): Boolean {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isWelcomeScreenShown", false)
    }

    private fun setWelcomeScreenShown() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isWelcomeScreenShown", true)
        editor.apply()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setFullScreen() {
        // For devices with Android 12 (API level 31) and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            window.insetsController?.apply {
                hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // For devices with Android versions below 12
            window.decorView.apply {
                systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }
}
