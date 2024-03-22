package com.mtcdb.stem.mathtrix.learn.subjects

import android.annotation.*
import android.os.*
import android.text.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import com.mtcdb.stem.mathtrix.*
import com.mtcdb.stem.mathtrix.learn.chapters.*

class SubjectsFragment : Fragment() {

    private val subjectsList = listOf(
        "Business Mathematics",
        "Business Finance",
        "Fundamentals of ABM I",
        "Applied Economics"
    )

    private lateinit var mainActivity : MainActivity
    private lateinit var subjectsAdapter : SubjectsAdapter
    private lateinit var searchEditText : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater : LayoutInflater, container : ViewGroup?,
        savedInstanceState : Bundle?,
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_subjects, container, false)

        // Initialize RecyclerView
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerViewSubjects)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        subjectsAdapter = SubjectsAdapter(subjectsList, ::onSubjectSelected)
        recyclerView.adapter = subjectsAdapter

        mainActivity = (activity as? MainActivity)!!

        // Initialize search EditText
        searchEditText = view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s : CharSequence?,
                start : Int,
                count : Int,
                after : Int,
            ) {
                // Not needed
            }

            override fun onTextChanged(s : CharSequence?, start : Int, before : Int, count : Int) {
                // Filter subjects based on search query
                filterSubjects(s.toString())
            }

            override fun afterTextChanged(s : Editable?) {
                // Not needed
            }
        })

        return view
    }

    private fun onSubjectSelected(subject : String) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, ChaptersFragment.newInstance(subject))
        transaction.addToBackStack(null)
        transaction.commit()
        mainActivity.toolbar.title = subject
    }

    private fun filterSubjects(query : String) {
        val filteredList = subjectsList.filter { it.contains(query, ignoreCase = true) }
        subjectsAdapter.updateSubjects(filteredList)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.toolbar.title = getString(R.string.app_name)
    }
}
