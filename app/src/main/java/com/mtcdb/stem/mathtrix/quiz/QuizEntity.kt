package com.mtcdb.stem.mathtrix.quiz

data class QuizEntity(
    val id: Long?,
    val question: String,
    val options: String,
    val correctAnswerIndex: Int,
    val difficultyLevel: String
)
