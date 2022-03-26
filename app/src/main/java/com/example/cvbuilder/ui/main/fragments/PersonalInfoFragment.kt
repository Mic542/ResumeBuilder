package com.example.cvbuilder.ui.main.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cvbuilder.R
import com.example.cvbuilder.databinding.PersonalInfoFragmentBinding
import com.example.cvbuilder.viewmodel.CVViewModel
import com.facebook.drawee.view.SimpleDraweeView


class PersonalInfoFragment : Fragment() {

    private var _binding: PersonalInfoFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val cvViewModel: CVViewModel by activityViewModels()
    private lateinit var fullNameEditText : EditText
    private lateinit var phoneNumber : EditText
    private lateinit var emailAddress : EditText
    private lateinit var residenceAddress : EditText
    private lateinit var careerObjective : EditText
    private lateinit var imageView : SimpleDraweeView

    private var imageUri : Uri? = null

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            imageUri = data?.data
            requireContext().contentResolver.takePersistableUriPermission(
                imageUri!!,
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
            cvViewModel.currentCVData.imageUri = imageUri.toString()
            binding.imageView.setImageURI(cvViewModel.currentCVData.imageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersonalInfoFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Personal Info"
        fullNameEditText = binding.editTextTextPersonName
        phoneNumber = binding.editTextPhone
        emailAddress = binding.editTextTextEmailAddress
        residenceAddress = binding.editTextTextPostalAddress
        careerObjective = binding.editTextCareerObjective
        imageView = binding.imageView
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("BACK", cvViewModel.currentCVData.imageUri.toString())
        imageView.setImageURI(cvViewModel.currentCVData.imageUri)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(!cvViewModel.isNew) {
            fullNameEditText.setText(cvViewModel.currentCVData.fullName)
            phoneNumber.setText(cvViewModel.currentCVData.mobilePhone)
            emailAddress.setText(cvViewModel.currentCVData.emailAddress)
            residenceAddress.setText(cvViewModel.currentCVData.residenceAddress)
            careerObjective.setText(cvViewModel.currentCVData.careerObjective)
            imageView.setImageURI(cvViewModel.currentCVData.imageUri)
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

        binding.addImageButton.setOnClickListener {
            openActivityForResult()
        }
    }

    private fun openActivityForResult() {
        startForResult.launch(Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}