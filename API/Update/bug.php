<?php

include 'getRole.php';

if(isset($partes[2])){
    switch($partes[2]){
        case "addBugProject":
            if(isset($_POST["bugTitle"]) and isset( $_POST["bugDescription"]) and isset($_POST["bugModule"]) and isset($_POST["bugType"]) and isset($_POST["bugPriority"]) and isset($_POST["bugProject"])){
                $query3 = "INSERT INTO bug(title, description, dateCreation, Priority_idPriority, Type_idType, Module_idModule, Project_idProject) VALUES (?,?,STR_TO_DATE(?, '%Y %m %d'),?,?,?,?,?)";
                $sql = mysqli_prepare($ligacao, $query3);
                $today = date("Y m d");                
                
                mysqli_stmt_bind_param($sql ,"sssiiii",$_POST["bugTitle"], $_POST["bugDescription"],$today, $_POST["bugPriority"],$_POST["bugType"],$_POST["bugModule"],$_POST["bugProject"]);
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("msg" => "Success");
                }else{
                    $msg = Array("msg" => "Unexpected error");
                }
                mysqli_stmt_close($sql);
            }else{
                $msg = Array("msg" => "Incomplete data");
            }
            break;
            
        case "edit":
            if(isset($_POST["newBugDescription"]) and isset($_POST["project"]) and isset($_POST["priority"]) and isset($_POST["newTitle"]) and isset($_POST["bugId"]) and isset($_SESSION["id"])){
                $query = "UPDATE Bug SET title = ?, description  = ?, Priority_idPriority = ? WHERE idBug = ?";
                
                if(getRole($_SESSION["id"] , $_POST["project"], $ligacao) < 4){
                    $sql = mysqli_prepare($ligacao,$query);
                    mysqli_stmt_bind_param($sql,'ssii', $_POST["newTitle"], $_POST["newBugDescription"], $_POST["priority"], $_POST["bugId"]);
                    if(mysqli_stmt_execute($sql)){
                        $msg = Array( "msg" => "Changed");
                    }else{
                        $msg = Array("msg" => "Error changing bug");
                    }        
                    mysqli_stmt_close($sql);
                }else{
                    $msg = Array("msg" => "Access Denied");
                }
            }else{
                $msg = Array("msg" => "Incomplete data");
            }
            break;
        
        case "solve":
            if(isset($_POST["idBug"]) and isset($_SESSION["id"]) and isset($_POST["project"])){
                $query = "UPDATE Bug SET Status_idStatus = 1, dateSolved = STR_TO_DATE(?, '%Y %m %d')  WHERE idBug = ?;";

                if(getRole($_SESSION["id"] , $_POST["project"], $ligacao) < 4){
                    $today = date("Y m d");
                    $sql = mysqli_prepare($ligacao,$query);
                    mysqli_stmt_bind_param($sql,'si', $today, $_POST["idBug"]);
                    if(mysqli_stmt_execute($sql)){
                        $msg = Array( "msg" => "Bug Solved");
                    }else{
                        $msg = Array("msg" => "Error changing DATA");
                    }   
                    mysqli_stmt_close($sql);
                }else{
                    $msg = Array("msg" => "Access denied");
                }
            }else{
                $msg = Array("msg" => "Incomplete data");
            }
            break;
        
        case "unsolve":
            if(isset($_POST["idBug"]) and isset($_SESSION["id"]) and isset($_POST["project"])){
                $query = "UPDATE Bug SET Status_idStatus = 3, dateSolved = null WHERE idBug = ?";

                if(getRole($_SESSION["id"] , $_POST["project"], $ligacao) < 4){
                    $sql = mysqli_prepare($ligacao,$query);
                    mysqli_stmt_bind_param($sql,'i', $_POST["idBug"]);
                    if(mysqli_stmt_execute($sql)){
                        $msg = Array("msg" => "Bug UnSolved");
                    }else{
                        $msg = Array("msg" => "Error changing DATA");
                    }   
                    mysqli_stmt_close($sql);
                }else{
                    $msg = Array("msg" => "Access denied");
                }
            }else{
                $msg = Array("msg" => "Incomplete data");
            }
            break;
            
        case "progress":
            if(isset($_POST["idBug"]) and isset($_SESSION["id"]) and isset($_POST["project"])){
                $query = "UPDATE Bug SET Status_idStatus = 2 WHERE idBug = ?";

                if(getRole($_SESSION["id"] , $_POST["project"], $ligacao) < 4){
                    $sql = mysqli_prepare($ligacao,$query);
                    mysqli_stmt_bind_param($sql,'i', $_POST["idBug"]);
                    if(mysqli_stmt_execute($sql)){
                        $msg = Array("msg" => "Status Changed");
                    }else{
                        $msg = Array("msg" => "Error changing DATA");
                    }   
                    mysqli_stmt_close($sql);
                }else{
                    $msg = Array("msg" => "Access denied");
                }
            }else{
                $msg = Array("msg" => "Incomplete data");
            }
            break;

        
        default:
            $msg = Array("msg" => "funcao desconhecida");
    }   
}

?>