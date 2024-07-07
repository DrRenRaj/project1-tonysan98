package edu.msudenver.cs3013.project1_s24

import java.io.Serializable

data class Project(
    val name: String,
    val contactPhoneNumber: String,
    val contactEmail: String,
    val carMake: String,
    val carModel: String,
    val carYear: String,
    val description: String,
    val dateArrived: String,
    val totalCost: String
) : Serializable

object ProjectDatabase {
    val projects = mutableListOf<Project>()

    fun addProject(project: Project) {
        projects.add(project)
    }
}