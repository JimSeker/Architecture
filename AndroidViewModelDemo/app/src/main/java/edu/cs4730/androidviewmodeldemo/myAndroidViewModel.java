package edu.cs4730.androidviewmodeldemo;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


/**
 * We never pass context to a ViewModel.  If you need context, use AndroidViewModel
 * It has a get getApplication() method built into it.  also passed to in a required constructor.
 * <p>
 * great for things like room db that needs context.
 */

public class myAndroidViewModel extends AndroidViewModel {
    int count =0 ;
    MutableLiveData<String> name= new MutableLiveData<String>();

    public myAndroidViewModel(@NonNull Application application) {
        super(application);
        name.setValue("None");
    }

    String getCount() {
        return String.valueOf(count);
    }

    void incrementCount() {
        count++;
    }

    String getName() {
        return name.getValue();
    }

    void setName(String item) {
        name.setValue(item);
        //We really should not be toasting from here.  But as an example it works.
        Toast.makeText(getApplication(), "New Name", Toast.LENGTH_SHORT).show();
    }

    public LiveData<String> getData() {
        return name;
    }
}
