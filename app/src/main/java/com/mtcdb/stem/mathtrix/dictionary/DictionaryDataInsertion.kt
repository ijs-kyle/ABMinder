package com.mtcdb.stem.mathtrix.dictionary

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle

class DictionaryDataInsertion : Activity() {

    private lateinit var databaseHelper: DictionaryDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        insertTermsIntoDatabase()
        databaseHelper = DictionaryDatabaseHelper(this)
    }

    private fun insertTermsIntoDatabase() {
        val databaseHelper = DictionaryDatabaseHelper(this)
        val db = databaseHelper.writableDatabase

        //checks if the terms are already inserted
        val isDatabasePopulated = checkIfDatabaseIsPopulated(db)

        if (!isDatabasePopulated) {
            db.beginTransaction()
            try {
                val termsToInsert = listOf(
                    Triple(
                        "Asset",
                        "Anything of value owned by a person or company, which can be converted into cash or provide future economic benefits.",
                        "Fixed assets (e.g., land, buildings); Current assets (e.g., cash, inventory)"
                    ),
                    Triple(
                        "Audit",
                        "A systematic examination of financial records, statements, or operations to ensure accuracy, compliance, and adherence to regulations.",
                        "Internal audit (conducted by the company's own auditors to assess internal controls); External audit (performed by independent auditors for regulatory compliance)"
                    ),
                    Triple(
                        "Annual Report",
                        "A comprehensive report by a company, providing financial and operational information to shareholders and the public, typically published on an annual basis.",
                        "Financial statements (e.g., balance sheet, income statement) summarizing financial performance; Shareholder report detailing company achievements and future goals"
                    ),
                    Triple(
                        "Angel Investor",
                        "An individual who provides capital for a business startup in exchange for ownership equity, mentorship, or other involvement.",
                        "Startup funding (providing initial capital for a tech startup); Seed capital (investing in early-stage ventures with high growth potential)"
                    ),
                    Triple(
                        "Accounts Payable",
                        "The amount of money a company owes to its suppliers or creditors for goods or services received.",
                        "Payment terms negotiated with suppliers for raw materials; Outstanding invoices awaiting payment for services rendered"
                    ),
                    Triple(
                        "Accrual Accounting",
                        "An accounting method where revenue and expenses are recorded when they are earned or incurred, not when cash is exchanged.",
                        "Recognizing revenue when goods are delivered, regardless of payment timing; Recording expenses when incurred, even if payment is delayed"
                    ),
                    Triple(
                        "Advertising",
                        "The promotion of a product, service, or brand to attract and persuade customers.",
                        "Marketing strategy (utilizing social media, TV ads); Ad campaign promoting a new product launch"
                    ),
                    Triple(
                        "Agile Methodology",
                        "A project management and product development approach that prioritizes flexibility and collaboration.",
                        "Scrum (iterative development process); Sprint planning (short development cycles for rapid product iteration)"
                    ),
                    Triple(
                        "Asset Turnover Ratio",
                        "A financial ratio that measures a company's ability to generate revenue from its assets.",
                        "Asset efficiency (how efficiently assets are utilized to generate sales); Sales turnover (speed at which inventory is sold)"
                    ),
                    Triple(
                        "Accounts Receivable Turnover",
                        "A ratio that measures how efficiently a company collects payments on its receivables.",
                        "Days sales outstanding (average number of days to collect receivables); Receivables management (strategies to improve collection efficiency)"
                    ),
                    Triple(
                        "Affiliate Marketing",
                        "A performance-based marketing strategy where a business rewards affiliates for bringing in customers or traffic through the affiliate's marketing efforts.",
                        "Affiliate network (platform facilitating affiliate partnerships); Commission-based marketing (compensating affiliates based on performance)"
                    ),
                    Triple(
                        "Antitrust Laws",
                        "Laws designed to promote fair competition and prevent monopolies or other anticompetitive business practices.",
                        "Competition law (regulating market competition); Regulatory compliance (ensuring adherence to antitrust regulations)"
                    ),
                    Triple(
                        "Asset Allocation",
                        "The strategic distribution of a portfolio's investments among various asset classes to optimize risk and return.",
                        "Diversification (reducing risk by investing in different asset types); Investment strategy (allocating funds based on risk tolerance)"
                    ),
                    Triple(
                        "Annual Percentage Rate (APR)",
                        "The annualized interest rate that considers additional fees and costs associated with a loan or financial product.",
                        "Examples: Loan cost calculation (estimating total borrowing expenses); Interest rate (cost of borrowing expressed annually)"
                    ),
                    Triple(
                        "Accounts Receivable Financing",
                        "A form of financing where a company uses its receivables as collateral to obtain a loan.",
                        "Factoring (selling receivables to a third party for immediate cash); Working capital financing (using receivables to secure short-term funding)"
                    ),
                    Triple(
                        "Arbitrage",
                        "The practice of taking advantage of price differences in different markets to make a profit.",
                        "Risk-free profit (exploiting market inefficiencies for financial gain); Market inefficiency (discrepancies in asset prices across markets)"
                    ),
                    Triple(
                        "Brand Equity",
                        "The perceived value or strength of a brand based on consumer perception and brand loyalty.",
                        "Brand recognition (awareness and familiarity with a brand); Brand building (activities aimed at enhancing brand value)"
                    ),
                    Triple(
                        "Break-Even Point",
                        "The level of sales at which a business covers its total costs and neither makes a profit nor incurs a loss.",
                        "Cost-volume-profit analysis (evaluating profitability at different sales levels); Breakeven analysis (determining breakeven sales volume)"
                    ),
                    Triple(
                        "Business Intelligence (BI)",
                        "The use of data analysis tools and techniques to make informed business decisions.",
                        "Data analytics (processing and interpreting business data); Decision support (using insights for strategic planning)"
                    ),
                    Triple(
                        "Balance Sheet",
                        "A financial statement that provides a snapshot of a company's financial position, showing assets, liabilities, and equity.",
                        "Financial statement (documenting financial status); Accounting (recording and reporting financial transactions)"
                    ),
                    Triple(
                        "Benchmarking",
                        "The process of comparing a company's performance metrics with those of industry leaders or competitors.",
                        "Performance measurement (evaluating company performance); Competitive analysis (assessing market position)"
                    ),
                    Triple(
                        "Back-End",
                        "The technical infrastructure of a software application, responsible for server-side processing and data storage.",
                        "Front-end vs. back-end (user interface vs. server logic); Web development (building and maintaining software systems)"
                    ),
                    Triple(
                        "Big Data",
                        "Large and complex sets of data that require advanced tools and technologies for analysis and processing.",
                        "Data analytics (extracting insights from large datasets); Data science (applying statistical techniques to big data)"
                    ),
                    Triple(
                        "Brainstorming",
                        "A creative problem-solving technique where a group generates ideas and solutions through open and spontaneous discussion.",
                        "Idea generation (sparking creativity); Team collaboration (working together to solve challenges)"
                    ),
                    Triple(
                        "Bull Market",
                        "A financial market characterized by rising prices and optimism among investors.",
                        "Stock market trends (direction of market movements); Market conditions (factors influencing investor sentiment)"
                    ),
                    Triple(
                        "Business Model",
                        "The plan or framework that outlines how a company creates, delivers, and captures value.",
                        "Revenue streams (sources of income); Monetization strategy (methods for generating revenue)"
                    ),
                    Triple(
                        "Buyer Persona",
                        "A detailed and semi-fictional representation of a company's ideal customer, based on market research and data.",
                        "Marketing strategy (targeting specific customer segments); Customer profiling (understanding buyer behaviors)"
                    ),
                    Triple(
                        "Blue Ocean Strategy",
                        "A business approach that focuses on creating and capturing new market space, rather than competing in existing markets.",
                        "Innovation strategy (pioneering new market opportunities); Market differentiation (creating unique value propositions)"
                    ),
                    Triple(
                        "Budget Variance",
                        "The difference between the planned or budgeted amount and the actual amount spent or earned.",
                        "Financial planning (allocating resources); Budget analysis (evaluating budget performance)"
                    ),
                    Triple(
                        "Burn Rate",
                        "The rate at which a company uses its available cash or capital, often expressed as a monthly or weekly expenditure.",
                        "Cash burn (rate of cash depletion); Runway (duration until funds are exhausted)"
                    ),
                    Triple(
                        "Brand Positioning",
                        "The way a brand is perceived in the minds of consumers relative to competitors.",
                        "Brand identity (unique brand characteristics); Market positioning (strategic brand placement)"
                    ),
                    Triple(
                        "Business Process Reengineering (BPR)",
                        "The redesign and optimization of business processes to achieve improvements in efficiency, quality, and performance.",
                        "Examples: Process improvement (enhancing operational workflows); Organizational change (transforming business practices)"
                    ),
                    Triple(
                        "B2B (Business-to-Business)",
                        "Commerce transactions between businesses, where products or services are sold from one business to another.",
                        "B2B marketing (targeting corporate clients); Corporate sales (business-to-business transactions)"
                    ),
                    Triple(
                        "B2C (Business-to-Consumer)",
                        "Commerce transactions between a business and individual consumers, involving the sale of products or services.",
                        "B2C e-commerce (online retailing); Retail marketing (promoting products to individual shoppers)"
                    ),
                    Triple(
                        "Blockchain",
                        "A decentralized and distributed digital ledger technology used to record transactions across multiple computers.",
                        "Cryptocurrency (digital currencies using blockchain); Smart contracts (self-executing contracts on blockchain)"
                    ),
                    Triple(
                        "Bootstrapping",
                        "The process of building or starting a business with minimal external capital or resources.",
                        "Self-funding (using personal savings for startup); Lean startup (building a business with limited resources)"
                    ),
                    Triple(
                        "Bill of Materials (BOM)",
                        "A comprehensive list of materials, components, and assemblies required to manufacture a product.",
                        "Manufacturing (producing goods); Production planning (scheduling and managing manufacturing processes)"
                    ),
                    Triple(
                        "Brand Ambassador",
                        "An individual or influencer who represents and promotes a brand to increase awareness and attract customers.",
                        "Brand advocacy (endorsing products on social media); Influencer marketing (leveraging influencers to reach target audience)"
                    ),
                    Triple(
                        "Business Continuity Planning",
                        "The process of creating systems and procedures to ensure a company can operate and recover from disruptions or disasters.",
                        "Disaster recovery (resuming operations after a crisis); Risk management (mitigating potential threats)"
                    ),
                    Triple(
                        "Beta Testing",
                        "The second phase of software testing, conducted by a select group of external users before the official release.",
                        "User acceptance testing (evaluating product usability); Quality assurance (ensuring product reliability)"
                    ),
                    Triple(
                        "Cash Conversion Cycle (CCC)",
                        "The time it takes for a company to convert its investments in inventory and other resources into cash flow from sales.",
                        "Working capital management (optimizing cash flow); Inventory turnover (speed of inventory conversion)"
                    ),
                    Triple(
                        "Corporate Social Responsibility (CSR)",
                        "A company's commitment to ethical and responsible business practices that contribute to social and environmental well-being.",
                        "Sustainability (environmental conservation initiatives); Social impact (community development projects)"
                    ),
                    Triple(
                        "Cost of Goods Sold (COGS)",
                        "The direct costs incurred in producing a product or delivering a service, including materials and labor.",
                        "COGS formula (calculation method); Gross profit (revenue minus COGS)"
                    ),
                    Triple(
                        "Crowdfunding",
                        "A fundraising method where individuals contribute small amounts of money to support a project or venture.",
                        "Kickstarter (popular crowdfunding platform); Crowdfunding platforms (online portals for fundraising)"
                    ),
                    Triple(
                        "Customer Acquisition Cost (CAC)",
                        "The total cost a company incurs to acquire a new customer, including marketing and sales expenses.",
                        "Customer retention (efforts to retain existing customers); CAC calculation (determining cost-effectiveness of acquisition)"
                    ),
                    Triple(
                        "Competitive Advantage",
                        "A unique strength or attribute that gives a company a significant advantage over its competitors.",
                        "Market differentiation (setting oneself apart from competitors); Strategic positioning (strategically positioning products in the market)"
                    ),
                    Triple(
                        "Customer Relationship Management (CRM)",
                        "A technology and strategy for managing a company's interactions with current and potential customers.",
                        "CRM software (tools for managing customer relationships); Customer loyalty (building and maintaining customer trust)"
                    ),
                    Triple(
                        "Core Competency",
                        "A specific area of expertise or capability that gives a company a competitive advantage in its industry.",
                        "Strategic management (managing core competencies); Competitive strategy (leveraging core competencies for competitive advantage)"
                    ),
                    Triple(
                        "Collaborative Economy",
                        "A business model where individuals share resources, services, or products through digital platforms.",
                        "Sharing economy (peer-to-peer resource sharing); Peer-to-peer (direct exchange between individuals)"
                    ),
                    Triple(
                        "Capital Expenditure (CapEx)",
                        "Spending on assets that will provide long-term benefits, such as property, equipment, or infrastructure.",
                        "CapEx vs. OpEx (capital vs. operating expenses); Capital budgeting (allocating funds for long-term investments)"
                    ),
                    Triple(
                        "Cost Leadership",
                        "A competitive strategy where a company aims to become the lowest-cost producer in its industry.",
                        "Cost advantage (lower costs compared to competitors); Economies of scale (cost advantages from increased production)"
                    ),
                    Triple(
                        "Convertible Bond",
                        "A type of bond that can be converted into a predetermined number of shares of the issuing company's stock.",
                        "Debt financing (raising funds through borrowing); Financial instruments (securities used for investment)"
                    ),
                    Triple(
                        "Cross-Selling",
                        "The strategy of selling additional products or services to existing customers, often related to their initial purchase.",
                        "Upselling (encouraging customers to buy higher-value items); Sales strategy (tactics for increasing revenue)"
                    ),
                    Triple(
                        "Critical Path Method (CPM)",
                        "A project management technique that identifies the sequence of tasks with the longest duration, determining the project's critical path.",
                        "Project scheduling (timelines for completing tasks); Task dependency (sequence of activities)"
                    ),
                    Triple(
                        "Crisis Management",
                        "The process of preparing for and responding to a crisis or emergency situation to minimize damage and ensure recovery.",
                        "Risk mitigation (reducing potential negative impacts); Emergency response (actions taken during crises)"
                    ),
                    Triple(
                        "Consumer Behavior",
                        "The study of how individuals and groups make decisions to satisfy their needs and wants as consumers.",
                        "Market research (collecting data on consumer preferences); Buying habits (patterns of consumer purchasing)"
                    ),
                    Triple(
                        "Cross-Functional Team",
                        "A team that includes members from different departments or functions within an organization, working together on a common goal.",
                        "Team collaboration (working together towards shared objectives); Interdisciplinary (integrating multiple disciplines)"
                    ),
                    Triple(
                        "Cost-Benefit Analysis",
                        "A systematic process for assessing the pros and cons of an action, decision, or project, comparing costs and expected benefits.",
                        "Decision-making (choosing between alternatives); Project evaluation (assessing project feasibility)"
                    ),
                    Triple(
                        "Corporate Governance",
                        "The system of rules, practices, and processes by which a company is directed and controlled, involving relationships among stakeholders.",
                        "Board of directors (governing body overseeing company operations); Ethical standards (guidelines for corporate conduct)"
                    ),
                    Triple(
                        "Customer Segmentation",
                        "The process of dividing a market into distinct groups of buyers with similar characteristics or needs.",
                        "Target audience (specific customer groups); Marketing strategy (tailoring offerings to segmented markets)"
                    ),
                    Triple(
                        "Crowdsourcing",
                        "The practice of obtaining ideas, content, or services by soliciting contributions from a large group of people, typically online.",
                        "Open innovation (collaborative idea generation); Collective intelligence (tapping into collective knowledge)"
                    ),
                    Triple(
                        "Centralized Organization",
                        "An organizational structure where decision-making authority is concentrated at the top levels of management.",
                        "Hierarchy (organizational structure); Organizational design (structuring decision-making processes)"
                    ),
                    Triple(
                        "Customer Lifetime Value (CLV)",
                        "The predicted total revenue a company expects to earn from a customer throughout their entire relationship.",
                        "Retention strategy (efforts to retain customers); CLV calculation (predicting long-term customer value)"
                    ),
                    Triple(
                        "Cost of Capital",
                        "The required rate of return that a company must achieve on its investments to maintain or increase its stock price.",
                        "WACC (weighted average cost of capital); Capital budgeting (allocating funds for investment opportunities)"
                    ),
                    Triple(
                        "Continuous Improvement",
                        "An ongoing effort to improve products, services, or processes through incremental and iterative changes.",
                        "Kaizen (Japanese concept of continuous improvement); Process optimization (enhancing operational efficiency)"
                    ),
                    Triple(
                        "Corporate Culture",
                        "The shared values, beliefs, and practices that shape the behavior and attitudes of individuals within an organization.",
                        "Organizational culture (company values and norms); Company values (core beliefs guiding behavior)"
                    ),
                    Triple(
                        "Competitive Intelligence",
                        "The process of gathering, analyzing, and using information about competitors and the competitive environment.",
                        "Market research (collecting competitor data); Business strategy (using competitor insights to inform strategy)"
                    ),
                    Triple(
                        "Cash Reserve Ratio",
                        "The percentage of a bank's deposits that must be held in reserve, not available for lending or investment.",
                        "Monetary policy (regulating money supply); Banking regulation (government rules for financial institutions)"
                    ),
                    Triple(
                        "Customer Churn",
                        "The rate at which customers stop doing business with a company over a given period.",
                        "Churn rate (percentage of customers lost); Customer retention (strategies to retain customers)"
                    ),
                    Triple(
                        "Cross-Training",
                        "The practice of training employees to perform tasks or responsibilities outside of their primary roles, increasing versatility.",
                        "Skill development (broadening employee capabilities); Employee training (ongoing professional development)"
                    ),
                    Triple(
                        "Cost-plus Pricing",
                        "A pricing strategy where a company determines the cost of producing a product and adds a markup to establish the selling price.",
                        "Markup (profit margin added to cost); Pricing strategy (setting product prices)"
                    ),
                    Triple(
                        "Cloud Computing",
                        "The delivery of computing services, including storage, processing power, and software, over the internet.",
                        "Infrastructure as a Service (IaaS) (cloud-based infrastructure); SaaS (Software as a Service) (cloud-based software)"
                    ),
                    Triple(
                        "Competitive Analysis",
                        "The process of evaluating and comparing a company's strengths and weaknesses against those of its competitors.",
                        "SWOT analysis (assessing strengths, weaknesses, opportunities, and threats); Market positioning (strategic differentiation)"
                    ),
                    Triple(
                        "Time Series Analysis",
                        "A statistical method that involves analyzing and interpreting data points collected over successive intervals of time to identify patterns or trends.",
                        "Analyzing monthly sales data for a retail business over the past three years to identify seasonal patterns and trends."
                    ),
                    Triple(
                        "Capital Gains",
                        "The profit realized from the sale of an asset, such as stocks or real estate, calculated as the difference between the selling price and the purchase price.",
                        "If you purchase 100 shares of a company's stock at $50 per share and sell them a year later at $65 per share, your capital gain is $15 per share"
                    ),
                    Triple(
                        "Dividend Yield",
                        "A financial ratio calculated by dividing the annual dividend payment by the stock's current market price, expressing the yield as a percentage.",
                        "If a stock is trading at $100 per share and pays an annual dividend of $5 per share, the dividend yield is 5% ($5/$100)."
                    ),
                    Triple(
                        "Cost of Equity",
                        "The return a company is expected to provide to its equity investors, often calculated using the Capital Asset Pricing Model (CAPM).",
                        "Using the Capital Asset Pricing Model (CAPM) to estimate the cost of equity, where the risk-free rate is 3%, the market risk premium is 7%, and the company's beta is 1.2, the cost of equity would be 11.4%."
                    ),
                    Triple(
                        "Default Risk",
                        "The risk that a borrower may fail to meet their debt obligations, leading to a potential loss for the lender.",
                        "A lender assessing a borrower's creditworthiness to determine the level of default risk before issuing a loan."
                    ),
                    Triple(
                        "Discounted Cash Flow (DCF)",
                        "A valuation method that estimates the present value of a future stream of cash flows, commonly used in investment analysis.",
                        "Using DCF to evaluate the potential return of an investment project by discounting its expected future cash flows."
                    ),
                    Triple(
                        "Operating Cycle",
                        "The time it takes for a company to convert its investments in raw materials, production, and receivables into cash through sales.",
                        "Calculating a company's operating cycle to understand its cash flow dynamics and working capital needs."
                    ),
                    Triple(
                        "Futures Contract",
                        "A standardized financial contract obligating the buyer to purchase, or the seller to sell, an underlying asset at a specified future date and agreed-upon price.",
                        "A farmer enters into a futures contract to sell 1,000 bushels of wheat at a price of $5 per bushel, with delivery scheduled in three months."
                    ),
                    Triple(
                        "Return on Assets (ROA)",
                        "A financial ratio that measures a company's efficiency in utilizing its assets to generate profits.",
                        "Example: If a company has a net income of $500,000 and total assets of $5 million, the ROA would be 10% ($500,000/$5,000,000)."
                    ),
                    Triple(
                        "Scenario Analysis",
                        "An analytical technique that examines various possible future events and their potential impact on a business or investment.",
                        "Assessing the potential financial impact of different economic scenarios (e.g., recession, growth) on a company's sales and profitability."
                    ),
                    Triple(
                        "Working Capital Turnover",
                        "A financial ratio that measures how efficiently a company uses its working capital to generate sales.",
                        "If a company has an annual revenue of $2 million and an average working capital of $500,000, the working capital turnover ratio would be 4 ($2,000,000/$500,000), indicating the company generates $4 in sales for every dollar of working capital"
                    ),
                    Triple(
                        "Net Present Value (NPV)",
                        "The present value of all future cash inflows and outflows associated with an investment, used to assess its profitability.",
                        "Calculating the NPV of a project to determine if it's expected to generate positive returns."
                    ),
                    Triple(
                        "Internal Rate of Return (IRR)",
                        "The discount rate at which the NPV of an investment is 0, commonly used to compare investment options.",
                        "Comparing the IRRs of different investment projects to select the most profitable one."
                    ),
                    Triple(
                        "Payback Period",
                        "The time it takes for an investment to recover its initial cost.",
                        "Calculating the payback period to estimate how quickly an investment will generate returns."
                    ),
                    Triple(
                        "Marginal Cost",
                        "The additional cost of producing one more unit of a product or service.",
                        "Calculating the marginal cost to determine the optimal production level."
                    ),
                    Triple(
                        "Break-Even Point",
                        "The point at which total revenue equals total cost, resulting in no profit or loss.",
                        "Determining the break-even point to understand the minimum sales volume required to cover costs."
                    ),
                    Triple(
                        "Contribution Margin",
                        "The amount of revenue that remains after variable costs are covered, contributing to fixed costs and profit.",
                        "Analyzing the contribution margin to evaluate product profitability and make pricing decisions."
                    ),
                    Triple(
                        "Budget Variance Analysis",
                        "Comparing actual results to budgeted amounts to identify deviations and understand performance drivers.",
                        "Performing budget variance analysis to track financial performance and identify areas for improvement."
                    ),
                    Triple(
                        "Economic Order Quantity (EOQ)",
                        "The optimal quantity of a product to order at a time to minimize total ordering and holding costs.",
                        "Calculating the EOQ to optimize inventory levels and reduce costs."
                    ),
                    Triple(
                        "Just-in-Time (JIT) Inventory",
                        "A management system that aims to minimize inventory levels by receiving materials only when needed for production.",
                        "Implementing a JIT inventory system to improve efficiency and reduce inventory costs."
                    ),
                    Triple(
                        "ABC Analysis",
                        "Classifying inventory items based on their value and criticality to optimize control and management efforts.",
                        "Applying ABC analysis to focus inventory management efforts on the most important items."
                    ),
                    Triple(
                        "Regression Analysis",
                        "Examining the relationship between two or more variables, often used to forecast future outcomes based on historical data.",
                        "Using regression analysis to predict sales trends or customer behavior."
                    ),
                    Triple(
                        "Correlation Analysis",
                        "Measuring the strength and direction of the linear relationship between two variables.",
                        "Calculating the correlation coefficient to assess the degree of association between variables."
                    ),
                    Triple(
                        "Decision Trees",
                        "Visual representations of possible choices and their potential consequences, helping to make informed decisions under uncertainty.",
                        "Creating a decision tree to analyze different investment options and evaluate their risks and rewards."
                    ),
                    Triple(
                        "Linear Programming",
                        "A mathematical technique for optimizing resource allocation within constraints, commonly used in production planning and scheduling.",
                        "Applying linear programming to determine the most efficient production plan that meets demand and resource constraints."
                    ),
                    Triple(
                        "Amortization",
                        "The process of allocating the cost of an asset over its useful life.",
                        "Amortizing the cost of a loan or intangible asset over time to reflect its declining value."
                    ),
                    Triple(
                        "Depreciation",
                        "The decline in the value of an asset over time due to wear and tear or obsolescence.",
                        "Depreciating the value of a physical asset, such as equipment, to reflect its usage and wear."
                    ),
                    Triple(
                        "Weighted Average Cost of Capital (WACC)",
                        "The average cost of a company's various financing sources, used to assess its overall capital cost and profitability.",
                        "A company calculates its WACC to determine the minimum rate of return it needs to generate from its investments to justify their cost."
                    ),
                    Triple(
                        "Capital Structure",
                        "The mix of debt and equity financing used by a company.",
                        "A company analyzes its capital structure to balance the potential benefits of leverage against the higher risk associated with debt."
                    ),
                    Triple(
                        "Financial Ratios",
                        "Quantitative measures used to assess a company's financial health and performance.",
                        "Investors use financial ratios like P/E ratio, debt-to-equity ratio, and return on equity to evaluate potential investments."
                    ),
                    Triple(
                        "Portfolio Theory",
                        "The study of constructing and managing investment portfolios to optimize risk and return.",
                        "Investors use Modern Portfolio Theory to diversify their portfolios and reduce risk without sacrificing expected returns."
                    ),
                    Triple(
                        "Options and Derivatives",
                        "Financial instruments that give the holder the right, but not the obligation, to buy or sell an underlying asset at a certain price by a certain date.",
                        "Companies and investors use options and derivatives to hedge risk, speculate on price movements, and generate income."
                    ),
                    Triple(
                        "Linear Programming",
                        "A mathematical technique used to optimize resource allocation within constraints.",
                        "A manufacturing company uses linear programming to determine the production schedule that minimizes costs while meeting demand."
                    ),
                    Triple(
                        "Inventory Management",
                        "The process of optimizing the levels and types of inventory a company holds to meet customer demand efficiently while minimizing holding costs.",
                        "A retailer uses forecasting techniques and inventory models to determine the optimal order quantity and reorder point for each product."
                    ),
                    Triple(
                        "Queuing Theory",
                        "The study of waiting lines and their impact on efficiency.",
                        "A bank uses queuing theory to analyze customer wait times and optimize the number of tellers needed to meet service level requirements."
                    ),
                    Triple(
                        "Decision Analysis",
                        "A framework for making informed decisions under uncertainty.",
                        "A company uses decision analysis to evaluate competing project proposals by considering potential risks, rewards, and expected outcomes."
                    ),
                    Triple(
                        "Simulation",
                        "A technique for modeling and analyzing complex systems by running them through a series of hypothetical scenarios.",
                        "An airline company uses simulation to test different pricing strategies and predict their impact on revenue and profitability."
                    ),
                    Triple(
                        "Financial Modeling",
                        "Building quantitative models to represent and analyze financial situations.",
                        "A company builds a financial model to forecast its future cash flow and evaluate different financing options."
                    ),
                    Triple(
                        "Monte Carlo Simulation",
                        "A statistical technique that uses random sampling to model and analyze risks and uncertainties.",
                        "A risk manager uses Monte Carlo simulation to estimate the potential losses from a natural disaster."
                    ),
                    Triple(
                        "Econometrics",
                        "The application of statistical methods to economic data in order to test economic theories and estimate relationships between economic variables.",
                        "An economist uses econometrics to analyze the impact of government policies on economic growth."
                    ),
                    Triple(
                        "Behavioral Finance",
                        "A field of finance that studies the influence of cognitive and emotional biases on financial decision-making.",
                        "Investors use behavioral finance insights to understand common investment mistakes and make more rational decisions."
                    ),
                    Triple(
                        "Financial Risk Management",
                        "The process of identifying, assessing, and mitigating financial risks.",
                        "A bank implements risk management practices to protect itself from potential losses due to credit defaults, market fluctuations, or operational failures."
                    ),
                    Triple(
                        "Corporate Finance",
                        "The area of finance that deals with the sources of funding, capital structure, and investment decisions of corporations.",
                        "A CFO uses corporate finance principles to make decisions about raising capital, investing in new projects, and managing dividends."
                    ),
                    Triple(
                        "International Finance",
                        "The study of financial transactions and institutions that operate across national borders.",
                        "A multinational corporation manages its foreign exchange exposure and navigates international tax laws."
                    ),
                    Triple(
                        "Financial Regulation",
                        "The set of laws, rules, and guidelines that govern the financial system.",
                        "Regulators implement financial regulations to protect investors, promote financial stability, and prevent financial crises."
                    ),
                    Triple(
                        "Market Efficiency",
                        "The degree to which asset prices reflect all available information.",
                        "Analyzing the efficiency of different markets to understand how quickly new information is incorporated into prices."
                    ),
                    Triple(
                        "Market Efficiency",
                        "The degree to which asset prices reflect all available information.",
                        "Analyzing the efficiency of different markets to understand how quickly new information is incorporated into prices."
                    ),
                    Triple(
                        "Market Microstructure",
                        "The study of the rules and institutions that govern how markets operate.",
                        "Understanding market microstructure can help investors identify trading opportunities and avoid trading costs."
                    ),
                    Triple(
                        "Trading Strategies",
                        "Rules or frameworks used to identify and capitalize on market opportunities.",
                        "Investors develop and implement trading strategies based on their risk tolerance, investment goals, and market analysis."
                    ),
                    Triple(
                        "Technical Analysis",
                        "The study of past price and volume data to predict future price movements.",
                        "Technical analysts use indicators and chart patterns to identify potential trading signals."
                    ),
                    Triple(
                        "Fundamental Analysis",
                        "The analysis of a company's financial statements and economic factors to assess its intrinsic value.",
                        "Fundamental analysts research companies to identify undervalued or overvalued investments."
                    ),
                    Triple(
                        "Swaps",
                        "Financial contracts that exchange cash flows based on different interest rates or indexes.",
                        "Companies use swaps to manage their interest rate risk and access lower borrowing costs."
                    ),
                    Triple(
                        "Futures",
                        "Standardized contracts to buy or sell an asset at a predetermined price on a future date.",
                        "Investors use futures to hedge their exposure to price movements in commodities or currencies."
                    ),
                    Triple(
                        "Options",
                        "Contracts that give the holder the right, but not the obligation, to buy or sell an asset at a certain price by a certain date.",
                        "Options provide investors with leverage and flexibility to manage risk and generate income."
                    ),
                    Triple(
                        "Stress Testing",
                        "Simulating how a portfolio or financial system would respond to extreme market conditions or unexpected events.",
                        "Stress testing helps identify potential vulnerabilities and improve risk management practices."
                    ),
                    Triple(
                        "Mergers and Acquisitions (M&A)",
                        "The consolidation of two or more companies through a combination of their assets and operations.",
                        "Companies engage in M&A to expand their market share, gain access to new technologies, or achieve operational synergies."
                    ),
                    Triple(
                        "Corporate Valuation",
                        "The process of estimating the economic value of a company.",
                        "Investors use valuation methods like P/E ratio, multiples analysis, and discounted cash flow to assess the worth of a company and make investment decisions."
                    ),
                    Triple(
                        "Real Options",
                        "The value of flexibility embedded in a company's assets or strategic options.",
                        "Identifying and valuing real options helps companies make informed decisions about future investments and growth opportunities."
                    ),
                    Triple(
                        "Dividend Policy",
                        "The decision of a company regarding how much of its profits to distribute to shareholders as dividends.",
                        "Companies consider factors like financial performance, growth prospects, and investor expectations when determining their dividend policy."
                    ),
                    Triple(
                        "Fintech",
                        "The use of technology to deliver financial services.",
                        "Fintech companies are disrupting traditional financial institutions by offering innovative and user-friendly services."
                    ),
                    Triple(
                        "Blockchain",
                        "A distributed ledger technology that securely records transactions in a transparent and tamper-proof way.",
                        "Blockchain has the potential to revolutionize various financial applications, including payments, asset management, and trade finance."
                    ),
                    Triple(
                        "Sustainable Finance",
                        "Investing in projects and companies that contribute to positive environmental and social outcomes.",
                        "Sustainable finance is gaining traction as investors increasingly seek to align their portfolios with environmental, social, and governance (ESG) principles."
                    ),
                    Triple(
                        "Accounting Equation",
                        "Assets = Liabilities + Equity, an underlying principle of double-entry accounting used to maintain financial balance.",
                        "Analyzing a company's balance sheet to understand its financial health and capital structure."
                    ),
                    Triple(
                        "Income Statement",
                        "Summarizes a company's revenues, expenses, and net income over a specific period.",
                        "Evaluating a company's profitability and operating efficiency by analyzing its income statement."
                    ),
                    Triple(
                        "Cash Flow Statement",
                        "Shows the movement of cash inflows and outflows from operating, investing, and financing activities.",
                        "Assessing a company's liquidity and ability to generate cash to cover expenses and investments."
                    ),
                    Triple(
                        "Ratio Analysis",
                        "Using financial ratios like debt-to-equity ratio, current ratio, and return on assets to assess various aspects of a company's performance.",
                        "Comparing a company's financial ratios to industry benchmarks to identify strengths and weaknesses."
                    ),
                    Triple(
                        "Financial Forecasting",
                        "Predicting future financial performance based on historical data, market trends, and economic conditions.",
                        "Companies use financial forecasting to budget effectively and make informed investment decisions."
                    ),
                    Triple(
                        "Modern Portfolio Theory (MPT)",
                        "Optimizing portfolios for risk and return by diversifying across different asset classes.",
                        "Investors use MPT to construct portfolios that maximize expected returns at a given level of risk."
                    ),
                    Triple(
                        "Asset Allocation",
                        "Dividing investment capital among different asset classes like stocks, bonds, and real estate.",
                        "Determining an asset allocation strategy based on your individual risk tolerance and investment goals."
                    ),
                    Triple(
                        "Modern Portfolio Optimization (MPO)",
                        "Applying mathematical models to build efficient portfolios based on specific risk-return objectives.",
                        "Using MPO software to identify optimal asset allocations for various risk tolerance levels."
                    ),
                    Triple(
                        "Performance Attribution",
                        "Analyzing the sources of return for a portfolio to understand what contributed to its performance.",
                        "Identifying which asset classes or investment strategies have driven portfolio returns over time."
                    ),
                    Triple(
                        "Behavioral Finance",
                        "Studying how psychological and emotional factors influence investment decisions.",
                        "Understanding common investment biases and developing strategies to avoid them."
                    ),
                    Triple(
                        "Behavioral Economics",
                        "Applying psychology and cognitive science to understand economic decision-making.",
                        "Analyzing consumer behavior and market responses to understand consumer preferences and market trends."
                    ),
                    Triple(
                        "Quantitative Finance",
                        "Applying mathematical and statistical tools to analyze financial markets and develop trading strategies.",
                        "Building quantitative models to predict market movements and manage risk in complex financial instruments."
                    ),
                    Triple(
                        "Computational Finance",
                        "Leveraging computational methods and algorithms to solve financial problems and analyze complex data sets.",
                        "Developing simulation models and machine learning algorithms to support investment decisions and risk management."
                    ),
                    Triple(
                        "Financial Big Data",
                        "Analyzing large datasets of financial information to extract insights and make informed decisions.",
                        "Using big data analytics to identify market trends, detect fraud, and personalize financial services."
                    ),
                    Triple(
                        "Financial Technology (FinTech)",
                        "Leveraging technology to develop innovative financial products and services.",
                        "Utilizing blockchain technology for secure transactions and decentralized financial services."
                    ),
                    Triple(
                        "Capital Budgeting Techniques",
                        "Methods for selecting long-term investment projects, including Net Present Value (NPV), Internal Rate of Return (IRR), and Payback Period.",
                        "A company uses NPV to compare different expansion projects and prioritize investments."
                    ),
                    Triple(
                        "Mergers and Acquisitions (M&A)",
                        "Consolidation of two or more companies through various approaches like mergers, acquisitions, or takeovers.",
                        "A large corporation acquires a smaller competitor to expand its market share and product portfolio."
                    ),
                    Triple(
                        "Leveraged Buyout (LBO)",
                        "Acquisition of a company financed primarily through debt, often used by private equity firms.",
                        "An LBO can unlock value in a company by restructuring its capital structure and improving operating efficiency."
                    ),
                    Triple(
                        "Corporate Restructuring",
                        "Reorganizing a company's structure, operations, or assets to improve profitability and competitiveness.",
                        "A company undergoing financial distress may implement a restructuring plan to reduce costs and generate cash."
                    ),
                    Triple(
                        "Value at Risk (VaR)",
                        "A statistical measure of the potential loss of a portfolio within a given time frame and confidence level.",
                        "Financial institutions use VaR to manage their risk exposure and capital allocation."
                    ),
                    Triple(
                        "Stress Testing",
                        "Simulating how a portfolio or financial system would respond to extreme market conditions or unexpected events.",
                        "Banks conduct stress tests to ensure they have sufficient capital to withstand potential financial crises."
                    ),
                    Triple(
                        "Sensitivity Analysis",
                        "Examining how changes in assumptions or variables affect the outcome of a financial model.",
                        "Investors conduct sensitivity analysis to understand how potential economic changes could impact their portfolio value."
                    ),
                    Triple(
                        "Monte Carlo Simulation",
                        "A statistical technique using random sampling to model and analyze risks and uncertainties in financial scenarios.",
                        "Insurance companies use Monte Carlo simulations to estimate potential losses from natural disasters."
                    ),
                    Triple(
                        "Financial Modeling",
                        "Building quantitative models to represent and analyze financial situations, such as forecasting future cash flows or valuing companies.",
                        "A startup uses a financial model to project its future revenue and expenses to secure funding from investors."
                    ),
                    Triple(
                        "Foreign Exchange Markets",
                        "Where currencies are traded and exchange rates are determined.",
                        "Companies operating in multiple countries need to manage their foreign exchange exposure to protect against currency fluctuations."
                    ),
                    Triple(
                        "International Trade Finance",
                        "Providing financial instruments and services to facilitate international trade and investment.",
                        "Letters of credit and export credit insurance help mitigate risks for exporters and importers."
                    ),
                    Triple(
                        "Emerging Markets",
                        "Economies experiencing rapid growth and development, often with higher returns but also higher risks for investors.",
                        "Investors considering investing in emerging markets need to understand the unique risks and opportunities associated with these economies."
                    ),
                    Triple(
                        "Global Financial Crisis",
                        "A major financial crisis affecting interconnected financial markets worldwide.",
                        "The 2008 financial crisis is a cautionary tale for understanding the systemic risks inherent in global financial systems."
                    ),
                    Triple(
                        "Sustainable Finance",
                        "Investing in projects and companies that contribute to positive environmental and social outcomes.",
                        "Investors increasingly seek ESG-focused investments to align their portfolios with ethical and sustainable practices."
                    ),
                    Triple(
                        "Callable Bond",
                        "A bond that can be redeemed by the issuer before its maturity date.",
                        "Companies may issue callable bonds to take advantage of lower interest rates in the future."
                    ),
                    Triple(
                        "Dupont Analysis",
                        "A method of breaking down ROE into its component parts to assess a company's efficiency, profitability, and financial leverage.",
                        "Dupont analysis helps identify areas for improvement in a company's return on equity."
                    ),
                    Triple(
                        "Capital Budgeting",
                        "The process of evaluating and selecting long-term investments that align with a company's strategic goals.",
                        "Capital budgeting involves analyzing projects such as new facilities or equipment acquisitions."
                    ),
                    Triple(
                        "Callable Preferred Stock",
                        "Preferred stock that the issuer can redeem at a specified price after a certain date.",
                        "Callable preferred stock gives the issuer flexibility in managing its capital structure."
                    ),
                    Triple(
                        "Working Capital Turnover Ratio",
                        "Measures how efficiently a company utilizes its working capital to generate sales.",
                        "A high working capital turnover ratio suggests effective management of short-term assets and liabilities."
                    ),
                    Triple(
                        "Cost of Goods Manufactured (COGM)",
                        "The total production cost of goods completed during a specific period.",
                        "COGM includes direct materials, direct labor, and manufacturing overhead."
                    ),
                    Triple(
                        "Payback Period",
                        "The time it takes for an investment to generate cash inflows sufficient to recover its initial cost.",
                        "A shorter payback period is often preferred, indicating a quicker return on investment."
                    ),
                    Triple(
                        "Predatory Pricing",
                        "A strategy where a company sets low prices to eliminate competitors and gain market share.",
                        "Some argue that predatory pricing can harm competition in the long run."
                    ),
                    Triple(
                        "Hedging",
                        "A risk management strategy that involves using financial instruments to offset potential losses in investments.",
                        "Businesses may use derivatives like futures contracts to hedge against fluctuating commodity prices."
                    ),
                    Triple(
                        "Straddle",
                        "An options trading strategy involving the purchase of both a call and a put option with the same strike price and expiration date.",
                        "Implementing a straddle to profit from significant price volatility in the underlying asset."
                    ),
                    Triple(
                        "Multivariate Analysis",
                        "Statistical analysis that involves the simultaneous examination of two or more variables to understand their relationships.",
                        "Studying the impact of both price and advertising expenditure on the sales of a product."
                    ),
                    Triple(
                        "Repo Rate (Repurchase Agreement Rate)",
                        "The interest rate at which a financial institution sells securities to another institution with an agreement to repurchase them at a higher price in the future.",
                        "Banks using repo transactions to manage their short-term liquidity needs."
                    ),
                    Triple(
                        "Bootstrapped Confidence Intervals",
                        "Confidence intervals derived through bootstrapping, a resampling technique, to estimate the uncertainty around a statistical measure.",
                        "Calculating a bootstrapped confidence interval for the mean return of a portfolio."
                    ),
                    Triple(
                        "Cramér's Rule",
                        "A mathematical technique used to solve a system of linear equations by expressing each variable's solution as a determinant ratio.",
                        "Applying Cramér's Rule to solve a system of equations in economic modeling."
                    ),
                    Triple(
                        "Geometric Brownian Motion",
                        "A mathematical model describing the random motion of particles, often used to model asset prices in finance.",
                        "Simulating stock price movements using geometric Brownian motion in option pricing."
                    ),
                    Triple(
                        "Discount Rate",
                        "The rate used to discount future cash flows to their present value, reflecting the time value of money.",
                        "Discounting future earnings to determine the present value of a business."
                    ),
                    Triple(
                        "Pareto Analysis",
                        "Identifying the 20% of factors that create 80% of the effects, focusing efforts on maximizing impact.",
                        "Marketing teams use Pareto analysis to prioritize their campaign activities for better ROI."
                    ),
                    Triple(
                        "Impulse Buying",
                        "The unplanned purchase of goods or services due to immediate desire or external triggers.",
                        "Retailers can encourage impulse buying by strategically placing high-margin items near checkout counters."
                    ),
                    Triple(
                        "Risk Pooling",
                        "Combining resources or exposures to lower individual risk and spread potential losses across a larger group.",
                        "Insurance companies pool premiums from many individuals to manage the financial impact of unforeseen events."
                    ),
                    Triple(
                        "Blue Ocean Strategy",
                        "Creating new market space with uncontested competition instead of fighting for existing share.",
                        "Airbnb disrupted the hospitality industry by offering a novel accommodation experience outside traditional hotels."
                    ),
                    Triple(
                        "Principal-Agent Problem",
                        "A conflict of interest between a principal (decision-maker) and their agent (acting party) due to information asymmetry.",
                        "Companies mitigate this by aligning incentives, improving communication, and implementing monitoring systems."
                    ),
                    Triple(
                        "Econometrics",
                        "Applying statistical methods to economic data to test economic theories and forecast future trends.",
                        "Central banks use econometric models to analyze inflation and set interest rates."
                    ),
                    Triple(
                        "Game Theory",
                        "Studying strategic interactions between rational decision-makers to predict their behavior and outcomes in competitive scenarios.",
                        "Game theory helps understand pricing strategies in oligopoly markets or negotiation tactics in business deals."
                    ),
                    Triple(
                        "Strategic Foresight",
                        "The ability to anticipate future trends and developments to make informed decisions in a changing environment.",
                        "Companies conduct scenario planning exercises to prepare for potential disruptions and identify strategic opportunities."
                    ),
                    Triple(
                        "Data Mining",
                        "Extracting meaningful patterns and insights from large datasets to inform decision-making and problem-solving.",
                        "Retailers use data mining to personalize customer recommendations and optimize product placement."
                    ),
                    Triple(
                        "Grey Market",
                        "Trade of goods through unauthorized or unofficial channels, often operating outside traditional distribution networks.",
                        "Luxury brands may face challenges with grey markets selling counterfeit or parallel imports of their products."
                    ),
                    Triple(
                        "Profit",
                        "Financial gain where revenue exceeds expenses.",
                        "A company earned $50,000 in sales and incurred $40,000 in costs, resulting in a profit of $10,000."
                    ),
                )

                val insertQuery =
                    "INSERT INTO dictionary_terms (term, definition, example) VALUES (?, ?, ?)"
                val insertStatement = db.compileStatement(insertQuery)

                for (termData in termsToInsert) {
                    insertStatement.bindString(1, termData.first)
                    insertStatement.bindString(2, termData.second)
                    insertStatement.bindString(3, termData.third)
                    insertStatement.executeInsert()
                }

                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }
    }

    private fun checkIfDatabaseIsPopulated(db: SQLiteDatabase): Boolean {
        val query = "SELECT COUNT(*) FROM dictionary_terms"
        val cursor = db.rawQuery(query, null)
        var isPopulated = false
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val count = cursor.getInt(0)
                isPopulated = count > 0
            }
            cursor.close()
        }
        return isPopulated
    }

    override fun onDestroy() {
        databaseHelper.close()
        super.onDestroy()
    }
}