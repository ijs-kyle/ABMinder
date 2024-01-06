package com.mtcdb.stem.mathtrix.learn

import java.io.Serializable

public data class LearnModel (
    val id : Int?,
    val title : String? = null,
    val icon : Int,
    val html : String,
) : Serializable {
    companion object {
        private val TAG = LearnModel::class.java.getSimpleName()
    }

    override fun toString(): String {
        return "$TAG($id, $title, $icon, $html)" ?: super.toString()
    }
}