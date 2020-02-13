package com.example.simpletodoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.simpletodoapp.ui.ITaskDeletable;
import com.example.simpletodoapp.ui.ITaskDoneable;
import com.example.simpletodoapp.R;
import com.example.simpletodoapp.model.Task;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter<Task> {

    private Context context;
    private List<Task> taskList;
    private ITaskDeletable iTaskDeletable;
    private ITaskDoneable iTaskDoneable;

    public ToDoAdapter(@NonNull Context context,
                       @NonNull List<Task> objects,
                       ITaskDeletable iTaskDeletable,
                       ITaskDoneable iTaskDoneable) {
        super(context, R.layout.item_to_do, objects);
        this.context = context;
        this.taskList = objects;
        this.iTaskDeletable = iTaskDeletable;
        this.iTaskDoneable = iTaskDoneable;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.item_to_do, null);

        CheckBox checkBox = convertView.findViewById(R.id.cb);
        TextView taskName = convertView.findViewById(R.id.twTaskName);
        ImageButton btnDelete = convertView.findViewById(R.id.btnDelete);

        final Task task = taskList.get(position);

        taskName.setText(task.Name);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iTaskDeletable.DeleteClick(task);
            }
        });

        checkBox.setChecked(task.isDone);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                iTaskDoneable.TaskDoneClick(task);
            }
        });


        return convertView;
    }
}
