package com.example.cvbuilder.ui.main.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.data.WorkExperience
import com.example.cvbuilder.databinding.MainFragmentBinding
import com.example.cvbuilder.ui.main.itemview.adapter.CVListAdapter
import com.example.cvbuilder.ui.main.itemview.adapter.WorkExperienceAdapter
import com.example.cvbuilder.viewmodel.CVViewModel

class CVListFragment : Fragment() {

    private val cvViewModel: CVViewModel by activityViewModels()

    private var _binding: MainFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var cvListAdapter: CVListAdapter
    private lateinit var cvList : ArrayList<CVData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        binding.cvLIstRecycleView.layoutManager = layoutManager

        cvViewModel.getAll().observe(viewLifecycleOwner) {
            cvList = ArrayList(it)
            cvListAdapter = CVListAdapter(cvList, cvViewModel)
            binding.cvLIstRecycleView.adapter = cvListAdapter

            if(it.isNotEmpty()) {
                binding.emptyListText.visibility = GONE
            } else {
                binding.emptyListText.visibility = VISIBLE
            }
        }

        binding.floatingActionButton.setOnClickListener {
            // Create new CV/Resume Data
            cvViewModel.currentCVData = CVData(
                uid = cvViewModel.generateUniqueId(),
                saveName = null,
                imageUri = null,
                fullName = null,
                mobilePhone = null,
                emailAddress = null,
                residenceAddress = null,
                careerObjective = null,
                yearsExperience = null,
                workSummary = null,
                skill = null,
                education = null,
                project = null
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