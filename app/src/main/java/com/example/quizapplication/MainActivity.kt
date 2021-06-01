package com.example.quizapplication

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    // storing progress bar's progress level
    private var prg = 0

    // list of questions
    private var questionsList: ArrayList<Question> = ArrayList()

    // set of question numbers to be displayed to user
    private var questionIndexList = HashSet<Int>()

    // current question number
    private var currentQuestionIndex: Int = 0

    // store player score
    private var playerScore = 0

    // store number of questions skipped by player
    private var unattemptedQuestions = 0

    // option selected by player
    private var selectedOption = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set progress bar to 0
        updateProgressBar()

// ********************************************************************************************************************************************

        var questionArraySize = 0
        // get all question objects of selected category from json file and store in questionsList
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val questionArray = obj.getJSONArray(Constants.CATEGORY_SELECTED)
            questionArraySize = questionArray.length()
            for (i in 0 until questionArray.length()) {
                val questionJSONObject = questionArray.getJSONObject(i)
                val question = Question(
                    id = questionJSONObject.getInt("ques_id"),
                    question = questionJSONObject.getString("question"),
                    option1 = questionJSONObject.getString("option1"),
                    option2 = questionJSONObject.getString("option2"),
                    option3 = questionJSONObject.getString("option3"),
                    option4 = questionJSONObject.getString("option4"),
                    answer = questionJSONObject.getInt("answer"),
                    category = Constants.CATEGORY_SELECTED
                )
                questionsList.add(question)
            }
        } catch (ex: JSONException) {
            ex.printStackTrace()
        }

// ********************************************************************************************************************************************

        // Generate a set of random numbers which represent the question numbers to be displayed
        while (questionIndexList.size != 10) questionIndexList.add((0 until questionArraySize).random())
        // iterator to iterate over questionIndexList
        val itr = questionIndexList.iterator()

        // display the first question
        currentQuestionIndex = itr.next()
        updateQuestion()

// ********************************************************************************************************************************************

        // when NEXT btn is pressed set playerScore and display next question and increment progress bar
        btn_next.setOnClickListener {

            if (btn_next.text == getString(R.string.next)) {
                if (itr.hasNext()) currentQuestionIndex = itr.next()
                else {
                    val resultIntent = Intent(this, ResultActivity::class.java)
                    resultIntent.putExtra("player score", playerScore)
                    resultIntent.putExtra("unattempted questions", unattemptedQuestions)
                    startActivity(resultIntent)
                    finish()
                }
                updateQuestion()
                setDefaultBackgroundOfAllOptions()

                if (prg < 100) prg += 10
                updateProgressBar()
                btn_next.text = getString(R.string.submit)
            } else {
                // if some option was selected and selected option is correct then increment player score
                if ((selectedOption != 0)) {
                    if ((selectedOption == questionsList[currentQuestionIndex].answer)) {
                        playerScore++
                        selectedOption = 0
                    }
                } else unattemptedQuestions++
                showCorrectAnswer()
                btn_next.text = getString(R.string.next)
            }
        }


        // when any option is selected set all options back to default and change background of selected option
        tv_option1.setOnClickListener {
            setDefaultBackgroundOfAllOptions()
            tv_option1.setBackgroundResource(R.drawable.selected_option_bg)
            selectedOption = 1
        }

        tv_option2.setOnClickListener {
            setDefaultBackgroundOfAllOptions()
            tv_option2.setBackgroundResource(R.drawable.selected_option_bg)
            selectedOption = 2
        }

        tv_option3.setOnClickListener {
            setDefaultBackgroundOfAllOptions()
            tv_option3.setBackgroundResource(R.drawable.selected_option_bg)
            selectedOption = 3
        }

        tv_option4.setOnClickListener {
            setDefaultBackgroundOfAllOptions()
            tv_option4.setBackgroundResource(R.drawable.selected_option_bg)
            selectedOption = 4
        }

        // if clear response is selected then set all options to default and set selected option to 0
        tv_clear_response.setOnClickListener {
            setDefaultBackgroundOfAllOptions()
            selectedOption = 0
        }

        // if end quiz button is selected then show dialog box to user if selected yes then move back to Category activity
        btn_end_quiz.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("End Quiz")
            builder.setMessage("Are you sure you want to end this quiz? Your progress would not be saved.")
            builder.setPositiveButton("Yes") { _, _ ->
                run {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            builder.setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }
            val alertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }

    // shows the correct answer and if selected option is wrong then shows selected option as red/wrong
    private fun showCorrectAnswer() {
        when (questionsList[currentQuestionIndex].answer) {
            1 -> tv_option1.setBackgroundResource(R.drawable.correct_option_bg)
            2 -> tv_option2.setBackgroundResource(R.drawable.correct_option_bg)
            3 -> tv_option3.setBackgroundResource(R.drawable.correct_option_bg)
            else -> tv_option4.setBackgroundResource(R.drawable.correct_option_bg)
        }

        if (selectedOption != questionsList[currentQuestionIndex].answer) {
            when (selectedOption) {
                1 -> tv_option1.setBackgroundResource(R.drawable.incorrect_option_bg)
                2 -> tv_option2.setBackgroundResource(R.drawable.incorrect_option_bg)
                3 -> tv_option3.setBackgroundResource(R.drawable.incorrect_option_bg)
                4 -> tv_option4.setBackgroundResource(R.drawable.incorrect_option_bg)
            }
        }
    }

    private fun updateQuestion() {
        tv_question.text = questionsList[currentQuestionIndex].question
        tv_option1.text = questionsList[currentQuestionIndex].option1
        tv_option2.text = questionsList[currentQuestionIndex].option2
        tv_option3.text = questionsList[currentQuestionIndex].option3
        tv_option4.text = questionsList[currentQuestionIndex].option4
    }

    private fun loadJSONFromAsset(): String {
        var json: String = ""
        try {
            val inputStream: InputStream = assets.open("questions.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return json
    }

    private fun updateProgressBar() {
        progress_bar.progress = prg
        tv_progress_bar.text = "$prg%"
    }

    private fun setDefaultBackgroundOfAllOptions() {
        tv_option1.setBackgroundResource(R.drawable.default_option_bg)
        tv_option2.setBackgroundResource(R.drawable.default_option_bg)
        tv_option3.setBackgroundResource(R.drawable.default_option_bg)
        tv_option4.setBackgroundResource(R.drawable.default_option_bg)
    }
}