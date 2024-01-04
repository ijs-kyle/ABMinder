package com.mtcdb.stem.mathtrix.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

class LearningMenuFragment : Fragment(), LearningListener {

    companion object {
        public val TAG : String = LearningMenuFragment::class.java.getSimpleName()

        public fun newInstance() : LearningMenuFragment = LearningMenuFragment()
    }

    private lateinit var mainActivity : MainActivity
    //private val viewModel : MainViewModel by activityViewModels<MainViewModel>()
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = requireActivity() as MainActivity
    }
    override fun onCreateView (inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_learning_menu, container, false)

    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = LearningAdapter(this@LearningMenuFragment,
            listOf<LearningModel>(
                LearningModel(0, "Sample 1", R.drawable.ic_launcher_foreground),
                LearningModel(1, "Sample 2", R.drawable.ic_launcher_foreground),
                LearningModel(2, "Sample 3", R.drawable.ic_launcher_foreground),
                LearningModel(3, "Sample 4", R.drawable.ic_launcher_foreground),
                LearningModel(4, "Sample 5", R.drawable.ic_launcher_foreground),
            )
        )
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onClickModel(model : LearningModel, position: Int) {
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SimpleInterestFragment.newInstance())
            //.addToBackStack(SimpleInterestFragment.TAG)
            .commit()
        mainActivity.toolbar.title = SimpleInterestFragment.TAG
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}