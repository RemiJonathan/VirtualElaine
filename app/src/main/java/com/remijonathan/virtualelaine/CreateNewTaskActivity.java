package com.remijonathan.virtualelaine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.material.chip.Chip;
import com.remijonathan.virtualelaine.model.Label;

import java.util.List;

public class CreateNewTaskActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    private EditText titleEditText;
    private Chip dateTimeSelect;
    private Calendar dueDateAndTime = Calendar.getInstance();
    private List<Label> labels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task);

        dateTimeSelect = findViewById(R.id.chip_dateTime);
        dateTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new SelectTimeFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        dueDateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        dueDateAndTime.set(Calendar.MINUTE, minute);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}