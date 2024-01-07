package com.mtcdb.stem.mathtrix.quiz

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
    private lateinit var database: QuizDatabase
    private lateinit var questions: List<QuizEntity>
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
        displayQuestion()

        // Fetch questions based on difficulty when the fragment is created


        difficultyLevel = "Easy"

        // Set a listener for the submit button
        submitButton.setOnClickListener {
            checkAnswer()
        }

        return view
    }

    private fun displayQuestion() {
        // Check if questions is not empty before accessing its elements
        if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
            questionTextView.text = questions[currentQuestionIndex].question

            optionsRadioGroup.removeAllViews()
            for ((index, option) in questions[currentQuestionIndex].options.withIndex()) {
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

    private fun checkAnswer() {
        val selectedOptionId = optionsRadioGroup.checkedRadioButtonId

        if (selectedOptionId != -1) {
            val selectedOptionIndex = optionsRadioGroup.indexOfChild(optionsRadioGroup.findViewById(selectedOptionId))

            if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
                // Correct answer, you can add your logic here
                Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Incorrect. The correct answer is ${questions[currentQuestionIndex].options[correctAnswers[currentQuestionIndex]]}", Toast.LENGTH_SHORT).show()
            }

            // Move to the next question
            currentQuestionIndex++

            if (currentQuestionIndex < questions.size) {
                displayQuestion()
            } else {
                // End of the quiz
                Toast.makeText(context, "Quiz completed!", Toast.LENGTH_SHORT).show()
            }
        } else {
            // No option selected
            Toast.makeText(context, "Please select an option.", Toast.LENGTH_SHORT).show()
        }
    }
}
