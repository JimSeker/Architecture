<?php

$id = addslashes($_REQUEST['id']);
$name = addslashes($_REQUEST['name']);
$score = addslashes($_REQUEST['score']);

include 'lib.php';

if($name != "" and $score != "" and $id != "") {
      $sql = "UPDATE restdata SET name = '$name',";
      $sql .= "score = $score";
      $sql .= " WHERE id = $id";

   echo "$sql\n";
   $result = mysql_query($sql);
  $i = mysql_affected_rows($link_id);
  if ($i >0) 
    $output = shell_exec('php ../fcm/SendMessage.php');
  echo $i;

} else {
  echo 0;
}
 mysql_close($link_id);
 
?>
