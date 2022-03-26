package com.example.cvbuilder.ui.main.itemview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cvbuilder.R
import com.example.cvbuilder.data.CVData
import com.example.cvbuilder.databinding.CvItemviewBinding
import com.example.cvbuilder.viewmodel.CVViewModel

class CVListAdapter(private var cvListData : ArrayList<CVData>, var viewModel : CVViewModel) :
    RecyclerView.Adapter<CVListAdapter.ViewHolder>() {

    inner class ViewHolder(binding: CvItemviewBinding) : RecyclerView.ViewHolder(binding.root)


    private var _binding : CvItemviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = CvItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(cvListData[position]) {
                binding.cvNameText.text = saveName
                binding.deleteCVButton.setOnClickListener {
                    viewModel.delete(cvListData[position])
                    cvListData.removeAt(position)
                    notifyItemRemoved(position)
                }
                binding.cvCardView.setOnClickListener{
                    viewModel.isNew = false
                    viewModel.currentCVData = cvListData[position]
                    Log.d("CURRENT CV FROM MAIN", viewModel.currentCVData.toString())
                    Navigation.findNavController(itemView).navigate(R.id.action_mainFragment_to_personalInfoFragment)
                }
            }
        }
    }

    override fun getItemCount() = cvListData.size
}