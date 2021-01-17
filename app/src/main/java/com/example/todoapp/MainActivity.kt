package com.example.todoapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.adapter.TodoListAdapter
import com.example.todoapp.db.TodoListItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity(), ItemClickListener {
    lateinit var mVm: MainActivityVm
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton
    lateinit var placeholder: CardView
    val adapter: TodoListAdapter by lazy { TodoListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        placeholder = findViewById(R.id.placeholder)
        mVm = ViewModelProvider(this, MainActivityVmFactory(this.application)).get(MainActivityVm::class.java)
        fab = findViewById(R.id.add_button)
        fab.setOnClickListener { customAlertDialog() }

        recyclerView = findViewById(R.id.home_recylerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        mVm.allTodoItems.observe(this, Observer {
            adapter.submitData(it)
            if(it.size == 0) {
                placeholder.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            else {
                placeholder.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }

    override fun onItemClick(actionType: String, todoListItem: TodoListItem) {
        when(actionType) {
            "DELETE" -> {
                mVm.deleteTodoItem(todoListItem )
            }
        }
    }


    // function to generate custom alert dialog
    fun customAlertDialog() {
        val dialog = getDialog()
        val myview: View = this.layoutInflater.inflate(R.layout.add_item_dialog, null)
        setupDialog(dialog, myview)

        val type_et: TextInputEditText = myview.findViewById(R.id.taskName_et)
        val description_et: TextInputEditText = myview.findViewById(R.id.descriptonet)
        val addButton: Button = myview.findViewById(R.id.addBtn)

        addButton.setOnClickListener {
            val title: String? = type_et.text?.toString()
            val description: String? = description_et.text?.toString()
            if(title.isNullOrBlank()) {
                return@setOnClickListener
            }
            val newtodo = TodoListItem(title,description ?: "no description")
            mVm.addTodoItem(newtodo)
            dialog.dismiss()
        }
    }

    private fun getDialog(): AlertDialog {
        val mydialog: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialog: AlertDialog = mydialog.create()
        return dialog
    }

    private fun setupDialog(dialog: AlertDialog, myview: View) {
        dialog.setView(myview)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
    }
}