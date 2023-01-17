package com.example.demo.data.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int noteNo;
    @NotNull
    private String contents;
    @NotNull
    private Date writeDate;

    @Ignore
    public Note(@NotNull String contents) {
        this.contents = contents;
        writeDate = new Date(System.currentTimeMillis());
    }

    public Note(int noteNo, @NotNull String contents, @NotNull Date writeDate) {
        this.noteNo = noteNo;
        this.contents = contents;
        this.writeDate = writeDate;
    }

    public int getNoteNo() { return noteNo; }
    @NotNull
    public String getContents() { return contents; }
    @NotNull
    public Date getWriteDate() { return writeDate; }

    public void setNoteNo(int noteNo) { this.noteNo = noteNo; }
    public void setContents(@NotNull String contents) { this.contents = contents; }
    public void setWriteDate(@NotNull Date writeDate) { this.writeDate = writeDate; }
}
