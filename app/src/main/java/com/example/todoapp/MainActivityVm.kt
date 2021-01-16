package com.example.todoapp

import android.app.Application
import androidx.lifecycle.*
import com.example.todoapp.db.TodoListItem
import com.example.todoapp.repository.TodoListRepository
import kotlinx.coroutines.launch

class MainActivityVm(application: Application): AndroidViewModel(application) {
    lateinit var repository: TodoListRepository
    init {
        repository = TodoListRepository(application)
    }
    val allTodoItems: LiveData<List<TodoListItem>> = repository.getAllTodoItems()

    fun deleteTodoItem(todoListItem: TodoListItem) {
        viewModelScope.launch {
            repository.deleteItem(todoListItem)
        }
    }

    fun addTodoItem(todoListItem: TodoListItem) {
        viewModelScope.launch {
            repository.insertItem(todoListItem)
        }
    }
}
class MainActivityVmFactory(val application: Application): ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityVm(application = application) as T
    }
}