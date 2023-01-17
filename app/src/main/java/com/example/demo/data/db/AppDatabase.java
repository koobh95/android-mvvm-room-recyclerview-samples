package com.example.demo.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.demo.data.dao.NoteDAO;
import com.example.demo.data.entity.Note;
import com.example.demo.util.RoomTypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = { Note.class }, version = 1, exportSchema = false)
@TypeConverters({ RoomTypeConverters.class })
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(4);

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "app_db").build();
        }
        return INSTANCE;
    }

    public abstract NoteDAO noteDao();
}
