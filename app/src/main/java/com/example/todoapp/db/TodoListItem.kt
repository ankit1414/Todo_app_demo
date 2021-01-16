package com.example.todoapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

const val TABLE_NAME = "todo_list_table"

@Entity(tableName = TABLE_NAME)
class TodoListItem (
    @ColumnInfo(name = "task_title")
    var title: String,

    @ColumnInfo(name = "task_description")
    var description: String = "no description"
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="task_id")
    var id: Int = 0
}