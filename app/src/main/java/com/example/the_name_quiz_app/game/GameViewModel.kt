package com.example.the_name_quiz_app.game

import androidx.lifecycle.viewModelScope
import com.example.the_name_quiz_app.R
import com.example.the_name_quiz_app.common.base.BaseViewModel
import com.example.the_name_quiz_app.people.data.PeopleDao
import com.example.the_name_quiz_app.people.domain.PeopleCardComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(
    peopleDao: PeopleDao
) : BaseViewModel<GameNavigation>() {

    val emptyPerson = PeopleCardComponent("", "", R.drawable.quiz)

    private val random = Random(1)
    var correctButton: Int? = null

    private val _people = peopleDao.getPeople()
        .map { person -> person.map { PeopleCardComponent(it.id, it.name, it.image) } }
    val people = _people.asState(listOf())

    private val _person = MutableStateFlow(emptyPerson)
    val person = _person.asState(emptyPerson)

    private val _score = MutableStateFlow(0)
    val score = _score.asState(0)

    fun nextPerson(button: Int) {
        viewModelScope.launch {
            _score.value = when (correctButton) {
                button -> _score.value + 1
                null -> 0
                else -> _score.value - 1
            }
            correctButton = random.nextInt(3)
            _person.value = people.filter { it != person }.first().randomOrNull() ?: emptyPerson
        }
    }
}
