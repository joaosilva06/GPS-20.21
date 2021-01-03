<?php
header('Content-Type: text/html; charset=utf-8');
//include 'config.php';


$ligacao=mysqli_connect("localhost","gps","gps123","BugTrackerDataBase");
mysqli_set_charset($ligacao,"utf8");

if (isset($_COOKIE[session_name()]) AND preg_match('/^[-,a-zA-Z0-9]{1,128}$/', $_COOKIE[session_name()])) {
    session_start();
} elseif (isset($_COOKIE[session_name()])) {
    unset($_COOKIE[session_name()]);
    session_start(); 
} else {
    session_start(); 
}
$salt = "123";

//explode converte uma string num array, tendo por base um separador -> /
$partes=explode("/", substr($_SERVER["PATH_INFO"],1));
$msg="Link Incorreto";


$nomeDoModulo = $partes[0]."/".$partes[1]. ".php";
if ( file_exists($nomeDoModulo) ) {
    include $nomeDoModulo;
} 
echo json_encode($msg);
?>
