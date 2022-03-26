package com.example.cvbuilder.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.data.EducationDetail
import com.example.cvbuilder.databinding.EducationFragmentBinding
import com.example.cvbuilder.ui.main.fragments.base.BaseFragmentWithAdapter
import com.example.cvbuilder.ui.main.itemview.adapter.EducationAdapter
import com.example.cvbuilder.viewmodel.CVViewModel

class EducationFragment() : Fragment(),
    BaseFragmentWithAdapter<EducationFragmentBinding, EducationAdapter, EducationDetail> {

    override var _binding: EducationFragmentBinding? = null
    override lateinit var adapter: EducationAdapter
    override lateinit var list: ArrayList<EducationDetail>
    private val cvViewModel: CVViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EducationFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Add Education"

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.educationRecycleView.layoutManager = layoutManager

        list = if(cvViewModel.currentCVData.education.isNullOrEmpty()) {
            ArrayList()
        } else {
            ArrayList(cvViewModel.currentCVData.education)
        }

        adapter = EducationAdapter(list)
        binding.educationRecycleView.adapter = adapter

        binding.addEducation.setOnClickListener {
            list.add(list.lastIndex+1, EducationDetail("", "", null, ""))
            adapter.notifyItemInserted(list.lastIndex)
        }

        binding.toProjectListButtton.setOnClickListener {
            cvViewModel.currentCVData.education = list
            findNavController().navigate(R.id.action_educationFragment_to_projectListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}