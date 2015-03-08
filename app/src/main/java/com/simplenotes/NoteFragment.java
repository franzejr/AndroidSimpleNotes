package com.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.simplenotes.adapters.NotesAdapter;
import com.simplenotes.controllers.NoteController;
import com.simplenotes.pojo.Note;

import java.util.ArrayList;


public class NoteFragment extends ListFragment implements OnDismissCallback {

    private static final int INITIAL_DELAY_MILLIS = 300;
    private ArrayList<Note> arrayList;
    public NoteController noteController;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        noteController = new NoteController(this);
        arrayList = new ArrayList<Note>();

        noteController.getAllNotes();

        NotesAdapter notesAdapter = new NotesAdapter(getActivity(), arrayList);
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(notesAdapter);
        swingBottomInAnimationAdapter.setAbsListView(getListView());
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);
        setListAdapter( swingBottomInAnimationAdapter);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Note note = arrayList.get(position);
        Intent intent = new Intent(getActivity(), NoteActivity.class);
        intent.putExtra("note",note);
        startActivity(intent);
    }

    public void onUpdateAdater(Note note){
        arrayList.add(note);
        setListAdapter(new NotesAdapter(getActivity(),arrayList));
    }


    @Override
    public void onDismiss(@NonNull ViewGroup viewGroup, @NonNull int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
            arrayList.remove(position);
        }
    }

    public void onUpdateAdater(ArrayList<Note> notes) {
        arrayList = notes;
    }
}
