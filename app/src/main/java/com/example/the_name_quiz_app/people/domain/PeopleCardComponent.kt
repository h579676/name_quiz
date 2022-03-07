package com.example.the_name_quiz_app.people.domain

import androidx.annotation.DrawableRes
import com.example.the_name_quiz_app.common.base.BaseComponent

data class PeopleCardComponent(
    val id: String,
    val title: String,
    @DrawableRes val image: Int,
    val onClick: (() -> Unit)? = null
) : BaseComponent
