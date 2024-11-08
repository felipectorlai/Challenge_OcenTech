package com.example.oceantech.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.oceantech.R
import com.example.oceantech.databinding.FragmentFormTaskBinding
import com.example.oceantech.ui.helper.FirebaseHelper
import com.example.oceantech.ui.model.Task

class FormTaskFragment : Fragment() {

    private var _binding: FragmentFormTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var task: Task
    private var newTask: Boolean = true
    private var statusTask: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener { validateData() }

        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            statusTask = when (id) {
                R.id.rbTodo -> 0
                R.id.rbDoing -> 1
                else -> 2
            }
        }
    }

    private fun validateData() {
        val description = binding.edtDescription.text.toString().trim()

        if (description.isNotEmpty()) {

            binding.progressBar.isVisible = true

            if (newTask) task = Task()
            task.description = description
            task.status = statusTask

            saveTask()
        } else {
            Toast.makeText(
                requireContext(),
                "Informe uma descrição para a tarefa.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveTask() {
        FirebaseHelper
            .getDatabase()
            .child("task")
            .child(FirebaseHelper.getIdUser() ?: "")
            .child(task.id)
            .setValue(task)
            .addOnCompleteListener { result ->
                binding.progressBar.isVisible = false

                if (result.isSuccessful) {
                    if (newTask) {
                        Toast.makeText(requireContext(), "Tarefa salva com sucesso.", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(requireContext(), "Tarefa atualizada com sucesso.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Erro ao salvar a tarefa.", Toast.LENGTH_SHORT).show()
                }
            } .addOnFailureListener{
                binding.progressBar.isVisible = false
                Toast.makeText(requireContext(), "Erro ao salvar a tarefa.", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
