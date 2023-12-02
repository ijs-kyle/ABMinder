package com.mtcdb.stem.mathtrix.quiz

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuizDao {

    @Query("SELECT * FROM quiz_questions")
    suspend fun getAllQuestions(): List<QuizQuestion>
}
