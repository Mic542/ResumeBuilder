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
import com.example.cvbuilder.data.SkillDetail
import com.example.cvbuilder.databinding.SkillFragmentBinding
import com.example.cvbuilder.ui.main.itemview.adapter.SkillAdapter
import com.example.cvbuilder.viewmodel.CVViewModel

class SkillFragment : Fragment() {

    private val cvViewModel: CVViewModel by activityViewModels()

    private var _binding : SkillFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var skillAdapter: SkillAdapter
    private lateinit var skillList : ArrayList<SkillDetail>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SkillFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.skillsRecycleView.layoutManager = layoutManager

        skillList = if(!cvViewModel.isNew) {
            if(!cvViewModel.currentCVData.skill.isNullOrEmpty()) {
                ArrayList(cvViewModel.currentCVData.skill!!)
            } else {
                ArrayList()
            }
        } else {
            ArrayList()
        }

        skillAdapter = SkillAdapter(skillList)
        binding.skillsRecycleView.adapter = skillAdapter

        binding.addSkillButton.setOnClickListener {
            skillList.add(skillList.lastIndex+1, SkillDetail(""))
            skillAdapter.notifyItemInserted(skillList.lastIndex)
        }

        binding.toEducationButton.setOnClickListener {
            cvViewModel.currentCVData.skill = skillList
            Log.d("SKILL", cvViewModel.currentCVData.skill.toString())
            findNavController().navigate(R.id.action_skillFragment_to_educationFragment)
        }

    }

}