<?php

$sitename = "https://192.168.242.1//login";
$hostname = "localhost";

$Host_username = "root";
$Host_password = "usbw";

$database_name = "login";


$con=mysqli_connect($hostname,$Host_username,$Host_password,$database_name);
//mysql_select_db("login",$con);
if (mysqli_connect_errno())
    echo "connect2DB Field: " . mysqli_connect_error();

$response["travel"]=array();
$result=mysqli_query($con,"SELECT * FROM travel");

if(mysqli_num_rows($result)>0){
	while($row=mysqli_fetch_array($result)){

	$temp=array();
	$temp["companyname"]=$row["companyname"];
	$temp["cod"]=$row["cod"];
	$temp["begn"]=$row["begn"];
	$temp["stop"]=$row["stop"];
	$temp["date_f"]=$row["date_f"];
	$temp["time_f"]=$row["time_f"];
	$temp["price"]=$row["price"];
	array_push($response["travel"],$temp);
	}
	
	
	$response["t"]=1;
	echo json_encode($response);
	

	}else{
		$response["t"]=0;
		$response["message"]="not fond";
		echo json_encode($response);
	}
?>