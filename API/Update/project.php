<?php
include ("../getRole.php");

if(isset($partes[2])){
    switch($partes[2]) {
        case 'add':
            if(isset($_POST["id"]) and isset($_POST["projName"])){
                $query = "Insert into Project(name, dateCreation) values(?, STR_TO_DATE(?, '%Y %m %d')); 
                Insert into Member(Role_idRole, User_idUser, Project_idProject) values(1,?, Select MAX(idProject) from Project) ";//adiconar member and role
                $sql = mysqli_prepare($ligacao, $query);
                $today = date("Y m d");
                mysqli_stmt_bind_param($sql, "ssi" , $_POST["name"], $today, $_POST["id"]); 
                mysqli_stmt_execute($sql);
                $result = Array();
                if(mysqli_affected_rows($sql) > 0){
                    while($row = mysqli_fetch_assoc($sql))
                        array_push($result, $row);
                    $msg = $result;
                }
                $msg = "no rows selected";
               
            }else{
                $msg = "Incomplete data";
            }
        break;

        case 'role':
            if(isset($_POST["id"]) and isset($_POST["role"]) and  isset($_POST["user"]) and isset($_POST["project"])){
                $query = "Update Member set Role_idRole = ? where Project_idProject = ? and User_idUser = ?;";

                $idRole = getRole($_POST["id"] , $_POST["project"]);
                if($idRole == 1 or ($idRole == 2 and $_POST["role"] >= 3)){

                    $sql = mysqli_prepare($ligacao, $query);
                    mysqli_stmt_bind_param($sql,"iii", $_POST["role"], $_POST["project"], $_POST["user"]); 
                    mysqli_stmt_execute($sql);
                    $result = Array();
                    if(mysqli_affected_rows($sql) > 0){
                        $msg = "Role set";
                    }
                    $msg = "no rows selected";

                }else{
                    $msg = "access denied";
                }
            }else{
                $msg = "Incomplete data";
            }
            break;

        case 'addMember':
            if(isset($_POST["id"]) and isset($_POST["role"]) and  isset($_POST["user"]) and isset($_POST["project"])){
                $query = "Insert into Member(Role_idRole, User_idUser, Project_idProject) values(?,?,?);";
                $idRole = getRole($_POST["id"] , $_POST["project"]);

                if($idRole == 1 or ($idRole == 2 and $_POST["role"] >= 3)){
                    $sql = mysqli_prepare($ligacao, $query);
                    mysqli_stmt_bind_param($sql,"iii", $_POST["role"], $_POST["project"], $_POST["user"]); 
                    mysqli_stmt_execute($sql);
                    $result = Array();
                    if(mysqli_stmt_num_rows($sql) > 0){
                        while($row = mysqli_fetch_assoc($sql))
                            array_push($result, $row);
                        $msg =$result;
                    }
                    $msg =  "no rows selected";

                }else{
                    $msg = "access denied";
                }
            }else{
                $msg = "Incomplete data";
            }
            break;

        case 'removeMember':
            if(isset($_POST["id"]) and isset($_POST["user"]) and isset($_POST["project"])){
                $query = "Delete From Member where Project_idProject = ? and User_idUser =  ?;";
                $idRole = getRole($_POST["id"] , $_POST["project"]);

                if($idRole == 1 or ($idRole == 2 and $_POST["role"] >= 3)){
                    $sql = mysqli_prepare($ligacao, $query);
                    mysqli_stmt_bind_param($sql,"iii", $_POST["role"], $_POST["project"], $_POST["user"]); 
                    mysqli_stmt_execute($sql);
                    $result = Array();
                    if(mysqli_stmt_num_rows($sql) > 0){
                        while($row = mysqli_fetch_assoc($sql))
                            array_push($result, $row);
                        $msg = $result);
                    }
                    $msg = "no rows selected";

                }else{
                    $msg = "access denied";
                }
            }else{
                $msg = "Incomplete data";
            }
            break;
        
        
        
        default:
            $msg = "Unkown function";
    }
}
?>