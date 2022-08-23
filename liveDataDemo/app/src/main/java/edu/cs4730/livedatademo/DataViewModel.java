package edu.cs4730.livedatademo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * this is a simple viewmodel class holding two data items.
 */

public class DataViewModel extends ViewModel {

    MutableLiveData<Integer> count = new MutableLiveData<Integer>(0);
    MutableLiveData<String> name = new MutableLiveData<String>("");


    public LiveData<Integer> getDataCount() {
        return count;
    }

    public LiveData<String> getDataName() {
        return name;
    }


    String getCount() {

        return String.valueOf(count.getValue());
    }

    String getName() {
        return name.getValue();
    }

    void incrementCount() {

        int c = count.getValue();
        c++;
        count.setValue(c);  //must use the setValue otherwise the observer will not be triggered!!!!
    }

    void setName(String item) {
        name.setValue(item);
    }

}
