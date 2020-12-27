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
    case 'register':
        if(isset($_POST["uName"]) and isset($_POST["email"]) and  isset($_POST["pass"])){
            $query2 ="INSERT INTO User(userName, email, password) VALUES (?,?,?)";
            $sql = mysqli_prepare($ligacao,$query2);
            $username = $_POST["uName"];
            $mail = $_POST["email"];
            $pass = md5($salt . $_POST["pass"] . $salt);
            mysqli_stmt_bind_param($sql,'sss', $username, $mail, $pass);   
            if(mysqli_stmt_execute($sql)){
                $msg = Array("error" => "false", "msg" => $username);

            }else{
                $msg = Array("error" => "true", "msg" => "already exist");

            }

        }else{
                $msg = Array("error" => "true", "msg" => "register incompleto");

        }
        break;

    
    default:
        


    }


}

?>