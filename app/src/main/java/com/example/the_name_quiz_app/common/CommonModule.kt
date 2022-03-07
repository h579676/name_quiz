package com.example.the_name_quiz_app.common

import androidx.room.Room
import com.google.gson.Gson
import com.example.the_name_quiz_app.common.room.AppDatabase
import org.koin.dsl.module

val commonModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "the_name_quiz_app_database"
        ).fallbackToDestructiveMigration().build()
    }

    single { Gson() }

}
