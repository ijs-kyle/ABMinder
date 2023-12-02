package com.mtcdb.stem.mathtrix.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class QuizViewModel(private val repository: QuizRepository) : ViewModel() {

    private val _currentQuestion = MutableLiveData<QuizQuestion>()
    val currentQuestion: LiveData<QuizQuestion>
        get() = _currentQuestion

    private val _quizCompleted = MutableLiveData<Boolean>()
    val quizCompleted: LiveData<Boolean>
        get() = _quizCompleted

    private val questions: MutableList<QuizQuestion> = mutableListOf() // Cached questions
    private var currentQuestionIndex = 0

    init {
        viewModelScope.launch {
            questions.addAll(repository.getAllQuestions())
            startQuiz()
        }
    }

    fun startQuiz() {
        currentQuestionIndex = 0
        _quizCompleted.value = false
        showNextQuestion()
    }

    private fun showNextQuestion() {
        if (currentQuestionIndex < questions.size) {
            _currentQuestion.value = questions[currentQuestionIndex]
        } else {
            _quizCompleted.value = true
        }
    }

    fun checkAnswer(selectedOption: String) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedOption == currentQuestion.correctOption) {
            // Correct answer logic, you can update the score here
        }

        currentQuestionIndex++
        showNextQuestion()
    }
}
