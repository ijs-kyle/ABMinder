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

class LessonsFragment : Fragment() {

    companion object {
        private const val ARG_SELECTED_CHAPTER = "selectedChapter"

        fun newInstance(selectedChapter: String): LessonsFragment {
            val fragment = LessonsFragment()
            val args = Bundle()
            args.putString(ARG_SELECTED_CHAPTER, selectedChapter)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var selectedChapter: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedChapter = arguments?.getString(ARG_SELECTED_CHAPTER) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewLessons)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter =
            LessonsAdapter(getLessonsForChapter(selectedChapter), ::onLessonSelected)

        return view
    }

    private fun onLessonSelected(lesson: Lesson) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.fragment_container,
            WebViewFragment.newInstance(arguments?.getString(ARG_SELECTED_CHAPTER), lesson)
        )
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun getLessonsForChapter(chapter: String): List<Lesson> {
        return when (chapter) {
            "Fundamental Operations on Fractions, Decimals, and Percentage" -> listOf(
                Lesson("Fundamental Operations", "fundamental_operations.html"),
                Lesson("Addition and Subtraction of Fractions", "addition_subtraction.html"),
                Lesson("Multiplication and Division of Fractions", "multiplication_division.html"),
                Lesson("Decimals and their Operations", "decimal_operations.html"),
                Lesson("Addition and Subtraction of Decimals", "addsub_decimals.html"),
                Lesson("Multiplication and Division of Decimals", "muldiv_decimals.html"),
                Lesson("Percentage", "percent.html"),
            )

            "Ratio and Proportion" -> listOf(
                Lesson("Ratio and Proportion", "ratio.html"),
                Lesson("Formulating and Solving Problems", "formulating_ratio.html"),
                Lesson("Using Proportions", "proportions.html"),
            )

            "Buying and Selling" -> listOf(
                Lesson("Introduction to Buying and Selling", "buying_and_selling.html"),
                Lesson("Pricing", "pricing.html"),
                Lesson("Profit and Loss", "profit_loss.html"),
                Lesson("Mark Up and Profit Margin", "lesson_3.3.html"),
                Lesson("Mark On", "mark_on.html"),
                Lesson("Mark Down", "mark_down.html"),
                Lesson("Commissions and Interest", "commissions_interest.html"),
            )

            "Salaries and Wages" -> listOf(
                Lesson("Salaries and Wages", "salaries_wages.html"),
                Lesson("Computing Gross and Net Earnings", "computing_earnings.html"),
                Lesson("Benefits and Deductions", "benefits_deductions.html"),
                Lesson("Overtime and E-spreadsheet", "overtime_spreadsheet.html"),
                Lesson("Taxation", "taxation.html"),

                )

            "Presentation and Analysis of Business Data" -> listOf(
                Lesson("Business Data and Forms", "business_data_forms.html"),
                Lesson("Graphs, Charts, and Tables", "graphs_charts_tables.html"),
                Lesson("Analysis and Interpretation", "analysis_interpretation.html"),
                Lesson("Software Applications", "software_applications.html"),
            )

            else -> emptyList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val mainActivity = requireActivity() as MainActivity
        mainActivity.toolbar.title = "Learn"
    }
}
