package com.levendoglu.todolistroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun getTaskDao() : TaskDao

    companion object {
        var INSTANCE:TaskDatabase? = null

        fun databaseAccess(context :Context) : TaskDatabase?{
            if (INSTANCE == null){
                synchronized(TaskDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,TaskDatabase::class.java,"task-lists.db")
                        .createFromAsset("task-lists.db").build()
                }
            }
            return INSTANCE
        }
    }
}