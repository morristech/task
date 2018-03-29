package com.evernote.android.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evernote.android.note.db.Note;
import com.evernote.android.note.db.NoteDao;
import com.evernote.android.note.db.NoteDatabaseProvider;
import com.thedeanda.lorem.LoremIpsum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private NoteDao mNoteDao;
    private NoteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNoteDao = NoteDatabaseProvider.get(this).noteDao();

        mAdapter = new NoteAdapter(mNoteDao.getAllNotes());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class)
                        .putExtra(NoteActivity.EXTRA_MODE, NoteActivity.MODE_CREATE);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.updateNotes(mNoteDao.getAllNotes());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quick_note:
                LoremIpsum ipsum = new LoremIpsum();
                Note note = new Note(ipsum.getTitle(3), ipsum.getWords(1000));
                NoteDatabaseProvider.get(this).noteDao().addNote(note);
                mAdapter.updateNotes(mNoteDao.getAllNotes());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

        private List<Note> mNotes;

        NoteAdapter(List<Note> notes) {
            setNotes(notes);
        }

        public void updateNotes(List<Note> notes) {
            setNotes(notes);
            notifyDataSetChanged();
        }

        private void setNotes(List<Note> notes) {
            mNotes = new ArrayList<>(notes);
            Collections.sort(mNotes, NOTE_COMPARATOR);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        @NonNull
        @Override
        public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(NoteListActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new NoteViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
            final Note note = mNotes.get(position);
            holder.mTitleView.setText(note.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), NoteActivity.class)
                            .putExtra(NoteActivity.EXTRA_MODE, NoteActivity.MODE_VIEW)
                            .putExtra(NoteActivity.EXTRA_NOTE_ID, note.id);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTitleView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            mTitleView = itemView.findViewById(android.R.id.text1);
        }
    }

    private static final Comparator<Note> NOTE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note n1, Note n2) {
            return n1.getTitle().compareTo(n2.getTitle());
        }
    };
}
