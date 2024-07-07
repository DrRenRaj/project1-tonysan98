package edu.msudenver.cs3013.project1_s24

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.msudenver.cs3013.project1_s24.R.id.fragment_container
import edu.msudenver.cs3013.project1_s24.databinding.FragmentHomeBinding
import edu.msudenver.cs3013.project1_s24.databinding.FragmentViewProjectsBinding


class ViewProjectsFragment : Fragment(), ProjectClickListener {
    private var _binding: FragmentViewProjectsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentViewProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ProjectAdapter(ProjectDatabase.projects, this)
        binding.projectsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.projectsRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun updateProjects() {
        val adapter = binding.projectsRecyclerView.adapter as? ProjectAdapter
        adapter?.let {
            it.projects = ProjectDatabase.projects
            it.notifyDataSetChanged()
        }
    }

    override fun onProjectClicked(project: Project) {
        // Ensure the fragment is attached to an activity
        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val projectDetailFragment = ProjectDetailFragment.newInstance(project)
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, projectDetailFragment)
                addToBackStack(null)
                commit() // Consider using commit() to debug
            }
        } else {
            // Log or handle the case where the fragment is not attached to an activity
        }
    }
}