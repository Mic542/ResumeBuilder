package com.example.cvbuilder.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cvbuilder.R
import com.example.cvbuilder.databinding.PersonalInfoFragmentBinding
import com.example.cvbuilder.viewmodel.CVViewModel

class PersonalInfoFragment : Fragment() {

    private var _binding: PersonalInfoFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PersonalInfoFragment()
    }

    private val cvViewModel: CVViewModel by activityViewModels()
    private lateinit var fullNameEditText : EditText
    private lateinit var phoneNumber : EditText
    private lateinit var emailAddress : EditText
    private lateinit var residenceAddress : EditText
    private lateinit var careerObjective : EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersonalInfoFragmentBinding.inflate(inflater, container, false)
        fullNameEditText = binding.editTextTextPersonName
        phoneNumber = binding.editTextPhone
        emailAddress = binding.editTextTextEmailAddress
        residenceAddress = binding.editTextTextPostalAddress
        careerObjective = binding.editTextCareerObjective
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(!cvViewModel.isNew) {
            fullNameEditText.setText(cvViewModel.currentCVData.fullName)
            phoneNumber.setText(cvViewModel.currentCVData.mobilePhone)
            emailAddress.setText(cvViewModel.currentCVData.emailAddress)
            residenceAddress.setText(cvViewModel.currentCVData.residenceAddress)
            careerObjective.setText(cvViewModel.currentCVData.careerObjective)
        }

        binding.toWorkSummary.setOnClickListener {
            val currentData = cvViewModel.currentCVData
            currentData.fullName = fullNameEditText.text.toString()
            currentData.mobilePhone = phoneNumber.text.toString()
            currentData.emailAddress = emailAddress.text.toString()
            currentData.residenceAddress = residenceAddress.text.toString()
            currentData.careerObjective = careerObjective.text.toString()
            findNavController().navigate(R.id.action_personalInfoFragment_to_workExperienceFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}