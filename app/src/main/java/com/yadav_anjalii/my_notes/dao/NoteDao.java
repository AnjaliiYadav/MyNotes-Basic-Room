package com.yadav_anjalii.my_notes.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.yadav_anjalii.my_notes.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * FROM NOTE")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM Note WHERE id =:taskId")
    LiveData<Note> getNote(int taskId);

}
