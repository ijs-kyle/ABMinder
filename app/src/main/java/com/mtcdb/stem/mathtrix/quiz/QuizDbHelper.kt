package com.mtcdb.stem.mathtrix.quiz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class QuizDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "quiz_database"
        const val DATABASE_VERSION = 2
        const val TABLE_QUIZ = "quiz_table"
        const val COLUMN_ID = "id"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_OPTIONS = "options"
        const val COLUMN_CORRECT_ANSWER_INDEX = "correct_answer_index"
        const val COLUMN_DIFFICULTY_LEVEL = "difficulty_level"
    }

    private val createTableQuery = """
        CREATE TABLE $TABLE_QUIZ (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_QUESTION TEXT,
            $COLUMN_OPTIONS TEXT,
            $COLUMN_CORRECT_ANSWER_INDEX INTEGER,
            $COLUMN_DIFFICULTY_LEVEL TEXT
        )
    """.trimIndent()

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTableQuery)

        insertSampleQuizData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // You can implement an upgrade strategy if needed
    }

    private fun insertSampleQuizData(db: SQLiteDatabase?) {
        // Easy questions
        val easyQuestion1 = QuizEntity(
            id = null,
            question = "What is the capital of Italy?",
            options = "Berlin,Paris,Rome,Madrid",
            correctAnswerIndex = 2,
            difficultyLevel = "Easy"
        )
        insertQuizData(db, easyQuestion1)

        val easyQuestion2 = QuizEntity(
            id = null,
            question = "Who wrote 'Romeo and Juliet'?",
            options = "Shakespeare,Dickens,Hemingway,Austen",
            correctAnswerIndex = 0,
            difficultyLevel = "Easy"
        )
        insertQuizData(db, easyQuestion2)

        // Medium questions
        val mediumQuestion1 = QuizEntity(
            id = null,
            question = "What is the capital of Brazil?",
            options = "Lima,Buenos Aires,Rio de Janeiro,Brasilia",
            correctAnswerIndex = 3,
            difficultyLevel = "Medium"
        )
        insertQuizData(db, mediumQuestion1)

        val mediumQuestion2 = QuizEntity(
            id = null,
            question = "Who discovered penicillin?",
            options = "Marie Curie,Isaac Newton,Alexander Fleming,Albert Einstein",
            correctAnswerIndex = 2,
            difficultyLevel = "Medium"
        )
        insertQuizData(db, mediumQuestion2)

        // Hard questions
        val hardQuestion1 = QuizEntity(
            id = null,
            question = "What is the largest mammal on Earth?",
            options = "Elephant,Giraffe,Blue Whale,Hippopotamus",
            correctAnswerIndex = 2,
            difficultyLevel = "Hard"
        )
        insertQuizData(db, hardQuestion1)

        val hardQuestion2 = QuizEntity(
            id = null,
            question = "Who is known as the 'Father of Computer Science'?",
            options = "Ada Lovelace,Alan Turing,Charles Babbage,John von Neumann",
            correctAnswerIndex = 1,
            difficultyLevel = "Hard"
        )
        insertQuizData(db, hardQuestion2)
    }

    private fun insertQuizData(db: SQLiteDatabase?, quizData: QuizEntity) {
        val values = ContentValues().apply {
            put(COLUMN_QUESTION, quizData.question)
            put(COLUMN_OPTIONS, quizData.options)
            put(COLUMN_CORRECT_ANSWER_INDEX, quizData.correctAnswerIndex)
            put(COLUMN_DIFFICULTY_LEVEL, quizData.difficultyLevel)
        }

        db?.insert(TABLE_QUIZ, null, values)
    }
}


