package com.example.the_name_quiz_app.common.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.the_name_quiz_app.people.data.PeopleDao
import com.example.the_name_quiz_app.people.domain.Person

@Database(entities = [Person::class], version = 4, exportSchema = false)
@TypeConverters(com.example.the_name_quiz_app.common.room.TypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPeopleDao(): PeopleDao
}
