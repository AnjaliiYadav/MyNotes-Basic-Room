package com.yadav_anjalii.my_notes.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yadav_anjalii.my_notes.dao.NoteDao;
import com.yadav_anjalii.my_notes.model.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
