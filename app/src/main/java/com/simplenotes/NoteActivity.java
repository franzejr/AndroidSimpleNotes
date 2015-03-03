package com.simplenotes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.simplenotes.pojo.Note;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class NoteActivity extends ActionBarActivity {

    Note note;

    @InjectView(R.id.note_title)
    TextView noteTitle;

    @InjectView(R.id.note_note)
    TextView noteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        note = getIntent().getParcelableExtra("note");

        ButterKnife.inject(this);

        noteTitle.setText(note.getTitle());
        noteNote.setText(note.getNote());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
