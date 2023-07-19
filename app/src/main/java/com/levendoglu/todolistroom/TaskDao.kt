package com.levendoglu.todolistroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM todolist")
    suspend fun allTasks() : List<Task>
    @Insert
    suspend fun addTask(task: Task)
    @Delete
    suspend fun deleteTask(task: Task)
}