package com.mtcdb.stem.mathtrix

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
}
