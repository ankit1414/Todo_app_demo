package com.example.todoapp

import com.example.todoapp.db.TodoListItem

interface ItemClickListener {
    fun onItemClick(actionType: String, todoListItem: TodoListItem)
}