package com.simplenotes;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.simplenotes.pojo.Note;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    private NoteFragment fragment;

    @InjectView(R.id.add_note)
    FloatingActionButton addNote;

    @OnClick(R.id.add_note) void addNote(){

        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.create_new_note)
                .customView(R.layout.dialog_create_note, true)
                .positiveText(R.string.create)
                .negativeText(android.R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        TextView noteTextView = (TextView)dialog.findViewById(R.id.note);
                        Note note = new Note(noteTextView.getText().toString());
                        fragment.noteController.create(note);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                    }
                }).build();

        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff303030")));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Simple Notes</font>"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        ButterKnife.inject(this);

        fragment = new NoteFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_notes, fragment)
                .commit();
    }
}
