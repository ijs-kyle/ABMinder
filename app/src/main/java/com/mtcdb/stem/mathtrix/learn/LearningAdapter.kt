package com.mtcdb.stem.mathtrix.learn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.R

public class LearningAdapter : RecyclerView.Adapter<LearningHolder> {

    companion object {
        private val TAG = LearningAdapter::class.java.getSimpleName()
    }

    private val listener : LearningListener
    private val list : MutableList<LearnModel> = mutableListOf<LearnModel>()

    constructor(listener : LearningListener) : super() {
        this.listener = listener
        setHasStableIds(false)
    }

    constructor(listener : LearningListener, list : List<LearnModel>) : super() {
        this.listener = listener
        setItems(list)
        setHasStableIds(false)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : LearningHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.getContext())
        val view : View = layoutInflater.inflate(R.layout.cell_learning_menu, parent, false)
        return LearningHolder(listener, view)
    }

    override fun onBindViewHolder(holder : LearningHolder, position : Int) {
        holder.bindDataToViewHolder(model = list.get(position), position = position)
    }

    override fun getItemCount() : Int {
        return list.size
    }

    override fun getItemId(position : Int) : Long {
        return super.getItemId(position)
    }

    public fun setItems(items : List<LearnModel>) {
        list.clear()
        list.addAll(items)
    }
}