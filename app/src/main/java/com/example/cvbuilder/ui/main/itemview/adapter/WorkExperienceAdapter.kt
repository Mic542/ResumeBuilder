package com.example.cvbuilder.ui.main.itemview.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.data.WorkExperience
import com.example.cvbuilder.databinding.WorkExperienceItemviewBinding

class WorkExperienceAdapter(private var workExperiences: ArrayList<WorkExperience>) :
    RecyclerView.Adapter<WorkExperienceAdapter.ViewHolder>() {

    inner class ViewHolder(binding: WorkExperienceItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    private var _binding : WorkExperienceItemviewBinding? = null
    private val binding get() = _binding!!

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        _binding = WorkExperienceItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(workExperiences[position]){
                binding.companyNameText.setText(this.companyName)
                var duration = ""
                if(this.duration != null) {
                    duration = this.duration.toString()
                }
                binding.durationText.setText(duration)
                binding.spinner.setSelection(this.yearOrMonth)

                binding.companyNameText.doAfterTextChanged {
                    if(!it.isNullOrEmpty()) {
                        workExperiences[position].companyName = it.toString()
                    }
                }
                binding.durationText.doAfterTextChanged {
                    if(!it.isNullOrEmpty()) {
                        workExperiences[position].duration = it.toString().toLong()
                    }
                }
                binding.spinner.selected {
                    workExperiences[position].yearOrMonth = it
                }
            }
            binding.deleteExperience.setOnClickListener {
                workExperiences.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = workExperiences.size

    // Extension for Spinner
    private fun Spinner.selected(action: (position:Int) -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                action(position)
            }
        }
    }
}