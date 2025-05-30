package com.hari.notes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hari.notes.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDAO(): NoteDAO
}