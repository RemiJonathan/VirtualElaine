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
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.remijonathan.virtualelaine.model.Label;

import java.util.List;

public class CreateNewTaskActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
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
        DialogFragment datePicker = new SelectDateFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dueDateAndTime.set(Calendar.YEAR, year);
        dueDateAndTime.set(Calendar.MONTH, month);
        dueDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String dueDate = String.format("%d/%d/%d %d:%02d", dueDateAndTime.get(Calendar.DAY_OF_MONTH), dueDateAndTime.get(Calendar.MONTH), dueDateAndTime.get(Calendar.YEAR), dueDateAndTime.get(Calendar.HOUR_OF_DAY), dueDateAndTime.get(Calendar.MINUTE));

        long timeBetween = ((dueDateAndTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 86400000) + 1;

        String timeToDate;
        if (timeBetween > 1) timeToDate = timeBetween + " days";
        else timeToDate = timeBetween + " day";

        dateTimeSelect.setText(String.format("%s (%s)", dueDate, timeToDate));
    }
}