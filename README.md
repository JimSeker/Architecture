Android Architecture examples
===========

`legacy/` Examples are no longer updated and likely deprecated. 

**ViewModel Examples:**

`viewModelDemo` (java) is an example of how to use the viewModel class.  Also shows viewModel can survive a rotation.

`viewModelDemo_kt` (Kotlin) is an example of how to use the viewModel class.  Also shows viewModel can survive a rotation.

`AndroidviewModelDemo` (Java) is an example of how to use the AndroidviewModel class, which is just a viewModel that can get context.

`AndroidviewModelDemo_kt` (Kotlin) is an example of how to use the AndroidviewModel class, which is just a viewModel that can get context.

`liveDataDemo`  is a simple demo of how to wrap LiveData classes with a the viewModel object to display data as it changes.

---

**Room Examples:**

`roomDemo` is an example of use the new room objects to create and access a database.

`LiveDataRoomDemo` shows how to use the liveData object with the database to have the display updated when new data is added.

`LiveDataRoomRecyclerDemo` shows a room database with livedata/viewModel that auto updates a recyclerview (using an observer in the adapter) as the data changes.

`ContentProviderRoomDemo` shows how to create a contentprovider around a room database.  It's based on [sqliteDemo in saveData Repo](https://github.com/JimSeker/saveData)

`RxJavaRoomDemo` is an example of using the Flow in RxJava and room to display data as it changes.  Similar to LiveData.

---

**Other Architecture examples:**

`retrofit2Demo` is a very simple example using the retrofit2 ReST API and JSON, instead of httpURLconnection.  

`Retrofit2LiveDataDemo` is a basic attempt at google's user example (on their web pages) using retrofit2 and LiveData.   This example is still pretty rough, but it does work.  It uses a private ReST server.  the php backend will be added later on.  This doesn't use JSON, instead the ReST is a basic CSV.

`FcmRetrrofit2LiveDemo` is a complex example using many of the examples above that uses a local room database to hold the cache data and receive push updates when the data on the rest service is update.  It uses firebase messaging as well.

`WorkManagerDemo` is an example of using the Architecture Worker Tasks.  This example is in located in https://github.com/JimSeker/service repo.

---

These may also be helpful links 
https://medium.com/google-developers/viewmodels-a-simple-example-ed5ac416317e<BR>
https://developer.android.com/topic/libraries/architecture/index.html <BR>
https://medium.com/@taman.neupane/basic-example-of-livedata-and-viewmodel-14d5af922d0 <BR>
https://medium.com/google-developers/lifecycle-aware-data-loading-with-android-architecture-components-f95484159de4<BR>
https://medium.com/@iammert/offline-app-with-rxjava-2-and-room-ccd0b5c18101  good example, uses older library.<BR>
https://developer.android.com/reference/android/arch/lifecycle/LiveDataReactiveStreams.html<BR>
https://android.jlelse.eu/android-reactive-architecture-part-1-introduction-60466bc88774  (kotlin)<BR>
https://developer.android.com/topic/libraries/architecture/guide.html  (able halfway down the page), doesn't work ,but some explanations. <BR>
https://github.com/googlesamples/android-architecture-components/tree/master/BasicRxJavaSample  is Google's example for it, poorly documented and almost no comments.<BR>

---

These are example code for University of Wyoming, Cosc 4730 Mobile Programming course.
All examples are for Android.
