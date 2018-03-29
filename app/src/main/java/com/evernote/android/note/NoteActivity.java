package com.evernote.android.note;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.evernote.android.note.db.Note;
import com.evernote.android.note.db.NoteDao;
import com.evernote.android.note.db.NoteDatabaseProvider;
import com.thedeanda.lorem.LoremIpsum;

public class NoteActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE_ID = "EXTRA_NOTE_ID";
    public static final String EXTRA_MODE = "EXTRA_MODE";

    public static final int MODE_VIEW = 1;
    public static final int MODE_CREATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        TextView contentView = findViewById(R.id.content);

        final NoteDao noteDao = NoteDatabaseProvider.get(this).noteDao();

        int mode = getIntent().getIntExtra(EXTRA_MODE, MODE_VIEW);
        switch (mode) {
            case MODE_VIEW:
                int id = getIntent().getIntExtra(EXTRA_NOTE_ID, -1);
                final Note note = noteDao.getNote(id);
                setTitle(note.getTitle());
                contentView.setText(note.getContent());

                fab.setImageResource(R.drawable.ic_delete_black_24dp);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noteDao.deleteNote(note);
                        finish();
                    }
                });
                break;

            case MODE_CREATE:
                final String title = new LoremIpsum().getTitle(2);
                final String content = new LoremIpsum().getWords(1000);

                setTitle(title);
                contentView.setText(content);

                fab.setImageResource(R.drawable.ic_save_black_24dp);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noteDao.addNote(new Note(title, content));
                        finish();
                    }
                });
                break;

            default:
                finish();
                break;
        }
    }
}
