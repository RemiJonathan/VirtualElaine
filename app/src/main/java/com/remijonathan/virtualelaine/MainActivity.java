package com.remijonathan.virtualelaine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.remijonathan.virtualelaine.model.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TaskOrderedByLabelFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.nav_by_label:
                    selectedFragment = new TaskOrderedByLabelFragment();
                    break;
                case R.id.nav_by_date:
                    selectedFragment = new TaskOrderedByDueDateFragment();
                    break;
                case R.id.nav_by_title:
                    selectedFragment = new TaskOrderedByTitleFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

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
