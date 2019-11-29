package com.remijonathan.virtualelaine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.remijonathan.virtualelaine.data.DatabaseHelper;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class CreateLabelActivity extends AppCompatActivity {
    private EditText titleEditText;
    private Chip chipColor;
    private EditText descriptionEditText;
    private Button saveButton;

    private int selectedColor = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_label);

        titleEditText = findViewById(R.id.label_name_edit_text);
        chipColor = findViewById(R.id.chip_color);
        descriptionEditText = findViewById(R.id.description_edit_text);
        saveButton = findViewById(R.id.save_button);

        chipColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateLabelActivity.this, "Open Color Picker", Toast.LENGTH_LONG).show();
                openColorPicker();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLabel();
            }
        });
    }

    public void saveLabel() {
        if (!isEmpty(titleEditText)) {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.createLabel(titleEditText.getText().toString(), selectedColor, descriptionEditText.getText().toString());
            Toast.makeText(this, "Label Created", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "Please enter a Title for your label", Toast.LENGTH_SHORT).show();

    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public void openColorPicker() {
        final ColorPicker colorPicker = new ColorPicker(this);

        //Building Color Palette
        ArrayList<String> colors = new ArrayList<>();

        //Triadic Colors of #9966FF
        colors.add("#FF9966");
        colors.add("#66FF99");
        colors.add("#9966FF");

        //Triadic Colors of #CCFF66
        colors.add("#CCFF66");
        colors.add("#66CCFF");
        colors.add("#FF66CC");

        colorPicker.setColors(colors)
                .setColumns(3)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        Toast.makeText(CreateLabelActivity.this, String.format("Color %d selected", color), Toast.LENGTH_LONG).show();

                        saveButton.setBackgroundColor(color);
                        selectedColor = color;
                        chipColor.setTextColor(color);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(CreateLabelActivity.this, "Selectrion canceled", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }
}

