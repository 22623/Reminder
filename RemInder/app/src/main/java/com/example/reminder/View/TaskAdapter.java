package com.example.reminder.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reminder.R;
import com.example.reminder.data.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final TaskAdapterEventListener eventListener;

    private List<Task> taskList;
    private Context context;

    public TaskAdapter(TaskAdapterEventListener eventListener, List<Task> taskList, Context context) {
        this.eventListener = eventListener;
        this.taskList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent,false);

        return new TaskViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Task taskClass = this.taskList.get(position);


        holder.setName(taskClass.getTaskName());
        holder.setText(taskClass.getTaskText());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventListener.onTaskClicked(taskClass.getTaskId());

            }
        });

        holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                eventListener.onTaskLongClicked(taskClass.getTaskId());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.taskList.size();
    }

    public void updateList(List<Task> allTasks){
        this.taskList = allTasks;
        notifyDataSetChanged();

    }


    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private TextView taskName;
        private TextView taskText;
        private Context context;

        public TaskViewHolder(@NonNull View rootView, Context context) {
            super(rootView);
            this.context = context;
            this.rootView = rootView;
            this.taskName = rootView.findViewById(R.id.textViewname);
            this.taskText = rootView.findViewById(R.id.textViewtext);
        }

        public void setName(String name) {this.taskName.setText(name);}

        public void setText(String text){this.taskText.setText(text);}
    }

    public interface TaskAdapterEventListener {
        void onTaskClicked(long taskId);
        void onTaskLongClicked(long taskId);
    }

}
