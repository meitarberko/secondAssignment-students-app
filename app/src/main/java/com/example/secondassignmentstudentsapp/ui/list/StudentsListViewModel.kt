package com.example.secondassignmentstudentsapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class StudentsListViewModel : ViewModel() {

    val students: LiveData<List<Student>> = StudentRepository.studentsLiveData

    fun init() {
        StudentRepository.seedIfEmpty()
    }

    fun toggleChecked(studentId: String) {
        StudentRepository.toggleChecked(studentId)
    }
}
