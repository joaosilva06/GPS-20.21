<?php
if(isset($partes[2])){
    switch($partes[2]) {
        case 'login':
            if(isset($_POST["uName"]) and isset($_POST["pass"])){
                $query = "SELECT idUser FROM User WHERE userName = ? and password = ?;";
                $sql = mysqli_prepare($ligacao,$query);
                $user = strtolower($_POST["uName"]);
                $pass = md5($salt . $_POST["pass"] . $salt);
                mysqli_stmt_bind_param($sql,'ss', $user, $pass); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id);
                mysqli_stmt_store_result($sql); 
                if(mysqli_stmt_num_rows($sql) > 0){
                    mysqli_stmt_fetch($sql);
                    $_SESSION["id"] = $id;
                    $arr["id"] = $id;
                    $arr["username"] = $user;
                    $arr["password"] = $pass;
                    $msg = $arr;
                }else
                $msg = "Login errado";
            }else{
                $msg = "Falta de dados";
            }
            break;     
        case 'logoff':
            if(isset($_SESSION["id"])){ 
                session_destroy();
                $msg = "Success";
            }else{
                $msg = "Failed to logoff";
            }
            break;


        case 'projects':
            if(isset($_SESSION["id"])){ 
                $query ="Select * From Project inner Join Member On Member.Project_idProject = Project.idProject WHERE Member.User_idUser = ?";
                $id = $_SESSION["id"];
                $sql = mysqli_prepare($ligacao,$query);
                mysqli_stmt_bind_param($sql,'i', $id); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id, $name,$date, $trash, $roleId, $trash, $trash);
                mysqli_stmt_store_result($sql); 
                $result = Array();
                if(mysqli_stmt_execute($sql)){
                    while(mysqli_stmt_fetch($sql)){
                        $arr["projectId"] = $id;
                        $arr["name"] = $name;
                        $arr["dateCreate"] = $date;
                        array_push($result, $arr);
                    }
                    $msg = $result;
                }else{
                    $msg = "Error fetching projects";

                }
            }else{
                $msg = "No login";
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
                    $msg = $mail;
  
                }else{
                    $msg = "exist";

                }
            }
            break;
        case 'search':
            if(isset($_SESSION["id"])){
                if(isset($_POST["search"])){
                    $query ="Select * From User Where email = ? or userName = ?;";
                    $look = strtolower($_POST["search"]);
                    $sql = mysqli_prepare($ligacao,$query);
                    mysqli_stmt_bind_param($sql,'ss', $look, $look); 
                    mysqli_stmt_execute($sql);
                    mysqli_stmt_bind_result($sql, $id, $user, $email, $pass, $date);
                    mysqli_stmt_store_result($sql); 
                    if(mysqli_stmt_num_rows($sql) > 0){
                        mysqli_stmt_fetch($sql);
                        $arr["id"] = $id;
                        $arr["username"] = $user;
                        $arr["password"] = $pass;
                        //$arr["email"] = $email;
                        
                        $msg = $arr;
    
                    }else{
                        $msg = "Doesn't Exists";

                    }
                }else{
                    $msg = "Info missing";
                }
            }else{
                $msg = "Login First";
            }
            break;

        default:
            $msg = "funcao desconhecida";
            break;
    }
}
?>