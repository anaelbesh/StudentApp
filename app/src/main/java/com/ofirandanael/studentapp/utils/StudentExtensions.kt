package com.ofirandanael.studentapp.utils

import com.ofirandanael.studentapp.model.Student

/**
 * Extension functions and utility methods for Student operations
 */

fun Student.toggleChecked(): Student {
    return this.copy(isChecked = !this.isChecked)
}

fun Student.isValid(): Boolean {
    return name.isNotBlank() &&
           id.isNotBlank() &&
           phone.isNotBlank() &&
           address.isNotBlank()
}

fun Student.getDisplayText(): String {
    return "$name (ID: $id)"
}

fun Student.formatPhoneNumber(): String {
    val digitsOnly = phone.replace(Regex("[^0-9]"), "")
    return when {
        digitsOnly.length == 10 -> "${digitsOnly.substring(0, 3)}-${digitsOnly.substring(3, 10)}"
        digitsOnly.length == 9 && digitsOnly.startsWith("5") -> "0${
            digitsOnly.substring(
                0,
                2
            )
        }-${digitsOnly.substring(2, 9)}"

        else -> phone // Return original if format is unexpected
    }
}

/**
 * Object containing validation rules for Student data
 */
object StudentValidator {

    private const val ID_MIN_LENGTH = 3
    private const val NAME_MIN_LENGTH = 2
    private const val PHONE_MIN_LENGTH = 9

    fun validateName(name: String): ValidationResult {
        return when {
            name.isBlank() -> ValidationResult.Error("Name cannot be empty")
            name.length < NAME_MIN_LENGTH -> ValidationResult.Error("Name must be at least $NAME_MIN_LENGTH characters")
            else -> ValidationResult.Success
        }
    }

    fun validateId(id: String): ValidationResult {
        return when {
            id.isBlank() -> ValidationResult.Error("ID cannot be empty")
            id.length < ID_MIN_LENGTH -> ValidationResult.Error("ID must be at least $ID_MIN_LENGTH characters")
            else -> ValidationResult.Success
        }
    }

    fun validatePhone(phone: String): ValidationResult {
        val digitsOnly = phone.replace(Regex("[^0-9]"), "")
        return when {
            phone.isBlank() -> ValidationResult.Error("Phone cannot be empty")
            digitsOnly.length < PHONE_MIN_LENGTH -> ValidationResult.Error("Phone number must have at least $PHONE_MIN_LENGTH digits")
            else -> ValidationResult.Success
        }
    }

    fun validateAddress(address: String): ValidationResult {
        return when {
            address.isBlank() -> ValidationResult.Error("Address cannot be empty")
            else -> ValidationResult.Success
        }
    }

    fun validateStudent(student: Student): ValidationResult {
        validateName(student.name).let { if (it is ValidationResult.Error) return it }
        validateId(student.id).let { if (it is ValidationResult.Error) return it }
        validatePhone(student.phone).let { if (it is ValidationResult.Error) return it }
        validateAddress(student.address).let { if (it is ValidationResult.Error) return it }
        return ValidationResult.Success
    }
}

/**
 * Sealed class representing validation results
 */
sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}

