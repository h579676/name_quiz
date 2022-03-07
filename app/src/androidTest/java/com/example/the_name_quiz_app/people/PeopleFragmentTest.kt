package com.example.the_name_quiz_app.people

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.the_name_quiz_app.R
import com.example.the_name_quiz_app.common.room.AppDatabase
import com.example.the_name_quiz_app.people.data.PeopleDao
import com.example.the_name_quiz_app.people.domain.Person
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PeopleFragmentTest {
    private lateinit var peopleDao: PeopleDao
    private lateinit var db: AppDatabase

    private val imageList = listOf(R.drawable.animal0, R.drawable.animal2, R.drawable.animal3)

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor())
            .build()
        peopleDao = db.getPeopleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    private fun getPeopleList(): List<Person> {
        val mutableList = mutableListOf<Person>()

        for (i in 0..9) {
            mutableList.add(
                Person(
                    id = i.toString(),
                    name = i.toString(),
                    image = imageList.random()
                )
            )
        }
        return mutableList
    }

    @Test
    @Throws(Exception::class)
    fun testInsertAndDelete() = testScope.runBlockingTest {
        val peopleList = getPeopleList()
        peopleDao.insertPeople(peopleList)

        val personList = async {
            listOf(
                peopleDao.getPersonById("0").firstOrNull(),
                peopleDao.getPersonById("1").firstOrNull(),
                peopleDao.getPersonById("2").firstOrNull()
            )
        }
        assertThat(personList.await(), equalTo(peopleList.subList(0, 3)))
        assertThat(peopleDao.getPeople().first().size, equalTo(10))

        peopleDao.deletePeople(peopleList[0])

        assertThat(peopleDao.getPeople().first().size, equalTo(9))
    }
}