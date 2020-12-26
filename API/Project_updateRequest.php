<?php

if(isset($partes[1])){
    switch($partes[1]) {
        case 'add':
            if(/*isset($_SESSION["id"]) and*/ isset($_POST["projName"])){
                $getBugsQuery = "Insert into Project(name, dateCreation) values name = ?,   dateCreation = ?;";
                $getBugsSQl = mysqli_prepare($ligacao, $getBugsQuery);
                $today = date("Y m d");
                mysqli_stmt_bind_param($getBugsSQl, $_POST["name"], $today); 
                mysqli_stmt_execute($getBugsSQl);
                $result = Array();
                if(mysqli_stmt_num_rows($getBugsSQl) > 0){
                    while($row = mysqli_fetch_assoc($getBugsSQl))
                        array_push($result, $row);
                    $msg = Array("error" => "false", "msg" => $result);
                }
                $msg = Array("error" => "false", "msg" => "no rows selected");
               
            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
        break;

        case 'remove':
            if(/*isset($_SESSION["id"]) and*/ isset($_POST["projID"])){
                $getBugsQuery = "Insert into Project(name, dateCreation) values name = ?,   dateCreation = ?;";
                $getBugsSQl = mysqli_prepare($ligacao, $getBugsQuery);
                $today = date("Y m d");
                mysqli_stmt_bind_param($getBugsSQl, $_POST["name"], $today); 
                mysqli_stmt_execute($getBugsSQl);
                $result = Array();
                if(mysqli_stmt_num_rows($getBugsSQl) > 0){
                    while($row = mysqli_fetch_assoc($getBugsSQl))
                        array_push($result, $row);
                    $msg = Array("error" => "false", "msg" => $result);
                }
                $msg = Array("error" => "false", "msg" => "no rows selected");
               
            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
        break;



        default:
            $msg = Array("error" => "true", "msg" => "Unkown function");
    }
}
?>