package com.yadav_anjalii.my_notes.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.yadav_anjalii.my_notes.dao.NoteDao;
import com.yadav_anjalii.my_notes.model.Note;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static volatile NoteDatabase INSTANCE;
    private static final String DB ="db_notes";

    public static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (NoteDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,NoteDatabase.class, DB)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
