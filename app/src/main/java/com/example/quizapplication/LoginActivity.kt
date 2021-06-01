package com.example.quizapplication

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizapplication.Constants.CURRENT_USERID
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            // validate the data provided by user
            if (!isDataValid()) {
                tv_warning.text = getString(R.string.invalid_input)
                tv_warning.visibility = View.VISIBLE
                return@setOnClickListener
            }

            // make user object from input data
            val user =
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(et_username.text.toString()).matches()) {
                    User(
                        email = et_username.text.toString(),
                        password = et_password.text.toString()
                    )
                } else
                    User(
                        username = et_username.text.toString(),
                        password = et_password.text.toString()
                    )

            val databaseHandler = DatabaseHandler.getInstance(this)

            // If user exists then allow login else deny
            when (val userid = databaseHandler?.loginUser(user)) {
                -1L -> {
                    tv_warning.text = getString(R.string.user_not_found)
                    tv_warning.visibility = View.VISIBLE
                }
                -2L -> {
                    tv_warning.text = getString(R.string.something_went_wrong)
                    tv_warning.visibility = View.VISIBLE
                }
                else -> {
                    // Set user id and continue with next activity
                    CURRENT_USERID = userid!!
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        tv_Signup_here.setOnClickListener {
            // open sign up activity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Returns false if data provided by user is invalid
    private fun isDataValid(): Boolean {
        if ((et_username.text!!.length >= 5) and (et_password.text!!.length >= 5)) return true
        return false
    }
}