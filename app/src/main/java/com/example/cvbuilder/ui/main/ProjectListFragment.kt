package com.example.cvbuilder.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cvbuilder.R
import com.example.cvbuilder.viewmodel.ProjectListViewModel

class ProjectListFragment : Fragment() {

    companion object {
        fun newInstance() = ProjectListFragment()
    }

    private lateinit var viewModel: ProjectListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.project_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}