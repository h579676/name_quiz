package com.example.the_name_quiz_app.game.domain

import com.example.the_name_quiz_app.common.base.BaseComponent
import com.example.the_name_quiz_app.people.domain.PeopleCardComponent

data class GameComponent(
    val button1: PeopleCardComponent,
    val button2: PeopleCardComponent,
    val button3: PeopleCardComponent
) : BaseComponent

