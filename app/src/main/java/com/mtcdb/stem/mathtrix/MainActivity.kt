package com.mtcdb.stem.mathtrix

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.mtcdb.stem.mathtrix.calculator.CalculatorOptionsFragment
import com.mtcdb.stem.mathtrix.dictionary.DictionaryDatabaseHelper
import com.mtcdb.stem.mathtrix.dictionary.DictionaryFragment
import com.mtcdb.stem.mathtrix.dictionary.EditTermFragment
import com.mtcdb.stem.mathtrix.learn.chapters.SubjectsFragment
import com.mtcdb.stem.mathtrix.quiz.DifficultyLevel
import com.mtcdb.stem.mathtrix.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var layoutCalculator: LinearLayout
    private var doubleBackToExitPressedOnce = false


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DictionaryFragment())
            .commit()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            showAddTermDialog()
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val calcView = layoutInflater.inflate(R.layout.fragment_calculator_options, null)
        layoutCalculator = calcView.findViewById(R.id.calculator_layout)

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
        navView.itemIconTintList = null // Disable tinting
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_item_calculator -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CalculatorOptionsFragment())
                        .addToBackStack(null)
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    toolbar.title = getString(R.string.app_name)
                }

                R.id.nav_item_dictionary -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DictionaryFragment())
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    toolbar.title = getString(R.string.dictionary)
                }

                R.id.nav_item_learn -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            SubjectsFragment(),
                            SubjectsFragment::class.java.simpleName
                        )
                        .addToBackStack(null)
                        .commit()
                    drawerLayout.closeDrawer(GravityCompat.START)
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
                }
            }
            return@setNavigationItemSelectedListener true
        }
        navView.setCheckedItem(R.id.nav_item_dictionary)
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


    private fun addTermToDatabase(term: String, definition: String?, example: String?) {
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

    private fun clearInputFields(dialogView: View) {
        // Clear input fields after adding the term
        dialogView.findViewById<EditText>(R.id.termEditText).text = null
        dialogView.findViewById<EditText>(R.id.definitionEditText).text = null
        dialogView.findViewById<EditText>(R.id.exampleEditText).text = null
    }


    override fun getOnBackInvokedDispatcher(): OnBackInvokedDispatcher {
        // Get the default dispatcher from the superclass
        val dispatcher = super.getOnBackInvokedDispatcher()

        // Add a callback to handle onBackPressed event
        onBackPressedDispatcher.addCallback(this) {
            drawerLayout = findViewById(R.id.drawer_layout)
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                // Close the navigation drawer if it's open
                drawerLayout.closeDrawer(GravityCompat.START)
            } else if (supportFragmentManager.backStackEntryCount > 0) {
                // If there are fragments in the back stack, pop the fragment
                toolbar.title = getString(R.string.app_name)
                supportFragmentManager.popBackStack()
            } else {
                // If there are no fragments in the back stack, handle the back button press behavior
                toolbar.title = getString(R.string.app_name)
                if (doubleBackToExitPressedOnce) {
                    // If the user presses back button twice within 2 seconds, exit the app
                    finish()
                } else {
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(
                        this@MainActivity,
                        "Press back again to exit",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Reset the flag after 2 seconds
                    Handler(mainLooper).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                }
            }
        }

        return dispatcher
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app_bar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {

        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.menu)
        val navigationIcon = toolbar.navigationIcon
        navigationIcon?.setColorFilter(getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
        return super.onPrepareOptionsMenu(menu)
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
