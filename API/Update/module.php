<?php
include ("../getRole.php");

if(isset($partes[2])){
    switch($partes[2]) {
        case 'new':
            if(isset($_SESSION["id"]) and isset($_POST["projId"]) and isset($_POST["moduleName"])){
                $insertQuery = "INSERT INTO Module( name, dateCreation, Project_idProject)  
                                VALUES(?, STR_TO_DATE(?, '%Y %m %d'),?)";
                $insertSQL = mysqli_prepare($ligacao, $insertQuery);
                $today = date("Y m d");
                mysqli_stmt_bind_param($insertSQL,'ssi', $_POST["moduleName"], $today, $_POST["projId"]);
                if(mysqli_stmt_execute($insertSQL)){
                    $msg =  "Module ".$_POST["moduleName"]." added to project";
                }else{
                    $msg = "Error adding new module";
                }

            }else{
                    $msg =  "register incompleto";
            }
            break;

        case 'remove':
            if(isset($_SESSION["id"]) and isset($_POST["idModule"]) and isset($_POST["project"])){
                $query = "Delete From Module where Project_idProject = ? and idModule =  ?;";
                $idRole = getRole($_SESSION["id"] , $_POST["project"]);

                if($idRole == 1 or $idRole == 2){
                    $sql = mysqli_prepare($ligacao, $query);
                    mysqli_stmt_bind_param($sql,"iii", $_POST["role"], $_POST["project"], $_POST["user"]); 
                    mysqli_stmt_execute($sql);
                    if(mysqli_stmt_num_rows($sql) > 0){
                        $msg = "Deleted";
                    }else{
                        $msg =  "no Rows";
                    }
                }else{
                    $msg = "access denied");
                }
            }else{
                $msg = "Incomplete data";
            }
            break;
    }
}

?>
