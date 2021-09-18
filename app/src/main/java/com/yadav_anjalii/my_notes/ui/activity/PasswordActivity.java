package com.yadav_anjalii.my_notes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yadav_anjalii.my_notes.R;
import com.yadav_anjalii.my_notes.databinding.ActivityPasswordBinding;
import com.yadav_anjalii.my_notes.model.Note;
import com.yadav_anjalii.my_notes.util.Constants;
import com.yadav_anjalii.my_notes.util.ScreenRedirect;
import com.yadav_anjalii.my_notes.util.Utils;

public class PasswordActivity extends AppCompatActivity implements Constants {

    ActivityPasswordBinding binding;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponent();
    }

    private void initComponent() {
        note = (Note) getIntent().getSerializableExtra(INTENT_TASK);
        binding.passwordVerification.hasFocus();
        binding.passwordToolbar.toolbarDone.setOnClickListener(view -> verifyPassword());
        binding.passwordToolbar.toolbarImage.setOnClickListener(view -> goBackMain());
    }

    private void verifyPassword() {
        String temp = binding.passwordVerification.getText().toString();
        if (note.getPassword().trim().equals(Utils.generateHash(temp))) {
            ScreenRedirect.navigate(this, NotesActivity.class, note);
            finish();
        } else {
            Utils.displayMessageToast(this, "Password Mis-match");
            goBackMain();
        }
    }

    private void goBackMain() {
        ScreenRedirect.navigate(this, MainActivity.class);
        finish();
    }

}