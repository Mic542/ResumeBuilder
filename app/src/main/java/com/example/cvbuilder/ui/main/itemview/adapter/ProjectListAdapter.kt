package com.example.cvbuilder.ui.main.itemview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.data.ProjectDetail
import com.example.cvbuilder.databinding.ProjectListFragmentBinding
import com.example.cvbuilder.databinding.ProjectListItemviewBinding

class ProjectListAdapter(private var projectListData : ArrayList<ProjectDetail>) :
    RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ProjectListItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    private var _binding : ProjectListItemviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ProjectListItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(projectListData[position]) {
                binding.projectNameText.setText(projectName)
                binding.teamSizeText.setText(teamSize.toString())
                binding.projectSummaryText.setText(summary)
                binding.technologyText.setText(technologyUsed)
                binding.roleText.setText(role)

                binding.projectNameText.doAfterTextChanged { projectListData[position].projectName = it.toString() }
                binding.teamSizeText.doAfterTextChanged { projectListData[position].teamSize = it.toString() }
                binding.projectSummaryText.doAfterTextChanged { projectListData[position].summary = it.toString() }
                binding.technologyText.doAfterTextChanged { projectListData[position].technologyUsed = it.toString() }
                binding.roleText.doAfterTextChanged { projectListData[position].role = it.toString() }
            }

            binding.deleteProject.setOnClickListener {
                projectListData.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount() = projectListData.size

}