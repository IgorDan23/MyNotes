package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            NameNotesFragment nameNotesFragment = NameNotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list, nameNotesFragment)
                    .commit();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Notes one = new Notes(0);
                NotesFragment notesFragment = NotesFragment.newInstance(one);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.notes, notesFragment)
                        .commit();


            }
        }
        Toolbar toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_exit):
                new MyDialogFragment().show(getSupportFragmentManager(), "1");
                return true;

            case (R.id.action_about):
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.list, new AboutFragment()).addToBackStack("").commit();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment backStackFragment = (Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.list);
        if (backStackFragment != null && backStackFragment instanceof NotesFragment) {
            onBackPressed();
        }
    }
}