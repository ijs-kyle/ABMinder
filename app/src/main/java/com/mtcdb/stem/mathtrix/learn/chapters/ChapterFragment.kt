package com.mtcdb.stem.mathtrix.learn.chapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

class ChaptersFragment : Fragment() {

    private val chaptersList = listOf(
        "Introduction to Business Mathematics",
        "Fundamental Operations on Fractions, Decimals, and Percentage",
        "Ratio and Proportion",
        "Buying and Selling",
        "Salaries and Wages",
        "Presentation and Analysis of Business Data",
    )

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chapters, container, false)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewChapters)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ChaptersAdapter(chaptersList, ::onChapterSelected)

       mainActivity = (activity as? MainActivity)!!

        return view
    }

    private fun onChapterSelected(chapter: String) {
        // Implement navigation to LessonsFragment with the selected chapter
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, LessonsFragment.newInstance(chapter))
        transaction.addToBackStack(null)
        transaction.commit()
        mainActivity.toolbar.title =chapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.toolbar.title = getString(R.string.app_name)
    }

}
