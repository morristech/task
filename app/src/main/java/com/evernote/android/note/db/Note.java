package com.evernote.android.note.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author rwondratschek
 */
@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String title;
    private String content;

    public Note() {
        this(null, null);
    }

    @Ignore
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
