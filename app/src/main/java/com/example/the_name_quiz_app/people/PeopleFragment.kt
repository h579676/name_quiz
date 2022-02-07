package com.example.the_name_quiz_app.people

import com.example.the_name_quiz_app.R
import com.example.the_name_quiz_app.common.base.BaseFragment
import com.example.the_name_quiz_app.common.extensions.hideKeyboard
import com.example.the_name_quiz_app.common.extensions.navigate
import com.example.the_name_quiz_app.databinding.FragmentPeopleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel



class PeopleFragment : BaseFragment<FragmentPeopleBinding, PeopleNavigation>() {
    override val layoutId: Int = R.layout.fragment_people
    override val viewModel: PeopleViewModel by viewModel()

    override fun onNavigationEvent(event: PeopleNavigation) {
        when (event) {
            is HideKeyboard -> hideKeyboard()
        }
    }
}
