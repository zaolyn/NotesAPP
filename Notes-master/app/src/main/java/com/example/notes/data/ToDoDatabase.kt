package com.example.notes.data

import android.content.Context
import androidx.room.*
import com.example.notes.data.model.ToDoData

@Database(entities = [ToDoData::class], version = 1,exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object{
        @Volatile
        private var INSTANCE: ToDoDatabase? =null

        fun getDataBase(context: Context): ToDoDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDatabase::class.java,
                        "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}