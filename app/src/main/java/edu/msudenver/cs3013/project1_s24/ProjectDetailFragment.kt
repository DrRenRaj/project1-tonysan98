package edu.msudenver.cs3013.project1_s24

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.io.Serializable

class ProjectDetailFragment : Fragment() {
    private var project: Project? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            project = it.getSerializable("project") as? Project
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // project details to views
        view.findViewById<TextView>(R.id.projectNameTextView).text = project?.name
        view.findViewById<TextView>(R.id.projectDescriptionTextView).text = project?.description

    }

    companion object {
        fun newInstance(project: Project): ProjectDetailFragment {
            val args = Bundle().apply {
                putSerializable("project", project)
            }
            return ProjectDetailFragment().apply {
                arguments = args
            }
        }
    }
}