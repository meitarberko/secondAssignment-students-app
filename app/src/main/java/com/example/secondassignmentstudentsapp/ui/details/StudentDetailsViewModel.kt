package com.example.secondassignmentstudentsapp.ui.details

import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class StudentDetailsViewModel : ViewModel() {

    fun getStudent(studentId: String): Student? =
        StudentRepository.getById(studentId)

    fun setChecked(studentId: String, checked: Boolean) {
        val current = StudentRepository.getById(studentId) ?: return
        if (current.isChecked == checked) return

        StudentRepository.update(studentId, current.copy(isChecked = checked))
    }
}
