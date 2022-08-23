package edu.cs4730.viewmodeldemo_kt

import androidx.lifecycle.ViewModel

/**
 * this is a simple viewmodel class holding two data items.
 */
class DataViewModel : ViewModel() {
    var count = 0
    var name = ""
    fun getCount(): String {
        return count.toString()
    }

    fun incrementCount() {
        count++
    }
}