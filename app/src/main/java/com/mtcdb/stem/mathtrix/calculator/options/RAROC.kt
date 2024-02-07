package com.mtcdb.stem.mathtrix.calculator.options

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class RAROCFragment : Fragment() {

    private lateinit var revenuesEditText: TextInputEditText
    private lateinit var costsEditText: TextInputEditText
    private lateinit var expectedLossesEditText: TextInputEditText
    private lateinit var economicCapitalEditText: TextInputEditText
    private lateinit var calculateButton: MaterialButton
    private lateinit var resultTextView: MaterialTextView
    private lateinit var description: TextView
    private lateinit var buttonSolution: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            com.calculator.calculatoroptions.R.layout.fragment_raroc,
            container,
            false
        )

        // Initialize UI components
        revenuesEditText =
            view.findViewById(com.calculator.calculatoroptions.R.id.editTextRevenuesRAROC)
        costsEditText =
            view.findViewById(com.calculator.calculatoroptions.R.id.editTextCostsRAROC)
        expectedLossesEditText =
            view.findViewById(com.calculator.calculatoroptions.R.id.editTextExpectedLossesRAROC)
        economicCapitalEditText =
            view.findViewById(com.calculator.calculatoroptions.R.id.editTextEconomicCapitalRAROC)
        calculateButton =
            view.findViewById(com.calculator.calculatoroptions.R.id.buttonCalculateRAROC)
        resultTextView = view.findViewById(com.calculator.calculatoroptions.R.id.textViewRAROCResult)
        description = view.findViewById(com.calculator.calculatoroptions.R.id.descriptionRAROC)
        buttonSolution = view.findViewById(com.calculator.calculatoroptions.R.id.buttonSolutionRAROC)

        calculateButton.setOnClickListener {
            calculateRAROC()
        }

        val rarocDescription = """
    Risk-Adjusted Return on Capital (RAROC) is a financial metric used to evaluate the performance of a business unit or investment, taking into account the risks involved. It is calculated by dividing the net income (revenues - costs - expected losses) by the economic capital.

    The formula for calculating RAROC is:

    RAROC = (Revenues - Costs - Expected Losses) / Economic Capital

    Key Components:
    - Revenues: All income generated by the business unit or investment
    - Costs: All expenses incurred in generating that income
    - Expected Losses: The best estimate of the losses that are likely to occur due to the inherent risks of the business unit or investment
    - Economic Capital: The amount of capital required to support the risks of the business unit or investment

    Interpretation:
    A higher RAROC indicates a higher return on capital, adjusted for risk. This can be used to compare the performance of different business units or investments, and to make informed decisions about where to allocate resources.

    Examples:
    For example, let's say a bank has a business unit that generates $10 million in revenue, incurs $5 million in costs, and has an expected loss of $1 million. The economic capital required to support the risks of this business unit is $5 million. Using the RAROC formula, we can calculate the RAROC as follows:

    RAROC = ($10 million - $5 million - $1 million) / $5 million = 1.4 or 140%

    This means that the business unit is generating a return on capital of 140%, adjusted for risk.

    """.trimIndent()

        description.text = rarocDescription

        buttonSolution.setOnClickListener {
            val revenues = revenuesEditText.text.toString().toDoubleOrNull() ?: 0.0
            val costs = costsEditText.text.toString().toDoubleOrNull() ?: 0.0
            val expectedLosses = expectedLossesEditText.text.toString().toDoubleOrNull() ?: 0.0
            val economicCapital =
                economicCapitalEditText.text.toString().toDoubleOrNull() ?: 0.0

            val raroc = (revenues - costs - expectedLosses) / economicCapital

            resultTextView.text =
                getString(com.calculator.calculatoroptions.R.string.raroc_result, raroc * 100)
            showExplanationDialog(revenues, costs, expectedLosses, economicCapital, raroc)
        }

        return view
    }

    private fun calculateRAROC() {
        val revenues = revenuesEditText.text.toString().toDoubleOrNull() ?: 0.0
        val costs = costsEditText.text.toString().toDoubleOrNull() ?: 0.0
        val expectedLosses = expectedLossesEditText.text.toString().toDoubleOrNull() ?: 0.0
        val economicCapital =
            economicCapitalEditText.text.toString().toDoubleOrNull() ?: 0.0

        val raroc = (revenues - costs - expectedLosses) / economicCapital

        resultTextView.text =
            getString(com.calculator.calculatoroptions.R.string.raroc_result, raroc * 100)
        showExplanationDialog(revenues, costs, expectedLosses, economicCapital, raroc)
    }

    private fun showExplanationDialog(
        revenues: Double,
        costs: Double,
        expectedLosses: Double,
        economicCapital: Double,
        raroc: Double
    ) {
        val explanation = """
            Risk-Adjusted Return on Capital (RAROC) is a financial metric used to evaluate the performance of a business unit or investment, taking into account the risks involved. It is calculated by dividing the net income (revenues - costs - expected losses) by the economic capital.
            
            The formula for calculating RAROC is:
            
            RAROC = (Revenues - Costs - Expected Losses) / Economic Capital
            
            Given:
                Revenues = $revenues
                Costs = $costs
                Expected Losses = $expectedLosses
                Economic Capital = $economicCapital
                
            Solution:
                RAROC = ($revenues - $costs - $expectedLosses) / $economicCapital
                RAROC = ${raroc * 100}%
                
            Therefore, the Risk-Adjusted Return on Capital (RAROC) is ${raroc * 100}%.
        """.trimIndent()

        // Display explanation in a custom dialog
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Risk-Adjusted Return on Capital (RAROC) Calculation")
            .setMessage(explanation)
            .setPositiveButton("OK", null)
            .show()
    }
}
