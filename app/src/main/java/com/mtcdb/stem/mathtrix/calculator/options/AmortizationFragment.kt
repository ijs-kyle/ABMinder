package com.mtcdb.stem.mathtrix.calculator.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.pow

class AmortizationFragment : Fragment() {

    private lateinit var loanAmountEditText: EditText
    private lateinit var interestRateEditText: EditText
    private lateinit var loanTermEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var explainButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView =
            inflater.inflate(com.calculator.calculatoroptions.R.layout.fragment_amortization_schedule, container, false)

        loanAmountEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextLoanAmount)
        interestRateEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextInterestRate)
        loanTermEditText = rootView.findViewById(com.calculator.calculatoroptions.R.id.editTextLoanTerm)
        calculateButton = rootView.findViewById(com.calculator.calculatoroptions.R.id.buttonCalculateAmortization)
        resultTextView = rootView.findViewById(com.calculator.calculatoroptions.R.id.textViewAmortizationResult)
        descriptionTextView = rootView.findViewById(com.calculator.calculatoroptions.R.id.tVAmortizationDescription)
        explainButton = rootView.findViewById(com.calculator.calculatoroptions.R.id.explainAmortizationButton)

        calculateButton.setOnClickListener {
            calculateButton.clearFocus()
            calculateAmortization()
        }

        explainButton.setOnClickListener {
            showAmortizationExplanation()
        }

        // Set up description text
        val description = """
            Amortization Schedule calculates the monthly installment payments for a loan.
            
            The formula for calculating monthly payments is:
            
            M = P * [r(1+r)^n] / [(1+r)^n-1]
            
            Where:
            M = Monthly payment
            P = Principal loan amount
            r = Monthly interest rate (annual rate / 12 / 100)
            n = Total number of payments (loan term * 12)
            
            Example:
            Suppose you take out a loan of $10,000 with an annual interest rate of 5% for 3 years.
            
            Monthly interest rate (r) = 0.05 / 12
            Total number of payments (n) = 3 * 12
            Monthly payment (M) = $299.71
        """.trimIndent()

        descriptionTextView.text = description

        return rootView
    }

    private fun calculateAmortization() {
        val loanAmount = loanAmountEditText.text.toString().toDoubleOrNull() ?: 0.0
        val interestRate = interestRateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val loanTerm = loanTermEditText.text.toString().toDoubleOrNull() ?: 0.0

        val monthlyInterestRate = interestRate / 12 / 100
        val totalPayments = loanTerm * 12
        val denominator = (1 + monthlyInterestRate).pow(totalPayments) - 1

        val monthlyPayment = loanAmount * monthlyInterestRate * (1 + monthlyInterestRate).pow(
            totalPayments
        ) / denominator

        resultTextView.text = String.format("Monthly Payment: $%.2f", monthlyPayment)
    }

    private fun showAmortizationExplanation() {
        val loanAmount = loanAmountEditText.text.toString().toDoubleOrNull() ?: 0.0
        val interestRate = interestRateEditText.text.toString().toDoubleOrNull() ?: 0.0
        val loanTerm = loanTermEditText.text.toString().toDoubleOrNull() ?: 0.0

        val monthlyInterestRate = interestRate / 12 / 100
        val totalPayments = loanTerm * 12
        val denominator = Math.pow((1 + monthlyInterestRate), totalPayments) - 1

        val monthlyPayment = loanAmount * monthlyInterestRate * (1 + monthlyInterestRate).pow(
            totalPayments
        ) / denominator

        val explanation = """
            Given:
                Loan Amount = $loanAmount
                Annual Interest Rate = $interestRate%
                Loan Term = $loanTerm years
                
            Formula:
                M = P * [r(1+r)^n] / [(1+r)^n-1]
                
            Solution:
                Monthly Interest Rate (r) = $monthlyInterestRate
                Total Number of Payments (n) = $totalPayments
                Monthly Payment (M) = $monthlyPayment
                
            Therefore, the Monthly Payment is $${String.format("%.2f", monthlyPayment)}
        """.trimIndent()

        // Display explanation in a custom dialog
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Amortization Schedule")
            .setMessage(explanation)
            .setPositiveButton("OK", null)
            .show()
    }
}