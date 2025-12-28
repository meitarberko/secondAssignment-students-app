package com.example.secondassignmentstudentsapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.secondassignmentstudentsapp.data.Student
import com.example.secondassignmentstudentsapp.databinding.ItemStudentRowBinding

class StudentAdapter(
    private val onRowClick: (Student) -> Unit,
    private val onCheckedClick: (Student) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentVH>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVH {
        val binding = ItemStudentRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StudentVH(binding)
    }

    override fun onBindViewHolder(holder: StudentVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StudentVH(private val binding: ItemStudentRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.tvName.text = student.name
            binding.tvId.text = student.id

            binding.cbChecked.setOnCheckedChangeListener(null)
            binding.cbChecked.isChecked = student.isChecked
            binding.cbChecked.setOnCheckedChangeListener { _, _ ->
                onCheckedClick(student)
            }

            binding.root.setOnClickListener { onRowClick(student) }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem
    }
}
