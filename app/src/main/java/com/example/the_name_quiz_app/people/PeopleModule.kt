package com.example.the_name_quiz_app.people

import com.example.the_name_quiz_app.common.room.AppDatabase
import com.example.the_name_quiz_app.people.data.PeopleDao
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val peopleModule = module {
    viewModel<PeopleViewModel>()
    fun getPeopleDao(db: AppDatabase): PeopleDao = db.getPeopleDao()
    single { getPeopleDao(get()) }
}
