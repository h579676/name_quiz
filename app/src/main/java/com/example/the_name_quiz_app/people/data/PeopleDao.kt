package com.example.the_name_quiz_app.people.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.the_name_quiz_app.people.domain.Person

@Dao
interface PeopleDao {
    @Query("SELECT * FROM Person")
    fun getPeople(): Flow<List<Person>>

    @Query("SELECT * FROM Person WHERE id = :id")
    fun getPersonById(id: String): Flow<Person?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(vararg person: Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(person: List<Person>)

    @Delete
    suspend fun deletePeople(vararg person: Person)

    @Delete
    suspend fun deletePeople(person: List<Person>)
}
