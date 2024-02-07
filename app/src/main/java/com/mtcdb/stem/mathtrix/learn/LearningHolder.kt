package com.mtcdb.stem.mathtrix.learn

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.R

class LearningHolder : RecyclerView.ViewHolder {

    private val listener : LearningListener
    private val title : TextView
    //private val icon : ImageView

    constructor(listener : LearningListener, view : View) : super(view) {
        this.listener = listener
        this.title = itemView.findViewById(R.id.text_view_title)
        //this.icon = itemView.findViewById<ImageView>(R.id.image_view_icon)
    }

    public fun bindDataToViewHolder(model : LearnModel, position : Int) {
        title.text = model.title
        //icon.setImageResource(model.icon)
        itemView.setOnClickListener {
            listener.onClickModel(model = model, position = position)
        }
    }
}