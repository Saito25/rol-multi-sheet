package com.example.rolmultisheet.presentation.util.recycler

import android.os.Parcelable
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView

interface ItemKeyPositionProvider<K> {
    fun getItemKey(position: Int): K?
    fun getItemPosition(key: K): Int
}

fun <VH : RecyclerView.ViewHolder, AD>
        newLongKeySelectionTrackerBuilder(
    selectionId: String,
    recyclerView: RecyclerView,
    adapter: AD
) where AD : RecyclerView.Adapter<VH>, AD : ItemKeyPositionProvider<Long> =
    SelectionTracker.Builder(
        selectionId,
        recyclerView,
        KeyProvider(adapter),
        DetailsLookup(recyclerView, adapter),
        StorageStrategy.createLongStorage()
    )

fun <VH : RecyclerView.ViewHolder, AD>
        newStringKeySelectionTrackerBuilder(
    selectionId: String,
    recyclerView: RecyclerView,
    adapter: AD
) where AD : RecyclerView.Adapter<VH>, AD : ItemKeyPositionProvider<String> =
    SelectionTracker.Builder(
        selectionId,
        recyclerView,
        KeyProvider(adapter),
        DetailsLookup(recyclerView, adapter),
        StorageStrategy.createStringStorage()
    )

fun <VH : RecyclerView.ViewHolder, AD, K : Parcelable>
        newParcelableKeySelectionTrackerBuilder(
    selectionId: String,
    recyclerView: RecyclerView,
    adapter: AD,
    keyClass: Class<K>
) where AD : RecyclerView.Adapter<VH>, AD : ItemKeyPositionProvider<K> =
    SelectionTracker.Builder(
        selectionId,
        recyclerView,
        KeyProvider(adapter),
        DetailsLookup(recyclerView, adapter),
        StorageStrategy.createParcelableStorage(keyClass)
    )

private class KeyProvider<K, VH : RecyclerView.ViewHolder, AD>(private val adapter: AD) :
    ItemKeyProvider<K>(SCOPE_CACHED) where AD : RecyclerView.Adapter<VH>, AD : ItemKeyPositionProvider<K> {
    override fun getKey(position: Int): K? = adapter.getItemKey(position)

    override fun getPosition(key: K): Int = adapter.getItemPosition(key)

}

private class Details<K, VH : RecyclerView.ViewHolder, AD>(
    private val holder: RecyclerView.ViewHolder,
    private val adapter: AD
) : ItemDetailsLookup.ItemDetails<K>() where AD : RecyclerView.Adapter<VH>, AD : ItemKeyPositionProvider<K> {
    override fun getSelectionKey(): K? = adapter.getItemKey(holder.adapterPosition)

    override fun getPosition(): Int = holder.adapterPosition

}

private class DetailsLookup<K, VH : RecyclerView.ViewHolder, AD>(
    private val recyclerView: RecyclerView, private val adapter: AD
) : ItemDetailsLookup<K>() where AD : RecyclerView.Adapter<VH>, AD : ItemKeyPositionProvider<K> {

    override fun getItemDetails(e: MotionEvent): ItemDetails<K>? =
        recyclerView.findChildViewUnder(e.x, e.y)?.let { view ->
            recyclerView.getChildViewHolder(view)?.let { holder ->
                Details(holder, adapter)
            }
        }

}