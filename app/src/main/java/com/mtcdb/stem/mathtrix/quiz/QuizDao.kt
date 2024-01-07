package com.mtcdb.stem.mathtrix.quiz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizDao {

    @Insert
    suspend fun insert(quizEntity: QuizEntity)

    @Query("SELECT * FROM quiz_table WHERE difficulty = :difficulty")
    suspend fun getQuestionsByDifficulty(difficulty: String): List<QuizEntity>
}
