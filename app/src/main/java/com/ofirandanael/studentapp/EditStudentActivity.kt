package com.ofirandanael.studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ofirandanael.studentapp.model.Model
import com.ofirandanael.studentapp.model.Student

/**
 * Activity for editing or deleting an existing student
 */
class EditStudentActivity : AppCompatActivity() {

    private lateinit var studentImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var checkedCheckBox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var deleteButton: Button
    private lateinit var cancelButton: Button

    private var originalStudentId: String? = null
    private var currentImageResId: Int = R.drawable.student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        title = "Edit Student"

        // Initialize views
        studentImageView = findViewById(R.id.editStudentImageView)
        nameEditText = findViewById(R.id.editStudentNameEditText)
        idEditText = findViewById(R.id.editStudentIdEditText)
        phoneEditText = findViewById(R.id.editStudentPhoneEditText)
        addressEditText = findViewById(R.id.editStudentAddressEditText)
        checkedCheckBox = findViewById(R.id.editStudentCheckBox)
        saveButton = findViewById(R.id.saveStudentButton)
        deleteButton = findViewById(R.id.deleteStudentButton)
        cancelButton = findViewById(R.id.cancelEditButton)

        val studentId = intent.getStringExtra(Constants.EXTRA_STUDENT_ID)

        if (studentId != null) {
            originalStudentId = studentId
            loadStudentData(studentId)
        } else {
            Toast.makeText(this, "Error: No student ID provided", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupButtons()
    }

    private fun loadStudentData(studentId: String) {
        val student = Model.shared.getStudentById(studentId)

        if (student != null) {
            nameEditText.setText(student.name)
            idEditText.setText(student.id)
            phoneEditText.setText(student.phone)
            addressEditText.setText(student.address)
            checkedCheckBox.isChecked = student.isChecked
            currentImageResId = student.imageResId
            studentImageView.setImageResource(currentImageResId)
        } else {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupButtons() {
        saveButton.setOnClickListener {
            saveStudent()
        }

        deleteButton.setOnClickListener {
            confirmDelete()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun saveStudent() {
        // Get values from fields
        val name = nameEditText.text.toString().trim()
        val newId = idEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()
        val isChecked = checkedCheckBox.isChecked

        // Validate input
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            nameEditText.requestFocus()
            return
        }

        if (newId.isEmpty()) {
            Toast.makeText(this, "Please enter an ID", Toast.LENGTH_SHORT).show()
            idEditText.requestFocus()
            return
        }

        if (phone.isEmpty()) {
            Toast.makeText(this, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            phoneEditText.requestFocus()
            return
        }

        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show()
            addressEditText.requestFocus()
            return
        }

        // Check if ID changed and if new ID already exists
        if (newId != originalStudentId && Model.shared.studentExists(newId)) {
            Toast.makeText(this, "A student with ID '$newId' already exists", Toast.LENGTH_LONG).show()
            idEditText.requestFocus()
            return
        }

        // Create updated student
        val updatedStudent = Student(
            name = name,
            id = newId,
            phone = phone,
            address = address,
            isChecked = isChecked,
            imageResId = currentImageResId
        )

        val success = originalStudentId?.let {
            Model.shared.updateStudent(it, updatedStudent)
        } ?: false

        if (success) {
            Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Failed to update student", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmDelete() {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete this student? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                deleteStudent()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteStudent() {
        val success = originalStudentId?.let {
            Model.shared.deleteStudent(it)
        } ?: false

        if (success) {
            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Failed to delete student", Toast.LENGTH_SHORT).show()
        }
    }
}

