package com.example.secondassignmentstudentsapp.ui.form

import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class EditStudentViewModel : ViewModel() {

    fun getStudent(studentId: String): Student? = StudentRepository.getById(studentId)

    fun updateStudent(oldId: String, updated: Student): Boolean {
        if (updated.id.isBlank() || updated.name.isBlank() || updated.phone.isBlank() || updated.address.isBlank()) {
            return false
        }
        if (StudentRepository.isIdTaken(updated.id, ignoreId = oldId)) return false

        return StudentRepository.update(oldId, updated)
    }

    fun deleteStudent(studentId: String): Boolean = StudentRepository.delete(studentId)
}
