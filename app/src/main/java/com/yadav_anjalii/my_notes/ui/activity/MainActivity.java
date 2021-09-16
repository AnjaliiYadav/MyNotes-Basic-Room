package com.yadav_anjalii.my_notes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yadav_anjalii.my_notes.R;
import com.yadav_anjalii.my_notes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponent();
    }

    private void initComponent() {
        binding.floatButton.setOnClickListener(view -> addNewNote());
    }

    private void addNewNote() {
        startActivity(new Intent(this, NotesActivity.class));
    }
}