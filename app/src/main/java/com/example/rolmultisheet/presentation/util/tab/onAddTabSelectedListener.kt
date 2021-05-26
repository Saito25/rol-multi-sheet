package com.example.rolmultisheet.presentation.util.tab

import com.google.android.material.tabs.TabLayout

fun TabLayout.onAddTabSelectedListener(onTabSelected: (tab: TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(
        object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                onTabSelected(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        }
    )
}

