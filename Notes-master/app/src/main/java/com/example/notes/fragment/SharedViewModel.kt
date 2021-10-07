package com.example.notes.fragment

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.notes.R
import com.example.notes.data.model.Priority
import com.example.notes.data.model.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {
    val emptyDataBase:MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(toDoData:List<ToDoData>){
        emptyDataBase.value = toDoData.isEmpty()
    }

    val listener:AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position){
                 0->{
                     (parent?.getChildAt(0) as TextView)
                             .setTextColor(
                                     ContextCompat.getColor(application, R.color.red)
                             )
                 }
                1->{
                    (parent?.getChildAt(0)as TextView)
                            .setTextColor(
                                    ContextCompat.getColor(application, R.color.yellow)
                            )
                }
                2-> {
                    (parent?.getChildAt(0)as TextView)
                            .setTextColor(
                                    ContextCompat.getColor(application, R.color.green)
                            )
                }
            }
        }
    }

     fun verifyDataFromUser(title: String, description: String): Boolean {
        return !(title.isEmpty() || description.isEmpty())
    }


     fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH
            }
            "Medium Priority" -> {
                Priority.MEDIUM
            }
            "Low Priority" -> {
                Priority.LOW
            }
            else -> Priority.LOW
        }

    }
}