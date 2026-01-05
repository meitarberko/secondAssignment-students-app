package com.example.secondassignmentstudentsapp.ui.form

import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class StudentFormViewModel : ViewModel() {

    fun createStudent(
        name: String,
        id: String,
        phone: String,
        address: String,
        checked: Boolean
    ): Boolean {
        if (name.isBlank() || id.isBlank() || phone.isBlank() || address.isBlank()) return false
        if (StudentRepository.isIdTaken(id)) return false

        StudentRepository.add(
            Student(
                id = id,
                name = name,
                phone = phone,
                address = address,
                isChecked = checked
            )
        )
        return true
    }
}
