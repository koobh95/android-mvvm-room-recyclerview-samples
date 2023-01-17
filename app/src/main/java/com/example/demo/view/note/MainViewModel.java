package com.example.demo.view.note;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.demo.data.entity.Note;
import com.example.demo.data.repository.NoteRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<String> noteContents;
    private MutableLiveData<List<Note>> liveNoteList;
    private NoteRepository noteRepository;

    private static final String TAG = "MainViewModel";

    public MainViewModel(@NotNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        noteContents = new MutableLiveData<>("");
        liveNoteList = noteRepository.getMemoList();
        noteRepository.getAllNotes();
    }

    public void onClick(View view) {
        if(Objects.equals(noteContents.getValue(), ""))
            return;
        noteRepository.addNote(noteContents.getValue());
    }

    public MutableLiveData<String> getNoteContents() { return noteContents; }
    public MutableLiveData<List<Note>> getNoteList() { return liveNoteList; }
}
