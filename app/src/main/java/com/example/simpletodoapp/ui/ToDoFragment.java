package com.example.simpletodoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.simpletodoapp.R;
import com.example.simpletodoapp.adapter.ToDoAdapter;
import com.example.simpletodoapp.db.AppDatabase;
import com.example.simpletodoapp.db.DatabaseClient;
import com.example.simpletodoapp.model.Task;
import java.util.List;

public class ToDoFragment extends Fragment implements ITaskDeletable, ITaskDoneable {
    private SwipeRefreshLayout refreshLayout;
    private ListView lvToDo;
    private EditText etTaskName;
    private Button btnAdd;
    private AppDatabase appDatabase;
    private List<Task> taskList;
    private ToDoAdapter toDoAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_do, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initId(view);

        appDatabase = DatabaseClient.getInstance(getContext().getApplicationContext()).getAppDatabase();

        prepareData();

    }

    private void initId(View view) {
        lvToDo = view.findViewById(R.id.lwToDo);
        etTaskName = view.findViewById(R.id.etToDo);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTaskName.getText().toString() == "") {
                    Toast.makeText(getContext(), "Name is Required", Toast.LENGTH_SHORT).show();
                } else {

                    Task task = new Task();
                    task.Name = etTaskName.getText().toString();
                    task.isDone = false;
                    appDatabase.taskDao().insert(task);

                    etTaskName.setText("");
                    refreshData();
                }
            }
        });
        refreshLayout = view.findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                refreshLayout.setRefreshing(false);

            }
        });
    }

    @Override
    public void DeleteClick(Task task) {
        appDatabase.taskDao().delete(task);
        refreshData();
        Toast.makeText(getContext(), "Task Deleted", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void TaskDoneClick(Task task) {
        task.isDone = true;
        appDatabase.taskDao().update(task);
        refreshData();
        Toast.makeText(getContext(), "Task Completed", Toast.LENGTH_SHORT).show();

    }

    private void prepareData() {
        taskList = appDatabase.taskDao().getAllToDo();
        toDoAdapter = new ToDoAdapter(ToDoFragment.this.getContext(), taskList, ToDoFragment.this, ToDoFragment.this);
        lvToDo.setAdapter(toDoAdapter);
    }

    private void refreshData() {
        taskList = appDatabase.taskDao().getAllToDo();
        toDoAdapter.clear();
        toDoAdapter.addAll(taskList);
        toDoAdapter.notifyDataSetChanged();

    }
}
