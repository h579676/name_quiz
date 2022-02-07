package com.example.the_name_quiz_app.startup

import com.example.the_name_quiz_app.startup.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startupModule = module {
    viewModel<SplashViewModel>()
}
