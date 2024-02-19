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

            //BUSINESS MATHEMATICS

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
                Lesson("Single Trade Discounts and Discount Series", "trade_discount.html"),
                Lesson("Simple Interest", "simple_interest.html"),
                Lesson("Compound Interest", "compound_interest.html"),
            )

            "Salaries and Wages" -> listOf(
                Lesson("Salaries and Wages", "salaries_wages.html"),
                Lesson("Benefits and Deductions", "benefits_deductions.html"),
                Lesson("Computing Gross and Net Earnings", "computing_earnings.html"),
                Lesson("Commissions", "commissions.html"),
            )

            "Presentation and Analysis of Business Data" -> listOf(
                Lesson("Business Data", "business_data_forms.html"),
                Lesson("Levels of Measurement", "measurement_levels.html"),
                Lesson("Measures of Central Tendency", "central_tendency.html"),
                Lesson("Presentations and Analysis of Data", "presentations_analysis.html"),
            )

            //BUSINESS FINANCE

            "Introduction to Business and Finance" -> listOf(
                Lesson("Business Finance", "business_finance.html"),
                Lesson("Financial Management", "financial_management.html"),
            )

            "Financial Statement Preparation, Analysis, and Interpretation" -> listOf(
                Lesson("Accounting", "accounting.html"),
                Lesson("Liquidity", "liquidity.html"),
                Lesson("Profitability", "profitability.html"),
                Lesson("Efficiency", "efficiency.html"),
                Lesson("Financial Leverage", "financial_leverage.html"),
                Lesson("Case Analysis", "case_analysis.html"),
                Lesson("Horizontal and Vertical Analysis", "hover_analysis.html"),
            )

            "Financial Planning Tools and Concepts" -> listOf(
                Lesson("Planning", "planning.html"),
                Lesson("Event Planning", "event_planning.html"),
                Lesson("Budgeting", "budgeting.html"),
                Lesson("Working Capital Assets", "working_capital_assets.html"),
                Lesson("Holding Cash", "holding_cash.html"),
            )

            "Sources and Uses of Short-Term and Long-Term Funds" -> listOf(
                Lesson("Financing", "financing.html"),
                Lesson("Short-Term and Long-Term Financing", "short_long_financing.html"),
                Lesson("Loans", "loans.html"),
            )

            "Long Term Financial Concepts" -> listOf(
                Lesson("Interest", "interest.html"),
                Lesson("Future Value and Present Value", "values.html"),
                Lesson("Annuity", "annuity.html"),
                Lesson("Risk and Return Trade-off", "risk_and_return.html"),
                Lesson("Loan Amortization", "loan_amortization.html"),
            )

            "Investments" -> listOf(
                Lesson("Investments", "investments.html"),
                Lesson("Risk", "risk.html"),
            )

            // FABM 1
            "Introduction to Accounting" -> listOf()
            "Branches of Accounting" -> listOf()
            "Users of Accounting Information" -> listOf()
            "Forms of Business Organization" -> listOf()
            "Types of Business According to Activities" -> listOf()
            "Accounting Concepts and Principles" -> listOf()
            "The Accounting Equation" -> listOf()

            else -> emptyList()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val mainActivity = requireActivity() as MainActivity
        mainActivity.toolbar.title = selectedChapter
    }
}
