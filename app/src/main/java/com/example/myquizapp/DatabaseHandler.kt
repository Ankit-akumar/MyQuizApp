package com.example.myquizapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        // Database Information
        private val DATABASE_NAME: String = "MyQuizAppDatabase"
        private val DATABASE_VERSION: Int = 1

        // Table Names
        private val USER_TABLE: String = "Users"
        private val QUIZ_TABLE: String = "Quizzes"

        // Users Table Columns
        private val KEY_USER_ID: String = "user id"
        private val KEY_USERNAME: String = "username"
        private val KEY_USER_PASSWORD: String = "password"
        private val KEY_USER_EMAIL: String = "email"
        private val KEY_USER_SECURITY_QUESTION: String = "question"
        private val KEY_USER_SECURITY_ANSWER: String = "answer"
        private val KEY_USER_PROFILE_PICTURE: String = "profile picture"

        // Quizzes Table Columns
        private val KEY_QUIZ_ID: String = "quiz id"
        private val KEY_QUIZ_TITLE: String = "title"
        private val KEY_QUIZ_SUBTITLE: String = "subtitle"
        private val KEY_QUIZ_IMAGE: String = "quiz image"

//        // Database instance
//        private var dbHandlerInstance: DatabaseHandler? = null
//
//        @Synchronized
//        fun getInstance(context: Context): DatabaseHandler? {
//            if (dbHandlerInstance == null) dbHandlerInstance = DatabaseHandler(context.applicationContext)
//            return dbHandlerInstance
//        }
    }

    // After creating database create tables
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuiz =
            ("CREATE TABLE $QUIZ_TABLE ($KEY_QUIZ_ID INTEGER PRIMARY KEY, $KEY_QUIZ_TITLE TEXT, $KEY_QUIZ_SUBTITLE TEXT, $KEY_QUIZ_IMAGE TEXT)")
        val createTableUser =
            ("CREATE TABLE $USER_TABLE ($KEY_USER_ID INTEGER PRIMARY KEY, $KEY_USERNAME TEXT, $KEY_USER_EMAIL TEXT, $KEY_USER_PASSWORD TEXT, $KEY_USER_PROFILE_PICTURE TEXT, $KEY_USER_SECURITY_QUESTION TEXT, $KEY_USER_SECURITY_ANSWER TEXT)")
        db?.execSQL(createTableUser)
        db?.execSQL(createTableQuiz)
    }

    // When table is upgraded drop existing tables and create new ones
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
            db?.execSQL("DROP TABLE IF EXISTS $QUIZ_TABLE")
            onCreate(db)
        }
    }
}














