package com.example.the_name_quiz_app.common.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun Fragment.navigate(directions: NavDirections) {
    val controller = findNavController()
    val currentDestination = when (val destination = controller.currentDestination) {
        is FragmentNavigator.Destination -> destination.className
        is DialogFragmentNavigator.Destination -> destination.className
        else -> null
    }
    if (currentDestination == this.javaClass.name) {
        controller.navigate(directions)
    }
}
fun Fragment.hideKeyboard() {
    val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    this.view?.rootView?.windowToken?.let {
        imm.hideSoftInputFromWindow(it, 0)
    }
}
