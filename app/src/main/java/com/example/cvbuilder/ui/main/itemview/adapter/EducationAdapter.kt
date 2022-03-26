package com.example.cvbuilder.ui.main.itemview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.data.EducationDetail
import com.example.cvbuilder.data.WorkExperience
import com.example.cvbuilder.databinding.EducationItemviewBinding
import com.example.cvbuilder.databinding.SkillItemviewBinding

class EducationAdapter(private var educationDetails: ArrayList<EducationDetail>) :
    RecyclerView.Adapter<EducationAdapter.ViewHolder>() {

    inner class ViewHolder(binding: EducationItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    private var _binding : EducationItemviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationAdapter.ViewHolder {
        _binding = EducationItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EducationAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(educationDetails[position]) {
                binding.institutionText.setText(institution)
                binding.classText.setText(graduation)
                if(passingYear != null) binding.passingYearText.setText(passingYear.toString())
                else binding.passingYearText.setText("")
                binding.percentageText.setText(finalScore)

                binding.institutionText.doAfterTextChanged { educationDetails[position].institution = it.toString() }
                binding.classText.doAfterTextChanged { educationDetails[position].graduation = it.toString() }
                binding.passingYearText.doAfterTextChanged { educationDetails[position].passingYear = it.toString() }
                binding.percentageText.doAfterTextChanged { educationDetails[position].finalScore = it.toString() }

                binding.deleteEducation.setOnClickListener {
                    educationDetails.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun getItemCount() = educationDetails.size
}