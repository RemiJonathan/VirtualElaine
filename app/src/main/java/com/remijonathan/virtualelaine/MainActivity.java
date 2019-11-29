package com.remijonathan.virtualelaine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /*Handling a Menu item
     * REMINDERS
     * return true; not super
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_menu_item:
                Toast.makeText(this, "Create new Task", Toast.LENGTH_LONG).show();
                goToCreateNewTaskActivity();
                return true;
            case R.id.create_label_menu_item:
                goToCreateLabelActivity();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Switching Activities
    public void goToCreateNewTaskActivity(){
        Intent intent = new Intent(this, CreateNewTaskActivity.class);
        startActivity(intent);
    }

    //Switching Activities
    public void goToCreateLabelActivity(){
        Intent intent = new Intent(this, CreateLabelActivity.class);
        startActivity(intent);
    }
}
