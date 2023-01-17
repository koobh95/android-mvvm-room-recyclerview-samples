package com.example.demo.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.demo.data.entity.Note;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("SELECT * FROM note ORDER BY noteNo")
    List<Note> selectAll();

    @Insert
    void insert(Note note);
}
