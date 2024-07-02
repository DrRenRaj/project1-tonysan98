package edu.msudenver.cs3013.project1_s24

data class JobEntry(
    val id: Int,
    val customerName: String,
    val vehicleDetails: String,
    val jobDescription: String,
    var jobStatus: String,
    var partsAvailability: Boolean
)

object JobDatabase {
    private val jobs = mutableListOf<JobEntry>()

    fun addJob(job: JobEntry) {
        jobs.add(job)
    }

    fun getJobs(): List<JobEntry> {
        return jobs
    }

    fun getJob(id: Int): JobEntry? {
        return jobs.find { it.id == id }
    }

    fun updateJob(job: JobEntry) {
        val index = jobs.indexOfFirst { it.id == job.id }
        if (index != -1) {
            jobs[index] = job
        }
    }

    fun deleteJob(id: Int) {
        jobs.removeIf { it.id == id }
    }
}