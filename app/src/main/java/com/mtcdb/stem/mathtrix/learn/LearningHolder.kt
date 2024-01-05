package com.mtcdb.stem.mathtrix.learn

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.R

public class LearningHolder : RecyclerView.ViewHolder {

    private val listener : LearningListener
    private val title : TextView
    private val icon : ImageView

    constructor(listener : LearningListener, view : View) : super(view) {
        this.listener = listener
        this.title = itemView.findViewById<TextView>(R.id.text_view_title)
        this.icon = itemView.findViewById<ImageView>(R.id.image_view_icon)
    }

    public fun bindDataToViewHolder(model : LearningModel, position : Int) {
        title.setText(model.title)
        icon.setImageResource(model.icon)
        itemView.setOnClickListener {
            listener.onClickModel(model = model, position = position)
        }
    }
}