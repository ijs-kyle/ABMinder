package com.mtcdb.stem.mathtrix.calculator

import TimeValueOfMoneyFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R
import com.mtcdb.stem.mathtrix.calculator.options.AmortizationFragment
import com.mtcdb.stem.mathtrix.calculator.options.AverageCollectionPeriodFragment
import com.mtcdb.stem.mathtrix.calculator.options.BreakEvenFragment
import com.mtcdb.stem.mathtrix.calculator.options.CompoundInterestFragment
import com.mtcdb.stem.mathtrix.calculator.options.DTERFragment
import com.mtcdb.stem.mathtrix.calculator.options.DiscountedCashFlowFragment
import com.mtcdb.stem.mathtrix.calculator.options.EPSFragment
import com.mtcdb.stem.mathtrix.calculator.options.GDPFragment
import com.mtcdb.stem.mathtrix.calculator.options.InflationAdjustedReturnFragment
import com.mtcdb.stem.mathtrix.calculator.options.InventoryTurnoverRatioFragment
import com.mtcdb.stem.mathtrix.calculator.options.NPVFragment
import com.mtcdb.stem.mathtrix.calculator.options.OperatingCashFlowRatioFragment
import com.mtcdb.stem.mathtrix.calculator.options.PaybackPeriodFragment
import com.mtcdb.stem.mathtrix.calculator.options.PercentageChangeFragment
import com.mtcdb.stem.mathtrix.calculator.options.ProfitMarginFragment
import com.mtcdb.stem.mathtrix.calculator.options.RAROCFragment
import com.mtcdb.stem.mathtrix.calculator.options.ROAFragment
import com.mtcdb.stem.mathtrix.calculator.options.ROIFragment
import com.mtcdb.stem.mathtrix.calculator.options.SavingsGoalFragment
import com.mtcdb.stem.mathtrix.calculator.options.SimpleInterestFragment
import com.mtcdb.stem.mathtrix.calculator.options.WACCFragment
import com.mtcdb.stem.mathtrix.calculator.options.WorkingCapitalFragment

class CalculatorOptionsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CalculationOptionAdapter
    private lateinit var searchView: SearchView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_calculator_options, container, false)

        searchView = rootView.findViewById(R.id.calculatorSearchView)
        val mainActivity = requireActivity() as MainActivity

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        recyclerView = rootView.findViewById(R.id.recyclerViewCalculationOptions)
        recyclerView.layoutManager = LinearLayoutManager(null)

        val calculationOptions = listOf(
            CalculationOption(
                "Compound Interest",
                "Compute interest on the initial principal and accumulated interest."
            ),
            CalculationOption(
                "Percentage Change",
                "Determine the percentage change between two values."
            ),
            CalculationOption(
                "Profit Margin",
                "Calculate the profit margin as a percentage of revenue."
            ),
            CalculationOption(
                "Amortization Schedule",
                "Generate a schedule of loan repayments with details on principal and interest."
            ),
            CalculationOption(
                "Net Present Value (NPV)",
                "Evaluate the profitability of an investment."
            ),
            CalculationOption(
                "Return on Investment (ROI)",
                "Measure the return on an investment as a percentage."
            ),
            CalculationOption(
                "Break-Even Point",
                "Find the point where revenue equals costs."
            ),
            CalculationOption(
                "Weighted Average Cost of Capital (WACC)",
                "Calculate the average rate of return a company is expected to provide to its investors."
            ),
            CalculationOption(
                "Time Value of Money (TVM)",
                "Evaluate the value of money over time."
            ),
            CalculationOption(
                "Discounted Cash Flow (DCF)",
                "Estimate the value of an investment based on its future cash flows."
            ),
            /*
            CalculationOption(
                "Earnings Per Share (EPS)",
                "Measure a company's profitability per outstanding share of common stock."
            ),
            CalculationOption(
                "Gross Domestic Product (GDP) Growth Rate",
                "Determine the rate at which a country's economy is growing."
            ),
            CalculationOption(
                "Debt to Equity Ratio",
                "Assess a company's financial leverage."),
            CalculationOption(
                "Operating Cash Flow Ratio",
                "Evaluate a company's ability to generate cash from its operations."
            ),
            CalculationOption(
                "Inventory Turnover Ratio",
                "Measure how many times a company's inventory is sold and replaced over a period."
            ),
            CalculationOption(
                "Return on Assets (ROA)",
                "Assess how efficiently a company uses its assets to generate earnings."
            ),
            CalculationOption(
                "Average Collection Period",
                "Calculate the average number of days it takes for a company to collect payments."
            ),
            CalculationOption(
                "Payback Period",
                "Determine the time it takes for an investment to generate cash equal to its initial cost."
            ),
            CalculationOption(
                "Risk-Adjusted Return on Capital (RAROC)",
                "Evaluate the return on an investment adjusted for its risk."
            ),
            CalculationOption(
                "Working Capital Ratio",
                "Assess a company's operational liquidity."
            ),
            CalculationOption(
                "Quick Ratio",
                "Measure a company's ability to meet its short-term obligations with its most liquid assets."
            ),
            CalculationOption(
                "Savings Goal Planner",
                "Plan and track progress toward a savings goal over time."
            ),
            CalculationOption(
                "Inflation Adjusted Return",
                "Calculate the real return on an investment after adjusting for inflation."
            ),
            CalculationOption(
                "Annuity Payment Calculator",
                "Determine regular payments for an annuity based on interest rate and time period."
            ),
            CalculationOption(
                "Depreciation Schedule",
                "Estimate the decrease in value of an asset over time."
            ), */
            CalculationOption(
                "Cost of Goods Sold (COGS)",
                "Calculate the direct costs of producing goods sold by a company."
            ),
            /*
            CalculationOption(
                "Equity Multiplier",
                "Assess the financial leverage of a company by measuring its equity multiplier."
            ),
            CalculationOption(
                "Dividend Discount Model (DDM)",
                "Estimate the fair value of a stock based on expected future dividends."
            ),
            CalculationOption(
                "Future Value of an Investment",
                "Compute the future value of an investment based on different compounding periods."
            ),
            CalculationOption(
                "Present Value of Cash Flows",
                "Evaluate the current value of future cash flows, considering the time value of money."
            ),
            CalculationOption(
                "Average Rate of Return",
                "Calculate the average rate of return on an investment over a specified time period."
            ),
            CalculationOption(
                "Capital Asset Pricing Model (CAPM)",
                "Determine the expected return on an investment considering risk and market conditions."
            ),
            CalculationOption(
                "Retention Ratio",
                "Calculate the percentage of earnings retained by a company."
            ),
            CalculationOption(
                "Economic Order Quantity (EOQ)",
                "Optimize order quantity to minimize total inventory costs."
            ),
            CalculationOption(
                "Coefficient of Variation (CV)",
                "Measure the relative variability of returns on an investment."
            ),
            CalculationOption(
                "Dividend Payout Ratio",
                "Assess the proportion of earnings paid out as dividends."
            ),
            CalculationOption(
                "Bond Duration",
                "Estimate the sensitivity of a bond's price to interest rate changes."
            ),
            CalculationOption(
                "Times Interest Earned Ratio",
                "Evaluate a company's ability to cover its interest payments."
            ),
            CalculationOption(
                "Internal Rate of Return (IRR)",
                "Determine the discount rate that makes the net present value of an investment zero."
            ),
            CalculationOption(
                "Cash Conversion Cycle (CCC)",
                "Evaluate the time it takes for a company to convert its investments in inventory to cash."
            ),
            CalculationOption(
                "Quick Asset Ratio",
                "Measure a company's ability to cover its current liabilities with its most liquid assets."
            ),
            CalculationOption(
                "Rule of 72",
                "Estimate the number of years required to double the value of an investment."
            ),*/
        )

        // Initialize the adapter
        adapter = CalculationOptionAdapter(calculationOptions) { selectedOption ->
            val calculatorFragment = when (selectedOption.name) {
                "Simple Interest" -> SimpleInterestFragment()
                "Compound Interest" -> CompoundInterestFragment()
                "Percentage Change" -> PercentageChangeFragment()
                "Profit Margin" -> ProfitMarginFragment()
                "Amortization Schedule" -> AmortizationFragment()
                "Net Present Value (NPV)" -> NPVFragment()
                "Return on Investment (ROI)" -> ROIFragment()
                "Break-Even Point" -> BreakEvenFragment()
                "Weighted Average Cost of Capital (WACC)" -> WACCFragment()
                "Time Value of Money (TVM)" -> TimeValueOfMoneyFragment()
                "Discounted Cash Flow (DCF)" -> DiscountedCashFlowFragment()
                "Earnings Per Share (EPS)" -> EPSFragment()
                "Gross Domestic Product (GDP) Growth Rate" -> GDPFragment()
                "Debt to Equity Ratio" -> DTERFragment()
                "Operating Cash Flow Ratio" -> OperatingCashFlowRatioFragment()
                "Inventory Turnover Ratio" -> InventoryTurnoverRatioFragment()
                "Return on Assets (ROA)" -> ROAFragment()
                "Average Collection Period" -> AverageCollectionPeriodFragment()
                "Payback Period" -> PaybackPeriodFragment()
                "Risk-Adjusted Return on Capital (RAROC)"-> RAROCFragment()
                "Working Capital Ratio" -> WorkingCapitalFragment()
                "Savings Goal Planner" -> SavingsGoalFragment()
                "Inflation Adjusted Return" -> InflationAdjustedReturnFragment()
                else -> null
            }

            if (calculatorFragment != null) {
                mainActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.calculator_container, calculatorFragment)
                    .addToBackStack(null)
                    .commit()
                searchView.clearFocus()
            }
        }
        recyclerView.adapter = adapter

        return rootView
    }


}
