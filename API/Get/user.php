<?php

if(isset($partes[2])){
    switch($partes[2]) {
        case 'login':
            if(isset($_POST["uName"]) and isset($_POST["pass"])){
                $query = "SELECT idUser FROM User WHERE userName = ? and password = ?;";
                $sql = mysqli_prepare($ligacao,$query);
                $user = $_POST["uName"];
                $pass = md5($salt . $_POST["pass"] . $salt);
                mysqli_stmt_bind_param($sql,'ss', $user, $pass); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id, $name);
                mysqli_stmt_store_result($sql); 
                if(mysqli_stmt_num_rows($sql) > 0){
                    mysqli_stmt_fetch($sql);
                    $_SESSION["userName"] = $user;
                    $_SESSION["id"] = $id;
                    $msg = Array("error" => "false", "msg" => $name);
                }else
                $msg = Array("error" => "true", "msg" => "Login errado");
            }else{
                $msg = Array("error" => "true", "msg" => "Falta de dados");
            }
        break;
            
        case 'logoff':
            if(isset($_SESSION["id"])){
                $_SESSION["userName"] = "";  
                session_destroy();
                $msg = Array("error" => "false", "msg" => "Success");
            }else{
                $msg = Array("error" => "true", "msg" => "Failed to logoff");
            }
            break;


        case 'projects':
            if(isset($_SESSION["id"])){ 
                $query2 ="Select * From Project 
                Inner Join Member 
                On Member.idUser = ? and Member.idProject = Project.idProject";
                $id = $_POST["id"];
                mysqli_stmt_bind_param($sql,'i', $id); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id);
                mysqli_stmt_store_result($sql); 
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("error" => "false", "msg" => $id);
  
                }else{
                    $msg = Array("error" => "true", "msg" => "projects");

                }
            }


        case 'reset':
            if(isset($_POST["mail"]))
                $query2 ="Select email From User Where email = ? ";
                $id = $_POST["mail"];
                mysqli_stmt_bind_param($sql,'s', $mail); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $mail);
                mysqli_stmt_store_result($sql); 
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("error" => "false", "msg" => $mail);
  
                }else{
                    $msg = Array("error" => "true", "msg" => "exist");

                }
            
        default:
            $msg = Array("error" => "true", "msg" => "funcao desconhecida");


    }


}

?>