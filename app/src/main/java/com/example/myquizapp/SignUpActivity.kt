package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btnSignUp.setOnClickListener {
            tv_warning.visibility = View.INVISIBLE

            // Verifying the data input by user. If invalid then return.
            validateInputData()
            if (tv_warning.isVisible) return@setOnClickListener
            Log.d("result_validate", "success")

            // Adding the user into database
            val user = User(
                1,
                et_username.text.toString(),
                et_password.text.toString(),
                et_email.text.toString(),
                R.drawable.profile_pic_default_big
            )

            val databaseHandler = DatabaseHandler.getInstance(this)
            when (databaseHandler.addUser(user)) {
                -1L -> { // In case a user with provided username already exists
                    tv_warning.text = getString(R.string.Username_already_exists)
                    tv_warning.visibility = View.VISIBLE
                }
                -2L -> { // In case a user with provided email is already exists
                    tv_warning.text = getString(R.string.email_already_registered)
                    tv_warning.visibility = View.VISIBLE
                }
                -3L -> { // In case SQLite exception is thrown
                    tv_warning.text = getString(R.string.an_error_occurred)
                    tv_warning.visibility = View.VISIBLE
                }
                else -> {
                    // Set user as the current logged in user
                    databaseHandler.setCurrentUser(user)
                    Log.d("AFTER SET CURRENT USER", "done")
                    // Go to main activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        tvBtnLoginHere.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputData() {
        when {
            !et_username.text.all { it.isLetterOrDigit() } -> {
                tv_warning.text = getString(R.string.username_must_contain_Letters_and_digits_only)
                tv_warning.visibility = View.VISIBLE
            }
            et_username.text.length < 5 -> {
                tv_warning.text = getString(R.string.username_must_be_5_characters_long)
                tv_warning.visibility = View.VISIBLE
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.text).matches() -> {
                tv_warning.text = getString(R.string.invalid_email)
                tv_warning.visibility = View.VISIBLE
            }
            !et_password.text.all { it.isLetterOrDigit() } -> {
                tv_warning.text = getString(R.string.password_must_contain_Letters_and_digits_only)
                tv_warning.visibility = View.VISIBLE
            }
            et_password.text.length < 5 -> {
                tv_warning.text = getString(R.string.password_must_be_5_characters_long)
                tv_warning.visibility = View.VISIBLE
            }
        }
    }
}