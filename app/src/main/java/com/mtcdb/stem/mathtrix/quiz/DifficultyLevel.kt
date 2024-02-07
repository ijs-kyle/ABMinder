package com.mtcdb.stem.mathtrix.quiz

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

@Suppress("DEPRECATION")
class DifficultyLevel : AppCompatActivity() {

    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button
    private lateinit var toolbar: MaterialToolbar
    private lateinit var intentM: Intent
    private lateinit var progressTrackerLayout: LinearLayout
    private lateinit var totalQuestionsAnsweredTextView: TextView
    private lateinit var totalCorrectTextView: TextView
    private lateinit var totalWrongTextView: TextView
    private lateinit var easyQuestionsAnsweredTextView: TextView
    private lateinit var mediumQuestionsAnsweredTextView: TextView
    private lateinit var hardQuestionsAnsweredTextView: TextView
    private lateinit var averageScoreTextView: TextView
    private var averageScore: Double = 0.0
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

        // Initialize progress tracker views
        progressTrackerLayout = findViewById(R.id.progressTrackerLayout)
        totalQuestionsAnsweredTextView = findViewById(R.id.totalQuestionsAnsweredTextView)
        totalCorrectTextView = findViewById(R.id.totalCorrectTextView)
        totalWrongTextView = findViewById(R.id.totalWrongTextView)
        easyQuestionsAnsweredTextView = findViewById(R.id.easyQuestionsAnsweredTextView)
        mediumQuestionsAnsweredTextView = findViewById(R.id.mediumQuestionsAnsweredTextView)
        hardQuestionsAnsweredTextView = findViewById(R.id.hardQuestionsAnsweredTextView)
        averageScoreTextView = findViewById(R.id.averageScoreTextView)

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

    public fun updateProgressTracker(
        isCorrect: Boolean,
        difficultyLevel: String,
        quizProgress: QuizProgress
    ) {
        quizProgress.totalQuestionsAnswered++
        if (isCorrect) {
            quizProgress.totalCorrect++
        } else {
            quizProgress.totalWrong++
        }

        when (difficultyLevel) {
            "Easy" -> quizProgress.easyQuestionsAnswered++
            "Medium" -> quizProgress.mediumQuestionsAnswered++
            "Hard" -> quizProgress.hardQuestionsAnswered++
        }

        // Update TextViews in DifficultyLevel activity
        totalQuestionsAnsweredTextView.text =
            "Total Questions Answered: ${quizProgress.totalQuestionsAnswered}"
        totalCorrectTextView.text = "Total Correct: ${quizProgress.totalCorrect}"
        totalWrongTextView.text = "Total Wrong: ${quizProgress.totalWrong}"
        easyQuestionsAnsweredTextView.text =
            "Easy Questions Answered: ${quizProgress.easyQuestionsAnswered}"
        mediumQuestionsAnsweredTextView.text =
            "Medium Questions Answered: ${quizProgress.mediumQuestionsAnswered}"
        hardQuestionsAnsweredTextView.text =
            "Hard Questions Answered: ${quizProgress.hardQuestionsAnswered}"

        // Calculate and update average score
        calculateAverageScore(quizProgress.totalCorrect, quizProgress.totalQuestionsAnswered)

    }

    private fun isContextValid(): Boolean {
        return this@DifficultyLevel.applicationContext != null
    }

    @SuppressLint("SetTextI18n")
    private fun calculateAverageScore(totalCorrect: Int, totalQuestionsAnswered: Int) {
        if (totalQuestionsAnswered > 0) {
            // Calculate the average score
            averageScore = (totalCorrect.toDouble() / totalQuestionsAnswered) * 100

            // Update UI with average score
            averageScoreTextView.text =
                String.format("Average Score: %.2f%%", averageScore)
        } else {
            // Handle the case where there are no questions answered
            averageScoreTextView.text = "No questions answered yet"
        }
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
