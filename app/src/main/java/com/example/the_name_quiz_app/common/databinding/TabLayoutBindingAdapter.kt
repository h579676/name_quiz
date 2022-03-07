package com.example.the_name_quiz_app.common.databinding

import android.view.ViewTreeObserver
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

object TabLayoutBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["viewPager", "pageNames"], requireAll = false)
    fun TabLayout.setViewPager(viewPager: ViewPager2, pageNames: List<String>?) {
        viewPager.viewTreeObserver?.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    TabLayoutMediator(this@setViewPager, viewPager) { tab, position ->
                        pageNames?.let { names ->
                            tab.text = names.getOrElse(position) { "" }
                        }
                    }.attach()
                    viewPager.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
    }

    @JvmStatic
    @BindingAdapter(value = ["onTabSelected", "onTabUnselected", "onTabReselected"], requireAll = false)
    fun TabLayout.setOnTabSelected(
        onTabSelected: ((TabLayout.Tab) -> Unit)? = null,
        onTabUnselected: ((TabLayout.Tab) -> Unit)? = null,
        onTabReselected: ((TabLayout.Tab) -> Unit)? = null
    ) {
        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                onTabSelected?.let { it(tab) }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
                onTabUnselected?.let { it(tab) }
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
                onTabReselected?.let { it(tab) }
            }
        })
    }

    @JvmStatic
    @BindingAdapter("currentTab")
    fun TabLayout.setCurrentTab(position: Int) {
        getTabAt(position)?.let { tab ->
            selectTab(tab)
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "currentTab")
    fun TabLayout.getCurrentTab(): Int =
        selectedTabPosition

    @JvmStatic
    @BindingAdapter("currentTabAttrChanged")
    fun TabLayout.setCurrentTabListener(attrChange: InverseBindingListener) {
        attrChange.onChange()
    }
}
