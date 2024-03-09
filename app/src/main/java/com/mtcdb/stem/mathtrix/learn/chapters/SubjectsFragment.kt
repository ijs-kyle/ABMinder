package com.mtcdb.stem.mathtrix.learn.chapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtcdb.stem.mathtrix.MainActivity
import com.mtcdb.stem.mathtrix.R

class SubjectsFragment : Fragment() {

    private val subjectsList = listOf(
        "Business Mathematics",
        "Business Finance",
        "Fundamentals of ABM I",
        "Applied Economics"
    )

    private lateinit var mainActivity: MainActivity
    private lateinit var subjectsAdapter: SubjectsAdapter
    private lateinit var searchEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subjects, container, false)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewSubjects)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        subjectsAdapter = SubjectsAdapter(subjectsList, ::onSubjectSelected)
        recyclerView.adapter = subjectsAdapter

        mainActivity = (activity as? MainActivity)!!

        // Initialize search EditText
        searchEditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filter subjects based on search query
                filterSubjects(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })

        return view
    }

    private fun onSubjectSelected(subject: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, ChaptersFragment.newInstance(subject))
        transaction.addToBackStack(null)
        transaction.commit()
        mainActivity.toolbar.title = subject
    }

    private fun filterSubjects(query: String) {
        val filteredList = subjectsList.filter { it.contains(query, ignoreCase = true) }
        subjectsAdapter.updateSubjects(filteredList)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.toolbar.title = getString(R.string.app_name)
    }
}
