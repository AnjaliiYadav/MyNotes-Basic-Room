package com.yadav_anjalii.my_notes.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.yadav_anjalii.my_notes.db.NoteDatabase;
import com.yadav_anjalii.my_notes.model.Note;

import java.util.List;

public class NoteRepository {

    private NoteDatabase noteDatabase;
    public NoteRepository(Context context) {
        noteDatabase = NoteDatabase.getDatabase(context);
    }

    public void insertNote(Note note){
        noteDatabase.noteDao().insertNote(note);
    }

    public void deleteNote(Note note){
        noteDatabase.noteDao().deleteNote(note);
    }

    public void updateNote(Note note){
        noteDatabase.noteDao().updateNote(note);
    }

    public LiveData<List<Note>> getALlNotes(){
       return noteDatabase.noteDao().getAllNotes();
    }

}
