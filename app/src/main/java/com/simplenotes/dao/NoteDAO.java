package com.simplenotes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.simplenotes.pojo.Note;
import com.simplenotes.util.NoteManagementInterface;
import com.simplenotes.sqlite.NoteSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO implements NoteManagementInterface {

    private SQLiteDatabase database;
    private NoteSQLiteHelper dbHelper;

    private static final String TAG = NoteDAO.class.getSimpleName();

    public NoteDAO(Context context) {
        dbHelper = new NoteSQLiteHelper(context);
    }

    public void openReadableDatabase() {
        database = dbHelper.getReadableDatabase();
    }

    public void openWritableDatabase() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeDatabase() {
        database.close();
    }

    @Override
    public void addNote(Note note) {
        openWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NoteSQLiteHelper.TITLE, note.getTitle());
        values.put(NoteSQLiteHelper.NOTE, note.getNote());

        database.insert(NoteSQLiteHelper.TABLE, null, values);

        closeDatabase();
    }

    @Override
    public Note getNote(Note note) {
        openReadableDatabase();

        Cursor cursor = database.query(NoteSQLiteHelper.TABLE,
                NoteSQLiteHelper.COLUMNS,
                NoteSQLiteHelper.ID + " = ?",
                new String[] { note.getId() },
                null,
                null,
                null,
                null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        note.setId(cursor.getString(0));
        note.setTitle(cursor.getString(1));
        note.setNote(cursor.getString(2));

        cursor.close();
        closeDatabase();

        return note;
    }

    @Override
    public String getTotalNotes() {
        return null;
    }

    @Override
    public List<Note> getAllNotes() {

        ArrayList<Note> notes = new ArrayList<Note>();
        String query = "SELECT " + NoteSQLiteHelper.ID + ", "
                + NoteSQLiteHelper.TITLE + ", "
                + NoteSQLiteHelper.NOTE +" FROM "
                + NoteSQLiteHelper.TABLE + " ORDER BY "
                + NoteSQLiteHelper.ID + " DESC";

        openReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        Note note = null;
        if (cursor.moveToFirst()) {
            do {
                note = new Note();
                note.setId(cursor.getString(0));
                note.setTitle(cursor.getString(1));
                note.setNote(cursor.getString(2));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        cursor.close();
        closeDatabase();

        return notes;
    }

    @Override
    public Note updateNote(Note note) {
        openWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NoteSQLiteHelper.ID, note.getId());
        values.put(NoteSQLiteHelper.TITLE, note.getTitle());
        values.put(NoteSQLiteHelper.NOTE, note.getNote());

        database.update(NoteSQLiteHelper.TABLE,
                values,
                NoteSQLiteHelper.ID + " = ?",
                new String[] { String.valueOf(note.getId()) });

        closeDatabase();

        return note;
    }

    @Override
    public void deleteNote(Note note) {
        openWritableDatabase();

        database.delete(NoteSQLiteHelper.TABLE,
                NoteSQLiteHelper.ID + " = ?",
                new String[] { note.getId() } );

        closeDatabase();
    }
}
