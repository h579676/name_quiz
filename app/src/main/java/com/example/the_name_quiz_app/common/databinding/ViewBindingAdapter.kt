package com.example.the_name_quiz_app.common.databinding

import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.the_name_quiz_app.common.extensions.onFinishedEditing

object ViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun setVisibleOrGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("onFinishEditing")
    fun setOnFinishEditing(view: EditText, onFinishEditing: () -> Unit) {
        view.onFinishedEditing(onFinishEditing)
    }

    @JvmStatic
    @BindingAdapter("onPageScrolled")
    fun onPageScrolled(view: RecyclerView, action: () -> Unit) {
        view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                action.invoke()
            }
        })
    }

    @JvmStatic
    @BindingAdapter("layout_constraint_vertical_bias")
    fun setLayoutConstraintVerticalBias(view: View, percent: Float) {
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.verticalBias = percent
        view.layoutParams = params
    }
}
