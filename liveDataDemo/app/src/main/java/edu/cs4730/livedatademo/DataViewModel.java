package edu.cs4730.livedatademo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


/**
 * this is a simple viewmodel class holding two data items.
 */

public class DataViewModel extends ViewModel {

    private MutableLiveData<dataObj> data;

    public LiveData<dataObj> getData() {
        if (data == null) {
            data = new MutableLiveData<dataObj>();
            data.setValue(new dataObj());
        }
        return data;
    }


    String getCount() {
        return String.valueOf(data.getValue().getCount());
    }

    String getName() {
        return data.getValue().name;
    }


    void incrementCount() {
        dataObj d = data.getValue();
        d.count++;
        data.setValue(d);  //must use the setValue otherwise the observer will not be triggered!!!!

    }

    void setName(String item) {
        dataObj d = data.getValue();
        d.name = item;
        data.setValue(d);

    }

}
