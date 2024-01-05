package com.mtcdb.stem.mathtrix.learn

import java.io.Serializable

public data class LearningModel (
    val id : Int?,
    val title : String? = null,
    val icon : Int,
    val formula : String,
    val units : String,
    val rate : String,
    val estimate : String,
    val shortcut : String,
    val solution : String,
) : Serializable {
    companion object {
        private val TAG = LearningModel::class.java.getSimpleName()
    }

    override fun toString(): String {
        return "$TAG($id, $title, $icon, $formula, $units, $rate, $estimate, $shortcut, $solution)" ?: super.toString()
    }
}
