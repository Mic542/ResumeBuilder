package com.example.cvbuilder.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cvbuilder.R
import com.example.cvbuilder.databinding.SaveFragmentBinding
import com.example.cvbuilder.viewmodel.CVViewModel
import kotlinx.coroutines.coroutineScope

class SaveDataFragment : Fragment() {
    private var _binding: SaveFragmentBinding? = null
    private val binding get() = _binding!!

    private val cvViewModel: CVViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SaveFragmentBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Save CV/Resume"
        return  binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.saveNameText.doAfterTextChanged {
            cvViewModel.currentCVData.saveName = it.toString()
        }

        binding.doneButton.setOnClickListener {
            cvViewModel.insert(cvViewModel.currentCVData)
            findNavController().navigate(R.id.action_saveDataFragment_to_exportFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}