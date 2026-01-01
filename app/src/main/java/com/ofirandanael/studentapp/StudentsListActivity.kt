package com.ofirandanael.studentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ofirandanael.studentapp.adapter.StudentsAdapter
import com.ofirandanael.studentapp.model.Model

/**
 * Activity that displays the list of students using RecyclerView
 */
class StudentsListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentsAdapter
    private lateinit var fabAddStudent: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        title = "Students List"

        recyclerView = findViewById(R.id.studentsRecyclerView)
        fabAddStudent = findViewById(R.id.fabAddStudent)

        setupRecyclerView()

        loadStudents()
    }

    private fun setupRecyclerView() {
        adapter = StudentsAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadStudents() {
        val students = Model.shared.getAllStudents()
        adapter.updateStudents(students)
    }

    override fun onResume() {
        super.onResume()
        loadStudents()
    }
}

