package com.levendoglu.todolistroom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.levendoglu.todolistroom.databinding.FragmentNewTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FragmentNewTask : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentNewTaskBinding
    private lateinit var db:TaskDatabase
    private lateinit var tdao:TaskDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = TaskDatabase.databaseAccess(requireContext())!!
        tdao = db.getTaskDao()

        binding.btnTime.setOnClickListener {
            
        }
        binding.btnSave.setOnClickListener {
            val intent = Intent(requireContext(),MainActivity::class.java)
            save()
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun save() {
        CoroutineScope(Dispatchers.Main).launch {
            val taskName = binding.inputTask.text.toString().trim()
            val newTask = Task(0,taskName)
            tdao.addTask(newTask)
        }
    }
}