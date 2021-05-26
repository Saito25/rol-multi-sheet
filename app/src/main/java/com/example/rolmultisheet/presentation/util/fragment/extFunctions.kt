package com.example.rolmultisheet.presentation.util.fragment

import androidx.fragment.app.Fragment

fun Fragment.findParentArgument(): Long =
    (requireParentFragment() as ArgumentsOwner).characterId
