package com.mtcdb.stem.mathtrix.quiz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class QuizDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "quiz.db"
        const val DATABASE_VERSION = 9

        const val TABLE_QUIZ = "quiz"
        const val COLUMN_ID = "_id"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_OPTIONS = "options"
        const val COLUMN_CORRECT_ANSWER_INDEX = "correct_answer_index"
        const val COLUMN_DIFFICULTY_LEVEL = "difficulty_level"

    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the table with an additional column for selected answer index
        db.execSQL(
            "CREATE TABLE $TABLE_QUIZ (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_QUESTION TEXT," +
                    "$COLUMN_OPTIONS TEXT," +
                    "$COLUMN_CORRECT_ANSWER_INDEX INTEGER," +
                    "$COLUMN_DIFFICULTY_LEVEL TEXT" +
                    ")"

        )

        /**
         *

        //Easy
        insertQuizData(
        db,
        "If a company sells 400 units at $50 per unit, what is the total revenue?",
        "A. $20,000,B. $40,000,C. $200,000,D. $2 Million",
        2,
        "Easy"
        )
        insertQuizData(
        db,
        "What financial acronym refers to Strengths, Weaknesses, Opportunities and Threats analysis?",
        "A. PEST,B. SWOT,C. MOST,D. STEA",
        1,
        "Easy"
        )
        insertQuizData(
        db,
        "Which metric measures income divided by average total assets over a period?",
        "A. Return on Assets,B. Profit Margin,C. Asset Turnover,D. Interest Coverage",
        0,
        "Easy"
        )
        insertQuizData(
        db,
        "Which option allows companies to raise money by offering partial ownership?",
        "A. Bank Loans,B. Bonds,C. Stocks,D. Crowdfunding",
        2,
        "Easy"
        )
        insertQuizData(
        db,
        "What does the sunk cost fallacy refer to in business?",
        "A. Ignoring unrecoverable costs and moving on,B. Letting previous investments influence future decisions,C. Preferring variable over fixed expenses,D. Assigning arbitrary costs lacking accuracy",
        1,
        "Easy"
        )
        insertQuizData(
        db,
        "Which financial institution insures bank deposits from individual investors?",
        "A. FDIC,B. SEC,C. FINRA,D. Comptroller",
        0,
        "Easy"
        )
        insertQuizData(
        db,
        "What is the total called when revenue exceeds all expenses?",
        "A. Net Loss,B. Net Income,C. Gross Loss,D. Gross Income",
        1,
        "Easy"
        )
        insertQuizData(
        db,
        "What does the accounting equation Assets = Liabilities + Equity mean?",
        "A. Assets always equal cash,B. Assets are used to pay for liabilities and equity,C. The value of a company's assets equals the claims on them,D. Equity can exceed assets",
        2,
        "Easy"
        )

        insertQuizData(
        db,
        "What financial statement shows revenues, expenses and profit/loss for a period of time?",
        "A. Balance sheet,B. Income statement,C. Statement of cash flows,D. Statement of retained earnings,",
        1,
        "Easy"
        )
        insertQuizData(
        db,
        "If $200 in sales brings $50 in profit over a period, what is the profit percentage?",
        "A. 20%,B. 25%,C. 30%, D. 75%",
        0,
        "Easy"
        )
        insertQuizData(
        db,
        "If inventory cost is $2,000, sales are $5,000 and gross profit is $2,000 over a period, what are the cost of goods sold?",
        "A. $1,000,B. $2,000,C. $3,000,D. $4,000",
        2,
        "Easy"
        )
        insertQuizData(
        db,
        "What does the financial term ROI stand for?",
        "A. Return on Inventory,B. Return on Income,   C. Return on Interest,D. Return on Investment",
        3,
        "Easy"
        )
        insertQuizData(
        db,
        "Which option below is NOT one of the 3 main financial statements?",
        "A. Balance sheet,B. Cash flow statement,C. Inventory report,D. Income statement",
        2,
        "Easy"
        )
        insertQuizData(
        db,
        "What does liquidity in finance refer to?",
        "A. Revenue growth,B. Availability of assets to cover short-term debts,C. Number of outstanding shares trading, D. Ability to develop new products",
        1,
        "Easy"
        )
        insertQuizData(
        db,
        "If a car is purchased for $15,000 and later sold for $10,000, what is the capital loss?",
        "A. $5,000,B. $10,000,C. $15,000,D. $25,000",
        0,
        "Easy"
        )
        insertQuizData(
        db,
        "Which metric calculates the % of net income distributed as dividends to shareholders?",
        "A. Dividend ratio,B. Dividend yield,C. Dividend payout ratio,D. Return on dividends,",
        2,
        "Easy"
        )
        insertQuizData(
        db,
        "What is a stock?",
        "A. A type of bond issued by a company, B. A type of investment that represents ownership in a company, C. A type of insurance policy, D. A type of bank account",
        1,  // Correct Answer Index
        "Easy"
        )
        insertQuizData(
        db,
        "What is revenue?",
        "A. The amount of money a company earns from selling its assets, B. The amount of money a company earns from its primary business activities, C. The amount of money a company owes to its creditors, D. The amount of money a company has in its bank accounts",
        1,  // Correct Answer Index
        "Easy"
        )
        insertQuizData(
        db,
        "What is a balance sheet?",
        "A. A financial statement that shows a company's revenues and expenses over a period of time, B. A financial statement that shows a company's assets, liabilities, and equity at a specific point in time, C. A financial statement that shows a company's cash flows over a period of time, D. A financial statement that shows a company's share price over a period of time",
        1,  // Correct Answer Index
        "Easy"
        )
        insertQuizData(
        db,
        "What is the difference between a budget and a forecast?",
        "A. A budget is a plan for future spending, while a forecast is a prediction of future events. B. A budget is a prediction of future events, while a forecast is a plan for future spending. C. A budget is a plan for future spending that is based on past performance, while a forecast is a prediction of future events that is based on current trends. D. A budget is a plan for future spending that is based on current trends, while a forecast is a prediction of future events that is based on past performance",
        2,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the importance of the time value of money in finance?",
        "A. It determines the time to invest, B. It assesses the value of money over time, C. It calculates interest rates, D. It measures inflation",
        1,
        "Easy"
        )
        insertQuizData(
        db,
        "What does the term 'Amortization' mean in business?",
        "A. Increase in stock price, B. Gradual reduction of debt, C. Increase in revenue, D. Calculating interest",
        1,
        "Easy"
        )
        insertQuizData(
        db,
        "What is the purpose of a balance sheet?",
        "A. To show daily expenses, B. To track inventory, C. To show financial position, D. To record sales",
        2,
        "Easy"
        )
        insertQuizData(
        db,
        "What is the difference between gross profit and net profit?",
        "A. Gross profit includes all costs, B. Net profit includes all costs, C. Gross profit is before tax while net profit is after tax, D. There is no difference",
        0,
        "Easy"
        )
        insertQuizData(
        db,
        "If a product costs $50 and is sold for $75, what is the profit percentage?",
        "A. 25%, B. 50%, C. 75%, D. 150%",
        0,
        "Easy"
        )
        insertQuizData(
        db,
        "What is the formula for calculating simple interest?",
        "A. P(1 + rt),B. P + r/n,C. P + rt,D. P(1 + r)^t",
        0,
        "Easy"
        )

        insertQuizData(
        db,
        "In business mathematics, what does ROI stand for?",
        "A. Return on Investment,B. Rate of Interest,C. Risk of Income,D. Revenue of Investment",
        0,
        "Easy"
        )

        insertQuizData(
        db,
        "What is the total revenue formula in business?",
        "A. TR = Q / P,B. TR = P / Q,C. TR = Q * P,D. TR = Q - P",
        2,
        "Easy"
        )

        insertQuizData(
        db,
        "What does the term 'CAGR' stand for in finance?",
        "A. Compound Annual Growth Rate,B. Cumulative Annual Growth Rate,C. Current Annual Growth Rate,D. Continuous Annual Growth Rate",
        0,
        "Easy"
        )

        insertQuizData(
        db,
        "What is the break-even point in business mathematics?",
        "A. The point where profit is maximized,B. The point where loss is minimized,C. The point where total cost equals total revenue,D. The point where revenue is zero",
        2,
        "Easy"
        )

        insertQuizData(
        db,
        "How is gross profit calculated?",
        "A. Gross Profit = Revenue - Expenses,B. Gross Profit = Revenue / Expenses,C. Gross Profit = Revenue * Expenses,D. Gross Profit = Revenue + Expenses",
        3,
        "Easy"
        )

        insertQuizData(
        db,
        "What is the formula for calculating net profit?",
        "A. Net Profit = Total Revenue - Total Expenses,B. Net Profit = Total Revenue / Total Expenses,C. Net Profit = Total Revenue * Total Expenses,D. Net Profit = Total Revenue + Total Expenses",
        0,
        "Easy"
        )

        insertQuizData(
        db,
        "In business, what does the term 'SWOT' stand for?",
        "A. Strengths Weaknesses Opportunities Threats,B. Sales Work Operations Tactics,C. Strategy Wealth Organization Teamwork,D. Systems Workflow Objectives Targets",
        0,
        "Easy"
        )

        insertQuizData(
        db,
        "What is the purpose of a cash flow statement?",
        "A. To show the company's overall financial health,B. To track the movement of money in and out of a business,C. To calculate the company's net worth,D. To assess the company's market share",
        1,
        "Easy"
        )

        insertQuizData(
        db,
        "What does the acronym 'EBITDA' stand for in finance?",
        "A. Earnings Before Interest Taxes Depreciation and Amortization,B. Expenditures Before Interest Taxes Depreciation and Assets,B. Estimated Business Income Taxes Depreciation and Amortization,C. Economic Benefits of Investments in Time Depreciation and Assets",
        0,
        "Easy"
        )

        // Populate the table with business mathematics sample data for Medium difficulty
        insertQuizData(
        db,
        "What is the concept of time value of money in finance?",
        "A. Money's worth changes over time,B. Time is valuable in business,C. Money is valued more over time,D. Time is the key factor in stock market",
        0,
        "Medium"
        )
        insertQuizData(
        db,
        "How is the present value of a future cash flow calculated?",
        "A. PV = FV / (1 + r)^n,B. PV = FV * (1 + r)^n,C. PV = FV * (1 - r)^n,D. PV = FV / (1 - r)^n",
        0,
        "Medium"
        )
        insertQuizData(
        db,
        "What is the internal rate of return (IRR) in finance?",
        "A. The rate at which investments are taxed,B. The rate at which the company grows internally,C. The discount rate that makes the net present value (NPV) of all cash flows from a particular project equal to zero,D. The interest rate at which banks lend to each other",
        2,
        "Medium"
        )
        insertQuizData(
        db,
        "What is the purpose of financial ratio analysis?",
        "A. To assess the overall profitability of a business,B. To evaluate a company's liquidity and solvency,C. To determine the total assets of a business,D. To calculate the company's gross profit margin",
        1,
        "Medium"
        )
        insertQuizData(
        db,
        "How is the debt-to-equity ratio calculated?",
        "A. Debt-to-Equity Ratio = Total Debt / Total Equity,B. Debt-to-Equity Ratio = Total Equity / Total Debt,C. Debt-to-Equity Ratio = Net Income / Total Equity,D. Debt-to-Equity Ratio = Net Income / Total Debt",
        0,
        "Medium"
        )
        insertQuizData(
        db,
        "What is the formula for calculating the payback period?",
        "A. Payback Period = Initial Investment / Annual Cash Flow,B. Payback Period = Initial Investment / Net Present Value,C. Payback Period = Net Income / Initial Investment,D. Payback Period = Net Present Value / Annual Cash Flow",
        0,
        "Medium"
        )
        insertQuizData(
        db,
        "What is the role of a balance sheet in financial statements?",
        "A. To show the company's profit and loss over a period of time,B. To provide a snapshot of a company's financial position at a specific point in time,C. To outline the company's future financial projections,D. To highlight the company's cash flow patterns",
        1,
        "Medium"
        )
        insertQuizData(
        db,
        "What does the term 'amortization' mean in finance?",
        "A. The process of paying off debt over time,B. The decrease in the value of an asset over time,C. The allocation of the cost of an intangible asset over its useful life,D. The process of converting assets into cash",
        2,
        "Medium"
        )

        insertQuizData(
        db,
        "How is the gross margin ratio calculated?",
        "A. Gross Margin Ratio = Gross Profit / Net Sales,B. Gross Margin Ratio = Net Income / Total Revenue,C. Gross Margin Ratio = Total Expenses / Gross Profit,D. Gross Margin Ratio = Net Sales / Gross Profit",
        0,
        "Medium"
        )
        insertQuizData(
        db,
        "If a company's liabilities increase faster than assets, what is happening to financial health?",
        "A. Improving, B. Worsening, C. Remaining steady, D. Fluctuating",
        1,
        "Medium"
        )
        insertQuizData(
        db,
        "What ratio shows how well short-term creditors are covered by assets that can be turned to cash quickly?",
        "A. Acid Test Ratio, B. Return on Equity, C. Inventory Turnover, D. Interest Coverage Ratio",
        0,
        "Medium"
        )
        insertQuizData(
        db,
        "What capital budgeting method calculates the time it takes to recoup the initial investment from project cash flows?",
        "A. Net Present Value, B. Internal Rate of Return, C. Payback Period, D. Accounting Rate of Return",
        2,
        "Medium"
        )
        insertQuizData(
        db,
        "Which financial statement item refers to the amount of net income kept by a company rather than paid out as dividends?",
        "A. Cost of Goods Sold, B. Gross Profit, C. Retained Earnings, D. Operating Expenses",
        2,
        "Medium"
        )
        insertQuizData(
        db,
        "What ratio indicates how well a company covers fixed interest payments over a period of time?",
        "A. Debt-to-Assets Ratio, B. Interest Coverage Ratio, C. Dividend Payout Ratio, D. Equity Multiplier",
        1,
        "Medium"
        )
        insertQuizData(
        db,
        "What budgeting technique allocates fixed periodic amounts to each department within an organization?",
        "A. Incremental Budgeting, B. Flexible Budgeting, C. Zero-Based Budgeting, D. Activity-Based Budgeting",
        2,
        "Medium"
        )
        insertQuizData(
        db,
        "What ratio helps evaluate how efficiently a company uses its labor force?",
        "A. Fixed Asset Turnover, B. Inventory Turnover, C. Receivables Turnover, D. Labor Productivity",
        3,
        "Medium"
        )
        insertQuizData(
        db,
        "What term refers to earnings kept by the company rather than paid out to stockholders as dividends?",
        "A. Equity, B. Retained Earnings, C. Treasury Stock, D. Capital Stock",
        1,
        "Medium"
        )
        // Populate the table with business mathematics sample data for Hard difficulty
        insertQuizData(
        db,
        "What does the term 'working capital' represent?",
        "A. The total capital invested in a business,B. The capital needed to start a business,C. The capital required to keep a business running smoothly,D. The capital spent on research and development",
        2,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the purpose of diversification in a portfolio?",
        "A. To maximize returns, B. To minimize risk, C. To increase liquidity, D. To reduce taxes",
        2,
        "Medium"
        )
        insertQuizData(
        db,
        "What is the difference between a stock and a mutual fund?",
        "A. A stock represents ownership in a company, while a mutual fund is a type of investment vehicle, B. A stock is a type of mutual fund, C. A mutual fund represents ownership in a company, while a stock is a type of investment vehicle, D. A stock is a type of bond, while a mutual fund is a type of stock",
        1,
        "Medium"
        )
        insertQuizData(
        db,
        "Which of the following is the most common measure of a portfolio's risk-adjusted performance?",
        "A. Sharpe ratio, B. Sortino ratio, C. Treynor ratio, D. Jensen's alpha",
        0,  // Correct Answer Index
        "Hard"
        )

        insertQuizData(
        db,
        "Which of the following is the most common type of bond security?",
        "A. Corporate bond, B. Government bond, C. Municipal bond, D. Mortgage-backed security",
        1,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of credit risk in investment analysis?",
        "A. Default risk, B. Liquidity risk, C. Inflation risk, D. Market risk",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of option in investment analysis?",
        "A. Call option, B. Put option, C. Covered option, D. Naked option",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of swap in investment analysis?",
        "A. Interest rate swap, B. Currency swap, C. Commodity swap, D. Equity swap",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of financial statement used in investment analysis?",
        "A. Balance sheet, B. Income statement, C. Cash flow statement, D. Statement of shareholders' equity",
        1,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of financial analysis used in investment management?",
        "A. Fundamental analysis, B. Technical analysis, C. Quantitative analysis, D. Behavioral finance",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of investment strategy used in real estate?",
        "A. Buy-and-hold, B. Flipping, C. Wholesaling, D. REITs",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common measure of a portfolio's overall performance?",
        "A. Total return, B. Annualized return, C. Compounded return, D. Average return",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common method used to calculate the cost basis of a security?",
        "A. First-in, first-out (FIFO), B. Last-in, first-out (LIFO), C. Weighted average, D. Specific identification",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the primary reason why diversification is important in investment portfolios?",
        "A. To reduce risk, B. To increase potential returns, C. To minimize taxes, D. To comply with regulations",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of retirement account in the United States?",
        "A. Traditional IRA, B. Roth IRA, C. 401(k), D. SEP IRA",
        2,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of investment risk?",
        "A. Market risk, B. Credit risk, C. Liquidity risk, D. Operational risk",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of investment strategy used by hedge funds?",
        "A. Long/short equity, B. Event-driven, C. Global macro, D. Quantitative",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of derivative used in investment management?",
        "A. Options, B. Futures, C. Swaps, D. Forwards",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of investment vehicle for institutional investors?",
        "A. Mutual funds, B. Exchange-traded funds (ETFs), C. Hedge funds, D. Private equity funds",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "Which of the following is the most common type of investment strategy used by private equity firms?",
        "A. Leveraged buyouts (LBOs), B. Venture capital, C. Growth equity, D. Mezzanine financing",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is the difference between a mutual fund and an ETF?",
        "A. A mutual fund is a type of investment company that pools money from many investors to purchase a diversified portfolio of stocks, bonds, or other securities, while an ETF is a type of investment company that is similar to a mutual fund but trades like a stock on an exchange. B. A mutual fund is a type of investment company that is traded on an exchange, while an ETF is a type of investment company that pools money from many investors to purchase a diversified portfolio of stocks, bonds, or other securities. C. A mutual fund is a type of investment company that is only available to accredited investors, while an ETF is a type of investment company that is available to all investors. D. A mutual fund is a type of investment company that has a fixed number of shares, while an ETF is a type of investment company that has a variable number of shares.",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is the difference between a traditional IRA and a Roth IRA?",
        "A. A traditional IRA is a retirement account that allows you to make contributions with money that you may be able to deduct on your tax return, while a Roth IRA is a retirement account that allows you to make contributions with money that you have already paid taxes on. B. A traditional IRA is a retirement account that allows you to make contributions with money that you have already paid taxes on, while a Roth IRA is a retirement account that allows you to make contributions with money that you may be able to deduct on your tax return. C. A traditional IRA is a retirement account that is only available to high-income earners, while a Roth IRA is a retirement account that is available to all income levels. D. A traditional IRA is a retirement account that has a required minimum distribution (RMD) at age 72, while a Roth IRA does not have an RMD.",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is the difference between a growth stock and a value stock?",
        "A. A growth stock is a company that is expected to grow at an above-average rate compared to other companies in the market, while a value stock is a company that is undervalued by the market and has the potential to outperform the market. B. A growth stock is a company that is undervalued by the market and has the potential to outperform the market, while a value stock is a company that is expected to grow at an above-average rate compared to other companies in the market. C. A growth stock is a company that pays a high dividend, while a value stock is a company that does not pay a dividend. D. A growth stock is a company that is in a mature industry, while a value stock is a company that is in a growing industry.",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is the difference between a bull market and a bear market?",
        "A. A bull market is a market condition in which stock prices are rising, while a bear market is a market condition in which stock prices are falling. B. A bull market is a market condition in which stock prices are falling, while a bear market is a market condition in which stock prices are rising. C. A bull market is a market condition in which the economy is growing, while a bear market is a market condition in which the economy is in a recession. D. A bull market is a market condition in which there is high volatility, while a bear market is a market condition in which there is low volatility.",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is 'margin' in finance?",
        "A. The difference between the purchase price and the market value of an investment, B. The amount of money borrowed from a broker to purchase securities, C. The amount of money set aside as collateral for a loan, D. The amount of money earned from the sale of an investment",
        1,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is 'short selling' in finance?",
        "A. The practice of selling a security that one does not own with the hope of buying it back at a lower price, B. The practice of buying a security with the hope of selling it at a higher price, C. The practice of holding a security for a long period of time, D. The practice of investing in a diversified portfolio",
        0,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is 'securitization' in finance?",
        "A. The process of converting a pool of assets into a security that can be traded on a market, B. The process of issuing new shares of stock in a company, C. The process of merging two or more companies, D. The process of restructuring a company's debt",
        0,  // Correct Answer Index
        "Hard"
        )

        insertQuizData(
        db,
        "What is the concept of 'modern portfolio theory' in finance?",
        "A. An investment approach that focuses on individual stocks, B. A theory that considers the relationship between risk and return when constructing a portfolio, C. A strategy that involves investing in assets with low volatility, D. An investment approach that aims to replicate the performance of a market index",
        1,  // Correct Answer Index
        "Hard"
        )
        insertQuizData(
        db,
        "What is the 'efficient market hypothesis' in finance?",
        "A. A theory that suggests that financial markets are perfectly efficient, B. A theory that states that it is impossible to consistently beat the market, C. A hypothesis that assumes that all publicly available information is immediately reflected in asset prices, D. A theory that argues that investors are rational and risk-averse",
        2,  // Correct Answer Index
        "Hard"
        )

        insertQuizData(
        db,
        "What is the purpose of a 'collateralized debt obligation' (CDO) in finance?",
        "A. To pool together various types of debt and sell them as securities, B. To provide a mechanism for short-selling stocks, C. To enable investors to speculate on the direction of commodity prices, D. To provide a way for companies to raise capital through the issuance of debt",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the concept of 'leverage' in finance?",
        "A. The use of debt to finance investments, B. The use of equity to finance investments, C. The use of derivatives to hedge risk, D. The use of cash to finance investments",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the purpose of a 'credit default swap' (CDS) in finance?",
        "A. To transfer credit risk from one party to another, B. To provide liquidity to the bond market, C. To enable investors to speculate on the direction of interest rates, D. To provide a mechanism for short-selling bonds",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the concept of 'duration' in finance?",
        "A. A measure of the sensitivity of a bond's price to interest rate changes, B. A measure of the time until a bond matures, C. A measure of the relative risk of an investment compared to a market benchmark, D. A measure of the level of marketability for a security",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the difference between a put option and a call option in finance?",
        "A. A put option gives the holder the right to sell an asset at a specified price, while a call option gives the holder the right to buy an asset at a specified price, B. A put option gives the holder the obligation to sell an asset at a specified price, while a call option gives the holder the obligation to buy an asset at a specified price, C. A put option gives the holder the right to buy an asset at a specified price, while a call option gives the holder the right to sell an asset at a specified price, D. A put option gives the holder the right to buy an asset at a market price, while a call option gives the holder the right to sell an asset at a market price",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the concept of 'debt servicing' in finance?",
        "A. The process of acquiring external funds to cover operating costs, B. The process of managing the total liabilities of a company, C. The process of meeting the contractual obligations of a bond issuer, D. The process of financing the purchase of long-term assets",
        3,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the purpose of the debt covenant in a corporate bond?",
        "A. To limit the issuance of additional debt, B. To provide protection for bondholders in the event of default, C. To determine the eligibility of bondholders for dividend payments, D. To regulate the level of bondholders' financial obligations",
        2,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the concept of 'convexity' in finance?",
        "A. A measure of the stability of the capital market, B. A measure of the sensitivity of a bond's yield to interest rate changes, C. A measure of the relative risk of an investment compared to a market benchmark, D. A measure of the level of marketability for a security",
        2,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the 'beta' coefficient in the capital asset pricing model (CAPM)?",
        "A. A measure of the relative risk of an investment compared to a market benchmark, B. A measure of the risk-free rate of return, C. A measure of the efficiency of the capital market, D. A measure of the level of marketability for a security",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the purpose of a stock split in finance?",
        "A. To increase a company's share price, B. To reduce a company's share price, C. To improve a company's liquidity, D. To enhance a company's trading volume",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the definition of 'alpha' in finance?",
        "A. A measure of a portfolio's volatility, B. A measure of a portfolio's exposure to systematic risk, C. A measure of a portfolio's excess return relative to a benchmark, D. A measure of a portfolio's liquidity",
        3,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the efficient frontier in modern portfolio theory?",
        "A. The set of optimal portfolios that offer the highest expected return for a given level of risk, B. The point at which the risk and return of a portfolio are balanced, C. The portfolio with the lowest possible risk, D. The portfolio with the highest possible return",
        1,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the Black-Scholes Model used for in finance?",
        "A. Calculating the fair value of options,B. Forecasting stock market trends,C. Assessing a company's liquidity,D. Predicting future interest rates",
        0,
        "Hard"
        )

        insertQuizData(
        db,
        "What is the difference between systematic and unsystematic risk?",
        "A. Systematic risk is specific to a particular industry, while unsystematic risk affects the entire market,B. Systematic risk is unpredictable, while unsystematic risk can be anticipated,C. Systematic risk is market-related, while unsystematic risk is specific to a company or industry,D. Systematic risk is the same as unsystematic risk",
        2,
        "Hard"
        )

        insertQuizData(
        db,
        "Explain the concept of net present value (NPV).",
        "A. NPV represents the total profit of a company,B. NPV is the difference between total revenue and total expenses,C. NPV measures the profitability of an investment by comparing its present value to the initial investment,D. NPV is the total value of a company's assets",
        2,
        "Hard"
        )

        insertQuizData(
        db,
        "What is the Modigliani-Miller Theorem in finance?",
        "A. A theory about the optimal capital structure of a firm,B. A theory about the relationship between risk and return,C. A theory about the impact of taxes on corporate financing,D. A theory about the relationship between interest rates and inflation",
        0,
        "Hard"
        )

        insertQuizData(
        db,
        "Define the concept of 'dividend yield' in stock investing.",
        "A. The percentage return on a stock investment based on its market price,B. The total dividends paid by a company divided by its market capitalization,C. The annualized return on a stock investment,D. The ratio of dividends per share to the stock's market price",
        3,
        "Hard"
        )

        insertQuizData(
        db,
        "Explain the Efficient Market Hypothesis (EMH) in finance.",
        "A. The theory that financial markets are always inefficient,B. The theory that stock prices reflect all available information and are therefore always fair and accurate,C. The theory that investors can consistently outperform the market,D. The theory that market prices are always manipulated by large institutional investors",
        1,
        "Hard"
        )

        insertQuizData(
        db,
        "What is the purpose of a sensitivity analysis in financial modeling?",
        "A. To analyze the sensitivity of financial markets to external factors,B. To assess the sensitivity of a project's NPV to changes in key assumptions,C. To evaluate the sensitivity of a company's stock price,D. To analyze the sensitivity of interest rates to changes in inflation",
        1,
        "Hard"
        )

        insertQuizData(
        db,
        "What does the Capital Asset Pricing Model (CAPM) help investors calculate?",
        "A. The expected return on a risky asset,B. The total return on a stock investment,C. The market value of a company's capital,D. The cost of debt for a company",
        0,
        "Hard"
        )

        insertQuizData(
        db,
        "Explain the term 'financial derivatives' in investment.",
        "A. Financial instruments with a fixed return,B. Investments that are considered low-risk,C. Contracts whose value is derived from the price of an underlying asset,D. Securities with guaranteed interest rates",
        2,
        "Hard"
        )

        insertQuizData(
        db,
        "Define the Modigliani Risk-Adjusted Performance (MRAP) measure.",
        "A. A measure of a portfolio's risk-adjusted return,B. A measure of market risk,C. A measure of a company's financial leverage,D. A measure of stock price volatility",
        0,
        "Hard"
        )
        insertQuizData(
        db,
        "How do you calculate the internal rate of return (IRR) of an investment?",
        "A. IRR is the discount rate that makes the NPV of an investment equal to zero,B. IRR is the ratio of the present value of cash inflows to the initial investment,C. IRR is the average rate of return over the investment period,D. IRR is the total return on an investment over the investment period",
        0,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the concept of time value of money?",
        "A. The idea that a dollar today is worth more than a dollar in the future,B. The idea that a dollar in the future is worth more than a dollar today,C. The idea that a dollar today is worth the same as a dollar in the future,D. The idea that a dollar in the future is worth the same as a dollar today",
        0,
        "Hard"
        )
        insertQuizData(
        db,
        "How do you calculate the breakeven point of a business venture?",
        "A. Breakeven point is the point where total revenue equals total expenses,B. Breakeven point is the point where total revenue exceeds total expenses,C. Breakeven point is the point where total expenses exceed total revenue,D. Breakeven point is the point where total expenses are equal to the initial investment",
        0,
        "Hard"
        )
        insertQuizData(
        db,
        "How do you calculate the net present value (NPV) of a series of cash flows?",
        "A. NPV = CF1 / (1 + r) + CF2 / (1 + r)^2 + CF3 / (1 + r)^3 + ... + CFn / (1 + r)^n,B. NPV = CF1 + CF2 + CF3 + ... + CFn,C. NPV = CF1 - CF2 - CF3 - ... - CFn,D. NPV = CF1 * (1 + r) + CF2 * (1 + r)^2 + CF3 * (1 + r)^3 + ... + CFn * (1 + r)^n",
        0,
        "Hard"
        )
        insertQuizData(
        db,
        "How do you calculate the payback period of an investment?",
        "A. Payback period is the time it takes for the initial investment to be recovered by the cash inflows,B. Payback period is the time it takes for the cash inflows to exceed the initial investment,C. Payback period is the time it takes for the cash inflows to equal the initial investment,D. Payback period is the time it takes for the initial investment to be doubled by the cash inflows",
        0,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the concept of beta in finance?",
        "A. Beta is a measure of the risk of an investment,B. Beta is a measure of the return on an investment,C. Beta is a measure of the liquidity of an investment,D. Beta is a measure of the volatility of an investment",
        0,
        "Hard"
        )
        insertQuizData(
        db,
        "Define the term 'financial derivatives' in investment.",
        "A. Financial instruments with a fixed return,B. Investments that are considered low-risk,C. Contracts whose value is derived from the price of an underlying asset,D. Securities with guaranteed interest rates",
        3,
        "Hard"
        )
        insertQuizData(
        db,
        "Explain the concept of CAPM (Capital Asset Pricing Model) in investment.",
        "A. CAPM is a method to calculate the payback period of an investment, B. CAPM is used to determine the market value of a company 's stock, C. CAPM is a model that assesses the relationship between risk and expected return, D. CAPM is a measure of a company's profitability ratio",
        2,
        "Hard"
        )
        insertQuizData(
        db,
        "What is the Black-Scholes-Merton model used for in finance?",
        "A. Calculating the future value of an investment, B. Pricing European-style options, C. Estimating the internal rate of return, D. Analyzing cash flow patterns",
        1,  // Correct Answer Index
        "Hard"
        )
         */


        insertQuizData(db, "What is 3/5 as a decimal?", "A. 0.6, B. 0.3, C. 1.2, D. 0.5", 0, "Easy")

        insertQuizData(
            db,
            "Which of the following is not a prime number?",
            "A. 17, B. 25, C. 31, D. 37",
            1,
            "Easy"
        )


        insertQuizData(
            db,
            "If a car travels 240 miles in 4 hours, how many miles will it travel in 7 hours at the same speed?",
            "A. 420, B. 220, C. 280, D. 340",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "If 5 pens cost $20, how much will 8 pens cost?",
            "A. $32, B. $48, C. $30, D. $50",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "What type of fraction is 2/5?",
            "A. Proper fraction, B. Improper fraction, C. Mixed numeral, D. Compound fraction",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "A shirt is priced at $45 after a 25% discount. What was its original price?",
            "A. $60, B. $55, C. $50, D. $40",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "If the cost price of an article is $80 and the marked price is $100, find the profit percentage.",
            "A. 20%, B. 25%, C. 15%, D. 30%",
            0,
            "Easy"
        )


        insertQuizData(
            db,
            "What is the simple interest on a $2000 loan at 5% per annum for 3 years?",
            "A. $300, B. $250, C. $150, D. $100",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "At what rate of interest will an amount double itself in 8 years?",
            "A. 6.25%, B. 8%, C. 12.5%, D. 16%",
            2,
            "Easy"
        )


        insertQuizData(
            db,
            "If an employee earns $15 per hour and works 8 hours a day, how much does the employee earn in a week (5 working days)?",
            "A. $560, B. $600, C. $480, D. $640",
            2,
            "Easy"
        )


        insertQuizData(
            db,
            "If a person's monthly salary is $3000 and he saves $1200, what percentage of his salary does he save?",
            "A. 40%, B. 35%, C. 30%, D. 45%",
            2,
            "Easy"
        )


        insertQuizData(
            db,
            "What is the median of the following set of numbers: 12, 18, 14, 20, 25?",
            "A. 16, B. 18, C. 20, D. 22",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "If the mean of 5 numbers is 20, and 3 of the numbers are 15, 25, and 30, what is the sum of the remaining two numbers?",
            "A. 25, B. 30, C. 35, D. 40",
            0,
            "Easy"
        )


        insertQuizData(
            db,
            "What is the definition of 'Net Income'?",
            "A. Total revenue minus total expenses, B. Total revenue plus total expenses, C. Total revenue divided by total expenses, D. Total revenue multiplied by total expenses",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Break-Even Point' in business mathematics.",
            "A. Total revenue equals total cost, B. Total revenue is greater than total cost, C. Total revenue is less than total cost, D. Total revenue divided by total cost",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "What does 'ROI' stand for in business?",
            "A. Return on Investment, B. Rate of Interest, C. Revenue of Income, D. Risk of Investment",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Gross Profit' in business terms.",
            "A. Revenue minus expenses, B. Revenue divided by expenses, C. Revenue plus expenses, D. Revenue minus cost of goods sold",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "What is the meaning of 'Amortization' in business finance?",
            "A. Repayment of loan principal, B. Increase in asset value, C. Decrease in liability, D. Allocation of expenses over time",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Cash Flow' in business.",
            "A. Total assets minus total liabilities, B. Total revenue minus total expenses, C. Movement of money in and out of a business, D. Profit earned by a business",
            2,
            "Easy"
        )
        insertQuizData(
            db,
            "What does 'Depreciation' mean in business accounting?",
            "A. Increase in asset value, B. Allocation of expenses over time, C. Repayment of loan principal, D. Decrease in liability",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Equity' in the context of business finance.",
            "A. Total assets minus total liabilities, B. Total revenue minus total expenses, C. Owner's claim on assets after liabilities, D. Movement of money in and out of a business",
            2,
            "Easy"
        )
        insertQuizData(
            db,
            "What is the meaning of 'Profit Margin' in business?",
            "A. Percentage of profit relative to revenue, B. Percentage of revenue relative to profit, C. Total profit divided by total revenue, D. Total revenue minus total expenses",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Working Capital' in business finance.",
            "A. Current assets minus current liabilities, B. Long-term assets minus long-term liabilities, C. Total assets minus total liabilities, D. Total revenue minus total expenses",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Numerator' in fractions.",
            "A. The bottom part of a fraction, B. The top part of a fraction, C. The whole fraction, D. The result of adding two fractions",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "What does 'Denominator' represent in fractions?",
            "A. The top part of a fraction, B. The result of adding two fractions, C. The bottom part of a fraction, D. The whole fraction",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "Name the two types of fractions.",
            "A. Proper and Improper, B. Numerical and Denominal, C. Simple and Complex, D. Odd and Even",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "How do you perform addition of fractions?",
            "A. Multiply the numerators, B. Divide the denominators, C. Add the numerators, D. Subtract the numerators",
            2,
            "Easy"
        )
        insertQuizData(
            db,
            "What is a 'Decimal' in mathematics?",
            "A. A whole number, B. A fraction, C. A number with a decimal point, D. A percentage",
            2,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Percent' in mathematics.",
            "A. A fraction with a denominator of 100, B. A whole number, C. A decimal, D. A ratio",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "How do you perform division of decimals?",
            "A. Multiply the decimals, B. Divide the decimals, C. Add the decimals, D. Subtract the decimals",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "What is the result of multiplying decimals?",
            "A. A fraction, B. A whole number, C. A percentage, D. Another decimal",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Discounts' in business terms.",
            "A. Additional charges on a product, B. Reduction in the original price, C. Commission earned, D. Extra profit",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "What is 'Commission' in the context of sales?",
            "A. Reduction in the original price, B. Extra profit, C. Additional charges on a product, D. Percentage of sales earned",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Ratio' in mathematics.",
            "A. A percentage, B. A fraction, C. A comparison of two quantities, D. A whole number",
            2,
            "Easy"
        )
        insertQuizData(
            db,
            "Name the two types of ratios.",
            "A. Simple and Complex, B. Direct and Inverse, C. Primary and Secondary, D. Odd and Even",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "What is 'Unit Rate' in mathematics?",
            "A. A comparison of two quantities, B. A rate with a denominator of 1, C. A whole number, D. A percentage",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Proportion' in mathematics.",
            "A. A whole number, B. A comparison of two quantities, C. A percentage, D. A fraction with a denominator of 100",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "Explain 'Mark-up', 'Mark-down', and 'Mark-on' in pricing.",
            "A. Increase, decrease, and no change in price, B. Additions to cost, deductions from cost, and total cost, C. Initial price, final price, and total price, D. Original cost, reduced cost, and increased cost",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Cost Price' in business terms.",
            "A. The price at which an item is sold, B. The price at which an item is bought, C. The discounted price, D. The final price after all expenses",
            1,
            "Easy"
        )
        insertQuizData(
            db,
            "What is 'Selling Price' in business?",
            "A. The price at which an item is sold, B. The original cost of an item, C. The discounted price, D. The initial price before any expenses",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Profit' in business.",
            "A. The price at which an item is bought, B. The total revenue, C. The difference between selling price and cost price, D. The original cost of an item",
            2,
            "Easy"
        )
        insertQuizData(
            db,
            "What is 'Sale Price' in business terms?",
            "A. The initial price before any expenses, B. The final price after all expenses, C. The price at which an item is sold, D. The discounted price",
            2,
            "Easy"
        )
        insertQuizData(
            db,
            "Explain 'Break-even' in business.",
            "A. The point where total revenue equals total cost, B. The point where total revenue is greater than total cost, C. The point where total revenue is less than total cost, D. The point where total revenue is divided by total cost",
            0,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Trade Discount' in business mathematics.",
            "A. Reduction in the original price, B. Additional charges on a product, C. Discount offered to the end consumer, D. Discount offered in business-to-business transactions",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "What is 'List Price' in business?",
            "A. The initial price before any expenses, B. The final price after all expenses, C. The discounted price, D. The manufacturer's suggested retail price",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Net Price' in business terms.",
            "A. The price at which an item is sold, B. The final price after all expenses, C. The discounted price, D. The price after deducting all discounts",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "What is an 'Invoice' in business?",
            "A. A bill sent by a customer, B. A receipt of payment, C. A request for a refund, D. A document detailing a transaction and requesting payment",
            3,
            "Easy"
        )
        insertQuizData(
            db,
            "Define 'Mark-up' in pricing.",
            "A. The increase in price from cost to selling price, B. The discount on a product, C. The original cost of a product, D. The profit after a sale",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "What is the 'Net Price' of a product?",
            "A. The price after applying discounts, B. The original price before any discounts, C. The selling price, D. The cost price",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "What does 'Break-even' point represent in business?",
            "A. The point where profit is maximized, B. The point where revenue equals expenses, C. The starting point of a business, D. The point where sales are minimal",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Trade Discount' in business transactions.",
            "A. A discount given to encourage trade, B. The difference between cost price and selling price, C. The total revenue of a business, D. An extra charge on a purchase",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "What is 'Simple Interest' in financial terms?",
            "A. The interest calculated on both the initial principal and the accumulated interest, B. The interest calculated only on the initial principal, C. The total interest earned over a specific period, D. The interest paid in installments",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Principal' in the context of finance.",
            "A. The original amount of money invested or borrowed, B. The interest earned on an investment, C. The profit margin on a sale, D. The total revenue of a business",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "What is the 'Rate of Interest'?",
            "A. The percentage increase in price, B. The cost price of a product, C. The percentage charged for the use of money, D. The profit margin on a sale",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "Explain 'Compound Interest' in financial terms.",
            "A. The interest calculated only on the initial principal, B. The total interest earned over a specific period, C. The interest calculated on both the initial principal and the accumulated interest, D. The interest paid in installments",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "What is 'Conversion Period' in finance?",
            "A. The time it takes to convert a product into cash, B. The time it takes to switch from decimals to fractions, C. The duration of a loan, D. The period during which interest is calculated",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Salary' in terms of income.",
            "A. Payment for hourly work, B. A fixed regular payment, C. Earnings based on sales, D. Additional payment for excellent performance",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Discounts' in the context of pricing.",
            "A. An increase in price, B. A reduction in price, C. The original cost of a product, D. The profit margin on a sale",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "What is the 'Selling Price' of a product?",
            "A. The original price before any discounts, B. The cost price, C. The price after applying discounts, D. The profit on a sale",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "Explain the term 'Profit' in business.",
            "A. The total revenue of a business, B. The percentage increase in price, C. The difference between selling price and cost price, D. The loss incurred in a transaction",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "What is the 'Sale Price' of a product?",
            "A. The original price before any discounts, B. The cost price, C. The price after applying discounts, D. The profit on a sale",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Income' in general terms.",
            "A. Money spent on goods and services, B. Earnings and payments received by an individual or business, C. The profit margin on a sale, D. The total revenue of a business",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "What is 'Benefit' in financial terms?",
            "A. A reduction in price, B. A gain or advantage resulting from something, C. The original cost of a product, D. The loss incurred in a transaction",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "What does 'Invoice' represent in business transactions?",
            "A. A request for payment, B. A receipt for a purchase, C. A discount given to encourage trade, D. An extra charge on a purchase",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "Explain the term 'Discount Series'.",
            "A. A sequence of discounts applied in a specific order, B. A series of price increases, C. The total revenue of a business, D. The profit margin on a sale",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "What is 'List Price' in pricing?",
            "A. The original price before any discounts, B. The cost price, C. The price after applying discounts, D. The maximum price at which a product is offered for sale",
            3,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Invoice Price' in business.",
            "A. The amount paid after applying discounts, B. The original price before any discounts, C. The total revenue of a business, D. The profit margin on a sale",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Interest' in financial terms.",
            "A. The profit earned on a sale, B. The fee paid for a service, C. The cost price of a product, D. The cost of borrowing money",
            3,
            "Easy"
        )

        insertQuizData(
            db,
            "What does 'Unit Rate' measure in a ratio?",
            "A. The total of the ratio, B. The difference between two quantities, C. The rate for one unit of a quantity, D. The sum of two numbers",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "Explain the term 'Mark-down' in pricing.",
            "A. An increase in price, B. A reduction in price, C. The original cost of a product, D. The profit margin on a sale",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "What is 'Mark-on' in pricing?",
            "A. The profit earned on a sale, B. An additional cost on a purchase, C. The original cost of a product, D. The total revenue of a business",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Ratio' in the context of mathematics.",
            "A. A fraction, B. A whole number, C. A comparison of two quantities, D. The sum of two numbers",
            2,
            "Easy"
        )

        insertQuizData(
            db,
            "What is the 'Break-even' point in business?",
            "A. The point where profit is maximized, B. The point where revenue equals expenses, C. The starting point of a business, D. The point where sales are minimal",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Net Price' in business transactions.",
            "A. The price after applying discounts, B. The original price before any discounts, C. The selling price, D. The cost price",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "What is 'Discount Series' in pricing?",
            "A. A sequence of discounts applied in a specific order, B. A series of price increases, C. The total revenue of a business, D. The profit margin on a sale",
            0,
            "Easy"
        )

        insertQuizData(
            db,
            "Explain the term 'Simple Interest'.",
            "A. The interest calculated only on the initial principal, B. The total interest earned over a specific period, C. The interest paid in installments, D. The interest calculated on both the initial principal and the accumulated interest",
            1,
            "Easy"
        )

        insertQuizData(
            db,
            "Define 'Salary' in terms of income.",
            "A. Payment for hourly work, B. A fixed regular payment, C. Earnings based on sales, D. Additional payment for excellent performance",
            1,
            "Easy"
        )

        //____________________MEDIUM______________MEDIUM___________MEDIUM______________MEDIUM_______________________MEDIUM___________MEDIUM_____________________________MEDIUM_______________________________MEDIUM???????__________________________MEDIUM________________________________MEDIUM_________________________MEDIUM_____________________________MEDIUM_________________________________MEDIUM____________________________________MEDIUM__________________________________MEDIUM


        insertQuizData(
            db,
            "If a person earns $4500 per month and spends 25% on rent, 30% on groceries, and saves the rest, how much does the person save?",
            "A. $2475, B. $2025, C. $1875, D. $1750",
            0,
            "Medium"
        )
        insertQuizData(
            db,
            "What is the relationship between the numerator and denominator in an improper fraction?",
            "A. Numerator is less than the denominator, B. Numerator is equal to the denominator, C. Numerator is greater than the denominator, D. Numerator is not related to the denominator",
            2,
            "Medium"
        )

        insertQuizData(
            db,
            "What is the formula for calculating the Rate of Mark-up?",
            "A. (SELLING PRICE - COST PRICE) / COST PRICE × 100%, B. (COST PRICE - SELLING PRICE) / COST PRICE × 100%, C. (SELLING PRICE + COST PRICE) / COST PRICE × 100%, D. (COST PRICE + SELLING PRICE) / COST PRICE × 100%",
            0,
            "Medium"
        )

        insertQuizData(
            db,
            "In the formula for Simple Interest (I = Prt), what does 'r' represent?",
            "A. Principal amount of loan or investment, B. Amount of interest, C. Time period of the loan or investment, D. Rate of interest",
            3,
            "Medium"
        )

        insertQuizData(
            db,
            "In the formula for Compound Interest (F = P(1 + i)n), what does 'i' represent?",
            "A. Future value or accumulated amount at the end of n conversion periods, B. Principal, C. Nominal interest rate per year (annual rate), D. j/m where j is the nominal interest rate per year and m is the number of the conversion period",
            3,
            "Medium"
        )

        insertQuizData(
            db,
            "What is the formula for calculating the Single Discount Rate?",
            "A. 1 - [(100% - Discount rate 1)(100% - Discount rate 2)...], B. 1 + [(100% - Discount rate 1)(100% - Discount rate 2)...], C. 1 - [(100% + Discount rate 1)(100% + Discount rate 2)...], D. 1 + [(100% + Discount rate 1)(100% + Discount rate 2)...]",
            0,
            "Medium"
        )

        insertQuizData(
            db,
            "What is the formula for calculating the Future Value in Compound Interest?",
            "A. F = P(1 + i)n, B. F = P(1 - i)n, C. F = P(1 + i)/n, D. F = P(1 - i)/n",
            0,
            "Medium"
        )

        insertQuizData(
            db,
            "What is the formula for calculating the Effective Rate of Interest (R)?",
            "A. R = (1 + j/m)^m - 1, B. R = (1 - j/m)^m - 1, C. R = (1 + j/m)^m + 1, D. R = (1 - j/m)^m + 1",
            0,
            "Medium"
        )





        //HARD_____________HARD_____________________HARD________________HARD____________________HARD_________________HARD_______________HARD__________________HARD_____________________________________HARD_________________________________________HARD__________________________________________HARD_______________HARD_____________________________________________HARD_____________________________________________HARD______________________________________HARD

        insertQuizData(
            db,
            "Evaluate the implications of Tax Exemptions for Non-Minimum Wage Earners in the context of Salaries and Wages.",
            "A. Non-Minimum Wage Earners are not eligible for tax exemptions, B. Tax exemptions apply only to employees with fringe benefits, C. Tax exemptions are based on income brackets, D. Tax exemptions provide relief for certain income levels",
            3, // Correct Answer Index (D)
            "Hard"
        )

        insertQuizData(
            db,
            "Examine the role of 13th and 14th Month Pays in the benefits of a Salary/Wage Earner.",
            "A. They are discretionary bonuses offered by employers, B. They are additional months of salary paid annually, C. They represent deferred compensation for employees, D. They are government-mandated payments for all employees",
            1, // Correct Answer Index (B)
            "Hard"
        )
        insertQuizData(
            db,
            "What is the difference between nominal and ordinal level of measurement?",
            "A. Nominal level of measurement is used for categorical data while ordinal level of measurement is used for numerical data, B. Nominal level of measurement is used for numerical data while ordinal level of measurement is used for categorical data, C. Nominal level of measurement is used for data that can be ranked while ordinal level of measurement is used for data that cannot be ranked, D. Nominal level of measurement is used for data that cannot be ranked while ordinal level of measurement is used for data that can be ranked",
            3,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Break-Even price (Cost Price + Operating Cost), what happens to the Break-Even price as the Operating Cost increases while the Cost Price remains constant?",
            "A. The Break-Even price increases, B. The Break-Even price decreases, C. The Break-Even price remains constant, D. The Break-Even price becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Future Value in Compound Interest (F = P(1 + i)n), what happens to the Future Value as 'n' (the total number of conversion periods) increases?",
            "A. The Future Value increases, B. The Future Value decreases, C. The Future Value remains constant, D. The Future Value becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Single Discount Rate (1 - [(100% - Discount rate 1)(100% - Discount rate 2)...]), what happens to the Single Discount Rate as each individual Discount Rate increases?",
            "A. The Single Discount Rate increases, B. The Single Discount Rate decreases, C. The Single Discount Rate remains constant, D. The Single Discount Rate becomes negative",
            1,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Effective Rate of Interest (R = (1 + j/m)^m - 1), what happens to R as 'j' (the nominal interest rate per year) increases?",
            "A. R increases, B. R decreases, C. R remains constant, D. R becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "What is the relationship between the conversion period and the nominal interest rate in the formula for Compound Interest (F = P(1 + i)n)?",
            "A. The conversion period is the reciprocal of the nominal interest rate, B. The conversion period is the product of the nominal interest rate and the number of years, C. The conversion period is the quotient of the nominal interest rate and the number of years, D. The conversion period is the sum of the nominal interest rate and the number of years",
            2,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Effective Rate of Interest (R = (1 + j/m)^m - 1), what happens to R as m approaches infinity?",
            "A. R approaches infinity, B. R approaches zero, C. R approaches j, D. R approaches 1",
            2,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Single Discount Rate (1 - [(100% - Discount rate 1)(100% - Discount rate 2)...]), what happens to the Single Discount Rate as the number of discount rates increases?",
            "A. The Single Discount Rate increases, B. The Single Discount Rate decreases, C. The Single Discount Rate remains constant, D. The Single Discount Rate becomes negative",
            1,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating Mark-up (SELLING PRICE - COST PRICE), what happens to the Mark-up as the Selling Price increases while the Cost Price remains constant?",
            "A. The Mark-up increases, B. The Mark-up decreases, C. The Mark-up remains constant, D. The Mark-up becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating Mark-down (SELLING PRICE - SALE PRICE), what happens to the Mark-down as the Selling Price decreases while the Sale Price remains constant?",
            "A. The Mark-down increases, B. The Mark-down decreases, C. The Mark-down remains constant, D. The Mark-down becomes negative",
            0,
            "Hard"
        )


        insertQuizData(
            db,
            "Explain the concept of Effective Rate of Interest (R) in Compound Interest.",
            "A. It is the interest rate that includes all nominal rates, B. It is the interest rate that is applied annually, C. It is the interest rate that considers the compounding frequency, D. It is the interest rate that is adjusted for inflation",
            2, // Correct Answer Index (C)
            "Hard"
        )

        insertQuizData(
            db,
            "Discuss the various benefits of a Salary/Wage Earner, including De Minimis Benefits and Fringe Benefits.",
            "A. Explain the benefits in terms of income tax, B. Discuss the legal aspects of benefits, C. Provide examples of benefits in salary and wages, D. Analyze the impact of benefits on employee motivation",
            2, // Correct Answer Index (C)
            "Hard"
        )
        insertQuizData(
            db,
            "What is the relationship between the conversion period and the nominal interest rate in the formula for Compound Interest (F = P(1 + i)n)?",
            "A. The conversion period is the reciprocal of the nominal interest rate, B. The conversion period is the product of the nominal interest rate and the number of years, C. The conversion period is the quotient of the nominal interest rate and the number of years, D. The conversion period is the sum of the nominal interest rate and the number of years",
            2,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Effective Rate of Interest (R = (1 + j/m)^m - 1), what happens to R as m approaches infinity?",
            "A. R approaches infinity, B. R approaches zero, C. R approaches j, D. R approaches 1",
            2,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Single Discount Rate (1 - [(100% - Discount rate 1)(100% - Discount rate 2)...]), what happens to the Single Discount Rate as the number of discount rates increases?",
            "A. The Single Discount Rate increases, B. The Single Discount Rate decreases, C. The Single Discount Rate remains constant, D. The Single Discount Rate becomes negative",
            1,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating Mark-up (SELLING PRICE - COST PRICE), what happens to the Mark-up as the Selling Price increases while the Cost Price remains constant?",
            "A. The Mark-up increases, B. The Mark-up decreases, C. The Mark-up remains constant, D. The Mark-up becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating Mark-down (SELLING PRICE - SALE PRICE), what happens to the Mark-down as the Selling Price decreases while the Sale Price remains constant?",
            "A. The Mark-down increases, B. The Mark-down decreases, C. The Mark-down remains constant, D. The Mark-down becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Break-Even price (Cost Price + Operating Cost), what happens to the Break-Even price as the Operating Cost increases while the Cost Price remains constant?",
            "A. The Break-Even price increases, B. The Break-Even price decreases, C. The Break-Even price remains constant, D. The Break-Even price becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Future Value in Compound Interest (F = P(1 + i)n), what happens to the Future Value as 'n' (the total number of conversion periods) increases?",
            "A. The Future Value increases, B. The Future Value decreases, C. The Future Value remains constant, D. The Future Value becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Single Discount Rate (1 - [(100% - Discount rate 1)(100% - Discount rate 2)...]), what happens to the Single Discount Rate as each individual Discount Rate increases?",
            "A. The Single Discount Rate increases, B. The Single Discount Rate decreases, C. The Single Discount Rate remains constant, D. The Single Discount Rate becomes negative",
            1,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating the Effective Rate of Interest (R = (1 + j/m)^m - 1), what happens to R as 'j' (the nominal interest rate per year) increases?",
            "A. R increases, B. R decreases, C. R remains constant, D. R becomes negative",
            0,
            "Hard"
        )

        insertQuizData(
            db,
            "In the formula for calculating Mark-up (SELLING PRICE - COST PRICE), what happens to the Mark-up as the Cost Price increases while the Selling Price remains constant?",
            "A. The Mark-up increases, B. The Mark-up decreases, C. The Mark-up remains constant, D. The Mark-up becomes negative",
            1,
            "Hard"
        )
        

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUIZ")
        onCreate(db)

    }

    private fun insertQuizData(
        db: SQLiteDatabase,
        question: String,
        options: String,
        correctAnswerIndex: Int,
        difficultyLevel: String
    ) {
        val values = ContentValues().apply {
            put(COLUMN_QUESTION, question)
            put(COLUMN_OPTIONS, options)
            put(COLUMN_CORRECT_ANSWER_INDEX, correctAnswerIndex)
            put(COLUMN_DIFFICULTY_LEVEL, difficultyLevel)
        }

        db.insert(TABLE_QUIZ, null, values)
    }

}
