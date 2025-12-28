package com.example.secondassignmentstudentsapp.ui.details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.secondassignmentstudentsapp.databinding.ActivityStudentDetailsBinding
import com.example.secondassignmentstudentsapp.utils.IntentKeys

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private val viewModel: StudentDetailsViewModel by viewModels()

    private lateinit var studentId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentId = intent.getStringExtra(IntentKeys.EXTRA_STUDENT_ID) ?: run {
            finish()
            return
        }

        binding.cbChecked.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setChecked(studentId, isChecked)
        }

        binding.btnEdit.setOnClickListener {
            // TODO (Edit commit): open EditStudentActivity with EXTRA_STUDENT_ID
        }
    }

    override fun onResume() {
        super.onResume()
        render()
    }

    private fun render() {
        val s = viewModel.getStudent(studentId) ?: run {
            finish()
            return
        }

        binding.tvName.text = "name: ${s.name}"
        binding.tvId.text = "id: ${s.id}"
        binding.tvPhone.text = "phone: ${s.phone}"
        binding.tvAddress.text = "address: ${s.address}"

        binding.cbChecked.setOnCheckedChangeListener(null)
        binding.cbChecked.isChecked = s.isChecked
        binding.cbChecked.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setChecked(studentId, isChecked)
        }

        // If ID was changed later in Edit screen, keep pointer updated safely
        studentId = s.id
    }
}
