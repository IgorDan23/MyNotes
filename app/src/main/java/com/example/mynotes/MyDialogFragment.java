package com.example.mynotes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireContext())
                .setTitle("Завершаем работу приложения")
                .setMessage("Вы уверены?")
                .setPositiveButton("Да", (dialog, which) -> {
                    showToast("Заметки закрыты");
                    requireActivity().finish();
                })
                .setNegativeButton("Нет", (dialog, which) -> {
                    dialog.dismiss();
                })
                .setNeutralButton("Поставить нам оценку", (dialog, which) -> {
                })
                .show();
    }

    void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
    }
}
