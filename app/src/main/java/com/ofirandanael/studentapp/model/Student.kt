package com.ofirandanael.studentapp.model

//Student data class representing a student entity
data class Student(
    val name: String,
    val id: String,
    val phone: String,
    val address: String,
    val isChecked: Boolean = false,
    val imageResId: Int
)

