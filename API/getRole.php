<?php

function getRole($idUser, $idProj, $ligacao) {
    $query = "Select Role_idRole From Member where User_idUser = ? and Project_idProject = ?;";
    $sql = mysqli_prepare($ligacao,$query);
    mysqli_stmt_bind_param($sql,'ii', $idUser, $idProj); 
    mysqli_stmt_execute($sql);
    mysqli_stmt_bind_result($sql, $role);
    mysqli_stmt_store_result($sql); 
    if(mysqli_stmt_num_rows($sql) > 0){
        mysqli_stmt_fetch($sql);
        return $role;
    }else{
        return -1;
    }
}


?>