package com.mtcdb.stem.mathtrix.quiz

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

@Suppress("DEPRECATION")
class DifficultyLevel : AppCompatActivity() {

    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button
    private lateinit var toolbar: Toolbar
    private lateinit var intentM: Intent
    private lateinit var progressTrackerLayout: LinearLayout
    private val scoresList: MutableList<Double> = mutableListOf()
    private lateinit var quizFragment: QuizFragment

    @SuppressLint("MissingInflatedId", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty_level)

        val difficulty = layoutInflater.inflate(R.layout.difficulty, null)
        difficulty.visibility = View.VISIBLE
        toolbar = findViewById(R.id.quiz_toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.quiz)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        easyButton = difficulty.findViewById(R.id.easyButton)
        mediumButton = difficulty.findViewById(R.id.mediumButton)
        hardButton = difficulty.findViewById(R.id.hardButton)

        supportFragmentManager.beginTransaction().replace(R.id.quiz_container, Difficulty())
            .commit()

        easyButton.setOnClickListener {
            showProgressTracker()
            navigateToQuizFragment("Easy")
        }

        mediumButton.setOnClickListener {
            showProgressTracker()
            navigateToQuizFragment("Medium")
        }

        hardButton.setOnClickListener {
            showProgressTracker()
            navigateToQuizFragment("Hard")
        }

        intentM = Intent(this, MainActivity::class.java)

        scoresList.add(80.0)
        scoresList.add(90.0)
        scoresList.add(75.0)


    }

    private fun showProgressTracker() {
        // Show progress tracker views
        progressTrackerLayout.visibility = View.VISIBLE
    }

    private fun hideProgressTracker() {
        // Hide progress tracker views
        progressTrackerLayout.visibility = View.GONE
    }

    private fun navigateToQuizFragment(difficultyLevel: String) {
        quizFragment = QuizFragment()
        val bundle = Bundle()
        bundle.putString("difficultyLevel", difficultyLevel)
        quizFragment.arguments = bundle

        hideProgressTracker()
        val difficulty = layoutInflater.inflate(R.layout.difficulty, null)
        difficulty.visibility = View.GONE
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.quiz_container, quizFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val difficulty = layoutInflater.inflate(R.layout.difficulty, null)
        when (item.itemId) {
            android.R.id.home -> {
                if (difficulty.visibility == View.GONE) {
                    val dialog = MaterialAlertDialogBuilder(this)
                        .setTitle("Leave QUIZ?")
                        .setMessage(
                            "Are you sure you want to leave the QUIZ? \n" +
                                    "Any progress will be lost."
                        )
                        .setNegativeButton("Exit", null)
                        .setPositiveButton("Cancel", null)
                        .show()
                    val aButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                    if (difficulty.visibility == View.GONE) {
                        aButton.setOnClickListener {
                            difficulty.visibility = View.VISIBLE
                            dialog.dismiss()
                        }
                    } else {
                        startActivity(intentM)
                    }

                } else {
                    startActivity(intentM)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val difficulty = layoutInflater.inflate(R.layout.difficulty, null)
        quizFragment = QuizFragment()
        if (quizFragment.isVisible) {
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle("Leave QUIZ?")
                .setMessage(
                    "Are you sure you want to leave the QUIZ? \n" +
                            "Any progress will be lost."
                )
                .setNegativeButton("Exit", null)
                .setPositiveButton("Cancel", null)
                .show()
            val aButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            if (quizFragment.isVisible) {
                aButton.setOnClickListener {
                    difficulty.visibility = View.VISIBLE
                    dialog.dismiss()
                    supportFragmentManager.beginTransaction().remove(quizFragment).commit()
                }
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}
