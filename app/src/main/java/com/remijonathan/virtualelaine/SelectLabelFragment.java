package com.remijonathan.virtualelaine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.remijonathan.virtualelaine.data.DatabaseHelper;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SelectLabelFragment extends DialogFragment {

    public interface OnInputListener{
        void sendInput(int input);
    }
    public OnInputListener onInputListener;

    private RecyclerView recyclerView;
    private SelectLabelAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final View view =  inflater.inflate(R.layout.fragment_select_label, container,false);

        final DatabaseHelper db = new DatabaseHelper(view.getContext());

        recyclerView = view.findViewById(R.id.all_labels_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new SelectLabelAdapter(db.getLabels());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SelectLabelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                int selectedLabel = db.getLabels().get(position).getId();
                String selectedLabelTitle = db.getLabels().get(position).getTitle();

                ((CreateNewTaskActivity)getActivity()).labelSelect.setText(selectedLabelTitle);

                onInputListener.sendInput(selectedLabel);

                //Toast.makeText(view.getContext(), db.getLabels().get(position).getTitle()+" label selected", Toast.LENGTH_LONG).show();
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException" +e.getMessage() );
        }
    }
}
