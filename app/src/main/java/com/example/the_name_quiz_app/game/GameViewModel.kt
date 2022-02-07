package com.example.the_name_quiz_app.game

import androidx.lifecycle.viewModelScope
import com.example.the_name_quiz_app.common.base.BaseViewModel
import com.example.the_name_quiz_app.people.data.PeopleDao
import com.example.the_name_quiz_app.people.domain.PeopleCardComponent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(
    peopleDao: PeopleDao
) : BaseViewModel<GameNavigation>() {

    private val emptyPerson = PeopleCardComponent("", "", 0)
    private val random = Random(1)
    private var correctButton = random.nextInt(3)

    private val _people = peopleDao.getPeople()
        .map { person -> person.map { PeopleCardComponent(it.id, it.name, it.image) } }
    val people = _people.asState(listOf())

    private val _person = MutableStateFlow(emptyPerson)
    val person = _person.asState(emptyPerson)

    private val _score = MutableStateFlow(0)
    val score = _score.asState(0)

    private val _button1 = MutableStateFlow(emptyPerson)
    val button1 = _button1.asState(emptyPerson)

    private val _button2 = MutableStateFlow(emptyPerson)
    val button2 = _button2.asState(emptyPerson)

    private val _button3 = MutableStateFlow(emptyPerson)
    val button3 = _button3.asState(emptyPerson)


    //TODO: something is broken in this logic
    fun nextPerson(button: Int) {
        viewModelScope.launch {
            correctButton = random.nextInt(3)
            _score.value = if (correctButton == button) _score.value + 1 else _score.value - 1
            _person.value = people.value.filter { it != person.value }.randomOrNull() ?: emptyPerson
            setButtonValues()
        }
    }

    private fun setButtonValues(){
        val fakePerson1 = people.value.filter { it != person.value }.randomOrNull() ?: emptyPerson
        val fakePerson2 = people.value.filter { it != person.value }.randomOrNull() ?: emptyPerson

        when (correctButton) {
            0 -> {
                _button1.value = person.value
                _button2.value = fakePerson1
                _button3.value = fakePerson2

            }
            1 -> {
                _button1.value = fakePerson1
                _button2.value = person.value
                _button3.value = fakePerson2
            }
            2 -> {
                _button1.value = fakePerson2
                _button2.value = fakePerson1
                _button3.value = person.value
            }
        }
    }

    init {
        viewModelScope.launch {
            _person.value = _people.first().randomOrNull() ?: emptyPerson
            setButtonValues()
        }
    }
}
