google android Architecture examples
===========

<b>viewModelDemo</b> is an example of how to use the viewModel class.  Also shows viewModel can survive a rotation.

<b>liveDataDemo</b>  is a simple demo of how to wrap LiveData classes with a the viewModel object to display data as it changes.

<b>roomDemo</b> is an example of use the new room objects to create and access a database.

<b>LiveDataRoomDemo</b> shows how to use the liveData object with the database to have the display updated when new data is added.

<b>LiveDataRoomRecyclerDemo</b> shows a room database with livedata/modelview that auto updates a recyclerview (using an observer in the adapter) as the data changes.

<b>ContentProviderRoomDemo</b> shows how to create a contentprovider around a room database.  It's based on sqliteDemo in the https://github.com/JimSeker/saveData 

<b>PagingRoomDemo</b> is an example using the page library to slowly load the data froma room database into a recyclerview, using PageList, PagedListAdaepter, and LivePagedListProvider (in the RoomDatabase).

<b>retrofit2Demo</b> is a very simple example using the retrofit2 ReST API and JSON, instead of httpURLconnection.  

<b>Retrofit2LiveDataDemo</b> is a basic attempt at google's user example (on their web pages) using retrofit2 and LiveData.   This example is still pretty rough, but it does work.
It uses a private ReST server.  the php backend will be added later on.  This doesn't use JSON, instead the ReST is a basic CSV.

<b>RxJavaRoomDemo</b> is an example of using the Flow in RxJava and room to display data as it changes.  Similiar to LiveData.

<br>FcmRetrrofit2LiveDemo</b> is a complex example using many of the examples above that uses a local room database to hold the cache data and receive push 
updates when the data on the rest service is update.  It uses firebase messaging as well.


These may also be helpful links 
https://medium.com/google-developers/viewmodels-a-simple-example-ed5ac416317e<BR>
https://developer.android.com/topic/libraries/architecture/index.html <BR>
https://medium.com/@taman.neupane/basic-example-of-livedata-and-viewmodel-14d5af922d0 <BR>
https://medium.com/google-developers/lifecycle-aware-data-loading-with-android-architecture-components-f95484159de4<BR>
https://medium.com/@iammert/offline-app-with-rxjava-2-and-room-ccd0b5c18101  good example, uses older library.<BR>
https://developer.android.com/reference/android/arch/lifecycle/LiveDataReactiveStreams.html<BR>
https://android.jlelse.eu/android-reactive-architecture-part-1-introduction-60466bc88774  (kotlin, not java!)<BR>
https://developer.android.com/topic/libraries/architecture/guide.html  (able halfway down the page), doesn't work ,but some explainations. <BR>
https://github.com/googlesamples/android-architecture-components/tree/master/BasicRxJavaSample  is google's example for it, poorely documented and almost no comments.<BR>

These are example code for University of Wyoming, Cosc 4730 Mobile Programming course.
All examples are for Android.
