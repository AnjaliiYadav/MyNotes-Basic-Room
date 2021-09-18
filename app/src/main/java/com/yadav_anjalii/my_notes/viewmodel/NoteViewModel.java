package com.yadav_anjalii.my_notes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yadav_anjalii.my_notes.model.Note;
import com.yadav_anjalii.my_notes.repository.NoteRepository;
import com.yadav_anjalii.my_notes.util.Utils;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
    }

    public void addNote(Note note){
        note.setModifiedAt(Utils.getCurrentDateTime());
        note.setCreatedAt(Utils.getCurrentDateTime());
        repository.insertNote(note);
    }

    public void updateNote(Note note){
        note.setModifiedAt(Utils.getCurrentDateTime());
        repository.updateNote(note);
    }

    public void deleteNote(Note note){
        repository.deleteNote(note);
    }

    public LiveData<List<Note>> getNoteAll(){
        return repository.getALlNotes();
    }



}
