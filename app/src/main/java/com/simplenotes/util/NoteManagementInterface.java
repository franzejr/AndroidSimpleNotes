package com.simplenotes.util;

import com.simplenotes.pojo.Note;

import java.util.List;

public interface NoteManagementInterface {

    public void addNote(Note note);
    public Note getNote(Note note);
    public String getTotalNotes();
    public List<Note> getAllNotes();
    public Note updateNote(Note note);
    public void deleteNote(Note note);
}
