package com.example.secondassignmentstudentsapp.ui.form

import android.os.Bundle
import android.widget.Toast
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

        binding.btnCancel.setOnClickListener { finish() }

        binding.btnSave.setOnClickListener {
            val student = Student(
                id = binding.etId.text?.toString().orEmpty().trim(),
                name = binding.etName.text?.toString().orEmpty().trim(),
                phone = binding.etPhone.text?.toString().orEmpty().trim(),
                address = binding.etAddress.text?.toString().orEmpty().trim(),
                isChecked = binding.cbChecked.isChecked
            )

            when (val res = viewModel.createStudent(student)) {
                is ValidationResult.Success -> finish()
                is ValidationResult.Error -> Toast.makeText(this, res.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}