package com.yadav_anjalii.my_notes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yadav_anjalii.my_notes.databinding.ActivityMainBinding;
import com.yadav_anjalii.my_notes.model.Note;
import com.yadav_anjalii.my_notes.ui.adapter.NoteAdapter;
import com.yadav_anjalii.my_notes.util.Constants;
import com.yadav_anjalii.my_notes.util.ScreenRedirect;
import com.yadav_anjalii.my_notes.util.Utils;
import com.yadav_anjalii.my_notes.viewmodel.NoteViewModel;

public class MainActivity extends AppCompatActivity implements NoteAdapter.ItemClickListener, Constants {

    private ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    private NoteAdapter adapter;

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
        ScreenRedirect.navigate(this, NotesActivity.class);
    }

    private void observeNotes() {
        noteViewModel.getNoteAll().observe(this, notes -> {
            adapter = new NoteAdapter(notes);
            adapter.setItemClickListener(this);
            binding.displayAllNotes.setAdapter(adapter);
        });
    }

    @Override
    public void itemOnClick(View view, Note note) {
        if (note.isEncrypt()) {
            redirectToPassword(note);
        } else {
            redirectToAddNote(note);
        }
    }

    private void redirectToPassword(Note note) {
        ScreenRedirect.navigate(this, PasswordActivity.class, note);
    }

    private void redirectToAddNote(Note note) {
        ScreenRedirect.navigate(this, NotesActivity.class, note);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == ACTIVITY_REQUEST_CODE) {
            if (data.hasExtra(INTENT_TASK)) {
                if (data.hasExtra(INTENT_DELETE)) {
                    noteViewModel.deleteNote((Note) data.getSerializableExtra(INTENT_TASK));
                } else {
                    noteViewModel.updateNote((Note) data.getSerializableExtra(INTENT_TASK));
                }
            } else {
                String title = data.getStringExtra(TITLE);
                String desc = data.getStringExtra(DESC);
                String password = data.getStringExtra(PASSWORD);
                if(password != null){
                   password= Utils.generateHash(password);
                }
                boolean encrypt = data.getBooleanExtra(ENCRYPT, false);
                Note note = new Note(title, desc, encrypt, password);
                noteViewModel.addNote(note);
            }
        }
    }

}