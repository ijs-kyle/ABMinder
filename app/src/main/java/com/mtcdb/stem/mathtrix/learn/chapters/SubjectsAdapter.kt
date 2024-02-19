package com.mtcdb.stem.mathtrix.learn.chapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.R

class SubjectsAdapter(
    private var subjects: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder>() {

    inner class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectTextView: TextView = itemView.findViewById(R.id.subjectTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val subject = subjects[position]
                    onItemClick(subject)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjects[position]
        holder.subjectTextView.text = subject
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    // Method to update the list of subjects
    @SuppressLint("NotifyDataSetChanged")
    fun updateSubjects(newSubjects: List<String>) {
        subjects = newSubjects
        notifyDataSetChanged()
    }
}
