package com.example.simpletodoapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.simpletodoapp.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
