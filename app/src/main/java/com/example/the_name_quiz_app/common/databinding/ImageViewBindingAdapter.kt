package com.example.the_name_quiz_app.common.databinding

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("tintColor")
    fun setTintColor(view: ImageView, @ColorRes colorId: Int) {
        view.setColorFilter(ContextCompat.getColor(view.context, colorId))
    }

    @JvmStatic
    @BindingAdapter("srcRes")
    fun setImageRes(view: ImageView, @DrawableRes res: Int) {
        if (res != -1) view.setImageResource(res)
    }
}
