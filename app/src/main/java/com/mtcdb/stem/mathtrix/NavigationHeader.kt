package com.mtcdb.stem.mathtrix

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class NavigationHeader : AppCompatActivity() {

    private lateinit var darkMode: ImageView
    private var isDarkMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_header)

        darkMode = findViewById(R.id.iVDarkMode)

        isDarkMode = false
        updateTheme(this.isDarkMode)
    }

    fun toggleTheme(view: View) {
        isDarkMode = !isDarkMode // Toggle the theme
        updateTheme(isDarkMode)
    }

    // Function to update the theme and ImageView resource
    private fun updateTheme(isDarkMode: Boolean) {
        if (isDarkMode) {
            // Set the dark theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            darkMode.setImageResource(R.drawable.baseline_light_mode_24)
        } else {
            // Set the light theme
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            darkMode.setImageResource(R.drawable.baseline_dark_mode_24)
        }
        // Recreate the activity to apply the theme change
        recreate()
    }
}
