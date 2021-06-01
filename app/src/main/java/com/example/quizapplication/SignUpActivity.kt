package com.example.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.et_password
import kotlinx.android.synthetic.main.activity_sign_up.et_username
import kotlinx.android.synthetic.main.activity_sign_up.tv_warning

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tv_login_here.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_sign_up.setOnClickListener {
            // Set default colors for all edit texts
            setDefaults()

            // check if data provided by user is valid or not
            if (!isDataValid()) {
                tv_warning.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val user = User(
                username = et_username.text.toString(),
                password = et_password.text.toString(),
                email = et_email.text.toString()
            )

            val databaseHandler = DatabaseHandler.getInstance(this)

            // check if user already exists
            when (databaseHandler?.signupUser(user)) {
                -1 -> { // user already exists
                    tv_warning.text = getString(R.string.username_already_taken)
                    tv_warning.visibility = View.VISIBLE
                }
                -2 -> { // email already registered
                    tv_warning.text = getString(R.string.email_already_registered)
                    tv_warning.visibility = View.VISIBLE
                }
                -3 -> { // exception
                    tv_warning.text = getString(R.string.something_went_wrong)
                    tv_warning.visibility = View.VISIBLE
                }
                else -> { // sign up successful
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun isDataValid(): Boolean {
        val redColor = ContextCompat.getColor(this, R.color.red)

        // username should have letters and digits only
        if (!et_username.text!!.all { it.isLetterOrDigit() }) {
            tv_warning.text = getString(R.string.username_must_contain_letter_and_digits_only)
            et_username.setTextColor(redColor)
            return false
        }

        // username must be longer than 4 characters
        if (et_username.text!!.length < 5) {
            tv_warning.text = getString(R.string.username_must_have_at_least_5_characters)
            et_username.setTextColor(redColor)
            return false
        }

        // Email entered matches email pattern
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.text.toString()).matches()) {
            tv_warning.text = getString(R.string.invalid_email)
            et_email.setTextColor(redColor)
            return false
        }

        // password must be longer than 4 characters
        if (et_password.text!!.length < 5) {
            tv_warning.text = getString(R.string.password_must_have_at_least_5_characters)
            et_password.setTextColor(redColor)
            return false
        }

        return true
    }

    private fun setDefaults() {
        val blackColor = ContextCompat.getColor(this, R.color.black)
        et_username.setTextColor(blackColor)
        et_username.setTextColor(blackColor)
        et_email.setTextColor(blackColor)
        et_password.setTextColor(blackColor)
        tv_warning.visibility = View.INVISIBLE
    }
}