package com.example.rolmultisheet.presentation.util.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.doOnSwiped(
    swipeDirs: Int = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
    onSwipedAction: (viewHolder: RecyclerView.ViewHolder, direction: Int) -> Unit =
        { _, _ -> }
) {
    val itemTouchHelper = ItemTouchHelper(
        object : ItemTouchHelper.SimpleCallback(0, swipeDirs) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onSwipedAction(viewHolder, direction)
            }
        })
    itemTouchHelper.attachToRecyclerView(this)
}
