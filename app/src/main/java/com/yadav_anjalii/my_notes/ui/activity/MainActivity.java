package com.yadav_anjalii.my_notes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yadav_anjalii.my_notes.R;
import com.yadav_anjalii.my_notes.databinding.ActivityMainBinding;
import com.yadav_anjalii.my_notes.model.Note;
import com.yadav_anjalii.my_notes.ui.adapter.NoteAdapter;
import com.yadav_anjalii.my_notes.viewmodel.NoteViewModel;

import static com.yadav_anjalii.my_notes.util.Constants.INTENT_TASK;

public class MainActivity extends AppCompatActivity implements NoteAdapter.ItemClickListener {

    private ActivityMainBinding binding;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initComponent();
        initViewModel();
    }

    private void initComponent() {
        binding.floatButton.setOnClickListener(view -> addNewNote());
    }

    private void initViewModel() {
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        observeNotes();
    }

    private void addNewNote() {
        startActivity(new Intent(this, NotesActivity.class));
    }

    private void observeNotes() {
        noteViewModel.getNoteAll().observe(this,notes -> {
            binding.displayAllNotes.setAdapter(new NoteAdapter(notes));
        });
    }


    @Override
    public void itemOnClick(View view, Note note) {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        intent.putExtra(INTENT_TASK, note);
        setResult(Activity.RESULT_OK,intent);
    }
}