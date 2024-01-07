package com.mtcdb.stem.mathtrix.quiz

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuizEntity::class], version = 1, exportSchema = false)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun quizDao(): QuizDao
}


