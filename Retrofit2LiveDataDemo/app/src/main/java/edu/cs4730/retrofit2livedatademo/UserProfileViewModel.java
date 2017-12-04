package edu.cs4730.retrofit2livedatademo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


/**
 * Created by Seker on 11/30/2017.
 */

public class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepo;


    public UserProfileViewModel() {
        userRepo = null;
    }

    public void setup(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(int userId) {
        if (this.user != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        user = userRepo.getUser(userId);

    }

    public LiveData<User> getUser() {
        return user;
    }

}
