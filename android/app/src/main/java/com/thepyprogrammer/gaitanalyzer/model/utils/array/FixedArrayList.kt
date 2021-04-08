package com.thepyprogrammer.gaitanalyzer.model.utils.array

import java.util.*


class FixedArrayList<T>(var capacity: Int) : ArrayList<T>(capacity) {
    override fun add(element: T): Boolean {
        return if (size < capacity) {
            super.add(element)
        } else false
    }
}