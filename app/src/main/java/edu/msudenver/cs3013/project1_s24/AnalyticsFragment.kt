package edu.msudenver.cs3013.project1_s24

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AnalyticsFragment : Fragment() {

    private lateinit var lineChart: LineChart

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analytics, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineChart = view.findViewById(R.id.chart)
        setupChart()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupChart() {
        val entries = ArrayList<Entry>() // Import Entry from com.github.mikephil.charting.data.Entry

        val dataPoints = getDataForChart() // This should return a List<Pair<String, Float>> representing Month and Total Cost

        dataPoints.forEachIndexed { index, dataPoint ->
            entries.add(Entry(index.toFloat(), dataPoint.second))
        }

        val dataSet = LineDataSet(entries, "Total Cost").apply {
            // Customize dataset appearance here
            valueTextColor = Color.BLACK
            color = Color.BLUE
        }

        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate() // Refresh the chart
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDataForChart(): List<Pair<String, Float>> {
        val costPerMonth = mutableMapOf<String, Float>()
        val dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy")

        ProjectDatabase.projects.forEach { project ->
            val date = LocalDate.parse(project.dateArrived, dateFormatter)
            val monthYear = "${date.monthValue.toString().padStart(2, '0')}/${date.year}"
            val cost = project.totalCost.toFloatOrNull() ?: 0f // Safely convert to Float, default to 0 if conversion fails

            costPerMonth[monthYear] = costPerMonth.getOrDefault(monthYear, 0f) + cost
        }

        // Convert map to list and sort by date
        return costPerMonth.toList().sortedBy { it.first }
    }
}