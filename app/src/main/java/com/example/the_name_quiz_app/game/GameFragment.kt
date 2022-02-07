package com.example.the_name_quiz_app.game

import com.example.the_name_quiz_app.R
import com.example.the_name_quiz_app.common.base.BaseFragment
import com.example.the_name_quiz_app.common.extensions.navigate
import com.example.the_name_quiz_app.databinding.FragmentGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameFragment : BaseFragment<FragmentGameBinding, GameNavigation>() {
    override val layoutId: Int = R.layout.fragment_game
    override val viewModel: GameViewModel by viewModel()

    override fun onNavigationEvent(event: GameNavigation) {
        when (event) {
            is GameSubmit -> navigate(GameFragmentDirections.toMain())
        }
    }
}
