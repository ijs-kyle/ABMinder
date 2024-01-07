package com.mtcdb.stem.mathtrix.learn

import java.io.Serializable

public data class LearnModel (
    public val id : Int?,
    public val title : String? = null,
    //public val icon : Int,
    public val html : String,
) : Serializable {
    companion object {
        private val TAG = LearnModel::class.java.getSimpleName()
    }

    override fun toString(): String {
        return "$TAG($id, $title, $html)" ?: super.toString()
    }
}