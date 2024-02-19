package com.mtcdb.stem.mathtrix

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.size
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.mtcdb.stem.mathtrix.calculator.CalculatorOptionsFragment
import com.mtcdb.stem.mathtrix.dictionary.DictionaryDatabaseHelper
import com.mtcdb.stem.mathtrix.dictionary.DictionarySuggestionAdapter
import com.mtcdb.stem.mathtrix.dictionary.DictionaryViewModel
import com.mtcdb.stem.mathtrix.dictionary.RecentSearch
import com.mtcdb.stem.mathtrix.dictionary.RecentSearchAdapter
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
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.action_quiz -> {
                val intent = Intent(this, DifficultyLevel::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

class DictionaryFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var termTextView: TextView
    private lateinit var termDefinitionTextView: TextView
    private lateinit var termExamplesListView: TextView
    private lateinit var layoutDictionary: LinearLayout
    private lateinit var recentSearchesRecyclerView: RecyclerView
    private lateinit var recentSearchesAdapter: RecentSearchAdapter
    private lateinit var viewModel: DictionaryViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_dictionary, container, false)

        searchView = rootView.findViewById(R.id.searchView)
        searchView.queryHint = "Search terms..."
        val searchIcon = searchView.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.visibility = View.GONE
        layoutDictionary = rootView.findViewById(R.id.dictionary_layout)
        layoutDictionary.visibility = View.GONE

        termTextView = rootView.findViewById(R.id.termTextView)
        termDefinitionTextView = rootView.findViewById(R.id.term_definition)
        termExamplesListView = rootView.findViewById(R.id.term_examples)
        recentSearchesRecyclerView = rootView.findViewById(R.id.recent_searches_list)
        recentSearchesRecyclerView.visibility = View.VISIBLE
        val recent = rootView.findViewById<TextView>(R.id.recent)
        val recentText: TextView = rootView.findViewById(R.id.recentText)
        recentText.visibility = View.GONE
        if (recentSearchesRecyclerView.size == 0 or -1 && recentSearchesAdapter.equals(0)) {
            recentText.visibility = View.VISIBLE
            recent.visibility = View.GONE
        } else {
            recentText.visibility = View.GONE
            recent.visibility = View.VISIBLE

        }

        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]

        // Initialize and set up the RecentSearchAdapter
        recentSearchesAdapter = RecentSearchAdapter(requireContext()) { clickedSearch ->
            Toast.makeText(requireContext(), "Clicked: ${clickedSearch.query}", Toast.LENGTH_SHORT)
                .show()
        }
        recentSearchesRecyclerView.adapter = recentSearchesAdapter

        // Observe changes in recent searches
        viewModel.recentSearches.observe(viewLifecycleOwner) { searches ->
            recentSearchesAdapter.submitList(searches)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    // Perform the search with the submitted query
                    searchView.setQuery(query, false)
                    searchView.clearFocus()
                    performSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    layoutDictionary.visibility = View.GONE
                    recentSearchesRecyclerView.visibility = View.VISIBLE
                }

                // Update the cursor in the adapter as the user types
                val cursor = getSuggestionsCursor(newText)
                searchView.suggestionsAdapter = context?.let {
                    DictionarySuggestionAdapter(
                        it,
                        cursor
                    )
                }
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
                layoutDictionary.visibility = View.VISIBLE
                recentSearchesRecyclerView.visibility = View.GONE

                // Adding the recent search
                handleRecentSearch(term, definition, example)

                return true
            }
        })

        return rootView
    }

    private fun getSuggestionsCursor(query: String?): Cursor? {
        val db = context?.let { DictionaryDatabaseHelper(it).readableDatabase }
        val selection = "term LIKE ?"
        val selectionArgs = arrayOf("$query%")
        val columns = arrayOf("_id", "term", "definition", "example")

        return db?.query(
            "dictionary_terms",
            columns,
            selection,
            selectionArgs,
            null,
            null,
            "term ASC"
        )
    }

    private fun handleRecentSearch(query: String, definition: String, example: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("SearchHistory", Context.MODE_PRIVATE)

        // Retrieve existing search history
        val searchHistory =
            sharedPreferences.getStringSet("searchHistory", HashSet())?.toMutableList()
                ?: mutableListOf()

        // Add the new search
        val newSearch = RecentSearch(query, definition, example)
        viewModel.addRecentSearch(newSearch)
        searchHistory.add(0, newSearch.toString()) // Convert to string for storage

        // Limit the search history to 10 items
        while (searchHistory.size > 10) {
            searchHistory.removeAt(searchHistory.size - 1)
        }

        // Save the updated search history
        sharedPreferences.edit().putStringSet("searchHistory", searchHistory.toSet()).apply()

    }

    private fun performSearch(query: String) {
        val cursor = getSuggestionsCursor(query)

        if (cursor != null && cursor.moveToFirst()) {
            // Match found, display the definition
            val term = cursor.getString(cursor.getColumnIndexOrThrow("term"))
            val definition = cursor.getString(cursor.getColumnIndexOrThrow("definition"))
            val example = cursor.getString(cursor.getColumnIndexOrThrow("example"))

            termTextView.text = term
            termDefinitionTextView.text = definition
            termExamplesListView.text = example
            searchView.clearFocus()
            layoutDictionary.visibility = View.VISIBLE
            recentSearchesRecyclerView.visibility = View.GONE

            // Adding the recent search
            handleRecentSearch(term, definition, example)
        } else {
            // No match found, do nothing or show a message
            // You can add a message to inform the user that no match was found
        }
    }
}
