package com.evernote.android.note.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * @author rwondratschek
 */
@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE note.id == :id")
    Note getNote(int id);

    @Insert
    void addNote(Note note);

    @Delete
    void deleteNote(Note note);
}
