package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;

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