package com.mtcdb.stem.mathtrix.quiz.database

open class QuizDataPopulator(private val dbHelper : QuizDatabaseHelper) {

    fun populateQuizData() {

        populateEasyQuestions()
        populateEasyQuestionsBUSINESS()
        populateEasyQuestionsFABM()
        populateEasyQuestionsECONOMICS()
        populateMediumQuestions()
        populateMediumQuestionsBUSINESS()
        populateMediumQuestionsFABM()
        populateMediumQuestionsECONOMICS()
        populateHardQuestions()
        populateHardQuestionsBUSINESS()
        populateHardQuestionsFABM()
        populateHardQuestionsECONOMICS()
    }

    private fun insertQuizData(
        subject : String,
        question : String,
        options : String,
        correctAnswerIndex : Int,
        difficultyLevel : String,
    ) {
        dbHelper.insertQuizData(subject, question, options, correctAnswerIndex, difficultyLevel)
    }

    private fun populateEasyQuestions() {

        insertQuizData(
            "Business Mathematics",
            "What is the formula for calculating gross profit?",
            "A. Revenue - Cost of Goods Sold, B. Revenue / Cost of Goods Sold, C. Revenue + Cost of Goods Sold, D. Revenue x Cost of Goods Sold",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "What is the formula for calculating net profit?",
            "A. Gross Profit - Expenses, B. Gross Profit / Expenses, C. Gross Profit + Expenses, D. Gross Profit x Expenses",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "What is the formula for calculating return on investment (ROI)?",
            "A. (Net Profit / Cost of Investment) x 100, B. (Gross Profit / Cost of Investment) x 100, C. (Revenue / Cost of Investment) x 100, D. (Expenses / Cost of Investment) x 100",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "What is the formula for calculating contribution margin?",
            "A. (Revenue - Variable Costs) / Revenue, B. (Fixed Costs / Revenue) x 100, C. (Variable Costs / Revenue) x 100, D. (Revenue - Fixed Costs) / Revenue",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "What is the formula to calculate simple interest?",
            "A. PRT, B. P/R, C. PT, D. PR/T",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "Define 'Profit Margin'.",
            "A. The total revenue minus total expenses, B. The percentage of revenue that represents profit, C. The difference between selling price and cost price, D. The total profit divided by the number of units sold",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "What does the term 'Break-even Point' represent in business?",
            "A. The point where total revenue equals total expenses, B. The point where total revenue exceeds total expenses, C. The point where total expenses are minimized, D. The point where total profit is maximized",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "Define 'Depreciation'.",
            "A. The process of increasing the value of an asset, B. The process of calculating the value of a liability, C. The process of allocating the cost of an asset over its useful life, D. The process of reducing the value of an asset over time",
            3, // Index of correct answer (D)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "What is the formula for calculating the break-even point in units?",
            "A. Fixed Costs / (Selling Price per Unit - Variable Costs per Unit), B. Fixed Costs / Selling Price per Unit, C. Variable Costs per Unit / (Selling Price per Unit - Variable Costs per Unit), D. Selling Price per Unit / Variable Costs per Unit",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Mathematics",
            "What is the average of a set of numbers called?",
            "A. Median, B. Mean, C. Mode, D. Range",
            0,
            "Easy"
        )
        insertQuizData(
            "Business Mathematics",
            "A percentage rate used to express the cost of borrowing money is called the:",
            "A. Interest rate, B. Markup rate, C. Discount rate, D. All of the above",
            0,
            "Easy"
        )
        insertQuizData(
            "Business Mathematics",
            "Subtracting a discount from the retail price of an item gives you the:",
            "A. Wholesale price, B. Net price, C. Profit margin, D. Selling price",
            0,
            "Easy"
        )
    }

    private fun populateMediumQuestions() {
        // Add medium Business Mathematics quiz questions here


        // Add medium Business Mathematics quiz questions here
        insertQuizData(
            "Business Mathematics",
            "What is 'Net Income' in business?",
            "A. The total revenue generated by a business, B. The amount of money invested in a business, C. The profit earned after deducting all expenses from revenue, D. The cost price of a product",
            2, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "Define 'Amortization' in business.",
            "A. The process of increasing the value of an asset, B. The process of decreasing the value of an asset over time, C. The cost of borrowing money, D. The process of paying off debt over a period of time",
            3, // Index of correct answer (D)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "What is 'Equity' in business?",
            "A. The percentage of profit earned on a product or service after deducting all costs, B. The amount of money invested in a business, C. The value of an asset after deducting liabilities, D. The total revenue generated by a business",
            2, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "Define 'Operating Expenses' in business.",
            "A. The total revenue generated by a business, B. The costs incurred in the day-to-day operations of a business, C. The percentage of profit earned on a product or service, D. The cost price of a product",
            1, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "What is 'Revenue' in business?",
            "A. The percentage of profit earned on a product or service after deducting all costs, B. The total expenses incurred by a business, C. The total amount of money earned from sales of goods or services, D. The cost price of a product",
            2, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "Define 'Gross Profit' in business.",
            "A. The total revenue generated by a business, B. The profit earned before deducting expenses, C. The percentage of profit earned on a product or service, D. The cost price of a product",
            1, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "What is 'Inventory Turnover Ratio' in business?",
            "A. The percentage of profit earned on a product or service after deducting all costs, B. The ratio of cost of goods sold to average inventory, C. The total revenue generated by a business, D. The cost price of a product",
            1, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "Define 'Operating Income' in business.",
            "A. The total revenue generated by a business, B. The income earned from investments, C. The profit earned from core business operations before interest and taxes, D. The cost price of a product",
            2, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Business Mathematics",
            "What is 'Return on Investment (ROI)' in business?",
            "A. The total revenue generated by a business, B. The percentage of profit earned on a product or service after deducting all costs, C. A measure of the profitability of an investment relative to its cost, D. The cost price of a product",
            2, // Index of correct answer (C)
            "Medium"
        )


    }

    private fun populateHardQuestions() {
        // Add hard Business Mathematics quiz questions here
        insertQuizData(
            "Business Mathematics",
            "A cellphone cover sells for ₱250 and costs ₱185 to manufacture. What is the margin in percentage?",
            "A. 0%, B. 0%, C. 26%, D. 35%",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A seller wants a mark-up based on cost of 12%. If a tumbler costs ₱460, how much will be the mark-up?",
            "A. ₱5, B. ₱55, C. ₱465, D. ₱515",
            3, // Index of correct answer (D)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "Claire sold her craft at a selling price of ₱750. The cost of her craft is ₱475. What is the mark-up percentage?",
            "A. 0%, B. 0%, C. 37%, D. 58%",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A company's total revenue is ₱500,000 and its total costs are ₱350,000. Calculate the company's profit margin in percentage.",
            "A. 30%, B. 40%, C. 50%, D. 60%",
            1, // Index of correct answer (B)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's fixed costs are ₱100,000, its variable costs are ₱50 per unit, and it sells each unit for ₱100, how many units does it need to sell to break even?",
            "A. 1,000 units, B. 2,000 units, C. 3,000 units, D. 4,000 units",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A store bought 100 units of a product for ₱10 each and sold them for ₱15 each. What is the store's total profit?",
            "A. ₱500, B. ₱1,000, C. ₱1,500, D. ₱2,000",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's revenue is ₱1,000,000 and its total costs are ₱800,000, what is the company's profit margin in percentage?",
            "A. 10%, B. 20%, C. 30%, D. 40%",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A company's initial investment is ₱50,000. If the company's profit is ₱20,000 per month, how many months will it take to recover the initial investment?",
            "A. 2.5 months, B. 5 months, C. 7.5 months, D. 10 months",
            3, // Index of correct answer (D)
            "Hard"
        )


        insertQuizData(
            "Business Mathematics",
            "A company's total revenue is ₱800,000 and its total variable costs are ₱400,000. Calculate the company's contribution margin ratio.",
            "A. 25%, B. 50%, C. 75%, D. 100%",
            1, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's fixed costs are ₱120,000, its variable costs are ₱30 per unit, and it sells each unit for ₱60, what is the company's break-even point in units?",
            "A. 2,000 units, B. 3,000 units, C. 4,000 units, D. 5,000 units",
            3, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A store bought 150 units of a product for ₱15 each and sold them for ₱25 each. What is the store's total profit?",
            "A. ₱1,500, B. ₱2,250, C. ₱3,000, D. ₱3,750",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's revenue is ₱3,000,000 and its total costs are ₱2,000,000, what is the company's profit margin in percentage?",
            "A. 25%, B. 33.33%, C. 50%, D. 66.67%",
            1, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A company's total revenue is ₱3,000,000 and its total variable costs are ₱1,200,000. If the company's fixed costs are ₱800,000, its semi-variable costs are ₱200,000, its step costs are ₱100,000, and its joint costs are ₱50,000, calculate the company's operating income.",
            "A. ₱750,000, B. ₱800,000, C. ₱850,000, D. ₱900,000",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's fixed costs are ₱500,000, its variable costs are ₱120 per unit, its semi-variable costs are ₱50,000, its step costs are ₱30,000, and it sells each unit for ₱200, what is the company's break-even point in units?",
            "A. 2,000 units, B. 2,500 units, C. 3,000 units, D. 3,500 units",
            1, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A store bought 1,000 units of a product for ₱50 each and sold them for ₱100 each. Considering additional costs of ₱20,000, step costs of ₱10,000, and joint costs of ₱5,000, what is the store's total profit?",
            "A. ₱45,000, B. ₱50,000, C. ₱55,000, D. ₱60,000",
            3, // Index of correct answer (D)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's revenue is ₱10,000,000 and its total costs are ₱5,500,000, including fixed costs of ₱1,500,000, variable costs of ₱2,500,000, semi-variable costs of ₱500,000, step costs of ₱200,000, and joint costs of ₱100,000, what is the company's profit margin in percentage?",
            "A. 45%, B. 50%, C. 55%, D. 60%",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A company's total revenue is ₱50,000,000 and its total variable costs are ₱20,000,000. If the company's fixed costs are ₱12,000,000, its semi-variable costs are ₱5,000,000, its step costs are ₱2,500,000, its joint costs are ₱1,000,000, its sunk costs are ₱500,000, its opportunity costs are ₱250,000, and its contingency costs are ₱100,000, calculate the company's operating income.",
            "A. ₱9,250,000, B. ₱9,500,000, C. ₱9,750,000, D. ₱10,000,000",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's fixed costs are ₱3,5000,,, its variable costs are ₱400 per unit,, its semi-variable costs are ₱200,,000,, its step costs are ₱100,,000,, and it sells each unit for ₱1,,500,, what is the company's break-even point in units?",
            "A. 2,,500 units,, B. 3,,000 units,, C. 3,,500 units,, D. 4,,000 units",
            0, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A store bought 20,,000 units of a product for ₱300 each and sold them for ₱1,,200 each. Considering additional costs of ₱150,,000,,, step costs of ₱75,,000,,, joint costs of ₱60,,000,,, sunk costs of ₱30,,000,,, opportunity costs of ₱15,,000,,, and contingency costs of ₱10,,000,,, what is the store's total profit?",
            "A. ₱21,,5000,,, B. ₱22,,00,,, C. ₱22,,50,,, D. ₱23",
            3, // Index of correct answer (D)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's revenue is ₱1000,,, and its total costs are ₱55,,,, including fixed costs of ₱15,,,, variable costs of ₱25,,,, semi-variable costs of ₱7,,,, step costs of ₱12,,,, joint costs of ₱6,,,, sunk costs of ₱3,,,, opportunity costs of ₱1,,,, and contingency costs of 5000,,, what is the company's profit margin in percentage?",
            "A. 45%, B. 50%, C. 55%, D. 60%",
            1,// Index of correct answer (B)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A company's total revenue is ₱20,000,000 and its total variable costs are ₱8,000,000. If the company's fixed costs are ₱5,000,000, its semi-variable costs are ₱2,000,000, its step costs are ₱1,000,000, its joint costs are ₱500,000, its sunk costs are ₱200,000, and its opportunity costs are ₱100,000, calculate the company's operating income.",
            "A. ₱3,500,000, B. ₱4,000,000, C. ₱4,500,000, D. ₱5,000,000",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's fixed costs are ₱2,000,000, its variable costs are ₱300 per unit, its semi-variable costs are ₱150,000, its step costs are ₱75,000, and it sells each unit for ₱1,000, what is the company's break-even point in units?",
            "A. 2,500 units, B. 3,000 units, C. 3,500 units, D. 4,000 units",
            0, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "A store bought 10,000 units of a product for ₱200 each and sold them for ₱800 each. Considering additional costs of ₱100,000, step costs of ₱50,000, joint costs of ₱40,000, sunk costs of ₱20,000, and opportunity costs of ₱10,000, what is the store's total profit?",
            "A. ₱6,500,000, B. ₱7,000,000, C. ₱7,500,000, D. ₱8,000,000",
            3, // Index of correct answer (D)
            "Hard"
        )

        insertQuizData(
            "Business Mathematics",
            "If a company's revenue is ₱80,000,000 and its total costs are ₱45,000,000, including fixed costs of ₱15,000,000, variable costs of ₱25,000,000, semi-variable costs of ₱7,000,000, step costs of ₱12,000,000, joint costs of ₱6,000,000, sunk costs of ₱3,000,000, and opportunity costs of ₱1,500,000, what is the company's profit margin in percentage?",
            "A. 45%, B. 50%, C. 55%, D. 60%",
            1, // Index of correct answer (B)
            "Hard"
        )

    }

    private fun populateEasyQuestionsBUSINESS() {
        insertQuizData(
            "Business Finance",
            "What is the difference between debit and credit in accounting?",
            "A. Debits increase expenses and credits increase assets, B. Credits increase expenses and debits increase assets, C. Debits and credits always decrease accounts, D. Credits and debits have no impact on accounts",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Finance",
            "What is the primary source of funding for most businesses?",
            "A. Bank loans, B. Venture capital, C. Personal savings, D. Share issuance",
            1, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Finance",
            "What does ROI stand for in finance?",
            "A. Return on Investment, B. Rate of Interest, C. Revenue of Income, D. Return on Income",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "Business Finance",
            "Which financial statement provides a snapshot of a company's financial position at a specific point in time?",
            "A. Income statement, B. Statement of cash flows, C. Balance sheet, D. Statement of retained earnings",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "Business Finance",
            "What does the term 'liquidity' refer to in finance?",
            "A. Ability to pay off long-term debt, B. Ability to convert assets into cash quickly, C. Ability to attract investors, D. Ability to generate revenue",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "Business Finance",
            "What is the term for the money a company brings in from selling goods or services?",
            "A. Profit, B. Revenue, C. Cost, D. Investment",
            1, // Index of correct answer (B)
            "Easy"
        )
        insertQuizData(
            "Business Finance",
            "Which of the following is NOT a common source of financing for a business?",
            "A. Equity capital (selling shares), B. Debt financing (loans), C. Retained earnings (profits kept by the company), D. Government grants",
            3, // Index of correct answer (D)
            "Easy"
        )
        insertQuizData(
            "Business Finance",
            "What is the main purpose of a business budget?",
            "A. To track past expenses, B. To forecast future income and expenses, C. To value the company, D. To manage employee salaries",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "Business Finance",
            "A company's ______ refers to the money it owes to creditors.",
            "A. Equity, B. Debt, C. Revenue, D. Assets",
            1, // Index of correct answer (B)
            "Easy"
        )
    }

    private fun populateMediumQuestionsBUSINESS() {
        // Add medium Business Finance quiz questions here
        insertQuizData(
            "Business Finance",
            "What term refers to funds provided by lenders or investors to enable businesses to operate?",
            "A. Equity, B. Assets, C. Liabilities, D. Capital",
            3, // Index of correct answer (D)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial metric measures a company's ability to generate profit from its resources?",
            "A. Return on investment (ROI), B. Return on assets (ROA), C. Earnings per share (EPS), D. Price-earnings ratio (P/E ratio)",
            2, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What is the term for the process of converting assets into cash?",
            "A. Amortization, B. Depreciation, C. Liquidity, D. Solvency",
            3, // Index of correct answer (C)
            "Medium"
        )
        insertQuizData(
            "Business Finance",
            "What is the formula to calculate the current ratio?",
            "A. Current assets / Current liabilities, B. Current liabilities / Current assets, C. Total assets / Total liabilities, D. Total liabilities / Total assets",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial ratio measures a company's ability to pay off its short-term liabilities with its short-term assets?",
            "A. Debt-to-Equity Ratio, B. Current Ratio, C. Return on Equity, D. Gross Profit Margin",
            2, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What does the term 'EBITDA' stand for in finance?",
            "A. Earnings Before Interest, Taxes, Depreciation, and Amortization, B. Earnings Before Interest, Taxes, Depreciation, and Assets, C. Earnings Before Interest, Taxes, Dividends, and Amortization, D. Earnings Before Income, Taxes, Depreciation, and Assets",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What does the term 'working capital' represent?",
            "A. Total assets minus total liabilities, B. Total liabilities minus total assets, C. Current assets minus current liabilities, D. Current liabilities minus current assets",
            3, // Index of correct answer (C)
            "Medium"
        )
        insertQuizData(
            "Business Finance",
            "What does the term 'WACC' stand for in finance?",
            "A. Weighted Average Cost of Capital, B. Weighted Average Cost of Cash, C. Weighted Annual Cash Cost, D. Weighted Annual Capital Cost",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What financial metric measures the efficiency of a company's operations in generating profit from its revenue?",
            "A. Gross profit margin, B. Operating profit margin, C. Net profit margin, D. EBITDA margin",
            2, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial statement shows a company's financial position at a specific point in time?",
            "A. Income statement, B. Balance sheet, C. Cash flow statement, D. Statement of retained earnings",
            2, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What is the term for the measure of how much profit a company generates with its shareholders' equity?",
            "A. Return on Investment (ROI), B. Return on Equity (ROE), C. Return on Assets (ROA), D. Net Profit Margin",
            2, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial ratio measures a company's ability to cover its interest expenses with its earnings?",
            "A. Debt-to-Equity Ratio, B. Interest Coverage Ratio, C. Quick Ratio, D. Debt Ratio",
            2, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What is the term for the process of estimating the value of an asset or investment?",
            "A. Budgeting, B. Forecasting, C. Valuation, D. Amortization",
            3, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial metric measures the profitability of a company's core business operations?",
            "A. Earnings Before Interest and Taxes (EBIT), B. Gross Profit, C. Operating Income, D. Net Income",
            3, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What term refers to the process of spreading out the cost of an intangible asset over its useful life?",
            "A. Amortization, B. Depreciation, C. Accrual, D. Impairment",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What is the formula to calculate the debt-to-equity ratio?",
            "A. Total debt / Total equity, B. Total equity / Total debt, C. Total assets / Total equity, D. Total equity / Total assets",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What does the term 'EBIT' stand for in finance?",
            "A. Earnings Before Interest and Taxes, B. Earnings Before Income and Taxes, C. Earnings Before Interest and Total Expenses, D. Earnings Before Interest and Depreciation",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial ratio measures a company's ability to meet its short-term obligations with its most liquid assets?",
            "A. Quick Ratio, B. Debt-to-Equity Ratio, C. Return on Assets, D. Gross Profit Margin",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What is the formula to calculate the operating cash flow?",
            "A. Net Income + Depreciation, B. Net Income - Depreciation, C. Net Income + Interest, D. Net Income - Interest",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What is the primary purpose of financial leverage in business?",
            "A. To reduce financial risk, B. To increase profitability, C. To increase return on investment, D. To increase financial flexibility",
            3, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial metric indicates the proportion of profits that a company returns to its shareholders in the form of dividends?",
            "A. Dividend Yield, B. Earnings per Share (EPS), C. Price-Earnings Ratio (P/E Ratio), D. Dividend Payout Ratio",
            4, // Index of correct answer (D)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What does the term 'IRR' stand for in finance?",
            "A. Internal Rate of Return, B. Interest Rate Reduction, C. Investment Return Ratio, D. Inflation Risk Reduction",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial metric measures the proportion of debt in a company's capital structure?",
            "A. Debt-to-Equity Ratio, B. Return on Investment (ROI), C. Price-Earnings Ratio (P/E Ratio), D. Current Ratio",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What is the term for the process of distributing the cost of an intangible asset over its useful life?",
            "A. Amortization, B. Depreciation, C. Impairment, D. Accrual",
            1, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "Which financial statement shows changes in a company's equity over a specific period of time?",
            "A. Income statement, B. Balance sheet, C. Statement of cash flows, D. Statement of retained earnings",
            4, // Index of correct answer (D)
            "Medium"
        )

        insertQuizData(
            "Business Finance",
            "What does the term 'CAPM' stand for in finance?",
            "A. Capital Asset Pricing Model, B. Cash Asset Pricing Model, C. Corporate Asset Pricing Model, D. Credit Asset Pricing Model",
            1, // Index of correct answer (A)
            "Medium"
        )
    }

    private fun populateHardQuestionsBUSINESS() {
        // Add hard Business Finance quiz questions here

        insertQuizData(
            "Business Finance",
            "A company needs ₱200,000 in 3 years for expansion. How much should they invest now at an annual interest rate of 12% compounded annually to meet this goal?",
            "A. ₱133,333.33, B. ₱145,212.30, C. ₱120,000.00, D. ₱156,324.17",
            0, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Finance",
            "If a project requires an initial investment of ₱50,000 and is expected to generate ₱20,000 per year for the next 5 years, what is the payback period?",
            "A. 3 years, B. 2.5 years, C. 4 years, D. 2 years",
            1, // Index of correct answer (B)
            "Hard"
        )

        insertQuizData(
            "Business Finance",
            "Company A is considering investing ₱100,000 in a project expected to generate ₱30,000 per year for 6 years. If the discount rate is 10%, should they proceed with the investment?",
            "A. Yes, B. No",
            0, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Finance",
            "A company wants to accumulate ₱1,000,000 in 10 years for a new project. If they can earn 8% annually, how much should they invest now as a lump sum to achieve this goal?",
            "A. ₱466,096.77, B. ₱450,000.00, C. ₱550,000.00, D. ₱425,324.17",
            0, // Index of correct answer (A)
            "Hard"
        )

        insertQuizData(
            "Business Finance",
            "A company has a fixed cost of ₱50,000, a variable cost per unit of ₱10, and sells its product for ₱30 per unit. How many units must it sell to achieve a target profit of ₱20,000?",
            "A. 3,000 units, B. 4,000 units, C. 5,000 units, D. 6,000 units",
            1, // Index of correct answer (B)
            "Hard"
        )

        insertQuizData(
            "Business Finance",
            "Calculate the net present value (NPV) of an investment project that requires an initial outlay of ₱200,000 and is expected to generate cash inflows of ₱80,000 per year for the next 5 years. Use a discount rate of 12%.",
            "A. ₱30,000, B. ₱40,000, C. ₱50,000, D. ₱60,000",
            2, // Index of correct answer (C)
            "Hard"
        )

        insertQuizData(
            "Business Finance",
            "A company is considering two mutually exclusive projects. Project A requires an initial investment of ₱150,000 and is expected to generate cash flows of ₱50,000 per year for 4 years. Project B requires an initial investment of ₱200,000 and is expected to generate cash flows of ₱70,000 per year for 3 years. Which project should the company choose based on the payback period method if the company's maximum acceptable payback period is 3.5 years?",
            "A. Project A, B. Project B, C. Both projects are acceptable, D. Neither project is acceptable",
            1, // Index of correct answer (B)
            "Hard"
        )

        insertQuizData(
            "Business Finance",
            "Calculate the internal rate of return (IRR) for a project with an initial investment of ₱300,000 and cash inflows of ₱100,000 for each of the next 5 years.",
            "A. 10%, B. 15%, C. 20%, D. 25%",
            2, // Index of correct answer (C)
            "Hard"
        )
    }

    private fun populateEasyQuestionsFABM() {
        // Add easy Business Finance quiz questions here
        insertQuizData(
            "FABM 1",
            "What is the main purpose of accounting?",
            "A. To hire employees, B. To design websites, C. To advertise products, D. To record financial transactions",
            3, // Index of correct answer (D)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Who are the primary users of financial statements?",
            "A. Competitors and regulators, B. Marketers and advertisers, C. Employees and suppliers, D. Investors and creditors",
            3, // Index of correct answer (D)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which form of business organization has unlimited liability for its owners?",
            "A. Limited Liability Company, B. Corporation, C. Partnership, D. Sole Proprietorship",
            3, // Index of correct answer (D)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which type of business focuses on providing services rather than selling products?",
            "A. Retail business, B. Manufacturing business, C. Service business, D. Merchandising business",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "What is the accounting concept that assumes a business will continue to operate indefinitely?",
            "A. Materiality concept, B. Going concern concept, C. Conservatism principle, D. Matching principle",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "What is the formula for the accounting equation?",
            "A. Assets - Liabilities = Equity, B. Assets / Liabilities = Equity, C. Assets x Liabilities = Equity, D. Assets = Liabilities + Equity",
            3, // Index of correct answer (D)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is an example of an operating expense?",
            "A. Purchase of equipment, B. Payment of salaries to employees, C. Repayment of a loan, D. Investment in stocks",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "What is the formula to calculate gross profit?",
            "A. Gross Profit = Revenue - Expenses, B. Gross Profit = Revenue / Expenses, C. Gross Profit = Revenue + Expenses, D. Gross Profit = Revenue x Expenses",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is a current asset?",
            "A. Land, B. Equipment, C. Accounts Receivable, D. Building",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is an example of an operating expense?",
            "A. Purchase of equipment, B. Payment of salaries to employees, C. Repayment of a loan, D. Investment in stocks",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "What is the formula to calculate gross profit?",
            "A. Gross Profit = Revenue - Expenses, B. Gross Profit = Revenue / Expenses, C. Gross Profit = Revenue + Expenses, D. Gross Profit = Revenue x Expenses",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is a current asset?",
            "A. Land, B. Equipment, C. Accounts Receivable, D. Building",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is a primary function of management?",
            "A. Planning, B. Recording, C. Summarizing, D. Posting",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "The process of recording financial transactions is known as:",
            "A. Analysis, B. Interpretation, C. Bookkeeping, D. Auditing",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "The accounting equation is expressed as:",
            "A. Assets = Liabilities + Owner's Equity, B. Assets = Liabilities - Owner's Equity, C. Assets + Liabilities = Owner's Equity, D. Assets - Liabilities = Owner's Equity",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which financial statement shows the financial position of a business at a specific point in time?",
            "A. Income Statement, B. Statement of Cash Flows, C. Balance Sheet, D. Statement of Retained Earnings",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Revenue is recognized in the accounting records when:",
            "A. Cash is received, B. Goods are sold or services are rendered, C. An invoice is issued, D. Expenses are paid",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is a current asset?",
            "A. Land, B. Buildings, C. Accounts Receivable, D. Equipment",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "The financial statement that reports the revenues and expenses of a business for a specific period of time is the:",
            "A. Balance Sheet, B. Income Statement, C. Statement of Cash Flows, D. Statement of Owner's Equity",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is NOT a characteristic of a sole proprietorship?",
            "A. Limited liability, B. Single ownership, C. Simple to establish, D. Owner receives all profits",
            0, // Index of correct answer (A)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which accounting principle states that accounting information should be based on actual cost?",
            "A. Going Concern, B. Historical Cost, C. Matching Principle, D. Revenue Recognition",
            1, // Index of correct answer (B)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "The document prepared by a seller when goods are shipped to a customer is called a(n):",
            "A. Invoice, B. Purchase Order, C. Sales Order, D. Bill of Lading",
            3, // Index of correct answer (D)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "What is the accounting concept that assumes a business will continue to operate indefinitely?",
            "A. Materiality concept, B. Matching principle, C. Going concern concept, D. Conservatism principle",
            2, // Index of correct answer (C)
            "Easy"
        )


        insertQuizData(
            "FABM 1",
            "What does ROI stand for in business analysis?",
            "A. Rate of Investment, B. Return on Income, C. Revenue over Income, D. Return on Investment",
            3, // Index of correct answer (D)
            "Easy"
        )
        insertQuizData(
            "FABM 1",
            "Which branch of accounting deals with the preparation of financial statements?",
            "A. Management Accounting, B. Tax Accounting, C. Financial Accounting, D. Cost Accounting",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Who are the primary users of financial statements?",
            "A. Employees and suppliers, B. Competitors and regulators, C. Investors and creditors, D. Marketers and advertisers",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which form of business organization has unlimited liability for its owners?",
            "A. Partnership, B. Corporation, C. Sole Proprietorship, D. Limited Liability Company",
            2, // Index of correct answer (C)
            "Easy"
        )

        insertQuizData(
            "FABM 1",
            "Which type of business focuses on providing services rather than selling products?",
            "A. Manufacturing business, B. Retail business, C. Merchandising business, D. Service business",
            3, // Index of correct answer (D)
            "Easy"
        )

        insertQuizData(
            "Business",
            "What are the different types of business costs?",
            "A. Fixed and variable costs, B. Direct and indirect costs, C. Explicit and implicit costs, D. All of the above",
            3, // Index of correct answer (D)
            "Easy"
        )

    }

    private fun populateMediumQuestionsFABM() {
        // Add medium Business Finance quiz questions here
        insertQuizData(
            "FABM 1",
            "What is the purpose of a cash flow statement in financial reporting?",
            "A. To assess a company's marketing strategies, B. To calculate a company's return on investment, C. To provide information about a company's liquidity and solvency, D. To report a company's revenues and expenses",
            2, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "FABM 1",
            "Define 'fixed costs' in business.",
            "A. Costs associated with marketing and advertising, B. Costs incurred for short-term projects, C. Costs that vary with the level of production or sales, D. Costs that remain constant regardless of the level of production or sales",
            3, // Index of correct answer (D)
            "Medium"
        )




        insertQuizData(
            "FABM 1",
            "Define 'operating income' in financial analysis.",
            "A. The revenue earned from non-operating activities, B. The total revenue generated by a company, C. The amount of profit retained by the company for future investments, D. The net income after deducting all expenses except taxes and interest",
            3, // Index of correct answer (D)
            "Medium"
        )

        insertQuizData(
            "FABM 1",
            "What is the purpose of a balance sheet in financial reporting?",
            "A. To report a company's revenues and expenses, B. To analyze the inflow and outflow of cash over a period of time, C. To list a company's assets, liabilities, and equity at a specific point in time, D. To provide information about a company's profitability over a period of time",
            2, // Index of correct answer (C)
            "Medium"
        )



        insertQuizData(
            "FABM 1",
            "What is the purpose of an income statement in financial reporting?",
            "A. To report a company's revenues and expenses over a period of time, B. To list a company's assets, liabilities, and equity at a specific point in time, C. To analyze the inflow and outflow of cash over a period of time, D. To provide information about a company's liquidity and solvency",
            0, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "FABM 1",
            "Define 'accounts receivable' in accounting.",
            "A. The money owed by customers to a company for goods or services provided on credit, B. The money owed by a company to its suppliers for goods or services received on credit, C. The total amount of money a company receives from its customers, D. The total amount of money a company pays to its suppliers",
            0, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "Business Management",
            "What are the four functions of management?",
            "A. Planning, Organizing, Leading, Controlling, B. Budgeting, Forecasting, Hiring, Marketing, C. Hiring, Training, Motivating, Supervising, D. Researching, Developing, Producing, Selling",
            0, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "FABM 1",
            "What is the purpose of a trial balance in accounting?",
            "A. To list a company's assets, liabilities, and equity at a specific point in time, B. To report a company's revenues and expenses over a period of time, C. To ensure that debits equal credits in the accounting records, D. To analyze the financial performance of a company",
            2, // Index of correct answer (C)
            "Medium"
        )

        insertQuizData(
            "Finance",
            "What is the Capital Asset Pricing Model (CAPM) used for?",
            "A.  Calculating the expected return of an individual stock, B. Estimating the cost of equity capital for a company, C.  Evaluating the performance of a mutual fund, D. Comparing the risk and return of different investments",
            1, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "Accounting",
            "What is the difference between IFRS and US GAAP?",
            "A. Both are the same set of accounting standards, B. IFRS are international standards, while US GAAP are specific to the United States, C. IFRS focus on cash flow, while US GAAP focus on profitability, D. None of the above",
            1, // Index of correct answer (B)
            "Medium"
        )


        insertQuizData(
            "Management",
            "What are the steps involved in the strategic management process?",
            "A. Setting goals, analyzing the environment, formulating a strategy, implementing the strategy, evaluating results, B. Hiring, training, motivating, controlling, C. Budgeting, forecasting, marketing, research and development, D. None of the above",
            0, // Index of correct answer (A)
            "Medium"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is a characteristic of financial accounting?",
            "A. Focuses on providing information for internal decision-making, B. Prepared in accordance with generally accepted accounting principles (GAAP), C. Emphasizes future-oriented information, D. Primarily used by managers and employees",
            1, // Index of correct answer (B)
            "Medium"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is a characteristic of management accounting?",
            "A. Focuses on historical financial information, B. Primarily used by external users, C. Emphasizes precision over relevance, D. Provides information for internal decision-making",
            3, // Index of correct answer (D)
            "Medium"
        )

        insertQuizData(
            "FABM 1",
            "Which of the following is an example of an external audit?",
            "A. Internal auditor reviewing financial statements, B. Government agency reviewing tax returns, C. Company's management reviewing budget variances, D. Board of directors reviewing financial performance",
            1, // Index of correct answer (B)
            "Medium"
        )

    }

    private fun populateHardQuestionsFABM() {
        // Add hard Business Finance quiz questions here
    }


    private fun populateEasyQuestionsECONOMICS() {
        // Add easy Business Finance quiz questions here
    }

    private fun populateMediumQuestionsECONOMICS() {
        // Add medium Business Finance quiz questions here
    }

    private fun populateHardQuestionsECONOMICS() {
        // Add hard Business Finance quiz questions here
    }
}