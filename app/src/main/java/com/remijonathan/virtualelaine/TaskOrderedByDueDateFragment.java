package com.remijonathan.virtualelaine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.remijonathan.virtualelaine.data.DatabaseHelper;
import com.remijonathan.virtualelaine.model.Task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskOrderedByDueDateFragment extends Fragment {

    private RecyclerView recyclerView;
    private SelectTaskAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_by_due_date, container, false);

        final DatabaseHelper db = new DatabaseHelper(view.getContext());

        recyclerView = view.findViewById(R.id.task_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());

        final List<Task> tasks = db.getTasks();
        Collections.sort(tasks, new SortByDueDate());

        adapter = new SelectTaskAdapter(tasks, db.getLabels());

        adapter = new SelectTaskAdapter(tasks, db.getLabels());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectTaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //Intent intent = new Intent();

            }

            @Override
            public void onDeleteClick(int position) {
                db.deleteTask(tasks.get(position).getId());
                tasks.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        return view;
    }

    class SortByDueDate implements Comparator<Task>
    {
        public int compare(Task a, Task b)
        {
            return (int) (a.getDueDate().getTimeInMillis() - b.getDueDate().getTimeInMillis());
        }
    }
}
