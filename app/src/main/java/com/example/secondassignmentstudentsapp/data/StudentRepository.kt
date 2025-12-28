package com.yourpackage.studentsapp.data

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
                Student("1001", "Kuku", "05434534534", "Rishon LeZion", true),
                Student("1002", "Dana", "0521234567", "Tel Aviv", false),
                Student("1003", "Noam", "0507654321", "Haifa", false),
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
        val removed = students.removeIf { it.id == id }
        if (removed) publish()
        return removed
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
