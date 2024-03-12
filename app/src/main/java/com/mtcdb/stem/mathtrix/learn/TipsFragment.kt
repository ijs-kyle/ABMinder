package com.mtcdb.stem.mathtrix.learn

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

class TipsFragment : Fragment() {

    companion object {
        val TAG : String = TipsFragment::class.java.getSimpleName()
        val MODEL : String = "LearningModel"

        fun newInstance(model : LearnModel) : TipsFragment {
            val bundle = Bundle()
            bundle.putSerializable(MODEL, model)
            val fragment = TipsFragment()
            fragment.setArguments(bundle)
            return fragment
        }
    }

    private var mainActivity : MainActivity? = null
    private var webView : WebView? = null
    private var model : LearnModel? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = requireActivity() as MainActivity
        model = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) arguments?.getSerializable(MODEL, LearnModel::class.java)
        else arguments?.getSerializable(MODEL) as? LearnModel
    }

    override fun onCreateView (inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.tips_fragment, container, false)
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.web_view)
        //webView?.settings?.setJavaScriptEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        webView?.loadUrl(getString(R.string.assets_path, model?.html))
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity?.toolbar?.title = getString(R.string.learn)
    }
}