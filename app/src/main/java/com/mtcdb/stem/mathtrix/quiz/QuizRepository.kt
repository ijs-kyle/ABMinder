package com.mtcdb.stem.mathtrix.quiz

class QuizRepository(private val quizDao: QuizDao) {

    suspend fun getAllQuestions(): List<QuizQuestion> {
        return quizDao.getAllQuestions()
    }
}
