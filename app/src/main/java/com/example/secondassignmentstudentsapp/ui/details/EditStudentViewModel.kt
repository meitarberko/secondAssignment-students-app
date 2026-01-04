package com.example.secondassignmentstudentsapp.ui.details

import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class EditStudentViewModel : ViewModel() {

    fun getStudent(studentId: String): Student? =
        StudentRepository.getById(studentId)

    /**
     * Update student by previous ID (because ID itself can change).
     */
    fun updateStudent(previousId: String, updated: Student) {
        StudentRepository.update(previousId, updated)
    }

    fun deleteStudent(studentId: String) {
        StudentRepository.delete(studentId)
    }
}
