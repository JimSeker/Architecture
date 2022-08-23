package edu.esp.livedatademo_kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

/**
 * this is a simple viewmodel class holding two data items.
 */
class DataViewModel : ViewModel() {
    var count = MutableLiveData(0)
    var name = MutableLiveData("")
    val dataCount: LiveData<Int>
        get() = count
    val dataName: LiveData<String>
        get() = name

    fun getCount(): String {
        return count.value.toString()
    }

    fun getName(): String? {
        return name.value
    }

    fun incrementCount() {
        var c = count.value!!
        c++
        count.value = c //must use the setValue otherwise the observer will not be triggered!!!!
    }

    fun setName(item: String) {
        name.value = item
    }
}