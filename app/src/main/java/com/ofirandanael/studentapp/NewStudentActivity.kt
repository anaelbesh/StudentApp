package com.ofirandanael.studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ofirandanael.studentapp.model.Model
import com.ofirandanael.studentapp.model.Student

/**
 * Activity for adding a new student to the database
 */
class NewStudentActivity : AppCompatActivity() {

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var idInputLayout: TextInputLayout
    private lateinit var idEditText: TextInputEditText
    private lateinit var phoneEditText: TextInputEditText
    private lateinit var addressEditText: TextInputEditText
    private lateinit var checkedCheckBox: CheckBox
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        title = getString(R.string.new_student)

        initializeViews()

        setupListeners()
    }

    private fun initializeViews() {
        nameInputLayout = findViewById(R.id.nameInputLayout)
        nameEditText = findViewById(R.id.nameEditText)
        idInputLayout = findViewById(R.id.idInputLayout)
        idEditText = findViewById(R.id.idEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        addressEditText = findViewById(R.id.addressEditText)
        checkedCheckBox = findViewById(R.id.checkedCheckBox)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)
    }

    private fun setupListeners() {
        saveButton.setOnClickListener {
            saveStudent()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun saveStudent() {
        nameInputLayout.error = null
        idInputLayout.error = null

        val name = nameEditText.text?.toString()?.trim() ?: ""
        val id = idEditText.text?.toString()?.trim() ?: ""
        val phone = phoneEditText.text?.toString()?.trim() ?: ""
        val address = addressEditText.text?.toString()?.trim() ?: ""
        val isChecked = checkedCheckBox.isChecked

        var isValid = true

        if (name.isEmpty()) {
            nameInputLayout.error = getString(R.string.error_name_required)
            isValid = false
        }

        if (id.isEmpty()) {
            idInputLayout.error = getString(R.string.error_id_required)
            isValid = false
        } else if (Model.shared.studentExists(id)) {
            idInputLayout.error = getString(R.string.error_id_exists)
            isValid = false
        }

        if (!isValid) {
            return
        }

        val newStudent = Student(
            name = name,
            id = id,
            phone = phone,
            address = address,
            isChecked = isChecked,
            imageResId = R.drawable.student
        )

        Model.shared.addStudent(newStudent)

        Toast.makeText(this, getString(R.string.student_added_success), Toast.LENGTH_SHORT).show()

        finish()
    }
}

