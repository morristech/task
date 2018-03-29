package com.evernote.android.db;

import android.arch.persistence.room.Room;

import com.evernote.android.note.db.Note;
import com.evernote.android.note.db.NoteDao;
import com.evernote.android.note.db.NoteDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author rwondratschek
 */
@RunWith(RobolectricTestRunner.class)
public class NoteDaoTest {

    private NoteDatabase mDatabase;
    private NoteDao mNoteDao;

    @Before
    public void createDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(RuntimeEnvironment.application, NoteDatabase.class).allowMainThreadQueries().build();
        mNoteDao = mDatabase.noteDao();
    }

    @After
    public void closeDb() {
        mDatabase.close();
    }

    @Test
    public void verifyCreate() {
        Note note = new Note("Title", "Description");
        mNoteDao.addNote(note);

        List<Note> notes = mNoteDao.getAllNotes();
        assertThat(notes).hasSize(1);
    }
}
