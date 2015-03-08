package com.simplenotes.controllers;

import android.support.v4.app.Fragment;

import com.simplenotes.NoteFragment;
import com.simplenotes.dao.NoteDAO;
import com.simplenotes.pojo.Note;
import com.simplenotes.sqlite.NoteSQLiteHelper;

import java.util.ArrayList;

public class NoteController {
    private final NoteDAO noteDAO;
    public Fragment calledFragment;

    public NoteController(Fragment fragment){
        this.calledFragment = fragment;
        noteDAO = new NoteDAO(calledFragment.getActivity());
    }
    public void create(Note note){
        createNote(note);
    }

    private void createNote(Note note) {

        NoteDAO noteDAO = new NoteDAO(calledFragment.getActivity());
        noteDAO.addNote(note);

        if(calledFragment instanceof NoteFragment){
            ((NoteFragment)calledFragment).onUpdateAdater(note);
        }
    }

    public void getAllNotes(){
        ArrayList<Note> notes = (ArrayList)noteDAO.getAllNotes();

        if(calledFragment instanceof NoteFragment){
            ((NoteFragment)calledFragment).onUpdateAdater(notes);
        }
    }
}
