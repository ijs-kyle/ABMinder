package com.mtcdb.stem.mathtrix.learn

public data class LearningModel (
    val id : Int?,
    val title : String? = null,
    val icon : Int
) {
    companion object {
        private val TAG = LearningModel::class.java.getSimpleName()
    }

    override fun toString(): String {
        return "$TAG($id, $title, $icon)" ?: super.toString()
    }
}
