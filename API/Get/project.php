<?php

if(isset($partes[2])){
    switch($partes[2]) {
        case 'bugs':
            if(isset($_POST["id"]) and isset($_POST["projId"])){
                $query = "SELECT * FROM Bug left join Module on Bug.Module_idModule = Module.idModule WHERE bug.Project_idProject = ?;";
                $sql = mysqli_prepare($ligacao, $query);
                mysqli_stmt_bind_param($sql, 'i', $_POST["projId"]);
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $id, $title, $desc, $date, $dateS, $status, $prio,$type, $trash, $project, $trash,$module,$trash,$trash);
                mysqli_stmt_store_result($sql);
                $result = Array();
                $rows =mysqli_stmt_num_rows($sql) ;
                if($rows > 0){
                    while($row = mysqli_stmt_fetch($sql)){
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
                        array_push($result, $arr);
                    }
                    $msg = $result;
                }else{
                    $msg = "no rows selected";
                }
               
            }else{
                $msg = "Incomplete data";
            }
        break;

        case 'members':
            if(isset($_POST["id"]) and isset($_POST["projId"])){
                $query = "SELECT userName, Role.description,  FROM User
                        INNER JOIN Member ON idUser = User_idUser
                        INNER JOIN Role ON Role_idRole = idRole
                        INNER JOIN Project ON Project_idProject = idProject
                        WHERE Member.Project_idProject = ?";

                $sql = mysqli_prepare($ligacao, $query);
                mysqli_stmt_bind_param($sql, 'i', $_POST["projId"]);
                mysqli_stmt_execute($sql);
                mysqli_stmt_bind_result($sql, $name, $role);
                mysqli_stmt_store_result($sql);
                $result = Array();
                if(mysqli_stmt_num_rows($sql) > 0){
                    while($row = mysqli_stmt_fetch($sql)){
                        $arr["userName"] = $name;
                        $arr["role"] = $role;
                        array_push($result, $arr);
                    }
                    $msg =  $result;
                }else{
                    $msg = "no rows selected";
                }

            }else{
                $msg = "Incomplete data";
            }
        break;

        case 'modules':
            if(isset($_POST["id"]) and isset($_POST["projId"])){
                $modulesQuery = "SELECT * FROM Module
                                WHERE Project_idProject = ?";

                $modulesSQL = mysqli_prepare($ligacao, $modulesQuery);
                mysqli_stmt_bind_param($modulesSQL, 'i', $_POST["projId"]);
                mysqli_stmt_execute($modulesSQL);
                mysqli_stmt_bind_result($modulesSQL, $id, $name, $date, $idProject);
                mysqli_stmt_store_result($modulesSQL);
                $result = Array();
                if(mysqli_stmt_num_rows($modulesSQL) > 0){
                    while($row = mysqli_stmt_fetch($modulesSQL)){
                        $arr["id"] = $id;
                        $arr["name"] = $name;
                        $arr["date"] = $date;
                        $arr["idProject"] = $idProject;
                        array_push($result, $arr);
                    }
                    $msg =  $result;
                }else{
                    $msg = "no rows selected";
                }

            }else{
                $msg = "Incomplete data");
            }
        break;
            
        
        default:
            $msg = "Unkown function";
    }
}

?>