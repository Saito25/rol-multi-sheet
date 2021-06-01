package com.example.rolmultisheet.presentation.util.tab

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

abstract class PageFragment constructor(@LayoutRes layoutResId: Int) :
    Fragment(layoutResId) {

    override fun onResume() {
        super.onResume()
        (parentFragment as PageContainer).currentPage = WeakReference<PageFragment>(this)
    }

    abstract fun onFabClick()


}