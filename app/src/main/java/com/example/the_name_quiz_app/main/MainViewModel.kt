package com.example.the_name_quiz_app.main

import android.content.Context
import androidx.lifecycle.*
import com.example.the_name_quiz_app.R
import kotlinx.coroutines.flow.*

class MainViewModel(
    private val context: Context
) : ViewModel() {
    val currentFragment = MutableStateFlow(R.id.splash_fragment)

    val topLevelDestinations = setOf(
        R.id.people_fragment,
        R.id.game_fragment
    )

    val appbarVisible: StateFlow<Boolean> = currentFragment.map { id ->
        listOf(
            R.id.splash_fragment
        ).contains(id).not()
    }.asState(false)

    val toolbarTitle: StateFlow<CharSequence> = currentFragment.map { id ->
        when (id) {
            R.id.people_fragment -> "Database"
            R.id.game_fragment -> "quiz"
            else -> "Back"
        }
    }.asState("")

    val bottomNavVisible: StateFlow<Boolean> = currentFragment.map { id ->
        listOf(
            R.id.people_fragment,
            R.id.game_fragment,
        ).contains(id)
    }.asState(false)

    private fun <T> Flow<T>.asState(initialValue: T): StateFlow<T> {
        return this.stateIn(viewModelScope, SharingStarted.Eagerly, initialValue)
    }
}
