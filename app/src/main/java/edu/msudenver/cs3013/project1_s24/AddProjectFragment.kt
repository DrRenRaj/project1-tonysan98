package edu.msudenver.cs3013.project1_s24

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.msudenver.cs3013.project1_s24.databinding.FragmentAddProjectBinding
import java.util.Calendar

class AddProjectFragment : Fragment() {

    private var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This shows the date when the user clicks on the hint
        binding.projectDateInput.setOnClickListener {
            showDatePickerDialog()
        }

        // this will save the information to the in memory database
        binding.submitProjectButton.setOnClickListener {
            val project = Project(
                name = binding.projectName.text.toString(),
                contactPhoneNumber = binding.projectContactPhoneNumber.text.toString(),
                contactEmail = binding.projectContactEmail.text.toString(),
                carMake = binding.projectCarMake.text.toString(),
                carModel = binding.projectCarModel.text.toString(),
                carYear = binding.projectCarYear.text.toString(),
                description = binding.projectCarDescription.text.toString(),
                dateArrived = binding.projectDateInput.text.toString(),
                totalCost = binding.projectCarCost.text.toString()
            )
            ProjectDatabase.addProject(project)
            // Clear the form
            binding.projectName.text.clear()
            binding.projectContactPhoneNumber.text.clear()
            binding.projectContactEmail.text.clear()
            binding.projectCarMake.text.clear()
            binding.projectCarModel.text.clear()
            binding.projectCarYear.text.clear()
            binding.projectCarDescription.text.clear()
            binding.projectDateInput.text.clear()
            binding.projectCarCost.text.clear()

            // Show a success message
            Toast.makeText(requireContext(), "Project added successfully", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
            val selectedDate = "${monthOfYear + 1}/$dayOfMonth/$year"
            binding.projectDateInput.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}