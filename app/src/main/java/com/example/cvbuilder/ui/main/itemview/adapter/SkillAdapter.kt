package com.example.cvbuilder.ui.main.itemview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.data.SkillDetail
import com.example.cvbuilder.databinding.SkillItemviewBinding
import com.example.cvbuilder.databinding.WorkExperienceItemviewBinding

class SkillAdapter(private var skillDetails: ArrayList<SkillDetail>) :
    RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    inner class ViewHolder(binding: SkillItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    private var _binding : SkillItemviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = SkillItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(skillDetails[position]) {
                binding.skillNameText.setText(skillName)
                binding.skillNameText.doAfterTextChanged {
                    if (!it.isNullOrBlank()) {
                        skillDetails[position].skillName = it.toString()
                    }
                }
            }

            binding.deleteButton.setOnClickListener{
                skillDetails.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    override fun getItemCount() = skillDetails.size


}