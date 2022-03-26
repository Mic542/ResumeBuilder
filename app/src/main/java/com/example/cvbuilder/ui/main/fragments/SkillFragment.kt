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
import com.example.cvbuilder.data.SkillDetail
import com.example.cvbuilder.databinding.SkillFragmentBinding
import com.example.cvbuilder.ui.main.fragments.base.BaseFragmentWithAdapter
import com.example.cvbuilder.ui.main.itemview.adapter.SkillAdapter
import com.example.cvbuilder.viewmodel.CVViewModel

class SkillFragment : Fragment(),
    BaseFragmentWithAdapter<SkillFragmentBinding, SkillAdapter, SkillDetail> {

    private val cvViewModel: CVViewModel by activityViewModels()

    override lateinit var adapter: SkillAdapter
    override lateinit var list: ArrayList<SkillDetail>
    override var _binding : SkillFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SkillFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Skill"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.skillsRecycleView.layoutManager = layoutManager

        list = if(cvViewModel.currentCVData.skill.isNullOrEmpty()) {
            ArrayList()
        } else {
            ArrayList(cvViewModel.currentCVData.skill)
        }

        adapter = SkillAdapter(list)
        binding.skillsRecycleView.adapter = adapter

        binding.addSkillButton.setOnClickListener {
            list.add(list.lastIndex+1, SkillDetail(""))
            adapter.notifyItemInserted(list.lastIndex)
        }

        binding.toEducationButton.setOnClickListener {
            cvViewModel.currentCVData.skill = list
            Log.d("SKILL", cvViewModel.currentCVData.skill.toString())
            findNavController().navigate(R.id.action_skillFragment_to_educationFragment)
        }

    }

}