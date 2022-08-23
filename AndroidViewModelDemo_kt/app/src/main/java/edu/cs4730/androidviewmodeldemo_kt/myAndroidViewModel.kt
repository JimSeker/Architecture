package edu.cs4730.androidviewmodeldemo_kt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import android.widget.Toast
import androidx.lifecycle.LiveData

/**
 * We never pass context to a ViewModel.  If you need context, use AndroidViewModel
 * It has a get getApplication() method built into it.  also passed to in a required constructor.
 *
 * great for things like room db that needs context.
 */
class myAndroidViewModel(application: Application) : AndroidViewModel(application) {
    var count = 0
    var name: MutableLiveData<String>
    fun getCount(): String {
        return count.toString()
    }

    fun incrementCount() {
        count++
    }

    fun getName(): String? {
        return name.value
    }

    fun setName(item: String) {
        name.value = item
        //We really should not be toasting from here.  But as an example it works.
        Toast.makeText(getApplication(), "New Name", Toast.LENGTH_SHORT).show()
    }

    val data: LiveData<String>
        get() = name

    init {
        name = MutableLiveData()
        //name.setValue("None");
    }
}