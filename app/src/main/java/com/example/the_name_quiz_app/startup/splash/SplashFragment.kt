package com.example.the_name_quiz_app.startup.splash

import com.example.the_name_quiz_app.R
import com.example.the_name_quiz_app.common.base.BaseFragment
import com.example.the_name_quiz_app.common.extensions.navigate
import com.example.the_name_quiz_app.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : BaseFragment<FragmentSplashBinding, SplashNavigation>() {

    override val layoutId: Int = R.layout.fragment_splash
    override val viewModel: SplashViewModel by viewModel()

    override fun onNavigationEvent(event: SplashNavigation) {
        when (event) {
            SplashNavigationMain -> navigate(SplashFragmentDirections.toMain())
        }
    }
}
