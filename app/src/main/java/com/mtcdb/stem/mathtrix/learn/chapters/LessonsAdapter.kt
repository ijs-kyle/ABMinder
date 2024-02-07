package com.mtcdb.stem.mathtrix.learn.chapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.R

class LessonsAdapter(
    private val lessonsList: List<Lesson>,
    private val onLessonSelected: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessonsList[position]
        holder.bind(lesson)
        holder.itemView.setOnClickListener { onLessonSelected(lesson) }
    }

    override fun getItemCount(): Int = lessonsList.size

    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lessonTextView: TextView = itemView.findViewById(R.id.lessonTextView)

        fun bind(lesson: Lesson) {
            lessonTextView.text = lesson.name
        }
    }
}
