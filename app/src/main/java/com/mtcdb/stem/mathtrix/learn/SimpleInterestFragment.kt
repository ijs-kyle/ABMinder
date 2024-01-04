package com.mtcdb.stem.mathtrix.learn

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mtcdb.stem.mathtrix.R

class SimpleInterestFragment : Fragment() {

    companion object {
        public val TAG : String = SimpleInterestFragment::class.java.getSimpleName()
        public val MODEL : String = "LearningModel"

        public fun newInstance(model : LearningModel) : SimpleInterestFragment {
            val bundle : Bundle = Bundle()
            bundle.putSerializable(MODEL, model)
            val fragment : SimpleInterestFragment = SimpleInterestFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var formulaTextView : TextView? = null
    private var unitsTextView : TextView? = null
    private var rateTextView : TextView? = null
    private var estimatingTextView : TextView? = null
    private var shortcutsTextView : TextView? = null
    private var solutionTextView : TextView? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView (inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_simple_interest, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formulaTextView = view.findViewById<TextView>(R.id.text_view_formula)
        unitsTextView = view.findViewById<TextView>(R.id.text_view_units)
        rateTextView = view.findViewById<TextView>(R.id.text_view_rate)
        estimatingTextView = view.findViewById<TextView>(R.id.text_view_estimating)
        shortcutsTextView = view.findViewById<TextView>(R.id.text_view_shortcuts)
        solutionTextView = view.findViewById<TextView>(R.id.text_view_solution)
    }

    override fun onResume() {
        super.onResume()
        //val settings = webView.settings
        //val res = resources
        //settings.defaultFontSize = 12
        val model : LearningModel? = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) arguments?.getSerializable(MODEL, LearningModel::class.java)
        else arguments?.getSerializable(MODEL) as? LearningModel
        //webView.loadDataWithBaseURL(null,resources.getString(R.string.tos_text),"text/html","utf-8",null)
        formulaTextView?.setText(model?.formula)
        unitsTextView?.setText(model?.units)
        rateTextView?.setText(model?.rate)
        estimatingTextView?.setText(model?.estimate)
        shortcutsTextView?.setText(model?.shortcut)
        solutionTextView?.setText(model?.solution)
    }
}