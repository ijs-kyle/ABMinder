package com.mtcdb.stem.mathtrix.dictionary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DictionaryDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dictionary.db"
        private const val DATABASE_VERSION = 10
        private const val TABLE_NAME = "dictionary_terms"
        private const val COLUMN_TERM = "term"
        private const val COLUMN_DEFINITION = "definition"
        private const val COLUMN_EXAMPLE = "example"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
                _id INTEGER PRIMARY KEY,
                $COLUMN_TERM TEXT NOT NULL,
                $COLUMN_DEFINITION TEXT NOT NULL,
                $COLUMN_EXAMPLE TEXT
            )
            """.trimIndent()
        )

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $DATABASE_NAME")
        onCreate(db)
    }


}
