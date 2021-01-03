<?php

if(isset($partes[2])){
    switch($partes[2]){

        case "request":
            if(isset($_SESSION["id"]) and isset($_POST["bugId"])){
                $query = "SELECT * FROM Bug 
                Inner join Project On Project.idProject = Bug.Project_idProject 
                Inner Join Type On Type.idType = Bug.Type_idType 
                Inner Join Status on Status.idStatus = Bug.Status_idStatus 
                Inner join Priority on Priority.idPriority = Bug.Priority_idPriority 
                left join Module on Bug.Module_idModule = Module.idModule 
                Where idBug = ? ";

                $sql = mysqli_prepare($ligacao, $query);
                mysqli_stmt_bind_param($sql,'i', $_POST["bugId"]);
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id, $title, $desc, $date, $dateS, $trash, 
                $trash, $trash, $trash, $trash, $trash, $project, $trash, $trash, $type,
                $trash, $status, $trash, $prio, $trash, $module, $trash,$trash);
                mysqli_stmt_store_result($sql);
                if(mysqli_stmt_num_rows($sql) > 0){
                    mysqli_stmt_fetch($sql);
                    $arr["id"] = $id;
                    $arr["title"] = $title;
                    $arr["description"] = $desc;
                    $arr["date"] = $date;
                    $arr["dateS"] = $dateS;
                    $arr["status"] = $status;
                    $arr["priority"] = $prio;
                    $arr["type"] = $type;
                    $arr["module"] = $module;
                    $arr["project"] = $project;
                    $msg = $arr;
                }else{
                    $msg =  "Error getting description";
                }
                mysqli_stmt_close($sql);
            }else{
                $msg = "Incomplete data";
            }
            break;
            
        case "check":
            if(isset($_POST["idBug"]) and isset($_SESSION["id"])){
                $query = "SELECT descripcion FROM bug WHERE idBug = ?";
                $sql = mysqli_prepare($ligacao, $query);
                mysqli_stmt_bind_param($sql,'i',$_POST["bugId"]);
                mysqli_stmt_bind_result($sql, $text);
                if(mysqli_stmt_execute($sql)){
                    $msg = $text;
                }else{
                    $msg =  "Error getting description";
                }
                mysqli_stmt_close($sql);
            }else{
                $msg = "Incomplete data";
            }
            break;

        
            
        default:
            $msg = "funcao desconhecida";
    }   
}

?>