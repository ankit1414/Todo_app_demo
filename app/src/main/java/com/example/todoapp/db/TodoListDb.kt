package com.example.todoapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DB_NAME = "todo_list_dp"

@Database(entities = arrayOf(TodoListItem::class), version = 1, exportSchema = false)
abstract class TodoListDb: RoomDatabase() {

    abstract fun todoListDao(): TodoListDao

    companion object{
        @Volatile
        private var INSTANCE: TodoListDb? = null

        fun getDatabase(context: Context): TodoListDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoListDb::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}