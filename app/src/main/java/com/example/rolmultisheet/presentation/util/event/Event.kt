package com.example.rolmultisheet.presentation.util.event

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

}

// Permite observar directamente el contenido de un Event.
inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner, { it.getContentIfNotHandled()?.let(onEventUnhandledContent) })
}
