/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.cs4730.retrofit2livedatademo;

public class User {

    private int mId;

    private String mUserName;

    User(int id, String userName) {
        this.mId = id;
        this.mUserName = userName;
    }

    User(String userName) {
        this.mId = 12;
        this.mUserName = userName;
    }

    User() {
        mId = 12;
        mUserName = "fred";
    }

    public int getId() {
        return mId;
    }

    public String getUser() {
        return mUserName;
    }
}
