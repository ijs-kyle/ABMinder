package com.mtcdb.stem.mathtrix.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

class LearnFragment : Fragment(), LearningListener {

    companion object {
        val TAG: String = LearnFragment::class.java.simpleName

        fun newInstance(): LearnFragment = LearnFragment()
    }

    private var mainActivity: MainActivity? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = requireActivity() as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learning_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val learnModels = listOf(
            LearnModel(0, title = "Simple Interest", html = "simple_interest.html"),
            LearnModel(1, title = "Compound Interest", html = "compound_interest.html"),
            LearnModel(3, title = "Profit Margin", html = "profit_margin.html"),
            LearnModel(2, title = "Net Profit Value", html = "net_profit_value.html"),
            LearnModel(4, title = "Return of Interest", html = "return_of_interest.html"),
            LearnModel(5, title = "Time Value of Money", html = "time_value_of_money.html"),
            LearnModel(
                6,
                title = "Discounted Cash Flow Analysis",
                html = "discounted_cash_flow.html"
            ),
            LearnModel(8, title = "Gross Domestic Product (GDP)", html = "gdp.html"),
            LearnModel(9, title = "Discounted Cash Flow (DCF)", html = "dcf.html"),
            LearnModel(
                10,
                title = "Operating Cash Flow Ratio",
                html = "operating_cash_flow_ratio.html"
            ),
            LearnModel(11, title = "Inventory Turnover", html = "inventory_turnover.html"),
            LearnModel(12, title = "Return on Assets (ROA)", html = "return_on_assets.html"),
            LearnModel(13, title = "Average Collection Period", html = "ACP.html"),
            LearnModel(14, title = "Payback Period", html = "payback_period.html"),
            LearnModel(15, title = "Risk-Adjusted Return on Capital (RAROC)", html = "raroc.html"),
            LearnModel(16, title = "Working Capital Ratio", html = "working_capital_ratio.html"),
            LearnModel(17, title = "Time Series Analysis", html = "time_series_analysis.html"),
            LearnModel(18, title = "Quick Ratio", html = "quick_ratio.html"),
            LearnModel(19, title = "Amortization", html = "amortization.html"),
            LearnModel(
                20,
                title = "Inflation-adjusted Return",
                html = "inflation_adjusted_return.html"
            ),
            LearnModel(21, title = "Asset", html = "asset.html"),
            LearnModel(22, title = "Accounting Equation", html = "accounting_equation.html"),
            LearnModel(23, title = "Business Ethics", html = "business_ethics.html"),
            LearnModel(24, title = "Capital", html = "capital.html"),
            LearnModel(25, title = "Compound Annual Growth Rate (CAGR)", html = "cagr.html"),
            LearnModel(26, title = "Capitalism", html = "capitalism.html"),
            LearnModel(17, title = "Equity", html = "equity.html"),
            LearnModel(17, title = "Cost Of Goods Sold (COGS)", html = "cogs.html"),
            LearnModel(18, title = "Finance", html = "finance.html"),
            LearnModel(1, title = "Gordon Growth Model", html = "ggm.html"),
            LearnModel(1, title = "High-Low Method", html = "highlow_method.html"),
            LearnModel(1, title = "Home Equity Loan", html = "home_equity_loan.html"),
            LearnModel(1, title = "Income", html = "income.html"),
            LearnModel(1, title = "Inflation", html = "inflation.html"),
            LearnModel(1, title = "Insurance", html = "insurance.html"),
            LearnModel(1, title = "Kaizen", html = "kaizen.hml"),
            LearnModel(1, title = "Corporate Governance", html = "corporate_governance.html"),
            LearnModel(1, title = "Fixing", html = "fixing.html"),
            LearnModel(1, title = "Law of Demand", html = "law_of_demand.html"),
            LearnModel(1, title = "Financial Leverage", html = "leverage.html"),
            LearnModel(1, title = "Rule Of 72", html = "rule_of_72.html"),
            LearnModel(1, title = "Return On Equity", html = "ROE.html"),
            LearnModel(1, title = "Real State", html = "real_state.html"),
            LearnModel(1, title = "Real State Investments Trusts", html = "REIT.html"),
            LearnModel(1, title = "Rate Of Return", html = "rate_of_return.html"),
            LearnModel(1, title = "Yield Curve", html = "yield_curve.html"),
            LearnModel(1, title = "Year End Bonus", html = "YEB.html"),
            LearnModel(1, title = "Weighted Average Cost of Capital (WACC)", html = "WACC.html"),
            LearnModel(1, title = "Wholesale Price Index (WPI)", html = "WPI.html"),
            LearnModel(1, title = "Wash Sales", html = "wash_sale.html"),
            LearnModel(1, title = "Wealth Management", html = "wealth_management.html"),
            LearnModel(1, title = "World Trade Organization (WTO)", html = "WTO.html"),
            LearnModel(1, title = "Weighted Average", html = "weighted_average.html"),
            LearnModel(1, title = "Withholding Tax", html = "withholding_tex.html"),
            LearnModel(1, title = "Value at Risk", html = "value_at_risk.html"),
            LearnModel(1, title = "Value Added", html = "value_added.html"),
            LearnModel(1, title = "Value Chain", html = "value_chain.html"),
            LearnModel(1, title = "Value Investing", html = "value_investing.html"),
            LearnModel(1, title = "Value Proposition", html = "value_proposition.html"),
            LearnModel(1, title = "Value-Added Tax", html = "value_added_tax.html"),
            LearnModel(1, title = "Variability", html = "variability.html"),
            LearnModel(1, title = "Variable Annuity", html = "variable_annuity.html"),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
            LearnModel(1, title = "", html = ""),
        )
        val sortedLearnModels = learnModels.sortedBy { it.title }

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.adapter = LearningAdapter(
            this@LearnFragment,
            sortedLearnModels
        )
    }

    override fun onClickModel(model: LearnModel, position: Int) {
        mainActivity?.supportFragmentManager?.beginTransaction()
            ?.add(
                R.id.fragment_container,
                TipsFragment.newInstance(model),
                TipsFragment::class.java.simpleName
            )
            ?.addToBackStack(TipsFragment.TAG)
            ?.commit()
        mainActivity?.toolbar?.title = model.title ?: TipsFragment.TAG
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity?.toolbar?.title = getString(R.string.app_name)
    }
}