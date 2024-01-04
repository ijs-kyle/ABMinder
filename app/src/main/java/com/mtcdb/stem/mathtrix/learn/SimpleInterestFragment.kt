package com.mtcdb.stem.mathtrix.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mtcdb.stem.mathtrix.R

class SimpleInterestFragment : Fragment() {

    companion object {
        public val TAG : String = SimpleInterestFragment::class.java.getSimpleName()

        public fun newInstance() : SimpleInterestFragment = SimpleInterestFragment()
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView (inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_simple_interest, container, false)

    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}