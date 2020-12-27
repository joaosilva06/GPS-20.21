<?php

if(isset($partes[1])){
    switch($partes[1]) {
        case 'bugs':
            if(isset($_SESSION["id"]) and isset($_POST["projId"])){
                $getBugsQuery = "SELECT * FROM Bug  WHERE Project_idProject = ?;";
                $getBugsSQl = mysqli_prepare($ligacao, $getBugsQuery);
                mysqli_stmt_bind_param($getBugsSQl, 'i', $_POST["projId"]);
                mysqli_stmt_execute($getBugsSQl);
                $result = Array();
                if(mysqli_stmt_num_rows($getBugsSQl) > 0){
                    while($row = mysqli_fetch_assoc($getBugsSQl))
                        array_push($result, $row);
                    $msg = Array("error" => "false", "msg" => $result);
                }
                $msg = Array("error" => "false", "msg" => "no rows selected");
               
            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
        break;

        case 'members':
            if(isset($_SESSION["id"]) and isset($_POST["projId"])){
                $memberQuery = "SELECT userName, Role.description,  FROM User
                                INNER JOIN Member ON idUser = User_idUser
                                INNER JOIN Role ON Role_idRole = idRole
                                INNER JOIN Project ON Project_idProject = idProject
                                WHERE Member.Project_idProject = ?";

                $membersSQL = mysqli_prepare($ligacao, $membersQuery);
                mysqli_stmt_bind_param($membersSQL, 'i', $_POST["projId"]);
                mysqli_stmt_execute($membersSQl);
                $result = Array();
                if(mysqli_stmt_num_rows($memberSQL) > 0){
                    while($row = mysqli_fetch_assoc($membersSQL)){
                        array_push($result, $row);
                    }
                    $msg = Array("error" => "false", "msg" => $result);
                }else{
                    $msg = Array("error" => "false", "msg" => "no rows selected");
                }

            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
        break;

        case 'modules':
<<<<<<< HEAD
            if(isset($_POST["projId"])){
=======
            if(isset($_SESSION["id"]) and isset($_POST["projId"])){
>>>>>>> main
                $modulesQuery = "SELECT * FROM Module
                                WHERE Project_idProject = ?";

                $modulesSQL = mysqli_prepare($ligacao, $modulesQuery);
                mysqli_stmt_bind_param($modulesSQL, 'i', $_POST["projId"]);
                mysqli_stmt_execute($modulesSQl);
                $result = Array();
                if(mysqli_stmt_num_rows($modulesSQL) > 0){
                    while($row = mysqli_fetch_assoc($modulesSQL)){
                        array_push($result, $row);
                    }
                    $msg = Array("error" => "false", "msg" => $result);
                }else{
                    $msg = Array("error" => "false", "msg" => "no rows selected");
                }

            }else{
                $msg = Array("error" => "true", "msg" => "Incomplete data");
            }
        break;
        default:
            $msg = Array("error" => "true", "msg" => "Unkown function");
    }
}
?>