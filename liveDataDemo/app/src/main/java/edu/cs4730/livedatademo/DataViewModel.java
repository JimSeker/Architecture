package edu.cs4730.livedatademo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * this is a simple viewmodel class holding two data items.
 */

public class DataViewModel extends ViewModel {

    private MutableLiveData<dataObj> data;  // =  new MutableLiveData<dataObj>() ;

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
