<?php

if(isset($partes[1])){
    switch($partes[1]) {
        case 'new':
            if(isset($_SESSION["id"]) and isset($_POST["projId"]) and isset($_POST["moduleName"])){
                $insertQuery = "INSERT INTO Module( `name`, `dateCreation`, `Project_idProject`) ". 
                                "VALUES(?, STR_TO_DATE(?, '%Y %m %d'),?)";
                $insertSQL = mysqli_prepare($ligacao, $insertQuery);
                $today = date("Y m d");
                mysqli_stmt_bind_param($insertSQL,'ssi', $_POST["moduleName"], $today, $_POST["projId"]);
                if(mysqli_stmt_execute($insertSQL)){
                    $msg = Array("error" => "false", "msg" => "Module ".$_POST["moduleName"]." added to project");
  
                }else{
                    $msg = Array("error" => "true", "msg" => "Error adding new module");
                }

            }else{
                    $msg = Array("error" => "true", "msg" => "register incompleto");

            }
            break;
    }
}

?>
