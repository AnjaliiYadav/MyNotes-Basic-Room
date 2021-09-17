package com.yadav_anjalii.my_notes.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yadav_anjalii.my_notes.R;
import com.yadav_anjalii.my_notes.databinding.ActivityNotesBinding;
import com.yadav_anjalii.my_notes.model.Note;
import com.yadav_anjalii.my_notes.util.Constants;

import java.util.Objects;


public class NotesActivity extends AppCompatActivity implements Constants {
    ActivityNotesBinding binding;
    private Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponents();
    }

    private void initComponents() {
        note = (Note) getIntent().getSerializableExtra(INTENT_TASK);
        getNoteIfExists(note);
        binding.checkPasswordNeeded.setOnCheckedChangeListener((compoundButton, isChecked) ->
                binding.settingPassword.setVisibility(isChecked? View.VISIBLE : View.GONE));
        binding.noteItemDetails.toolbarDone.setOnClickListener(view -> saveNote());
        binding.noteItemDetails.toolbarImage.setOnClickListener(view -> {
            if ((int)view.getTag() == R.drawable.ic_baseline_delete_24){
                deleteNote();
            }else {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }

    private void saveNote() {
        String title = Objects.requireNonNull(binding.editTitle.getText()).toString();
        String description = Objects.requireNonNull(binding.editDescription.getText()).toString();
        boolean isEncrypt = binding.checkPasswordNeeded.isChecked();
        String password = Objects.requireNonNull(binding.password.getText()).toString();
        Intent intent = getIntent();
        if (note != null){
            note.setDescription(description);
            note.setTitle(title);
            note.setEncrypt(isEncrypt);
            note.setPassword(password);
            intent.putExtra(INTENT_TASK, note);
        }
        else{
            intent.putExtra(TITLE, title);
            intent.putExtra(DESC, description);
            intent.putExtra(ENCRYPT, isEncrypt);
            intent.putExtra(PASSWORD, password);
        }
        setResult(Activity.RESULT_OK, intent);
        finish();

    }

    private void deleteNote(){
        Intent intent = getIntent();
        intent.putExtra(INTENT_DELETE, true);
        intent.putExtra(INTENT_TASK, note );
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void getNoteIfExists(Note temp){
        if (temp == null){
            changeToolbarTitle(getString(R.string.add_note));
            binding.noteItemDetails.toolbarImage.setImageResource(R.drawable.ic_baseline_close_24);
            binding.noteItemDetails.toolbarImage.setTag(R.drawable.ic_baseline_close_24);
        }else{
            changeToolbarTitle(getString(R.string.edit_note));
            binding.noteItemDetails.toolbarImage.setImageResource(R.drawable.ic_baseline_delete_24);
            binding.noteItemDetails.toolbarImage.setTag(R.drawable.ic_baseline_delete_24);
        }
    }

    private void changeToolbarTitle(String title) {
        binding.noteItemDetails.toolbarHeader.setText(title);
    }
}