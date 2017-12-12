<?php 
//importing required files 
require_once 'Firebase.php';

include 'lib.php';

//getting the push from push object
$mPushNotification = array();
$mPushNotification['data']['message'] = "update";

 
//getting all the tokens from database
//$devicetoken = $db->getAllTokens();
$devicetoken = array();
$sql = "SELECT token FROM devices";
$result = mysql_query($sql);
while($ary = mysql_fetch_array($result)) {
  array_push($devicetoken, $ary['token']);
}

//creating firebase class object 
$firebase = new Firebase(); 

//sending push notification and displaying result 
echo $firebase->send($devicetoken, $mPushNotification);

