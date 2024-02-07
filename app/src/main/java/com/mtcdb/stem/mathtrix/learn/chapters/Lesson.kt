package com.mtcdb.stem.mathtrix.learn.chapters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lesson(val name: String, val htmlFileName: String) : Parcelable
