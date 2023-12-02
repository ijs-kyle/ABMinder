package com.mtcdb.stem.mathtrix.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mtcdb.stem.mathtrix.R

class QuizFragment : Fragment() {

    private lateinit var viewModel: QuizViewModel
    private lateinit var optionA: Button
    private lateinit var optionB: Button
    private lateinit var optionC: Button
    private lateinit var questionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            QuizViewModelFactory(QuizRepository(QuizDatabase.getDatabase(requireContext()).quizDao()))
        )[QuizViewModel::class.java]

        // Observe question changes
        viewModel.currentQuestion.observe(viewLifecycleOwner) { question ->
            displayQuestion(question)
        }

        // Observe quiz completion
        viewModel.quizCompleted.observe(viewLifecycleOwner) { isCompleted ->
            if (isCompleted) {
                displayQuizResult()
            }
        }

        // Set up button click listeners
        optionA.setOnClickListener { onOptionSelected(optionA.text.toString()) }
        optionB.setOnClickListener { onOptionSelected(optionB.text.toString()) }
        optionC.setOnClickListener { onOptionSelected(optionC.text.toString()) }

        // Start the quiz
        viewModel.startQuiz()
    }

    private fun displayQuestion(question: QuizQuestion) {
        questionTextView.text = question.question
        optionA.text = question.optionA
        optionB.text = question.optionB
        optionC.text = question.optionC
    }

    private fun onOptionSelected(selectedOption: String) {
        viewModel.checkAnswer(selectedOption)
    }

    private fun displayQuizResult() {
        // Implement logic to display quiz result (e.g., navigate to result fragment)
        // You can pass the quiz result to the result fragment
    }
}
