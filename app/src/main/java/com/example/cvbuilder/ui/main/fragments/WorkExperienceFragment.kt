package com.example.cvbuilder.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.data.WorkExperience
import com.example.cvbuilder.databinding.WorkExperienceFragmentBinding
import com.example.cvbuilder.ui.main.fragments.base.BaseFragmentWithAdapter
import com.example.cvbuilder.ui.main.itemview.adapter.WorkExperienceAdapter
import com.example.cvbuilder.viewmodel.CVViewModel

class WorkExperienceFragment() : Fragment(),
    BaseFragmentWithAdapter<WorkExperienceFragmentBinding, WorkExperienceAdapter, WorkExperience> {

    private val cvViewModel : CVViewModel by activityViewModels()

    override lateinit var adapter: WorkExperienceAdapter
    override lateinit var list: ArrayList<WorkExperience>
    override var _binding: WorkExperienceFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WorkExperienceFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Work Experience"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        list = if(cvViewModel.currentCVData.workSummary.isNullOrEmpty()) {
            ArrayList()
        } else {
            ArrayList(cvViewModel.currentCVData.workSummary)
        }

        adapter = WorkExperienceAdapter(list)
        binding.recyclerView.adapter = adapter

        binding.totalExpText.setText(cvViewModel.currentCVData.yearsExperience?.toString())

        binding.addNewWorkExpButton.setOnClickListener {
            list.add(list.lastIndex+1,WorkExperience(null, null, 0))
            adapter.notifyItemInserted(list.lastIndex)
        }

        binding.toSkillFragmentButton.setOnClickListener {
            if(binding.totalExpText.text.isNullOrEmpty()) {
                cvViewModel.currentCVData.yearsExperience = null
            } else {
                cvViewModel.currentCVData.yearsExperience = binding.totalExpText.text?.toString()?.toInt()
            }
            cvViewModel.currentCVData.workSummary = list
            Log.d("WORK SUMMARY", cvViewModel.currentCVData.workSummary.toString())
            findNavController().navigate(R.id.action_workExperienceFragment_to_skillFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
}