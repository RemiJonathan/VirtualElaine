package com.remijonathan.virtualelaine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.remijonathan.virtualelaine.model.Label;

import java.util.List;

public class SelectLabelAdapter extends RecyclerView.Adapter<SelectLabelAdapter.LabelViewHolder> {

    private List<Label> labels;

    public static class LabelViewHolder extends RecyclerView.ViewHolder{
        public TextView labelTitleTextView;
        public TextView labelDescriptionTextView;
        public TextView colorSampleTextView;

        public LabelViewHolder(@NonNull View itemView) {
            super(itemView);
            labelTitleTextView = itemView.findViewById(R.id.label_title_text_view);
            labelDescriptionTextView = itemView.findViewById(R.id.label_description_text_view);
            colorSampleTextView = itemView.findViewById(R.id.color_sample_text_view);
        }
    }

    public SelectLabelAdapter(List<Label> labels){
        this.labels = labels;
    }

    @NonNull
    @Override
    public LabelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.label_item, parent, false);
        LabelViewHolder labelViewHolder = new LabelViewHolder(view);
        return  labelViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LabelViewHolder holder, int position) {
        Label label = labels.get(position);

        holder.labelTitleTextView.setText(label.getTitle());

        holder.labelDescriptionTextView.setText(label.getDescription());

        holder.colorSampleTextView.setText("");
        holder.colorSampleTextView.setBackgroundColor(label.getColor());
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }
}
