package com.example.mynotes;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
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


        textViewTwo.setOnClickListener(view1 -> {
            showPush(text[notes.getNumber()]);
        });

        textViewOne.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), view);
                requireActivity().getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case (R.id.action_clear):
                                textViewOne.setAlpha(0);
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });


    }

    private void showPush(String text) {
        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("1", "CHANNEL1", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Ванжные заметки");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        Notification notification = new NotificationCompat.Builder(requireContext(), "1")
                .setContentTitle("Важная заметка")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_baseline_adb_24)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        notificationManager.notify(1, notification);
    }
}