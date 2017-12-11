<?php

$name = addslashes($_REQUEST['name']);
$score = addslashes($_REQUEST['score']);

include 'lib.php';

if($name != "" and $score != "") {
   $sql = "INSERT into restdata (name, score) values";
   $sql .= "('$name','$score')";
   //echo "$sql\n";
   $result = mysql_query($sql);
  echo mysql_affected_rows($link_id);
} else {
  echo 0;
}
 mysql_close($link_id);
 
?>
