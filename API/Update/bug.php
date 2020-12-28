<?php

if(isset($partes[2])){
    switch($partes[2]){
        case "addBugProject":
            if(isset($_POST["bugTitle"]) and isset( $_POST["bugDescription"]) and isset($_POST["bugModule"]) and isset($_POST["bugType"]) and isset($_POST["bugPriority"]) and isset($_POST["bugProject"])){
                $query3 = "INSERT INTO bug(title, description, dateCreation, Priority_idPriority, Type_idType, Module_idModule, Project_idProject) VALUES (?,?,STR_TO_DATE(?, '%Y %m %d'),?,?,?,?,?)";
                $sql = mysqli_prepare($ligacao, $query3);
                $today = date("Y m d");
                mysqli_stmt_bind_param($sql ,"sssiiii",$_POST["bugTitle"], $_POST["bugDescription"],$today, $_POST["bugPriority"],$_POST["bugType"],$_POST["bugModule"],$_POST["bugProject"]));
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("error" => "false", "msg" => "Success");
                }else{
                    $msg = Array("error" => "true", "msg" => "Unexpected error");
                }
                mysqli_stmt_close($sql);
            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
            break;
            
           case "edit":
            if(isset($_POST["newBugDescription"]) and isset($_POST["newTitle"]) and isset($_POST["bugId"]) and isset($_SESSION["id"])){
                $query = "UPDATE Bug SET title = ?, description  = ? WHERE idBug = ?";
                $sql = mysqli_prepare($ligacao,$query);
                mysqli_stmt_bind_param($sql,'ssi', $_POST["newTitle"], $_POST["newBugDescription"], $_POST["idBug"]);
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("error" => "false", "msg" => "Changed");
                }else{
                    $msg = Array("error" => "true", "msg" => "Error changing bug");
                }        
                mysqli_stmt_close($sql);
            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
            break;
        
        case "solve":
            if(isset($_POST["idBug"]) and isset($_SESSION["id"])){
                $query = "UPDATE Bug SET solved = 1 WHERE idBug = ?";
                $sql = mysqli_prepare($ligacao,$query);
                mysqli_stmt_bind_param($sql,'i', $_POST["idBug"]);
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("error" => "false", "msg" => "Bug Solved");
                }else{
                    $msg = Array("error" => "true", "msg" => "Error changing DATA");
                }   
                mysqli_stmt_close($sql);
            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
            break;
        
        case "unsolve":
            if(isset($_POST["idBug"]) and isset($_SESSION["id"])){
                $query = "UPDATE Bug SET solved = 0 WHERE idBug = ?";
                $sql = mysqli_prepare($ligacao,$query);
                mysqli_stmt_bind_param($sql,'i', $_POST["idBug"]);
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("error" => "false", "msg" => "Bug UnSolved");
                }else{
                    $msg = Array("error" => "true", "msg" => "Error changing DATA");
                }   
                mysqli_stmt_close($sql);
            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
            break;
            
        default:
            $msg = Array("error" => "true", "msg" => "funcao desconhecida");
    }   
}

?>