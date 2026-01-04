package com.example.secondassignmentstudentsapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondassignmentstudentsapp.databinding.ActivityStudentsListBinding
import com.example.secondassignmentstudentsapp.ui.details.StudentDetailsActivity
import com.example.secondassignmentstudentsapp.utils.IntentKeys

class StudentsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsListBinding
    private val viewModel: StudentsListViewModel by viewModels()

    private val adapter = StudentAdapter(
        onRowClick = { student ->
            val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                putExtra(IntentKeys.EXTRA_STUDENT_ID, student.id)
            }
            startActivity(intent)
        },
        onCheckedClick = { student ->
            viewModel.toggleChecked(student.id)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvStudents.layoutManager = LinearLayoutManager(this)
        binding.rvStudents.adapter = adapter

        binding.fabAdd.setOnClickListener {
            // TODO: open NewStudentActivity when implemented
        }

        // Observe list
        viewModel.studentsLiveData.observe(this) { students ->
            adapter.submitList(students)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}
