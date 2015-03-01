package com.simplenotes.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.simplenotes.R;
import com.simplenotes.pojo.Note;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NotesAdapter extends ArrayAdapter<Note> {

    private final Context context;

    public NotesAdapter(Context context, ArrayList<Note> notesArrayList) {
        super(context, R.layout.listview_note_item, notesArrayList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Note note = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_note_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(rowView);

        viewHolder.note.setText(note.getNote());

        return rowView;
    }

    static class ViewHolder {
        @InjectView(R.id.note)
        TextView note;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);}
    }
}
