package com.hari.notes.di

import androidx.room.Room
import com.hari.notes.data.db.AppDatabase
import com.hari.notes.data.db.NoteDAO
import com.hari.notes.data.repository.NoteRepoImpl
import com.hari.notes.domain.repository.NoteRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DIModules {
    val appModule = module {
        single<AppDatabase> {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "AppDatabase").build()
        }

        factory<NoteDAO> {
            get<AppDatabase>().getDAO()
        }

        factory<NoteRepo> {
            NoteRepoImpl(get())
        }
    }
}