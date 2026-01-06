package com.example.secondassignmentstudentsapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class StudentsListViewModel : ViewModel() {

    val studentsLiveData: LiveData<List<Student>> = StudentRepository.studentsLiveData

    init {
        StudentRepository.seedIfEmpty()
    }

    fun refresh() {
    }

    fun toggleChecked(studentId: String) {
        StudentRepository.toggleChecked(studentId)
    }
}
