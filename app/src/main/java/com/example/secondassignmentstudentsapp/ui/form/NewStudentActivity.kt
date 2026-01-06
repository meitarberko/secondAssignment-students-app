package com.example.secondassignmentstudentsapp.ui.form

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.databinding.ActivityNewStudentBinding

class NewStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewStudentBinding
    private val viewModel: StudentFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveStudent()
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }



    }
    private fun saveStudent() {
        val name = binding.etName.text.toString().trim()
        val id = binding.etId.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val isChecked = binding.cbChecked.isChecked

        if (name.isEmpty() || id.isEmpty()) {
            return
        }

        val student = Student(
            id = id,
            name = name,
            phone = phone,
            address = address,
            isChecked = isChecked
        )

        viewModel.createStudent(name = name,
            id = id,
            phone = phone,
            address = address,
            checked = isChecked)

        finish()
    }
}

