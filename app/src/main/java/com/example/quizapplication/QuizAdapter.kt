package com.example.quizapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class QuizAdapter(private val mQuizzes: List<Question>) :
//    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {
//    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
//        val titleTextView: TextView = listItemView.findViewById(R.id.tv_title)
//        val descTextView: TextView = listItemView.findViewById(R.id.tv_desc)
//        val backgroundImageView: ImageView = listItemView.findViewById(R.id.iv_background)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        val quizView = inflater.inflate(R.layout.item_quiz, parent, false)
//        return ViewHolder(quizView)
//    }
//
//    override fun onBindViewHolder(holder: QuizAdapter.ViewHolder, position: Int) {
//        val question: Question = mQuizzes[position]
//        val textView = holder.titleTextView
//        textView.text = question.name
//        holder.descTextView.text = question.desc
//        holder.backgroundImageView.setImageResource(question.image)
//    }
//
//    override fun getItemCount(): Int {
//        return mQuizzes.size
//    }
//}