package com.example.the_name_quiz_app.people

import android.content.Context
import android.view.View
import androidx.lifecycle.viewModelScope
import com.example.the_name_quiz_app.BR
import com.example.the_name_quiz_app.R
import com.example.the_name_quiz_app.common.base.BaseViewModel
import com.example.the_name_quiz_app.people.data.PeopleDao
import com.example.the_name_quiz_app.people.domain.PeopleCardComponent
import com.example.the_name_quiz_app.people.domain.Person
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.*

class PeopleViewModel(
    private val context: Context,
    private val peopleDao: PeopleDao,
) : BaseViewModel<PeopleNavigation>() {

    val userInput = MutableStateFlow("")
    val hideKeyboard = { navigate(HideKeyboard) }

    val currentTab = MutableStateFlow(PeopleTabType.ALPHABETICAL.ordinal)
    val onTabSelected: (TabLayout.Tab) -> Unit = { tab ->
        PeopleTabType.values().firstOrNull { it.ordinal == tab.position }?.let { mode ->
            currentTab.value = mode.ordinal
        }
    }

    private val imageList = listOf(R.drawable.animal0, R.drawable.animal2, R.drawable.animal3)

    private val _people = peopleDao.getPeople()
        .map { person -> person.map { PeopleCardComponent(it.id, it.name, it.image, hideKeyboard) } }

    val items = combine(_people, currentTab) { people, tab ->
        when (tab) {
            PeopleTabType.ALPHABETICAL.ordinal -> people.sortedBy { it.title }
            else -> people.sortedByDescending { it.title }
        }
    }.asState(listOf())

    val itemBinding = ItemBinding.of<PeopleCardComponent> { itemBinding, _, item ->
        itemBinding.set(BR.component, R.layout.component_people_item).bindExtra(
            BR.onClick,
            View.OnClickListener { removePlayer(item) }
        )
    }


    private fun removePlayer(item: PeopleCardComponent) {
        viewModelScope.launch(Dispatchers.Main) {
            peopleDao.deletePeople(Person(item.id, item.title, item.image))
        }
    }

    fun addPlayer(): Boolean {
        viewModelScope.launch {
            peopleDao.insertPeople(
                Person(
                    id = UUID.randomUUID().toString(),
                    name = if (userInput.value.isNotBlank()) userInput.value else context.getString(R.string.people_hint, items.value.size.inc()),
                    image = imageList.random()
                )
            )
        }
        return true
    }
}

