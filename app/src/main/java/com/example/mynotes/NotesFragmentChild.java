package com.example.mynotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

public class NotesFragmentChild extends Fragment {
    private Notes notes;
    public static final String KEY = "key";

    public static NotesFragmentChild newInstance(Notes notes) {
        NotesFragmentChild fragment = new NotesFragmentChild();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY, notes);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notes = getArguments().getParcelable(KEY);
        String[] name = getResources().getStringArray(R.array.NotesName);
        String[] text = getResources().getStringArray(R.array.NotesText);
        String[] data = getResources().getStringArray(R.array.NotesData);
        TextView textViewOne = new TextView(getContext());
        TextView textViewTwo = new TextView(getContext());
        TextView textViewThree = new TextView(getContext());
        textViewOne.setTextSize(40f);
        textViewTwo.setTextSize(28f);
        textViewThree.setTextSize(15f);
        textViewOne.setText(name[notes.getNumber()]);
        textViewTwo.setText(text[notes.getNumber()]);
        textViewThree.setText(String.format("Дата создания:%s", data[notes.getNumber()]));
        ((LinearLayout) view).addView(textViewOne);
        ((LinearLayout) view).addView(textViewTwo);
        ((LinearLayout) view).addView(textViewThree);
        view.findViewById(R.id.back_Child).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });


    }
}