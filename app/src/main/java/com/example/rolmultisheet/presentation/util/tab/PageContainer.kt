package com.example.rolmultisheet.presentation.util.tab

import java.lang.ref.WeakReference

interface PageContainer {

    var currentPage: WeakReference<PageFragment>?

}