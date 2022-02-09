package com.raphaelbgr.myweatherapplication.placeholder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

object PlaceholderContent {

    val ITEMS: MutableList<PlaceholderItem> = ArrayList()
    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    private val COUNT = 5

    init {
        for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return PlaceholderItem(position.toString(), getCityName(position), makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0 until position) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    private fun getCityName(position: Int): String {
        return when (position) {
            1 -> "Los Angeles"
            2 -> "California"
            3 -> "London"
            4 -> "Ontario Canada"
            5 -> "Rio de Janeiro"
            else -> {
                "Invalid City"
            }
        }
    }

    @Parcelize
    data class PlaceholderItem(val id: String, val city: String, val details: String) :
        Parcelable {
        override fun toString(): String = city
    }
}