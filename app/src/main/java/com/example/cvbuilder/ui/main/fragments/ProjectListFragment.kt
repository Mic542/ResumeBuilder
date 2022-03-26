package com.example.cvbuilder.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.data.ProjectDetail
import com.example.cvbuilder.databinding.ProjectListFragmentBinding
import com.example.cvbuilder.ui.main.fragments.base.BaseFragmentWithAdapter
import com.example.cvbuilder.ui.main.itemview.adapter.ProjectListAdapter
import com.example.cvbuilder.viewmodel.CVViewModel


class ProjectListFragment : Fragment(),
    BaseFragmentWithAdapter<ProjectListFragmentBinding, ProjectListAdapter, ProjectDetail> {

    private val cvViewModel: CVViewModel by activityViewModels()

    override lateinit var adapter: ProjectListAdapter
    override lateinit var list: ArrayList<ProjectDetail>
    override var _binding : ProjectListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProjectListFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Project"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.projectListRecycleView.layoutManager = layoutManager

        list = if(cvViewModel.currentCVData.project.isNullOrEmpty()) {
            ArrayList()
        } else {
            ArrayList(cvViewModel.currentCVData.project)
        }

        adapter = ProjectListAdapter(list)
        binding.projectListRecycleView.adapter = adapter

        binding.addProject.setOnClickListener {
            list.add(list.lastIndex+1, ProjectDetail("", "1", "", "", ""))
            adapter.notifyItemInserted(list.lastIndex)
        }

        binding.toSaveFragment.setOnClickListener {
            cvViewModel.currentCVData.project = list
            findNavController().navigate(R.id.action_projectListFragment_to_saveDataFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}