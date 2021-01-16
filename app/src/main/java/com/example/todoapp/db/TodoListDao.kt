package com.example.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(todoListItem: TodoListItem)

    @Delete
    suspend fun deleteItem(todoListItem: TodoListItem)

    @Query("DELETE FROM todo_list_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_list_table")
    fun getAll(): LiveData<List<TodoListItem>>
}