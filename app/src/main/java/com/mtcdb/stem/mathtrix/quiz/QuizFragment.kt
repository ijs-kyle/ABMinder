package com.mtcdb.stem.mathtrix.quiz

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.google.android.material.radiobutton.MaterialRadioButton
import com.mtcdb.stem.mathtrix.R
import java.util.Locale

@Suppress("NAME_SHADOWING")
class QuizFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var dbHelper: QuizDbHelper
    private lateinit var tVQuestions: TextView
    private lateinit var database: SQLiteDatabase
    private var questions: MutableList<QuizEntity> = mutableListOf()
    private var currentQuestionIndex = 0
    private lateinit var timerTextView: TextView
    private lateinit var timerProgressBar: RoundCornerProgressBar
    private var selectedOptionIndex = -1
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftMillis: Long = 300000 // 5 minutes in milliseconds
    private val totalTimeMillis: Long = 300000 // 5 minutes in milliseconds
    private var totalQuestionsPerGame = 10
    private var questionsAnswered = 0
    private var quizStartTimeMillis: Long = 0
    private lateinit var quizProgress: QuizProgress
    private lateinit var totalQuestionsAnsweredTextView: TextView
    private lateinit var totalCorrectTextView: TextView
    private lateinit var totalWrongTextView: TextView
    private lateinit var easyQuestionsAnsweredTextView: TextView
    private lateinit var mediumQuestionsAnsweredTextView: TextView
    private lateinit var hardQuestionsAnsweredTextView: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)

        timerTextView = view.findViewById(R.id.timerTextView)
        questionTextView = view.findViewById(R.id.questionTextView)
        optionsRadioGroup = view.findViewById(R.id.optionsRadioGroup)
        timerProgressBar = view.findViewById(R.id.timerProgress)
        tVQuestions = view.findViewById(R.id.tv_questions)

        val progressView = layoutInflater.inflate(R.layout.activity_difficulty_level, null, true)

        // Initialize your TextViews
        totalQuestionsAnsweredTextView =
            progressView.findViewById(R.id.totalQuestionsAnsweredTextView)
        totalCorrectTextView = progressView.findViewById(R.id.totalCorrectTextView)
        totalWrongTextView = progressView.findViewById(R.id.totalWrongTextView)
        easyQuestionsAnsweredTextView =
            progressView.findViewById(R.id.easyQuestionsAnsweredTextView)
        mediumQuestionsAnsweredTextView =
            progressView.findViewById(R.id.mediumQuestionsAnsweredTextView)
        hardQuestionsAnsweredTextView =
            progressView.findViewById(R.id.hardQuestionsAnsweredTextView)

        // Display the first question
        fetchQuestions()
        displayQuestion()

        dbHelper = QuizDbHelper(requireContext())
        database = dbHelper.writableDatabase

        val totalQuestions = questions.size
        val currentQuestionNumber = currentQuestionIndex + 1
        quizStartTimeMillis = System.currentTimeMillis()

        view?.findViewById<TextView>(R.id.tv_progress)?.text = buildString {
            append("Question ")
            append(currentQuestionNumber)
        }
        tVQuestions.text = buildString {
            append("out of ")
            append(totalQuestions)
        }

        startTimer()
        quizProgress = QuizProgress()

        return view
    }

    private fun displayQuestion() {
        // Check if questions are not empty before accessing their elements
        if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]

            questionTextView.text = currentQuestion.question
            optionsRadioGroup.removeAllViews()

            for ((index, option) in currentQuestion.options.withIndex()) {
                val radioButton = context?.let { MaterialRadioButton(it) }
                radioButton?.text = option
                radioButton?.id = index
                radioButton?.setBackgroundResource(R.drawable.option_border_bg)
                radioButton?.setTextColor(Color.parseColor("#FF000000"))
                radioButton?.setPadding(32, 16, 32, 16)

                val linearLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                linearLayoutParams.setMargins(0, 0, 0, 32)

                radioButton?.layoutParams = linearLayoutParams
                optionsRadioGroup.addView(radioButton)

                // Set a listener for each RadioButton
                radioButton?.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        // Highlight the selected option when the RadioButton is checked
                        selectedOptionIndex = index
                    } else {
                        radioButton.setBackgroundResource(R.drawable.option_border_bg)
                    }
                }

                radioButton?.setOnClickListener {
                    checkAnswer(index)
                }
            }

            // Update the question number display
            updateQuestionNumber()
        } else {
            // Handle the case where questions are empty or all questions have been displayed
        }
    }


    // Function to update the question number display
    private fun updateQuestionNumber() {
        val currentQuestionNumber = currentQuestionIndex + 1

        view?.findViewById<TextView>(R.id.tv_progress)?.text = buildString {
            append("Question ")
            append(currentQuestionNumber)
        }
    }

    private fun fetchQuestions() {
        dbHelper = QuizDbHelper(requireContext())
        database = dbHelper.writableDatabase

        // Determine the difficulty level
        val difficultyLevel = when (this.arguments?.getString("difficultyLevel")) {
            "Easy" -> "Easy"
            "Medium" -> "Medium"
            "Hard" -> "Hard"
            else -> "Easy"
        }// Default to Easy if difficulty level is not recognized

        totalQuestionsPerGame = when (difficultyLevel) {
            "Easy" -> 10
            "Medium" -> 15
            "Hard" -> 20
            else -> 10 // Default to 10 if difficulty level is not recognized
        }

        // Fetch questions based on the selected difficulty level
        val cursor = database.rawQuery(
            "SELECT * FROM ${QuizDbHelper.TABLE_QUIZ} WHERE ${QuizDbHelper.COLUMN_DIFFICULTY_LEVEL} = ? LIMIT ?",
            arrayOf(difficultyLevel, totalQuestionsPerGame.toString())
        )

        if (cursor.moveToFirst()) {
            // If there are questions in the cursor, convert them to a list of QuizEntity
            questions = mutableListOf()
            do {
                val idColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_ID)
                val questionColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_QUESTION)
                val optionsColumnIndex = cursor.getColumnIndex(QuizDbHelper.COLUMN_OPTIONS)
                val correctAnswerColumnIndex =
                    cursor.getColumnIndex(QuizDbHelper.COLUMN_CORRECT_ANSWER_INDEX)
                val difficultyLevelColumnIndex =
                    cursor.getColumnIndex(QuizDbHelper.COLUMN_DIFFICULTY_LEVEL)

                if (idColumnIndex >= 0 && questionColumnIndex >= 0 && optionsColumnIndex >= 0 &&
                    correctAnswerColumnIndex >= 0 && difficultyLevelColumnIndex >= 0
                ) {
                    val id = cursor.getLong(idColumnIndex)
                    val question = cursor.getString(questionColumnIndex)
                    val options = cursor.getString(optionsColumnIndex).split(",")
                    val correctAnswerIndex = cursor.getInt(correctAnswerColumnIndex)
                    val difficultyLevel = cursor.getString(difficultyLevelColumnIndex)

                    val quizEntity = QuizEntity(
                        id,
                        question,
                        options,
                        correctAnswerIndex,
                        difficultyLevel
                    )
                    questions.add(quizEntity)
                } else {
                    // Handle the case where one or more columns are not found
                    // You can log an error or add your custom logic here
                }
            } while (cursor.moveToNext())
            questions.shuffle()

            // Display the first question
            displayQuestion()
            updateQuestionNumber()
        } else {
            // Handle the case where there are no questions
            // You can add your logic here
        }
        updateQuestionNumber()
        cursor.close()
    }

    private fun checkAnswer(selectedOptionIndex: Int) {
        if (selectedOptionIndex != -1) {

            if (questions.isNotEmpty() && currentQuestionIndex < questions.size) {
                questions[currentQuestionIndex].selectedAnswerIndex = selectedOptionIndex

                val correctAnswerIndex = questions[currentQuestionIndex].correctAnswerIndex
                // Determine the difficulty level
                val difficultyLevel = when (this.arguments?.getString("difficultyLevel")) {
                    "Easy" -> "Easy"
                    "Medium" -> "Medium"
                    "Hard" -> "Hard"
                    else -> "Easy"
                }// Default to Easy if difficulty level is not recognized

                if (selectedOptionIndex == correctAnswerIndex) {
                    // Correct answer
                    markOptionAsCorrect(selectedOptionIndex)
                } else {
                    // Incorrect answer
                    val correctOptionIndex = questions[currentQuestionIndex].correctAnswerIndex
                    markOptionAsIncorrect(selectedOptionIndex, correctOptionIndex)
                }


                // Delay for 1 second before moving to the next question
                Handler(Looper.getMainLooper()).postDelayed({

                    // Move to the next question
                    currentQuestionIndex++
                    optionsRadioGroup.clearCheck()

                    if (currentQuestionIndex < questions.size) {
                        // Clear the background color before displaying the next question
                        clearOptionBackgrounds()
                        displayQuestion()
                    } else {
                        // End of the quiz
                        val score = calculateScore()
                        navigateToQuizResult(score)
                    }
                }, 1000) //(1 second) delay
            }
        } else {
            // No option selected
            Toast.makeText(context, "Please select an option.", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateProgressTracker(
        isCorrect: Boolean,
        difficultyLevel: String,
        quizProgress: QuizProgress
    ) {
        // Update progress directly in the QuizFragment

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

        // Update TextViews or UI elements in the QuizFragment layout
        updateProgress()

        // Example: Update TextViews in the QuizFragment layout
        totalQuestionsAnsweredTextView.text = buildString {
                append("Total Questions Answered: ")
                append(quizProgress.totalQuestionsAnswered.toString())
            }
        totalCorrectTextView.text = buildString {
            append("Total Correct: ")
            append(quizProgress.totalCorrect.toString())
        }
        totalWrongTextView.text = buildString {
            append("Total Wrong: ")
            append(quizProgress.totalWrong.toString())
        }
        easyQuestionsAnsweredTextView.text = buildString {
                append("Easy Questions Answered: ")
                append(quizProgress.easyQuestionsAnswered.toString())
            }
        mediumQuestionsAnsweredTextView.text = buildString {
                append("Medium Questions Answered: ")
                append(quizProgress.mediumQuestionsAnswered.toString())
            }
        hardQuestionsAnsweredTextView.text = buildString {
                append("Hard Questions Answered: ")
                append(quizProgress.hardQuestionsAnswered.toString())
            }

    }

    fun updateProgress() {
        // Update your UI or perform any necessary updates based on quizProgress

        // Example: Update a progress bar
        val totalQuestions = 10 // Change this to the total number of questions
        val progressPercentage =
            (quizProgress.totalQuestionsAnswered.toDouble() / totalQuestions) * 100

        // You can update other UI elements or perform additional actions as needed
    }


    private fun clearOptionBackgrounds() {
        for (i in 0 until optionsRadioGroup.childCount) {
            optionsRadioGroup.getChildAt(i)?.background = null
        }
    }

    private fun markOptionAsCorrect(optionIndex: Int) {
        optionsRadioGroup.getChildAt(optionIndex)?.setBackgroundResource(R.drawable.option_correct)
    }

    private fun markOptionAsIncorrect(selectedIndex: Int, correctIndex: Int) {
        val correctColor = R.drawable.option_correct
        val incorrectColor = R.drawable.option_wrong
        optionsRadioGroup.getChildAt(selectedIndex)?.setBackgroundResource(incorrectColor)
        optionsRadioGroup.getChildAt(correctIndex)?.setBackgroundResource(correctColor)
    }

    private fun calculateScore(): Int {
        var score = 0
        for (question in questions) {
            if (question.isAnswerCorrect) {
                score++
            }
        }
        return score
    }

    private fun moveNextOrEndQuiz() {
        clearOptionBackgrounds()
        optionsRadioGroup.clearCheck()
        if (questionsAnswered < totalQuestionsPerGame) {
            // Move to the next question
            currentQuestionIndex++
            questionsAnswered++
            displayQuestion()
            startTimer()
        } else {
            // End of the quiz
            val score = calculateScore()
            navigateToQuizResult(score)
        }
    }

    private fun startTimer() {
        // Cancel any existing timer to avoid overlapping
        countDownTimer?.cancel()

        // Create a new CountDownTimer
        countDownTimer = object : CountDownTimer(timeLeftMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Update the timer text
                timerTextView.text = buildString {
                    append("Time left: ")
                    append(formatTime(millisUntilFinished))
                }
                // Update the timer ProgressBar
                timerProgressBar.setProgress(((totalTimeMillis - millisUntilFinished) / 1000).toInt())
            }

            override fun onFinish() {
                // Time's up, move to the next question or end the quiz
                moveNextOrEndQuiz()
            }
        }

        // Start the timer
        countDownTimer?.start()
    }

    private fun formatTime(millis: Long): String {
        val minutes = millis / 60000
        val seconds = (millis % 60000) / 1000
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

    private fun navigateToQuizResult(score: Int) {
        val resultFragment = QuizResultFragment()
        val difficulty = this.arguments?.getString("difficultyLevel")

        // Calculate time taken
        val quizEndTimeMillis = System.currentTimeMillis()
        val timeTakenMillis = quizEndTimeMillis - quizStartTimeMillis

        // Convert time taken to a formatted string (e.g., "03:30" for 3 minutes and 30 seconds)
        val formattedTimeTaken = formatTime(timeTakenMillis)

        // Pass the questions, difficulty, time taken, and score as arguments to the fragment
        val bundle = Bundle()
        bundle.putParcelableArrayList("quizQuestions", ArrayList(questions))
        bundle.putInt("quizScore", score)
        bundle.putString("difficulty", difficulty)
        bundle.putString("timeTaken", formattedTimeTaken)
        resultFragment.arguments = bundle

        // Perform the fragment transaction
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.quiz_container, resultFragment)
            .commit()
    }
}