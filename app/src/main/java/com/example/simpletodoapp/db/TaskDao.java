package com.example.simpletodoapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.simpletodoapp.model.Task;
import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Query("SELECT * FROM task WHERE isDone=0")
    List<Task> getAllToDo();

    @Query("SELECT * FROM task WHERE isDone=1")
    List<Task> getAllDone();

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);


}
