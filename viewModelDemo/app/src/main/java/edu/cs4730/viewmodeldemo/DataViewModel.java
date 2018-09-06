package edu.cs4730.viewmodeldemo;

import androidx.lifecycle.ViewModel;

/**
 * this is a simple viewmodel class holding two data items.
 */

public class DataViewModel extends ViewModel {

    int count = 0;
    String name = "";

    String getCount() {
        return String.valueOf(count);
    }

    void incrementCount() {
        count++;
    }
}
