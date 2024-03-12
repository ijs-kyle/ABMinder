package com.mtcdb.stem.mathtrix.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.mtcdb.stem.mathtrix.R

class Difficulty : Fragment() {

    private lateinit var easyButton: AppCompatButton
    private lateinit var mediumButton: AppCompatButton
    private lateinit var hardButton: AppCompatButton
    private lateinit var quizFragment: QuizFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = layoutInflater.inflate(R.layout.difficulty, container, false)

        easyButton = rootView.findViewById(R.id.easyButton)
        mediumButton = rootView.findViewById(R.id.mediumButton)
        hardButton = rootView.findViewById(R.id.hardButton)
        easyButton.setOnClickListener {
            navigateToQuizFragment("Easy")
        }

        mediumButton.setOnClickListener {
            navigateToQuizFragment("Medium")
        }

        hardButton.setOnClickListener {
            navigateToQuizFragment("Hard")
        }

        return rootView
    }

    private fun navigateToQuizFragment(difficultyLevel: String) {
        quizFragment = QuizFragment()
        val bundle = Bundle()
        bundle.putString("difficultyLevel", difficultyLevel)
        quizFragment.arguments = bundle
        quizFragment.isVisible

        val difficulty = layoutInflater.inflate(R.layout.difficulty, null)
        difficulty.visibility = View.GONE
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.quiz_container, quizFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}