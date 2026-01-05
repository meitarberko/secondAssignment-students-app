package com.example.secondassignmentstudentsapp.ui.form

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.databinding.ActivityEditStudentBinding
import com.example.secondassignmentstudentsapp.utils.IntentKeys

class EditStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditStudentBinding
    private val viewModel: EditStudentViewModel by viewModels()

    private lateinit var originalId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        originalId = intent.getStringExtra(IntentKeys.EXTRA_STUDENT_ID) ?: run {
            finish(); return
        }

        val s = viewModel.getStudent(originalId) ?: run {
            finish(); return
        }
        bindStudent(s)

        binding.btnCancel.setOnClickListener { finish() }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteStudent(originalId)
            finish()
        }

        binding.btnSave.setOnClickListener {
            val updated = readStudentFromForm()
            val ok = viewModel.updateStudent(originalId, updated)
            if (ok) {
                finish()
            } else {
                Toast.makeText(this, "Invalid data or ID already exists", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bindStudent(s: Student) {
        binding.etName.setText(s.name)
        binding.etId.setText(s.id)
        binding.etPhone.setText(s.phone)
        binding.etAddress.setText(s.address)
        binding.cbChecked.isChecked = s.isChecked
    }

    private fun readStudentFromForm(): Student {
        return Student(
            id = binding.etId.text.toString().trim(),
            name = binding.etName.text.toString().trim(),
            phone = binding.etPhone.text.toString().trim(),
            address = binding.etAddress.text.toString().trim(),
            isChecked = binding.cbChecked.isChecked
        )
    }
}
