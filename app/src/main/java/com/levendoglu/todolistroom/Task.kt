package com.levendoglu.todolistroom

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.time.LocalTime

@Entity(tableName = "todolist")
data class Task(@PrimaryKey(autoGenerate = true)
                @ColumnInfo(name = "id") var id:Int,
                @ColumnInfo(name = "name") var name:String) {

}