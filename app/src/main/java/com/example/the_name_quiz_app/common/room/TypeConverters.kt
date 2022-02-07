package com.example.the_name_quiz_app.common.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.the_name_quiz_app.people.domain.Person
import org.koin.java.KoinJavaComponent.inject

class TypeConverters {
    private val _gson: Gson by inject(Gson::class.java)

    @TypeConverter
    fun personListToJson(stringList: List<Person>): String =
        try {
            val objType = object : TypeToken<List<Person>>() {}.type
            _gson.toJson(stringList, objType)
        } catch (e: Exception) {
            ""
        }

    @TypeConverter
    fun personListFromJson(json: String): List<Person> {
        val objType = object : TypeToken<List<Person>>() {}.type
        return _gson.fromJson(json, objType)
    }

    @TypeConverter
    fun personDataToJson(departmentData: Person): String =
        _gson.toJson(departmentData)

    @TypeConverter
    fun personDataFromJson(json: String): Person =
        _gson.fromJson(json, Person::class.java)
}
