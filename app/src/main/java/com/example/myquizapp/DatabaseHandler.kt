package com.example.myquizapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DatabaseHandler(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        // Database Information
        private const val DATABASE_NAME: String = "MyQuizAppDatabase"
        private const val DATABASE_VERSION: Int = 1

        // Table Names
        private const val USER_TABLE: String = "Users"
        private const val QUIZ_TABLE: String = "Quizzes"

        // Users Table Columns
        private const val KEY_USER_ID: String = "user id"
        private const val KEY_USERNAME: String = "username"
        private const val KEY_USER_PASSWORD: String = "password"
        private const val KEY_USER_EMAIL: String = "email"
        private const val KEY_USER_PROFILE_PICTURE: String = "profile picture"

        // Quizzes Table Columns
        private const val KEY_QUIZ_ID: String = "quiz id"
        private const val KEY_QUIZ_TITLE: String = "title"
        private const val KEY_QUIZ_SUBTITLE: String = "subtitle"
        private const val KEY_QUIZ_IMAGE: String = "quiz image"

        private val databaseHandler: DatabaseHandler? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHandler {
            if (databaseHandler == null) DatabaseHandler(context.applicationContext)
            return databaseHandler!!
        }
    }

    // After creating database create tables
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuiz =
            ("CREATE TABLE $QUIZ_TABLE ($KEY_QUIZ_ID INTEGER PRIMARY KEY, $KEY_QUIZ_TITLE TEXT, $KEY_QUIZ_SUBTITLE TEXT, $KEY_QUIZ_IMAGE TEXT)")
        val createTableUser =
            ("CREATE TABLE $USER_TABLE ($KEY_USER_ID INTEGER PRIMARY KEY, $KEY_USERNAME TEXT, $KEY_USER_EMAIL TEXT, $KEY_USER_PASSWORD TEXT, $KEY_USER_PROFILE_PICTURE TEXT)")
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

    /* In addUser,
       when returned value
       is 1 -> transaction successful
       is -1 -> username already exists
       is -2 -> email already exists
       is -3 -> SQLException was thrown
     */
    fun addUser(user: User): Long {
        var success = 1L
        val db = this.writableDatabase

        val pairUsernameAndEmail = doesUserExists(user)
        when {
            pairUsernameAndEmail.first -> {
                success = -1L
            }
            pairUsernameAndEmail.second -> {
                success = -2L
            }
            else -> {
                db.beginTransaction()
                try {
                    val contentValues = ContentValues()
                    contentValues.put(KEY_USERNAME, user.userName)
                    contentValues.put(KEY_USER_EMAIL, user.email)
                    contentValues.put(KEY_USER_PASSWORD, user.password)
                    contentValues.put(KEY_USER_PROFILE_PICTURE, user.profilePicture)
                    db.insertOrThrow(USER_TABLE, null, contentValues)
                    db.setTransactionSuccessful()
                } catch (ex: Exception) {
                    success = -3L
                } finally {
                    db.endTransaction()
                }
            }
        }
        db.close()
        return success
    }

    // returns true if user already exists
    fun doesUserExists(user: User): Pair<Boolean, Boolean> {
        var usernameExists = false
        var emailExists = false
        val db = this.readableDatabase
        // Query username
        val queryOnUsername = "SELECT * FROM $USER_TABLE WHERE $KEY_USERNAME = ?"
        // Query email
        val queryOnEmail = "SELECT * FROM $USER_TABLE WHERE $KEY_USER_EMAIL = ?"
        var cursor: Cursor = db.rawQuery(queryOnUsername, arrayOf(user.userName))
        if (cursor.moveToFirst()) usernameExists = true
        cursor = db.rawQuery(queryOnEmail, arrayOf(user.email))
        if (cursor.moveToFirst()) emailExists = true
        cursor.close()
        db.close()
        return Pair(first = usernameExists, second = emailExists)
    }
}














