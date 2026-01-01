package com.ofirandanael.studentapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ofirandanael.studentapp.R
import com.ofirandanael.studentapp.model.Student

/**
 * RecyclerView Adapter for displaying a list of students
 */
class StudentsAdapter(
    private var students: List<Student>
) : RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    /**
     * ViewHolder for student items
     */
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentImageView: ImageView = itemView.findViewById(R.id.studentImageView)
        val studentNameTextView: TextView = itemView.findViewById(R.id.studentNameTextView)
        val studentIdTextView: TextView = itemView.findViewById(R.id.studentIdTextView)
        val studentCheckBox: CheckBox = itemView.findViewById(R.id.studentCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        // Bind student data to views
        holder.studentImageView.setImageResource(student.imageResId)
        holder.studentNameTextView.text = student.name
        holder.studentIdTextView.text = holder.itemView.context.getString(R.string.student_id_format, student.id)
        holder.studentCheckBox.isChecked = student.isChecked
    }

    override fun getItemCount(): Int = students.size

    /**
     * Update the adapter's data with a new list of students
     */
    fun updateStudents(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}

