package com.example.simpletodoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.simpletodoapp.R;
import com.example.simpletodoapp.adapter.DoneAdapter;
import com.example.simpletodoapp.db.AppDatabase;
import com.example.simpletodoapp.db.DatabaseClient;
import com.example.simpletodoapp.model.Task;

import java.util.List;

public class DoneFragment extends Fragment implements ITaskDeletable {

    private SwipeRefreshLayout refreshLayout;
    private ListView lvDone;
    private AppDatabase appDatabase;
    private List<Task> taskList;
    private DoneAdapter doneAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_done, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvDone = view.findViewById(R.id.lwDone);
        refreshLayout = view.findViewById(R.id.swiperefresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                refreshLayout.setRefreshing(false);
            }
        });

        appDatabase = DatabaseClient.getInstance(getContext()).getAppDatabase();

        prepareData();

    }

    @Override
    public void DeleteClick(Task task) {
        appDatabase.taskDao().delete(task);
        refreshData();
        Toast.makeText(getContext(), "Task Deleted", Toast.LENGTH_SHORT).show();
    }

    private void prepareData() {
        taskList = appDatabase.taskDao().getAllDone();
        doneAdapter = new DoneAdapter(getContext(), taskList, this);
        lvDone.setAdapter(doneAdapter);
    }

    private void refreshData() {
        taskList = appDatabase.taskDao().getAllDone();
        doneAdapter.clear();
        doneAdapter.addAll(taskList);
        doneAdapter.notifyDataSetChanged();

    }
}
