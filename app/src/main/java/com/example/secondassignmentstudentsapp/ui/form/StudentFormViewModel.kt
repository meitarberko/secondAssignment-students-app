package com.example.secondassignmentstudentsapp.ui.form

import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class StudentFormViewModel : ViewModel() {

    fun createStudent(student: Student): ValidationResult {
        if (!isValid(student)) return ValidationResult.Error("All fields are required")
        if (StudentRepository.isIdTaken(student.id)) return ValidationResult.Error("ID already exists")
        StudentRepository.add(student)
        return ValidationResult.Success
    }

    fun loadStudent(studentId: String): Student? = StudentRepository.getById(studentId)

    fun updateStudent(oldId: String, updated: Student): ValidationResult {
        if (!isValid(updated)) return ValidationResult.Error("All fields are required")
        if (StudentRepository.isIdTaken(updated.id, ignoreId = oldId)) {
            return ValidationResult.Error("ID already exists")
        }
        val ok = StudentRepository.update(oldId, updated)
        return if (ok) ValidationResult.Success else ValidationResult.Error("Student not found")
    }

    fun deleteStudent(studentId: String): Boolean = StudentRepository.delete(studentId)

    private fun isValid(s: Student): Boolean {
        return s.name.isNotBlank() && s.id.isNotBlank() && s.phone.isNotBlank() && s.address.isNotBlank()
    }
}

sealed class ValidationResult {
    data object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}
