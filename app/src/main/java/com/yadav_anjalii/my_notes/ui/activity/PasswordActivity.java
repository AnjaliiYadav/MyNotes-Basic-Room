package com.yadav_anjalii.my_notes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yadav_anjalii.my_notes.R;
import com.yadav_anjalii.my_notes.databinding.ActivityPasswordBinding;

public class PasswordActivity extends AppCompatActivity {

    ActivityPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponent();
    }

    private void initComponent() {

    }
}