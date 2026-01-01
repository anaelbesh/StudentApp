package com.ofirandanael.studentapp

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ofirandanael.studentapp.model.Model

/**
 * Activity that displays the details of a selected student
 */
class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var studentImageView: ImageView
    private lateinit var studentNameTextView: TextView
    private lateinit var studentIdTextView: TextView
    private lateinit var studentPhoneTextView: TextView
    private lateinit var studentAddressTextView: TextView
    private lateinit var studentCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        title = "Student Details"

        studentImageView = findViewById(R.id.studentDetailsImageView)
        studentNameTextView = findViewById(R.id.studentDetailsNameTextView)
        studentIdTextView = findViewById(R.id.studentDetailsIdTextView)
        studentPhoneTextView = findViewById(R.id.studentDetailsPhoneTextView)
        studentAddressTextView = findViewById(R.id.studentDetailsAddressTextView)
        studentCheckBox = findViewById(R.id.studentDetailsCheckBox)

        val studentId = intent.getStringExtra(Constants.EXTRA_STUDENT_ID)

        if (studentId != null) {
            loadStudentDetails(studentId)
        } else {
            finish()
        }
    }

    private fun loadStudentDetails(studentId: String) {
        val student = Model.shared.getStudentById(studentId)

        if (student != null) {
            studentImageView.setImageResource(student.imageResId)
            studentNameTextView.text = student.name
            studentIdTextView.text = getString(R.string.student_id_format, student.id)
            studentPhoneTextView.text = student.phone
            studentAddressTextView.text = student.address
            studentCheckBox.isChecked = student.isChecked
        } else {
            finish()
        }
    }
}

