package com.example.the_name_quiz_app.game

import android.os.Bundle
import android.view.View
import com.example.the_name_quiz_app.R
import com.example.the_name_quiz_app.common.base.BaseFragment
import com.example.the_name_quiz_app.common.extensions.navigate
import com.example.the_name_quiz_app.databinding.FragmentGameBinding
import com.example.the_name_quiz_app.people.domain.PeopleCardComponent
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameFragment : BaseFragment<FragmentGameBinding, GameNavigation>() {
    override val layoutId: Int = R.layout.fragment_game
    override val viewModel: GameViewModel by viewModel()

    override fun onNavigationEvent(event: GameNavigation) {
        when (event) {
            is GameSubmit -> navigate(GameFragmentDirections.toMain())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.person.observe { person ->
            setButtonValues(person)
        }
    }

    private fun setButtonValues(person: PeopleCardComponent) {
        val fakePerson1 =
            viewModel.people.value.filter { it != person }.randomOrNull() ?: viewModel.emptyPerson
        val fakePerson2 =
            viewModel.people.value.filter { it != person && it != fakePerson1 }.randomOrNull()
                ?: viewModel.emptyPerson

        when (viewModel.correctButton) {
            0 -> {
                binding.name1.text = person.title
                binding.name2.text = fakePerson1.title
                binding.name3.text = fakePerson2.title

            }
            1 -> {
                binding.name1.text = fakePerson1.title
                binding.name2.text = person.title
                binding.name3.text = fakePerson2.title
            }
            2 -> {
                binding.name1.text = fakePerson2.title
                binding.name2.text = fakePerson1.title
                binding.name3.text = person.title
            }
        }
    }
}
