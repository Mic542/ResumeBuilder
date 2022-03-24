package com.example.cvbuilder.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.data.WorkExperience
import com.example.cvbuilder.databinding.WorkExperienceFragmentBinding
import com.example.cvbuilder.ui.main.itemview.adapter.WorkExperienceAdapter
import com.example.cvbuilder.viewmodel.CVViewModel

class WorkExperienceFragment : Fragment() {

    private val cvViewModel : CVViewModel by activityViewModels()

    private var _binding: WorkExperienceFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var workExperienceAdapter: WorkExperienceAdapter
    private lateinit var workExperienceList : ArrayList<WorkExperience>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WorkExperienceFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.layoutManager = layoutManager

        workExperienceList = if(!cvViewModel.isNew) {
            if(!cvViewModel.currentCVData.workSummary.isNullOrEmpty()) {
                ArrayList(cvViewModel.currentCVData.workSummary!!)
            } else {
                ArrayList()
            }
        } else {
            ArrayList()
        }

        workExperienceAdapter = WorkExperienceAdapter(workExperienceList)
        binding.recyclerView.adapter = workExperienceAdapter

        binding.totalExpText.setText(cvViewModel.currentCVData.yearsExperience?.toString())

        binding.addNewWorkExpButton.setOnClickListener {
            workExperienceList.add(workExperienceList.lastIndex+1,WorkExperience(null, null, 0))
            workExperienceAdapter.notifyItemInserted(workExperienceList.lastIndex)
        }

        binding.toSkillFragmentButton.setOnClickListener {
            cvViewModel.currentCVData.yearsExperience = binding.totalExpText.text?.toString()?.toInt()
            cvViewModel.currentCVData.workSummary = workExperienceList
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