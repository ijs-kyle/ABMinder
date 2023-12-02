package com.mtcdb.stem.mathtrix.quiz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_questions")
data class QuizQuestion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val correctOption: String
)
