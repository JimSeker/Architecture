<?php 
 $response = array(); 
 $token = $_REQUEST['token'];

 include 'lib.php';


if ($token != "" ) {
  $sql = "SELECT id FROM devices WHERE token='$token'";
  //echo "$sql\n"; 
  $result = mysql_query($sql);
    
  if ($ary = mysql_fetch_array($result)) {
    //already exists, don't add again.
    $response['error'] = true; 
    $response['message'] = 'Device already registered'; //id is ' + $ary['id'];
  } else {
    //register the device
    $sql = "INSERT into devices (token) values ('$token')";
    //echo "$sql\n";
    $result = mysql_query($sql);
    if (msyql_affected_rows($link_id) == 1) {
      $response['error'] = false; 
      $response['message'] = 'Device registered successfully';
    } else {
      $response['error'] = true; 
      $response['message'] = 'Device registeration failure';
    }
  }
}else{
  $response['error']=true;
  $response['message']='Invalid Request...';
}
 
 echo json_encode($response);
