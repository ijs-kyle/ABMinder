package com.mtcdb.stem.mathtrix.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.mtcdb.stem.mathtrix.R

class DifficultyLevelFragment : Fragment() {

    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_difficulty_level, container, false)

        easyButton = view.findViewById(R.id.easyButton)
        mediumButton = view.findViewById(R.id.mediumButton)
        hardButton = view.findViewById(R.id.hardButton)

        easyButton.setOnClickListener {
            navigateToQuizFragment("Easy")
        }

        mediumButton.setOnClickListener {
            navigateToQuizFragment("Medium")
        }

        hardButton.setOnClickListener {
            navigateToQuizFragment("Hard")
        }

        return view
    }

    private fun navigateToQuizFragment(difficultyLevel: String) {
        val quizFragment = QuizFragment()
        val bundle = Bundle()
        bundle.putString("difficultyLevel", difficultyLevel)
        quizFragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, quizFragment) // Replace R.id.fragmentContainer with the ID of your fragment container
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
