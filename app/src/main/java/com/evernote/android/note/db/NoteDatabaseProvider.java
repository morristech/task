package com.evernote.android.note.db;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * @author rwondratschek
 */
public class NoteDatabaseProvider {

    private static NoteDatabase instance;

    public static NoteDatabase get(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, NoteDatabase.class, "note-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
