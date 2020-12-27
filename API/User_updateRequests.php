<?php

if(isset($partes[1])){
    switch($partes[1]) {
        case "rename":
            if(isset($_SESSION["id"]) and isset($_POST["newName"]))){
                $query = "Update User Set userName = ? Where idUser = ?";
                $sql = mysqli_prepare($ligacao,$query);
                $sql = mysqli_prepare($ligacao,$query);
                mysqli_stmt_bind_param($sql,'si', $_POST["newName"]), $_SESSION["id"]); 
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
            
    case "pass":
        if(isset($_SESSION["id"]) and isset($_POST["newPass"]))){
            $query = "Update User Set password = ? Where idUser = ?";
            $sql = mysqli_prepare($ligacao,$query);
            $pass = md5($salt . $_POST["newPass"] . $salt);
            mysqli_stmt_bind_param($sql,'si', $pass, $_SESSION["id"]); 
            mysqli_stmt_execute($sql);
            //help here
            mysqli_stmt_bind_result($sql, "is", $id, $name);
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

    case "namePass":
        if(isset($_SESSION["id"]) and isset($_POST["newName"]) and  isset($_POST["newPass"]))){
                
            $query = "Update User Set userNamer= ?,password = ? Where idUser = ?";
            $sql = mysqli_prepare($ligacao,$query);
            $user = $_POST["newName"];
            $pass = md5($salt . $_POST["newPass"] . $salt);
            mysqli_stmt_bind_param($sql,'ssi', $user, $pass, $_SESSION["id"]); 
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

    
    default:
        $msg = Array("error" => "true", "msg" => "funcao desconhecida");



    }


}

?>