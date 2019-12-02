package com.remijonathan.virtualelaine;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.chip.Chip;
import com.remijonathan.virtualelaine.data.DatabaseHelper;
import com.remijonathan.virtualelaine.model.Task;

public class EditTaskActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, SelectLabelFragment.OnInputListener {

    Chip labelSelect;
    private EditText titleEditText;
    private Chip dateTimeSelect;
    private Calendar dueDateAndTime = Calendar.getInstance();
    private int selectedLabel = 0;
    private EditText descriptionEditText;
    private Button saveButton;
    private String DateTimeDueJson = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task);

        Intent intent = getIntent();
        final int taskId = intent.getIntExtra("task index", 0);

        DatabaseHelper db = new DatabaseHelper(this);

        Task task = db.getTask(taskId);

        titleEditText = findViewById(R.id.title_edit_text);
        titleEditText.setText(task.getTitle());

        dateTimeSelect = findViewById(R.id.chip_dateTime);
        dateTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new SelectTimeFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        labelSelect = findViewById(R.id.chip_label);
        labelSelect.setVisibility(View.GONE);
        descriptionEditText = findViewById(R.id.description_edit_text);
        descriptionEditText.setText(task.getDescription());

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(titleEditText)) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                    //db.putTask("'"+titleEditText.getText().toString()+"'", "'"+DateTimeDueJson+"'", ""+selectedLabel,"'"+descriptionEditText.getText().toString()+"'");
                    db.updateTaskTitle(taskId, titleEditText.getText().toString());
                    db.updateTaskDueDate(taskId, DateTimeDueJson);
                    db.updateTaskDescription(taskId, descriptionEditText.getText().toString());

                    Intent intent = new Intent(EditTaskActivity.this, MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Task '" + titleEditText.getText().toString() + "' Saved", Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }
            }
        });


        java.util.Calendar.getInstance();
        java.util.Calendar dueDateAndTime = task.getDueDate();


        String dueDate = String.format("%d/%d/%d %d:%02d", dueDateAndTime.get(java.util.Calendar.DAY_OF_MONTH), dueDateAndTime.get(java.util.Calendar.MONTH) + 1, dueDateAndTime.get(java.util.Calendar.YEAR), dueDateAndTime.get(java.util.Calendar.HOUR_OF_DAY), dueDateAndTime.get(java.util.Calendar.MINUTE));

        long timeBetween = ((dueDateAndTime.getTimeInMillis() - java.util.Calendar.getInstance().getTimeInMillis()) / 86400000) + 1;

        String timeToDate;
        if (timeBetween > 1) timeToDate = timeBetween + " days";
        else timeToDate = timeBetween + " day";

        dateTimeSelect.setText(String.format("%s (%s)", dueDate, timeToDate));
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
        Toast.makeText(this, "" + month, Toast.LENGTH_LONG).show();
        dueDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String dueDate = String.format("%s/%s/%d %d:%02d", "" + dueDateAndTime.get(Calendar.DAY_OF_MONTH), "" + (dueDateAndTime.get(Calendar.MONTH) + 1), dueDateAndTime.get(Calendar.YEAR), dueDateAndTime.get(Calendar.HOUR_OF_DAY), dueDateAndTime.get(Calendar.MINUTE));

        long timeBetween = ((dueDateAndTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 86400000) + 1;

        String timeToDate;
        if (timeBetween > 1) timeToDate = timeBetween + " days";
        else timeToDate = timeBetween + " day";

        dateTimeSelect.setText(String.format("%s (%s)", dueDate, timeToDate));

        DateTimeDueJson = dueDate;
    }

    public void showLabelSelectDialog() {
        DialogFragment selectLabelFragment = new SelectLabelFragment();
        selectLabelFragment.show(getSupportFragmentManager(), "label picker");

    }

    @Override
    public void sendInput(int input) {
        selectedLabel = input;
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
