package edu.msudenver.cs3013.project1_s24

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface ProjectClickListener {
    fun onProjectClicked(project: Project)
}

class ProjectAdapter(var projects: MutableList<Project>, private val clickListener: ProjectClickListener) : RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_item, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.projectName.text = project.name
        holder.projectDescription.text = project.description
        // Set other project details to the views here

        holder.itemView.setOnClickListener {
            clickListener.onProjectClicked(project)
        }
    }

    override fun getItemCount(): Int = projects.size

    fun updateProjects(newProjects: List<Project>) {
        projects.clear()
        projects.addAll(newProjects)
        notifyDataSetChanged()
    }

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val projectName: TextView = itemView.findViewById(R.id.projectName)
        val projectDescription: TextView = itemView.findViewById(R.id.projectCarDescription)
        // Initialize other views here
    }
}