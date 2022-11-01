package edu.cs4730.retrofit2demo;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {

    private MutableLiveData<List<String>> myData = new MutableLiveData<>(new ArrayList<String>());

    LiveData<List<String>> getData() {
        return myData;
    }

    void addData(String item) {

        List<String> tmp = myData.getValue();
        if (tmp == null)
            tmp = new ArrayList<String>();
        tmp.add(item);
        myData.postValue(tmp);
    }


}
