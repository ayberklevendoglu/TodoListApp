package com.levendoglu.todolistroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.levendoglu.todolistroom.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:Adapter
    private lateinit var taskList: List<Task>
    private lateinit var db:TaskDatabase
    private lateinit var tdao:TaskDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAll()


        binding.btnNewTask.setOnClickListener {
            FragmentNewTask().show(supportFragmentManager,"New Task")
        }
    }

    private fun getAll(){
        CoroutineScope(Dispatchers.Main).launch {
            binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.rv.setHasFixedSize(true)
            db = TaskDatabase.databaseAccess(this@MainActivity)!!
            tdao = db.getTaskDao()
            taskList = tdao.allTasks()
            adapter = Adapter(this@MainActivity,taskList, db, tdao)
            binding.rv.adapter = adapter

        }
    }
}