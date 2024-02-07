package com.mtcdb.stem.mathtrix.learn

import java.io.Serializable

data class LearnModel (
    val id : Int?,
    val title : String? = null,
    //public val icon : Int,
    val html : String,
) : Serializable {
    companion object {
        private val TAG = LearnModel::class.java.simpleName
    }

    override fun toString(): String {
        return "$TAG($id, $title, $html)"
    }
}