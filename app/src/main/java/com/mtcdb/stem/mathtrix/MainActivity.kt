package com.mtcdb.stem.mathtrix

import android.annotation.*
import android.content.*
import android.graphics.*
import android.os.*
import android.view.*
import android.widget.*
import android.window.*
import androidx.activity.*
import androidx.appcompat.app.*
import androidx.appcompat.widget.Toolbar
import androidx.core.content.*
import androidx.core.view.*
import androidx.drawerlayout.widget.*
import androidx.fragment.app.*
import com.google.android.material.floatingactionbutton.*
import com.google.android.material.navigation.*
import com.mtcdb.stem.mathtrix.calculator.*
import com.mtcdb.stem.mathtrix.dictionary.*
import com.mtcdb.stem.mathtrix.learn.subjects.*
import com.mtcdb.stem.mathtrix.quiz.*
import com.mtcdb.stem.mathtrix.quiz.database.*
import com.mtcdb.stem.mathtrix.settings.*

class MainActivity : AppCompatActivity() {

    lateinit var toolbar : Toolbar
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var layoutCalculator : LinearLayout
    private var doubleBackToExitPressedOnce = false
    private lateinit var dbHelper : QuizDatabaseHelper

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = QuizDatabaseHelper(this)
        val quizDataPopulator = QuizDataPopulator(dbHelper)
        quizDataPopulator.populateQuizData()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        drawerLayout = findViewById(R.id.drawer_layout)

        fab.setOnClickListener {
            showAddTermDialog()
        }

        // Initialize toolbar and other UI components
        initViews()

        // Set initial fragment
        setCurrentFragment(DictionaryFragment())

        val calcView = layoutInflater.inflate(R.layout.fragment_calculator_options, null)
        layoutCalculator = calcView.findViewById(R.id.calculator_layout)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close

        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.itemIconTintList = null // Disable tinting
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_calculator -> {
                    setCurrentFragment(CalculatorOptionsFragment())
                    toolbar.title = getString(R.string.app_name)
                }

                R.id.nav_item_dictionary -> {
                    setCurrentFragment(DictionaryFragment())
                    toolbar.title = getString(R.string.dictionary)
                }

                R.id.nav_item_learn -> {
                    setCurrentFragment(SubjectsFragment())
                    toolbar.title = "Learn"
                }

                /*
                R.id.nav_item_learn -> {
                supportFragmentManager.beginTransaction()
                .replace(
                R.id.fragment_container,
                LearnFragment.newInstance(),
                LearnFragment::class.java.simpleName
                )
                .addToBackStack(LearnFragment.TAG)
                .commit()
                drawerLayout.closeDrawer(GravityCompat.START)
                toolbar.title = getString(R.string.learn)
                }
                */


                R.id.nav_item_quiz -> {
                    val intent = Intent(this, DifficultyLevel::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(GravityCompat.START)
                    toolbar.title = getString(R.string.quiz)
                    navView.setCheckedItem(R.id.nav_item_dictionary)
                }
            }
            return@setNavigationItemSelectedListener true
        }
        navView.setCheckedItem(R.id.nav_item_dictionary)
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setCurrentFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        drawerLayout.closeDrawer(GravityCompat.START)
        // Update FAB visibility based on the current fragment
        setFabVisibility(fragment)
    }


    private fun setFabVisibility(fragment : Fragment) {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.visibility = when (fragment) {
            is DictionaryFragment -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun showAddTermDialog() {
        val dialogView =
            LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_add_term, null)
        val termEditText = dialogView.findViewById<EditText>(R.id.termEditText)
        val definitionEditText = dialogView.findViewById<EditText>(R.id.definitionEditText)
        val exampleEditText = dialogView.findViewById<EditText>(R.id.exampleEditText)

        val dialog = AlertDialog.Builder(this@MainActivity)
            .setTitle("Add New Term")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val term = termEditText.text.toString().trim()
                val definition = definitionEditText.text.toString().trim()
                val example = exampleEditText.text.toString().trim()
                addTermToDatabase(term, definition, example)
                clearInputFields(dialogView) // Clear input fields after adding the term
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }


    private fun addTermToDatabase(term : String, definition : String?, example : String?) {
        if (term.isBlank()) {
            // Inform the user that the term is required
            Toast.makeText(this@MainActivity, "Term is required.", Toast.LENGTH_SHORT).show()
            return
        }

        val dbHelper = DictionaryDatabaseHelper(this@MainActivity)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("term", term)
            definition?.let { put("definition", it) }
            example?.let { put("example", it) }
        }

        val newTerm = db.insert("dictionary_terms", null, values)

        if (newTerm != -1L) {
            // Term added successfully
            Toast.makeText(this@MainActivity, "Term added to the database.", Toast.LENGTH_SHORT)
                .show()
        } else {
            // Failed to add term
            Toast.makeText(
                this@MainActivity,
                "Failed to add term to the database.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun clearInputFields(dialogView : View) {
        // Clear input fields after adding the term
        dialogView.findViewById<EditText>(R.id.termEditText).text = null
        dialogView.findViewById<EditText>(R.id.definitionEditText).text = null
        dialogView.findViewById<EditText>(R.id.exampleEditText).text = null
    }


    override fun getOnBackInvokedDispatcher() : OnBackInvokedDispatcher {
        // Get the default dispatcher from the superclass
        val dispatcher = super.getOnBackInvokedDispatcher()

        // Add a callback to handle the onBackPressed event
        onBackPressedDispatcher.addCallback(this) {
            handleBackPressed()
        }

        return dispatcher
    }

    private fun handleBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        handleDoubleBackToExit()

        // Check if the navigation drawer is open
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // Close the navigation drawer
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount > 0) {
            // If there are fragments in the back stack, pop the fragment
            toolbar.title = getString(R.string.app_name)
            supportFragmentManager.popBackStack()
        } else {
            // If there are no fragments in the back stack, handle the back button press behavior
            toolbar.title = getString(R.string.app_name)
            handleDoubleBackToExit()
        }
    }

    private fun handleDoubleBackToExit() {
        if (doubleBackToExitPressedOnce) {
            // If the user presses the back button twice within 2 seconds, finish the hosting activity
            finish()
        } else {
            //doubleBackToExitPressedOnce = true
            Toast.makeText(this@MainActivity, "Press back again to exit", Toast.LENGTH_SHORT).show()
            doubleBackToExitPressedOnce = true

            // Reset the flag after 2 seconds
            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
    }


    override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
        menuInflater.inflate(R.menu.menu_app_bar, menu)
        return true
    }

    @Suppress("DEPRECATION")
    override fun onPrepareOptionsMenu(menu : Menu) : Boolean {

        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.menu)
        val navigationIcon = toolbar.navigationIcon
        navigationIcon?.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
                true
            }

            R.id.action_progress -> {
                setCurrentFragment(QuizProgressFragment())
                toolbar.title = "Progress"
                true
            }

            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.action_edit_term -> {
                // Navigate to the EditTermFragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, EditTermFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
