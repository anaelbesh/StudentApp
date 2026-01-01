package com.ofirandanael.studentapp.model

import com.ofirandanael.studentapp.R

/**
 * Model class that manages the student database
 * Uses singleton pattern via companion object
 */
class Model private constructor() {
    private val students: MutableList<Student> = mutableListOf()

    init {
        loadStudents(INITIAL_STUDENT_COUNT)
    }

    private fun loadStudents(count: Int) {
        for (i in 1..count) {
            val student = Student(
                name = "Student $i",
                id = i.toString(),
                phone = "050-${1000000 + i}",
                address = "Address $i",
                isChecked = false,
                imageResId = R.drawable.student
            )
            students.add(student)
        }
    }
    fun getAllStudents(): List<Student> {
        return students.toList()
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(originalId: String, updatedStudent: Student): Boolean {
        val index = students.indexOfFirst { it.id == originalId }
        return if (index != -1) {
            students[index] = updatedStudent
            true
        } else {
            false
        }
    }

    fun deleteStudent(id: String): Boolean {
        return students.removeIf { it.id == id }
    }

    fun clearAll() {
        students.clear()
    }

    fun getStudentCount(): Int {
        return students.size
    }

    fun studentExists(id: String): Boolean {
        return students.any { it.id == id }
    }

    fun getStudentsByCheckedStatus(isChecked: Boolean): List<Student> {
        return students.filter { it.isChecked == isChecked }
    }

    fun toggleStudentChecked(id: String): Boolean {
        val student = getStudentById(id)
        return if (student != null) {
            updateStudent(id, student.copy(isChecked = !student.isChecked))
        } else {
            false
        }
    }

    companion object {
        private const val INITIAL_STUDENT_COUNT = 20

        val shared = Model()
    }
}

