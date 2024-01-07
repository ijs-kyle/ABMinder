package com.mtcdb.stem.mathtrix.quiz

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mtcdb.stem.mathtrix.R

class QuizFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var difficultyLevel: String
    private lateinit var dbHelper: QuizDbHelper
    private lateinit var database: SQLiteDatabase
    private var questions: MutableList<QuizEntity> = mutableListOf()
    private val correctAnswers = listOf(0, 0) // Example values, modify as needed

    private var currentQuestionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        // Initialize views
        questionTextView = view.findViewById(R.id.questionTextView)
        optionsRadioGroup = view.findViewById(R.id.optionsRadioGroup)
        submitButton = view.findViewById(R.id.submitButton)

        // Display the first question
        fetchQuestions()
        displayQuestion()

        dbHelper = QuizDbHelper(requireContext())
        database = dbHelper.writableDatabase

        difficultyLevel = arguments?.getString("difficultyLevel") ?: "Easy"

        // Set a listener for the submit button
        submitButton.setOnClickListener {
            checkAnswer()
        }

        questions = mutableListOf()

        return view
    }

    // Inside displayQuestion function
    private fun displayQuestion() {
        // Check if questions is not empty before accessing its elements
        if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
            questionTextView.text = questions[currentQuestionIndex].question

            optionsRadioGroup.removeAllViews()

            // Split options directly
            val options = questions[currentQuestionIndex].options.split(",")

            for ((index, option) in options.withIndex()) {
                val radioButton = RadioButton(context)
                radioButton.text = option
                radioButton.id = index
                optionsRadioGroup.addView(radioButton)
            }
        } else {
            // Handle the case where questions is empty or all questions have been displayed
            // You can add your logic here
        }
    }

    // Inside fetchQuestions function
    private fun fetchQuestions() {
        difficultyLevel = arguments?.getString("difficultyLevel") ?: "Easy"
        dbHelper = QuizDbHelper(requireContext())
        database = dbHelper.writableDatabase
        // Fetch questions based on the selected difficulty level
        val cursor = database.rawQuery("SELECT * FROM ${QuizDbHelper.TABLE_QUIZ} WHERE ${QuizDbHelper.COLUMN_DIFFICULTY_LEVEL} = ?", arrayOf(difficultyLevel))

        if (cursor.moveToFirst()) {
            // If there are questions in the cursor, convert them to a list of QuizEntity
            questions = mutableListOf()
            do {
                val idColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_ID)
                val questionColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_QUESTION)
                val optionsColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_OPTIONS)
                val correctAnswerColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_CORRECT_ANSWER_INDEX)
                val difficultyLevelColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_DIFFICULTY_LEVEL)

                if (idColumnIndex >= 0 && questionColumnIndex >= 0 && optionsColumnIndex >= 0 &&
                    correctAnswerColumnIndex >= 0 && difficultyLevelColumnIndex >= 0) {
                    val id = cursor.getLong(idColumnIndex)
                    val question = cursor.getString(questionColumnIndex)
                    val options = cursor.getString(optionsColumnIndex).split(",") // Split the options by comma
                    val correctAnswerIndex = cursor.getInt(correctAnswerColumnIndex)
                    val difficultyLevel = cursor.getString(difficultyLevelColumnIndex)

                    val quizEntity = QuizEntity(id, question,
                        options.toString(), correctAnswerIndex, difficultyLevel)
                    questions.add(quizEntity)
                } else {
                    // Handle the case where one or more columns are not found
                    // You can log an error or add your custom logic here
                }
            } while (cursor.moveToNext())

            // Display the first question
            displayQuestion()
        } else {
            // Handle the case where there are no questions
            // You can add your logic here
        }

        cursor.close()
    }

    // Inside checkAnswer function
    private fun checkAnswer() {
        val selectedOptionId = optionsRadioGroup.checkedRadioButtonId

        if (selectedOptionId != -1) {
            val selectedOptionIndex = optionsRadioGroup.indexOfChild(optionsRadioGroup.findViewById(selectedOptionId))

            if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
                val correctAnswerIndex = correctAnswers[currentQuestionIndex]
                val correctOption = questions[currentQuestionIndex].options[correctAnswerIndex]

                if (selectedOptionIndex == correctAnswerIndex) {
                    // Correct answer
                    showResultMessage("Correct!")
                } else {
                    // Incorrect answer
                    showResultMessage("Incorrect. The correct answer is $correctOption")
                }

                // Move to the next question
                currentQuestionIndex++

                if (currentQuestionIndex < questions.size) {
                    displayQuestion()
                } else {
                    // End of the quiz
                    showResultMessage("Quiz completed!")
                }
            } else {
                // Handle the case where questions is empty or all questions have been displayed
                // You can add your logic here
            }
        } else {
            // No option selected
            Toast.makeText(context, "Please select an option.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showResultMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
