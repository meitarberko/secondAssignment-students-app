package com.example.secondassignmentstudentsapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object StudentRepository {

    private val students = mutableListOf<Student>()

    private val _studentsLiveData = MutableLiveData<List<Student>>(emptyList())
    val studentsLiveData: LiveData<List<Student>> = _studentsLiveData

    fun seedIfEmpty() {
        if (students.isNotEmpty()) return
        students.addAll(
            listOf(
                Student("1001", "Hadar", "0509796100", "Tel Aviv", true),
                Student("1002", "Meitar", "0504532007", "Hod Hasharon", true),
                Student("1003", "Avshi", "0528057979", "Rishon LeZion", false),
            )
        )
        publish()
    }

    fun getById(id: String): Student? = students.firstOrNull { it.id == id }

    fun add(student: Student) {
        students.add(student)
        publish()
    }

    fun update(oldId: String, updated: Student): Boolean {
        val index = students.indexOfFirst { it.id == oldId }
        if (index == -1) return false
        students[index] = updated
        publish()
        return true
    }

    fun delete(id: String): Boolean {
        val index = students.indexOfFirst { it.id == id }
        if (index == -1) return false
        students.removeAt(index)
        publish()
        return true
    }


    fun toggleChecked(id: String): Boolean {
        val index = students.indexOfFirst { it.id == id }
        if (index == -1) return false
        val s = students[index]
        students[index] = s.copy(isChecked = !s.isChecked)
        publish()
        return true
    }

    fun isIdTaken(id: String, ignoreId: String? = null): Boolean {
        return students.any { it.id == id && it.id != ignoreId }
    }

    private fun publish() {
        _studentsLiveData.value = students.toList()
    }
}
