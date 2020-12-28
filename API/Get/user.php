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
                mysqli_stmt_bind_result($sql, $id);
                mysqli_stmt_store_result($sql); 
                if(mysqli_stmt_num_rows($sql) > 0){
                    mysqli_stmt_fetch($sql);
                    $_SESSION["userName"] = $user;
                    $_SESSION["id"] = $id;
                    $arr[0] = $id;
                    $arr[1] = $user;
                    $arr[2] = $pass;
                    $msg = Array("error" => "false", "msg" => $arr);
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
                $sql ="Select * From Project 
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
            break;

        case 'reset':
            if(isset($_POST["mail"])){
                $query ="Select email From User Where email = ? ";
                $mail = $_POST["mail"];
                $sql = mysqli_prepare($ligacao,$query);
                mysqli_stmt_bind_param($sql,'s', $mail); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $mail);
                mysqli_stmt_store_result($sql); 
                if(mysqli_stmt_execute($sql)){
                    $msg = Array("error" => "false", "msg" => $mail);
  
                }else{
                    $msg = Array("error" => "true", "msg" => "exist");

                }
            }
            break;


        case 'search':
            if(isset($_SESSION["id"]) and isset($_POST["search"])){
                $query ="Select * From User Where email = ? or userName = ?;";
                $look = $_POST["search"];
                $sql = mysqli_prepare($ligacao,$query);
                mysqli_stmt_bind_param($sql,'ss', $look, $look); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id, $user, $email, $pass, $date);
                mysqli_stmt_store_result($sql); 
                if(mysqli_stmt_num_rows($sql) > 0){
                    mysqli_stmt_fetch($sql);
                    $arr[0] = $id;
                    $arr[1] = $user;
                    $arr[2] = $pass;
                    
                    $msg = Array("error" => "false", "msg" => $arr);
  
                }else{
                    $msg = Array("error" => "true", "msg" => "exist");

                }
            }
            break;

        default:
            $msg = Array("error" => "true", "msg" => "funcao desconhecida");


    }


}
            

?>