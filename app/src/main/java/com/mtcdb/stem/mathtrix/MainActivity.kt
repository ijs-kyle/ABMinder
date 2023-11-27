package com.mtcdb.stem.mathtrix

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.mtcdb.stem.mathtrix.calculator.CalculatorOptionsActivity
import com.mtcdb.stem.mathtrix.dictionary.DictionaryDatabaseHelper
import com.mtcdb.stem.mathtrix.dictionary.DictionarySuggestionAdapter

class   MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var searchView: SearchView
    private lateinit var termTextView: TextView
    private lateinit var termDefinitionTextView: TextView
    private lateinit var termExamplesListView: TextView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var databaseHelper: DictionaryDatabaseHelper
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DictionaryDatabaseHelper(this)
        insertTermsIntoDatabase()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        searchView = findViewById(R.id.searchView)
        searchView.queryHint = "Search"
        searchView.isInEditMode
        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.visibility = View.GONE

        termTextView = findViewById(R.id.termTextView)
        termDefinitionTextView = findViewById(R.id.term_definition)
        termExamplesListView = findViewById(R.id.term_examples)

        val bottomCardView = findViewById<LinearLayout>(R.id.bottom_sheet_card_view)
        bottomCardView.isInEditMode

        val bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        val swipeUp = findViewById<ImageView>(R.id.swipe_up)
        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED, BottomSheetBehavior.STATE_SETTLING -> {
                        swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                    }
                    BottomSheetBehavior.STATE_EXPANDED, BottomSheetBehavior.STATE_DRAGGING  -> {
                        swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                    }

                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                    }

                    BottomSheetBehavior.STATE_HIDDEN -> {
                        swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
        
        swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
        swipeUp.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
            }
        }
        swipeUp.setOnLongClickListener {
            Snackbar.make(it,"Dictionary", Snackbar.LENGTH_SHORT).show()
           true
        }

        searchView.suggestionsAdapter = null
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search query submission if needed
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Update the cursor in the adapter as the user types
                val cursor = getSuggestionsCursor(newText)

                searchView.suggestionsAdapter = DictionarySuggestionAdapter(
                    this@MainActivity,
                    cursor
                )
                return true
            }
        })

        searchView.suggestionsAdapter = null
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {

                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = searchView.suggestionsAdapter?.getItem(position) as Cursor
                val term = cursor.getString(cursor.getColumnIndexOrThrow("term"))
                val definition = cursor.getString(cursor.getColumnIndexOrThrow("definition"))
                val example = cursor.getString(cursor.getColumnIndexOrThrow("example"))

                termTextView.text = term
                termDefinitionTextView.text = definition
                termExamplesListView.text = example
                searchView.clearFocus()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                swipeUp.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                return true
            }
        })

        drawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_calculator -> {
                    val intent = Intent(this, CalculatorOptionsActivity::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return@setNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    fun onBackPressedDispatcher() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                super.onBackPressedDispatcher
            }
        }
    }

    private fun getSuggestionsCursor(query: String?): Cursor? {
        val db = DictionaryDatabaseHelper(this).readableDatabase
        val selection = "term LIKE ?"
        val selectionArgs = arrayOf("$query%")
        val columns = arrayOf("_id", "term", "definition", "example")

        return db.query(
            "dictionary_terms",
            columns,
            selection,
            selectionArgs,
            null,
            null,
            "term ASC"
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                true
            }

            R.id.action_settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        databaseHelper.close()
        super.onDestroy()
    }

    private fun insertTermsIntoDatabase() {
        val db = databaseHelper.writableDatabase

        //checks if the terms are already inserted
        val isDatabasePopulated = checkIfDatabaseIsPopulated(db)

        if (!isDatabasePopulated) {
            db.beginTransaction()
            try {
                val termsToInsert = listOf(
                    Triple("Asset", "Anything of value owned by a person or company, which can be converted into cash.", "Fixed assets; Current assets"),
                    Triple("Audit", "A systematic examination of financial records, statements, or operations to ensure accuracy and compliance.", "Internal audit; External audit"),
                    Triple("Amortization", "The process of spreading the cost of an intangible asset over its useful life.", "Amortization schedule; Intangible assets"),
                    Triple("Annual Report", "A comprehensive report by a company, providing financial and operational information to shareholders and the public.", "Financial statements; Shareholder report"),
                    Triple("Angel Investor", "An individual who provides capital for a business startup in exchange for ownership equity.", "Startup funding; Seed capital"),
                    Triple("Accounts Payable", "The amount of money a company owes to its suppliers or creditors for goods or services received.", "Payment terms; Outstanding invoices"),
                    Triple("Accrual Accounting", "An accounting method where revenue and expenses are recorded when they are earned or incurred, not when cash is exchanged.", "Accrual basis; Accounting principles"),
                    Triple("Advertising", "The promotion of a product, service, or brand to attract and persuade customers.", "Marketing strategy; Ad campaign"),
                    Triple("Agile Methodology", "A project management and product development approach that prioritizes flexibility and collaboration.", "Scrum; Sprint planning"),
                    Triple("Asset Turnover Ratio", "A financial ratio that measures a company's ability to generate revenue from its assets.", "Asset efficiency; Sales turnover"),
                    Triple("Accounts Receivable Turnover", "A ratio that measures how efficiently a company collects payments on its receivables.", "Days sales outstanding; Receivables management"),
                    Triple("Allocation", "The distribution or assignment of resources, such as funds, time, or personnel, for a specific purpose.", "Resource allocation; Budget allocation"),
                    Triple("Appreciation", "An increase in the value of an asset over time, usually due to market conditions or improvements.", "Asset appreciation; Real estate appreciation"),
                    Triple("Average Cost", "The total cost of a set of items divided by the number of units, providing a per-unit cost.", "Cost calculation; Cost accounting"),
                    Triple("Asymmetric Information", "A situation in which one party in a transaction has more or better information than the other.", "Information asymmetry; Market efficiency"),
                    Triple("Authority Matrix", "A document that defines the levels of decision-making authority within an organization.", "Responsibility chart; Decision-making hierarchy"),
                    Triple("Accounts Receivable Aging", "A report that categorizes a company's receivables by the length of time they have been outstanding.", "Aging schedule; Receivables management"),
                    Triple("Automation", "The use of technology to perform tasks or processes without human intervention.", "Workflow automation; Process automation"),
                    Triple("Alpha Testing", "The initial phase of software testing, conducted by the internal development team before releasing the product to external users.", "Software testing; Quality assurance"),
                    Triple("Appraisal", "An evaluation or assessment of the value, performance, or quality of something.", "Performance appraisal; Property appraisal"),
                    Triple("Annualized Return", "The calculated annual rate of return on an investment over a given period.", "Investment performance; Rate of return"),
                    Triple("Abandonment Cost", "The cost incurred when a project, product, or initiative is discontinued or not pursued.", "Sunk cost; Project management"),
                    Triple("Average Revenue", "The total revenue generated by a company divided by the quantity of units sold.", "Revenue per unit; Sales analysis"),
                    Triple("Affiliate Marketing", "A performance-based marketing strategy where a business rewards affiliates for bringing in customers or traffic through the affiliate's marketing efforts.", "Affiliate network; Commission-based marketing"),
                    Triple("Antitrust Laws", "Laws designed to promote fair competition and prevent monopolies or other anticompetitive business practices.", "Competition law; Regulatory compliance"),
                    Triple("Asset Allocation", "The strategic distribution of a portfolio's investments among various asset classes to optimize risk and return.", "Diversification; Investment strategy"),
                    Triple("Annual Percentage Rate (APR)", "The annualized interest rate that considers additional fees and costs associated with a loan or financial product.", "Loan cost calculation; Interest rate"),
                    Triple("Accounts Receivable Financing", "A form of financing where a company uses its receivables as collateral to obtain a loan.", "Factoring; Working capital financing"),
                    Triple("Arbitrage", "The practice of taking advantage of price differences in different markets to make a profit.", "Risk-free profit; Market inefficiency"),
                    Triple("Brand Equity", "The perceived value or strength of a brand based on consumer perception and brand loyalty.", "Brand recognition; Brand building"),
                    Triple("Break-Even Point", "The level of sales at which a business covers its total costs and neither makes a profit nor incurs a loss.", "Cost-volume-profit analysis; Breakeven analysis"),
                    Triple("Business Intelligence (BI)", "The use of data analysis tools and techniques to make informed business decisions.", "Data analytics; Decision support"),
                    Triple("Balance Sheet", "A financial statement that provides a snapshot of a company's financial position, showing assets, liabilities, and equity.", "Financial statement; Accounting"),
                    Triple("Benchmarking", "The process of comparing a company's performance metrics with those of industry leaders or competitors.", "Performance measurement; Competitive analysis"),
                    Triple("Back-End", "The technical infrastructure of a software application, responsible for server-side processing and data storage.", "Front-end vs. back-end; Web development"),
                    Triple("Big Data", "Large and complex sets of data that require advanced tools and technologies for analysis and processing.", "Data analytics; Data science"),
                    Triple("Brainstorming", "A creative problem-solving technique where a group generates ideas and solutions through open and spontaneous discussion.", "Idea generation; Team collaboration"),
                    Triple("Bull Market", "A financial market characterized by rising prices and optimism among investors.", "Stock market trends; Market conditions"),
                    Triple("Business Model", "The plan or framework that outlines how a company creates, delivers, and captures value.", "Revenue streams; Monetization strategy"),
                    Triple("Buyer Persona", "A detailed and semi-fictional representation of a company's ideal customer, based on market research and data.", "Marketing strategy; Customer profiling"),
                    Triple("Blue Ocean Strategy", "A business approach that focuses on creating and capturing new market space, rather than competing in existing markets.", "Innovation strategy; Market differentiation"),
                    Triple("Budget Variance", "The difference between the planned or budgeted amount and the actual amount spent or earned.", "Financial planning; Budget analysis"),
                    Triple("Burn Rate", "The rate at which a company uses its available cash or capital, often expressed as a monthly or weekly expenditure.", "Cash burn; Runway"),
                    Triple("Brand Positioning", "The way a brand is perceived in the minds of consumers relative to competitors.", "Brand identity; Market positioning"),
                    Triple("Business Process Reengineering (BPR)", "The redesign and optimization of business processes to achieve improvements in efficiency, quality, and performance.", "Process improvement; Organizational change"),
                    Triple("B2B (Business-to-Business)", "Commerce transactions between businesses, where products or services are sold from one business to another.", "B2B marketing; Corporate sales"),
                    Triple("B2C (Business-to-Consumer)", "Commerce transactions between a business and individual consumers, involving the sale of products or services.", "B2C e-commerce; Retail marketing"),
                    Triple("Blockchain", "A decentralized and distributed digital ledger technology used to record transactions across multiple computers.", "Cryptocurrency; Smart contracts"),
                    Triple("Bootstrapping", "The process of building or starting a business with minimal external capital or resources.", "Self-funding; Lean startup"),
                    Triple("Bill of Materials (BOM)", "A comprehensive list of materials, components, and assemblies required to manufacture a product.", "Manufacturing; Production planning"),
                    Triple("Brand Ambassador", "An individual or influencer who represents and promotes a brand to increase awareness and attract customers.", "Brand advocacy; Influencer marketing"),
                    Triple("Business Continuity Planning", "The process of creating systems and procedures to ensure a company can operate and recover from disruptions or disasters.", "Disaster recovery; Risk management"),
                    Triple("Beta Testing", "The second phase of software testing, conducted by a select group of external users before the official release.", "User acceptance testing; Quality assurance"),
                    Triple("Cash Conversion Cycle (CCC)", "The time it takes for a company to convert its investments in inventory and other resources into cash flow from sales.", "Working capital management; Inventory turnover"),
                    Triple("Corporate Social Responsibility (CSR)", "A company's commitment to ethical and responsible business practices that contribute to social and environmental well-being.", "Sustainability; Social impact"),
                    Triple("Cost of Goods Sold (COGS)", "The direct costs incurred in producing a product or delivering a service, including materials and labor.", "COGS formula; Gross profit"),
                    Triple("Crowdfunding", "A fundraising method where individuals contribute small amounts of money to support a project or venture.", "Kickstarter; Crowdfunding platforms"),
                    Triple("Customer Acquisition Cost (CAC)", "The total cost a company incurs to acquire a new customer, including marketing and sales expenses.", "Customer retention; CAC calculation"),
                    Triple("Competitive Advantage", "A unique strength or attribute that gives a company a significant advantage over its competitors.", "Market differentiation; Strategic positioning"),
                    Triple("Cash Flow Statement", "A financial statement that shows the inflow and outflow of cash within a company during a specific period.", "Operating activities; Financing activities"),
                    Triple("Customer Relationship Management (CRM)", "A technology and strategy for managing a company's interactions with current and potential customers.", "CRM software; Customer loyalty"),
                    Triple("Core Competency", "A specific area of expertise or capability that gives a company a competitive advantage in its industry.", "Strategic management; Competitive strategy"),
                    Triple("Collaborative Economy", "A business model where individuals share resources, services, or products through digital platforms.", "Sharing economy; Peer-to-peer"),
                    Triple("Capital Expenditure (CapEx)", "Spending on assets that will provide long-term benefits, such as property, equipment, or infrastructure.", "CapEx vs. OpEx; Capital budgeting"),
                    Triple("Cost Leadership", "A competitive strategy where a company aims to become the lowest-cost producer in its industry.", "Cost advantage; Economies of scale"),
                    Triple("Convertible Bond", "A type of bond that can be converted into a predetermined number of shares of the issuing company's stock.", "Debt financing; Financial instruments"),
                    Triple("Cross-Selling", "The strategy of selling additional products or services to existing customers, often related to their initial purchase.", "Upselling; Sales strategy"),
                    Triple("Critical Path Method (CPM)", "A project management technique that identifies the sequence of tasks with the longest duration, determining the project's critical path.", "Project scheduling; Task dependency"),
                    Triple("Crisis Management", "The process of preparing for and responding to a crisis or emergency situation to minimize damage and ensure recovery.", "Risk mitigation; Emergency response"),
                    Triple("Consumer Behavior", "The study of how individuals and groups make decisions to satisfy their needs and wants as consumers.", "Market research; Buying habits"),
                    Triple("Cross-Functional Team", "A team that includes members from different departments or functions within an organization, working together on a common goal.", "Team collaboration; Interdisciplinary"),
                    Triple("Cost-Benefit Analysis", "A systematic process for assessing the pros and cons of an action, decision, or project, comparing costs and expected benefits.", "Decision-making; Project evaluation"),
                    Triple("Corporate Governance", "The system of rules, practices, and processes by which a company is directed and controlled, involving relationships among stakeholders.", "Board of directors; Ethical standards"),
                    Triple("Customer Segmentation", "The process of dividing a market into distinct groups of buyers with similar characteristics or needs.", "Target audience; Marketing strategy"),
                    Triple("Crowdsourcing", "The practice of obtaining ideas, content, or services by soliciting contributions from a large group of people, typically online.", "Open innovation; Collective intelligence"),
                    Triple("Centralized Organization", "An organizational structure where decision-making authority is concentrated at the top levels of management.", "Hierarchy; Organizational design"),
                    Triple("Customer Lifetime Value (CLV)", "The predicted total revenue a company expects to earn from a customer throughout their entire relationship.", "Retention strategy; CLV calculation"),
                    Triple("Cost of Capital", "The required rate of return that a company must achieve on its investments to maintain or increase its stock price.", "WACC; Capital budgeting"),
                    Triple("Continuous Improvement", "An ongoing effort to improve products, services, or processes through incremental and iterative changes.", "Kaizen; Process optimization"),
                    Triple("Corporate Culture", "The shared values, beliefs, and practices that shape the behavior and attitudes of individuals within an organization.", "Organizational culture; Company values"),
                    Triple("Competitive Intelligence", "The process of gathering, analyzing, and using information about competitors and the competitive environment.", "Market research; Business strategy"),
                    Triple("Cash Reserve Ratio", "The percentage of a bank's deposits that must be held in reserve, not available for lending or investment.", "Monetary policy; Banking regulation"),
                    Triple("Customer Churn", "The rate at which customers stop doing business with a company over a given period.", "Churn rate; Customer retention"),
                    Triple("Cross-Training", "The practice of training employees to perform tasks or responsibilities outside of their primary roles, increasing versatility.", "Skill development; Employee training"),
                    Triple("Cost-plus Pricing", "A pricing strategy where a company determines the cost of producing a product and adds a markup to establish the selling price.", "Markup; Pricing strategy"),
                    Triple("Cloud Computing", "The delivery of computing services, including storage, processing power, and software, over the internet.", "Infrastructure as a Service (IaaS); SaaS"),
                    Triple("Competitive Analysis", "The process of evaluating and comparing a company's strengths and weaknesses against those of its competitors.", "SWOT analysis; Market positioning"),
                    Triple("Consumer Surplus", "The economic measure of the difference between what a consumer is willing to pay for a good or service and what", "Yes")
                )

                val insertQuery = "INSERT INTO dictionary_terms (term, definition, example) VALUES (?, ?, ?)"
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
}