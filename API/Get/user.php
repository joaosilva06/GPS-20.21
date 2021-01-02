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
                    $_SESSION["uName"] = $user;
                    $_SESSION["id"] = $id;
                    $arr["id"] = $id;
                    $arr["name"] = $user;
                    $arr["pass"] = $pass;
                    $msg = Array("msg" => $arr);
                }else
                $msg = Array("msg" => "Login errado");
            }else{
                $msg = Array("msg" => "Falta de dados");
            }
        break;
            
        case 'logoff':
            if(isset($_SESSION["id"])){
                $_SESSION["userName"] = "";  
                session_destroy();
                $msg = Array("msg" => "Success");
            }else{
                $msg = Array("msg" => "Failed to logoff");
            }
            break;


        case 'projects':
            if(isset($_SESSION["id"])){ 
                $sql ="Select * From Project inner Join Member On Member.User_idUser = ? and Member.Project_idProject = Project.idProject ";
                $id = $_POST["id"];
                mysqli_stmt_bind_param($sql,'i', $id); 
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id, $name,$date, $trash, $roleId, $trash, $trash);
                mysqli_stmt_store_result($sql); 
                $result = Array();
                if(mysqli_stmt_execute($sql)){
                    $rows =mysqli_stmt_num_rows($sql) ;
                    if($rows > 0)
                        while($row = mysqli_stmt_fetch($sql)){
                        $arr["projectId"] = $id;
                        $arr["name"] = $name;
                        $arr["dateCreate"] = $date;
                        array_push($result, $arr);
                        }
                    $msg = Array( "msg" => $result);
                }else{
                    $msg = Array( "msg" => "projects");

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
                    $msg = Array( "msg" => $mail);
  
                }else{
                    $msg = Array("msg" => "exist");

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
                    $arr["id"] = $id;
                    $arr["name"] = $user;
                    $arr["pass"] = $pass;
                    $arr["email"] = $email;
                    
                    $msg = Array( "msg" => $arr);
  
                }else{
                    $msg = Array("msg" => "exist");

                }
            }
            break;

        default:
            $msg = Array("msg" => "funcao desconhecida");


    }


}
            

?>