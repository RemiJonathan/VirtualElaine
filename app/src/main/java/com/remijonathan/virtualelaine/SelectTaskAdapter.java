package com.remijonathan.virtualelaine;

import java.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.remijonathan.virtualelaine.model.Label;
import com.remijonathan.virtualelaine.model.Task;

import java.util.List;

public class SelectTaskAdapter extends RecyclerView.Adapter<SelectTaskAdapter.SelectTaskViewHolder> {

    private List<Task> tasks;
    private List<Label> labels;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public static class SelectTaskViewHolder extends RecyclerView.ViewHolder{
        public TextView taskTitleTextView;
        public TextView taskDescriptionTextView;
        public TextView taskDueDateTextView;
        public TextView taskLabelTextView;

        public SelectTaskViewHolder(@NonNull View itemView, final OnItemClickListener onItemClickListener) {
            super(itemView);
            taskTitleTextView = itemView.findViewById(R.id.task_title_text_view);
            taskDescriptionTextView = itemView.findViewById(R.id.task_description_text_view);
            taskDueDateTextView = itemView.findViewById(R.id.task_due_date_text_view);
            taskLabelTextView = itemView.findViewById(R.id.task_label_text_view);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.completed_box).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            onItemClickListener.onDeleteClick(position);

                        }
                    }
                }
            });
        }
    }

    public SelectTaskAdapter(List<Task> tasks, List<Label> labels){
        this.tasks = tasks;
        this.labels = labels;
    }

    @NonNull
    @Override
    public SelectTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        SelectTaskViewHolder selectTaskViewHolder = new SelectTaskViewHolder(view, onItemClickListener);
        return selectTaskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SelectTaskViewHolder holder, int position) {
        Task task = tasks.get(position);

        holder.taskTitleTextView.setText(task.getTitle());
        holder.taskDescriptionTextView.setText(task.getDescription());

        Calendar.getInstance();
        Calendar dueDateAndTime = task.getDueDate();


        String dueDate = String.format("%d/%d/%d %d:%02d", dueDateAndTime.get(Calendar.DAY_OF_MONTH), dueDateAndTime.get(Calendar.MONTH)+1, dueDateAndTime.get(Calendar.YEAR), dueDateAndTime.get(Calendar.HOUR_OF_DAY), dueDateAndTime.get(Calendar.MINUTE));

        long timeBetween = ((dueDateAndTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 86400000) + 1;

        String timeToDate;
        if (timeBetween > 1) timeToDate = timeBetween + " days";
        else timeToDate = timeBetween + " day";

        holder.taskDueDateTextView.setText(String.format("%s (%s)", dueDate, timeToDate));

        //Handle no label case
        if((Integer.parseInt(task.getLabel())-1) != -1){
            Label taskLabel = labels.get(Integer.parseInt(task.getLabel())-1);
            holder.taskLabelTextView.setText(taskLabel.getTitle());
            holder.taskLabelTextView.setBackgroundColor(taskLabel.getColor());
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
