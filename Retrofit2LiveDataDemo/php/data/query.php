<?php

$id = $_REQUEST['id'];

include 'lib.php';

if($id == "") {
   $sql = 'SELECT id,name,score FROM restdata';
} else {
   $sql = 'SELECT id,name,score FROM restdata WHERE id='.$id;
}

//echo "$sql\n";

$result = mysql_query($sql);
while($ary = mysql_fetch_array($result)) {
  $id = stripslashes($ary["id"]);
  $name = stripslashes($ary["name"]);
  $score = stripslashes($ary["score"]);
  echo "$id,$name,$score\n";
}
 mysql_close($link_id);

?>
