package com.mtcdb.stem.mathtrix.quiz

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizApplication : Application() {

    companion object {
        lateinit var database: QuizDatabase
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {

        database = Room.databaseBuilder(
            applicationContext,
            QuizDatabase::class.java,
            "quiz_database"
        ).build()

        // Populate the database on a background thread
        GlobalScope.launch {
            populateDatabase()
        }

        super.onCreate()
    }

    private suspend fun populateDatabase() {
        val quizDao = database.quizDao()

        // Check if the database is already populated
        if (quizDao.getQuestionsByDifficulty("Easy").isEmpty()) {
            // If not populated, insert sample questions
            quizDao.insert(QuizEntity(question = "Sample Question 1", options = listOf("A", "B", "C"), correctAnswerIndex = 0, difficulty = "Easy"))
            quizDao.insert(QuizEntity(question = "Sample Question 2", options = listOf("X", "Y", "Z"), correctAnswerIndex = 1, difficulty = "Medium"))
            quizDao.insert(QuizEntity(question = "Sample Question 3", options = listOf("1", "2", "3"), correctAnswerIndex = 2, difficulty = "Hard"))
            // Add more questions as needed
        }
    }
}
