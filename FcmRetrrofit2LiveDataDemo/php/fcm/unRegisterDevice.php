<?php 
 $response = array(); 
 $token = $_REQUEST['token'];

 include 'lib.php';


if ($token != "" ) {
  $sql = "DELETE FROM devices WHERE token='$token'";
  //echo "$sql\n"; 
  $result = mysql_query($sql);
  if (msyql_affected_rows($link_id) == 1) {
      $response['error'] = true; 
      $response['message'] = 'Device unregistered successfully';
  } else {
      $response['error'] = true; 
      $response['message'] = 'Device unregisteration failure';
    }
  }
}else{
  $response['error']=true;
  $response['message']='Invalid Request...';
}
 
 echo json_encode($response);
