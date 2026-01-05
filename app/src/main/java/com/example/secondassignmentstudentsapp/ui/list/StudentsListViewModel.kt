package com.example.secondassignmentstudentsapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.data.StudentRepository

class StudentsListViewModel : ViewModel() {

    val studentsLiveData: LiveData<List<Student>> = StudentRepository.studentsLiveData

    init {
        // Ensure initial data exists on first app launch
        StudentRepository.seedIfEmpty()
    }

    fun refresh() {
        // No-op: repository updates LiveData internally (publish()).
        // Exists because Activity may call refresh() onResume.
    }

    fun toggleChecked(studentId: String) {
        StudentRepository.toggleChecked(studentId)
    }
}
