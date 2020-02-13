package com.example.simpletodoapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    public int TaskId;

    @ColumnInfo(name = "name")
    public String Name;

    @ColumnInfo(name = "isDone")
    public Boolean isDone;

}
