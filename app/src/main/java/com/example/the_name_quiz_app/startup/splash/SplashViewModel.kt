package com.example.the_name_quiz_app.startup.splash

import com.example.the_name_quiz_app.common.base.BaseViewModel

class SplashViewModel : BaseViewModel<SplashNavigation>() {

    init { navigate(SplashNavigationMain) }
}
