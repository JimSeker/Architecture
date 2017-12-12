<?php

$id = addslashes($_REQUEST['id']);

include 'lib.php';

if($id != "" ) {  //delete 1
   $sql = "DELETE from restdata where id= $id";
   //echo "$sql\n";
   $result = mysql_query($sql);
} else {  //delete all.
   $sql = "DELETE from restdata";
   //echo "$sql\n";
   $result = mysql_query($sql);
}
$i = mysql_affected_rows($link_id);
if ($i >0) 
$output = shell_exec('php ../fcm/SendMessage.php');
echo $i;

mysql_close($link_id);
 
?>
