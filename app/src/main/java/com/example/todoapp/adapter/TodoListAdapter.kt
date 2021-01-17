package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.ItemClickListener
import com.example.todoapp.R
import com.example.todoapp.db.TodoListItem

class TodoListAdapter(private val itemClickListener: ItemClickListener):  RecyclerView.Adapter<TodoListAdapter.TodoItemViewHolder>(){

    val data: ArrayList<TodoListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val vh = TodoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
        vh.deleteButton.setOnClickListener{ itemClickListener.onItemClick("DELETE", data[vh.adapterPosition])}
        return vh
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.description.text = data[position].description
    }

    fun submitData(dataArray: List<TodoListItem>) {
        data.clear()
        data.addAll(dataArray)
        notifyDataSetChanged()
    }

    class TodoItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleTV)
        val description: TextView = view.findViewById(R.id.descriptontv)
        val deleteButton: AppCompatImageButton = view.findViewById(R.id.removeBtn)
    }
}