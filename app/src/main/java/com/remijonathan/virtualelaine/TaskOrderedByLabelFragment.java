package com.remijonathan.virtualelaine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.remijonathan.virtualelaine.data.DatabaseHelper;
import com.remijonathan.virtualelaine.model.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskOrderedByLabelFragment extends Fragment {

    private RecyclerView recyclerView;
    private SelectTaskAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_by_label, container, false);

        final DatabaseHelper db = new DatabaseHelper(view.getContext());

        recyclerView = view.findViewById(R.id.task_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());

        List<Task> tasks = db.getTasks();
        Collections.sort(tasks, new SortByLabel());

        adapter = new SelectTaskAdapter(tasks, db.getLabels());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    class SortByLabel implements Comparator<Task>
    {
        public int compare(Task a, Task b)
        {
            return a.getLabel().compareTo(b.getLabel());
        }
    }
}
