package com.mtcdb.stem.mathtrix.learn.chapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.R

class ChaptersAdapter(
    private val chaptersList: List<String>,
    private val onChapterSelected: (String) -> Unit
) : RecyclerView.Adapter<ChaptersAdapter.ChapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return ChapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterViewHolder, position: Int) {
        val chapter = chaptersList[position]
        holder.bind(chapter)
        holder.itemView.setOnClickListener { onChapterSelected(chapter) }
    }

    override fun getItemCount(): Int = chaptersList.size

    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chapterTextView: TextView = itemView.findViewById(R.id.chapterTextView)

        fun bind(chapter: String) {
            chapterTextView.text = chapter
        }
    }
}
