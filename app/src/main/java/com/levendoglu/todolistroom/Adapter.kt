package com.levendoglu.todolistroom

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Adapter(private val mContext:Context,private val taskList:List<Task>,private var db:TaskDatabase,private var tdao:TaskDao) : RecyclerView.Adapter<Adapter.taskViewHolder>() {

    inner class taskViewHolder(view: View):RecyclerView.ViewHolder(view){
        var taskName:TextView
        var checkBox:CheckBox
        var imagePop:ImageView
        init {
            taskName = view.findViewById(R.id.taskName)
            checkBox = view.findViewById(R.id.checkBox)
            imagePop = view.findViewById(R.id.imagePop)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
        val binding = LayoutInflater.from(mContext).inflate(R.layout.task_card,parent,false)
        return taskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }


    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val listItem = taskList[position]
        holder.taskName.text = listItem.name
        holder.imagePop.setOnClickListener {

            val popupMenu = PopupMenu(mContext,holder.imagePop)
            popupMenu.menuInflater.inflate(R.menu.menu_popup,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.action_delete->{
                                val job = CoroutineScope(Dispatchers.Main).launch {
                                    db = TaskDatabase.databaseAccess(mContext)!!
                                    tdao = db.getTaskDao()
                                    val task = Task(listItem.id,"")
                                    tdao.deleteTask(task)
                                    mContext.startActivity(Intent(mContext,MainActivity::class.java))

                                }
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

}