package com.simplenotes.controllers;

import android.support.v4.app.Fragment;

import com.simplenotes.NoteFragment;
import com.simplenotes.pojo.Note;

public class NoteController {
    public Fragment calledFragment;

    public NoteController(Fragment fragment){
        this.calledFragment = fragment;
    }
    public void create(Note note){
        createNote(note);
    }

    private void createNote(Note note) {
        if(calledFragment instanceof NoteFragment){
            ((NoteFragment)calledFragment).onUpdateAdater(note);
        }
    }
}
