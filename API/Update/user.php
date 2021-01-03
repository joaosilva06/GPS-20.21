<?php

if(isset($partes[2])){
    switch($partes[2]) {
        case "rename":
            if(isset($_SESSION["id"]) and isset($_POST["newName"])){
                $query = "Update User Set userName = ? Where idUser = ?";
                $sql = mysqli_prepare($ligacao,$query);
                $name = strtolower($_POST["newName"]);
                mysqli_stmt_bind_param($sql,'si', $name, $_SESSION["id"]); 
                if(mysqli_stmt_execute($sql)){
                    $msg = $name;
                }else
                    $msg = "Login errado";
            }else{
                $msg = "Falta de dados";
            }
        break;
            
    case "pass":
        if(isset($_SESSION["id"]) and isset($_POST["newPass"])){ 
            $query = "Update User Set password = ? Where idUser = ?";
            $sql = mysqli_prepare($ligacao,$query);
            $pass = md5($salt . $_POST["newPass"] . $salt);
            mysqli_stmt_bind_param($sql,'si', $pass, $_SESSION["id"]); 
            mysqli_stmt_execute($sql);
            mysqli_stmt_bind_result($sql, $id , $name);
            mysqli_stmt_store_result($sql); 
            if(mysqli_affected_rows($sql) > 0){
                $msg = $name;
            }else
                $msg = "Login errado";
        }else{
            $msg ="Falta de dados";
        }
        break;
        
    case 'register':
        if(isset($_POST["uName"]) and isset($_POST["email"]) and  isset($_POST["pass"])){
            $query2 ="INSERT INTO User(userName, email, password, dateCreateAccount) VALUES (?,?,?, STR_TO_DATE(?, '%Y %m %d'))";
            $sql = mysqli_prepare($ligacao,$query2);
            $username = strtolower($_POST["uName"]);
            $mail = $_POST["email"];
            $pass = md5($salt . $_POST["pass"] . $salt);
            $today = date("Y m d");
            mysqli_stmt_bind_param($sql,'ssss', $username, $mail, $pass, $today);   

            if(mysqli_stmt_execute($sql)){
                $query ="Select Max(idUser) from User;";
                $sql_id = mysqli_prepare($ligacao,$query);
                mysqli_stmt_execute($sql_id);
                mysqli_stmt_bind_result($sql_id, $id);
                mysqli_stmt_store_result($sql_id); 
                mysqli_stmt_fetch($sql_id);
                
                $arr["id"] = $id;
                $arr["username"] = strval($username);
                $arr["email"] = strval($mail);
                $arr["password"] = strval($pass);
                
                $msg = $arr;

            }else{
                $msg = "already exist";

            }

        }else{
                $msg = "register incompleto";

        }
        break;

    
    default:
        $msg = "funcao desconhecida";



    }


}

?>