<?php
   include_once dirname(__FILE__) . '/../Config.php';
  $link_id = mysql_connect(DB_HOST,DB_USERNAME, DB_PASSWORD);
  mysql_select_db(DB_NAME,$link_id);

?>

