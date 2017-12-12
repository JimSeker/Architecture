FcmRetrrofit2LiveDataDemo
===========

This example needs to more info to explain everything that is going on.
The basics is this:  The app will get push updates when the rest service updates the data.
So it saves time and network data, since doesn't poll.  The data is stored locally in a room database and updated from the rest service only when needed.

So Score, ScoreDoa, AppDatabase, and ScoreListViewModel  are for the "front end".
They are the room database and liveData update pieces.


MyFirebaseInsanceIDservice, myFirbaseMessageingService and the sendToken method in Mainactivity are for the Firebase Cloud messaging, which is the backend.  The app registers it's
 firebase token with the rest service, so that can notify it when there are updates.
 * sharedPrefManager class holds the firebase token.
 * With the Oreo restrictions there is MyJobService as well, but it's not working.   Instead I'm ignore it and using the service, since it is allowed to run without restrictions when the app is running.
 * the messaging serivce that updates the room database with the new data and the liveData obersver in MainActivity will then update the data on the "front end"
 * endpoints class is just the constants for where the rest service it.  Which is a local private webserver.  You will need to get the php code and create your own server.
 
For the backend rest service, it uses retrofit2 (which yes, it's misspelled in the app name, but hard to change at this point) to access the rest service.
 * Webservice and SocreRepository are for retrofit2.   When fcm receives a message, it launch this to download the data, which updates the roomdatabase.
 
 
php directory is the php code and readme to create the necessary database for the rest services.  you will need a webserver with php and mysql/maria database.
  * use sendMessage.php to notify the app about updates.  
  * this code needs cleaned up and commented.  Also likely changed to deal with registration and unregistration.  And it appears the token will need a unique name too.  likely an instance ID. https://developers.google.com/instance-id/guides/android-implementation
  
Todo:  If I get time or care.
  * update on first instance.
  * add in a real UI in mainactivity
    * also added update, insert, and delete to the app as well.
  * fix the fcm token update setup and removal.




