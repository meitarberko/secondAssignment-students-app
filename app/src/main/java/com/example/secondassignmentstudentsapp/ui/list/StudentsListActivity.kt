package com.example.secondassignmentstudentsapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondassignmentstudentsapp.databinding.ActivityStudentsListBinding
import com.example.secondassignmentstudentsapp.ui.details.StudentDetailsActivity
import com.example.secondassignmentstudentsapp.ui.form.NewStudentActivity
import com.example.secondassignmentstudentsapp.utils.IntentKeys

class StudentsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsListBinding
    private val viewModel: StudentsListViewModel by viewModels()

    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = StudentAdapter(
            onRowClick = { student ->
                val i = Intent(this, StudentDetailsActivity::class.java)
                i.putExtra(IntentKeys.EXTRA_STUDENT_ID, student.id)
                startActivity(i)
            },
            onCheckedClick = { student ->
                viewModel.toggleChecked(student.id)
            }
        )

        binding.rvStudents.layoutManager = LinearLayoutManager(this)
        binding.rvStudents.adapter = adapter

        viewModel.studentsLiveData.observe(this) { students ->
            adapter.submitList(students)
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, NewStudentActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}
