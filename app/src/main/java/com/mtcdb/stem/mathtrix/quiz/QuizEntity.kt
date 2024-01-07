package com.mtcdb.stem.mathtrix.quiz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_table")
data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val difficulty: String
)
