package com.example.secondassignmentstudentsapp.ui.form

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignmentstudentsapp.databinding.ActivityEditStudentBinding
import com.example.secondassignmentstudentsapp.ui.details.EditStudentViewModel
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
            finish()
            return
        }

        val student = viewModel.getStudent(originalId) ?: run {
            finish()
            return
        }

        // Prefill
        binding.etName.setText(student.name)
        binding.etId.setText(student.id)
        binding.etPhone.setText(student.phone)
        binding.etAddress.setText(student.address)
        binding.cbChecked.isChecked = student.isChecked

        binding.btnCancel.setOnClickListener { finish() }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteStudent(originalId)
            finish()
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val id = binding.etId.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val address = binding.etAddress.text.toString().trim()
            val checked = binding.cbChecked.isChecked

            if (name.isBlank() || id.isBlank()) {
                Toast.makeText(this, "Name and ID are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updated = student.copy(
                name = name,
                id = id,
                phone = phone,
                address = address,
                isChecked = checked
            )

            viewModel.updateStudent(originalId, updated)
            finish()
        }


        binding.btnCancel.setOnClickListener {
            // הפקודה finish() סוגרת את ה-Activity הנוכחי וחוזרת למסך הקודם
            finish()
        }
    }
}
