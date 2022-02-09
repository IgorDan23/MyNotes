package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState==null){
            NameNotesFragment nameNotesFragment = NameNotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                                       .replace(R.id.list,nameNotesFragment)
                                       .commit();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Notes one = new Notes("Понедельник", "23.10.2021","Погулять с собакой");
                NotesFragment notesFragment=NotesFragment.newInstance(one);
                getSupportFragmentManager().beginTransaction()
                                           .replace(R.id.notes,notesFragment)
                                           .commit();


            }
        }

    }
}