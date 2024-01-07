package com.mtcdb.stem.mathtrix.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

class DifficultyLevelFragment : Fragment() {

    private lateinit var easyButton: Button
    private lateinit var mediumButton: Button
    private lateinit var hardButton: Button
    private lateinit var navController: NavController
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_difficulty_level, container, false)

        easyButton = view.findViewById(R.id.easyButton)
        mediumButton = view.findViewById(R.id.mediumButton)
        hardButton = view.findViewById(R.id.hardButton)


        easyButton.setOnClickListener {

        }

        mediumButton.setOnClickListener {

        }

        hardButton.setOnClickListener {

        }

        return view
    }
}
