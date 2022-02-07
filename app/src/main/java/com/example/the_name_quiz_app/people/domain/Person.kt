package com.example.the_name_quiz_app.people.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: Int
)
