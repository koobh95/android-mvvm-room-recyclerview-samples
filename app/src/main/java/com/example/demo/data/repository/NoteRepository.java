package com.example.demo.data.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.demo.data.dao.NoteDAO;
import com.example.demo.data.db.AppDatabase;
import com.example.demo.data.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    private NoteDAO noteDao;
    private MutableLiveData<List<Note>> liveNoteList;
    private List<Note> noteList;

    public NoteRepository(Application application) {
        noteDao = AppDatabase.getInstance(application).noteDao();
        noteList = new ArrayList<>();
        liveNoteList = new MutableLiveData<>(noteList);
    }

    public void getAllNotes() {
        AppDatabase.dbWriteExecutor.execute(() -> {
            List<Note> list = noteDao.selectAll();
            noteList.addAll(list);
            liveNoteList.postValue(noteList);
        });
    }

    public void addNote (String contents) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            Note note = new Note(contents);
            noteDao.insert(note);
            noteList.add(note);
            liveNoteList.postValue(noteList);
        });
    }

    public MutableLiveData<List<Note>> getMemoList() {
        return liveNoteList;
    }
}
