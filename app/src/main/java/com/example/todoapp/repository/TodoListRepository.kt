package com.example.todoapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.todoapp.db.TodoListDb
import com.example.todoapp.db.TodoListItem

class TodoListRepository(application: Application) {

    val todoListDao = TodoListDb.getDatabase(application).todoListDao()

    fun getAllTodoItems(): LiveData<List<TodoListItem>> {
        return todoListDao.getAll()
    }
    suspend fun insertItem(todoListItem: TodoListItem) {
        todoListDao.insertItem(todoListItem)
    }
    suspend fun deleteItem(todoListItem: TodoListItem) {
        todoListDao.deleteItem(todoListItem)
    }
    suspend fun deleteAllItems(todoListItem: TodoListItem) {
        todoListDao.deleteAll()
    }
}