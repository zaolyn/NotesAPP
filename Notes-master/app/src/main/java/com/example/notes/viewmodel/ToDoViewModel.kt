package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.notes.data.ToDoDatabase
import com.example.notes.data.model.ToDoData
import com.example.notes.data.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application): AndroidViewModel(application) {

    private val toDoDao = ToDoDatabase.getDataBase(
            application
    ).toDoDao()

    private val repository: ToDoRepository

    val getAllData: LiveData<List<ToDoData>>

    init {
        repository = ToDoRepository(toDoDao)
        getAllData = repository.getAllData
    }

    fun insertData(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(toDoData)
        }
    }
    fun updateData(toDoData: ToDoData){
        viewModelScope.launch  (Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(toDoData)
        }
    }
    fun deleteAllData(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllData()
        }
    }
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>{
        return repository.searchDatabase(searchQuery)
    }

}