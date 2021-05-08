package com.example.myquizapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler private constructor(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
) {
    companion object {
        // Database Information
        private const val DATABASE_NAME = "MyQuizAppDatabase"
        private const val DATABASE_VERSION = 1

        // Table Names
        private const val USER_TABLE: String = "Users"
        private const val QUIZ_TABLE: String = "Quizzes"

        // Users Table Columns
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_USER_PASSWORD = "password"
        private const val KEY_USER_EMAIL = "email"
        private const val KEY_USER_PROFILE_PICTURE = "profile_picture"
        private const val KEY_USER_IS_CURRENT_USER = "is_current_user"

        // Quizzes Table Columns
        private const val KEY_QUIZ_ID = "quiz id"
        private const val KEY_QUIZ_TITLE = "title"
        private const val KEY_QUIZ_SUBTITLE = "subtitle"
        private const val KEY_QUIZ_IMAGE = "quiz image"

        // Making this a Singleton class
        private var databaseHandler: DatabaseHandler? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHandler {
            if (databaseHandler == null) databaseHandler =
                DatabaseHandler(context.applicationContext)
            return databaseHandler!!
        }
    }

    // After creating database create tables
    override fun onCreate(db: SQLiteDatabase?) {
        // create scripts
//        val createTableQuiz =
//            ("CREATE TABLE $QUIZ_TABLE ($KEY_QUIZ_ID INTEGER PRIMARY KEY, $KEY_QUIZ_TITLE TEXT, $KEY_QUIZ_SUBTITLE TEXT, $KEY_QUIZ_IMAGE TEXT)")
        val createTableUser =
            ("CREATE TABLE $USER_TABLE ($KEY_USER_ID INTEGER PRIMARY KEY, $KEY_USERNAME TEXT, $KEY_USER_EMAIL TEXT, $KEY_USER_PASSWORD TEXT, $KEY_USER_PROFILE_PICTURE TEXT, $KEY_USER_IS_CURRENT_USER INTEGER)")
        db?.execSQL(createTableUser)
//        db?.execSQL(createTableQuiz)
    }

    // When table is upgraded drop existing tables and create new ones
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion != newVersion) {
            db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE")
//            db?.execSQL("DROP TABLE IF EXISTS $QUIZ_TABLE")
            onCreate(db)
        }

    }

    /* addUser adds a new user to database and returns,
       1 -> transaction successful
       -1 -> username already exists
       -2 -> email already exists
       -3 -> SQLException was thrown
     */
    fun addUser(user: User): Long {
        var success = 1L

        val pairUsernameAndEmail = doesUserExists(user)
        when {
            pairUsernameAndEmail.first -> {
                success = -1L
            }
            pairUsernameAndEmail.second -> {
                success = -2L
            }
            else -> {
                val db = this.writableDatabase
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
        return success
    }

    // Returns true if user already exists
    fun doesUserExists(user: User): Pair<Boolean, Boolean> {
        var usernameExists = false
        var emailExists = false
        val db = this.readableDatabase
        db.beginTransaction()
        // Query username
        val queryOnUsername = "SELECT * FROM $USER_TABLE WHERE $KEY_USERNAME = ?"
        // Query email
        val queryOnEmail = "SELECT * FROM $USER_TABLE WHERE $KEY_USER_EMAIL = ?"
        var cursor: Cursor = db.rawQuery(queryOnUsername, arrayOf(user.userName))

        // If cursor found the required record using username then check if password provided by the user is correct
        if (cursor.moveToFirst() && (cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)) == user.password)) {
            usernameExists = true
        }

        cursor = db.rawQuery(queryOnEmail, arrayOf(user.email))
        // If cursor found the required record using email then check if password provided by the user is correct
        if (cursor.moveToFirst() && (cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)) == user.password)) {
            emailExists = true
        }
        cursor.close()
        db.endTransaction()
        return Pair(first = usernameExists, second = emailExists)
    }

    // Takes a user and sets it as the current logged in user
    fun setCurrentUser(user: User): Boolean {
        var success = false
        val db = this.writableDatabase
        db.beginTransaction()
        val contentValues = ContentValues()
        contentValues.put(KEY_USER_IS_CURRENT_USER, "1")
        if (db.update(
                USER_TABLE,
                contentValues,
                "$KEY_USER_ID = ?",
                arrayOf("${user.userId}")
            ) == 1
        ) {
            success = true
            db.setTransactionSuccessful()
        }
        db.endTransaction()
        return success
    }

    // Returns the current logged in user
    fun getCurrentUser(): User? {
        var user: User? = null
        val db = this.readableDatabase
        db.beginTransaction()
        val query = "SELECT * FROM $USER_TABLE WHERE $KEY_USER_IS_CURRENT_USER = ?"
        val cursor = db.rawQuery(query, arrayOf("1"))
        if (cursor.moveToFirst())
            user = User(
                cursor.getLong(cursor.getColumnIndex(KEY_USER_ID)),
                cursor.getString(cursor.getColumnIndex(KEY_USERNAME)),
                cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)),
                cursor.getString(cursor.getColumnIndex(KEY_USER_EMAIL)),
                cursor.getInt(cursor.getColumnIndex(KEY_USER_PROFILE_PICTURE))
            )
        cursor.close()
        db.endTransaction()
        return user
    }
}













