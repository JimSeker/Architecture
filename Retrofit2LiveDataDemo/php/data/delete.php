<?php

$id = addslashes($_REQUEST['id']);

include 'lib.php';

if($id != "" ) {  //delete 1
   $sql = "DELETE from restdata where id= $id";
   //echo "$sql\n";
   $result = mysql_query($sql);
   echo mysql_affected_rows($link_id);

} else {  //delete all.
   $sql = "DELETE from restdata";
   //echo "$sql\n";
   $result = mysql_query($sql);
   echo mysql_affected_rows($link_id);
}
 mysql_close($link_id);
 
?>
