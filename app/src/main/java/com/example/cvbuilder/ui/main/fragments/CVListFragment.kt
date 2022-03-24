package com.example.cvbuilder.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cvbuilder.R
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.databinding.MainFragmentBinding
import com.example.cvbuilder.viewmodel.CVViewModel

class CVListFragment : Fragment() {

    companion object {
        fun newInstance() = CVListFragment()
    }

    private val cvViewModel: CVViewModel by activityViewModels()

    private var _binding: MainFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.floatingActionButton.setOnClickListener {
            // Create new CV/Resume Data
            cvViewModel.currentCVData = CVData(
                uid = cvViewModel.generateUniqueId(),
                fullName = null,
                mobilePhone = null,
                emailAddress = null,
                residenceAddress = null,
                careerObjective = null,
                yearsExperience = null,
                workSummary = null,
                skill = null,
//                education = null,
//                project = null
            )
            cvViewModel.isNew = true
            Log.d("CURRENT CV", cvViewModel.currentCVData.toString())
            findNavController().navigate(R.id.action_mainFragment_to_personalInfoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}