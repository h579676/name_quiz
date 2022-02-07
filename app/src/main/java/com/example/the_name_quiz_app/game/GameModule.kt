package com.example.the_name_quiz_app.game

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {
    viewModel<GameViewModel>()
}
