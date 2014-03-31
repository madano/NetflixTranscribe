<?php
	exec('python amt_connect.py', $output);

	$con = mysqli_connect("localhost","USERNAME","PASSWORD","DATABASE");
	if (mysqli_connect_errno())
	{
		echo "gethits.php COULD NOT CONNECT TO MYSQL: ".mysqli_connect_error()."<br /><br />";
	}

	foreach ($output as $line)
	{
		preg_match("/^(\d+)/", $line, $time);
		$transc = preg_replace("/^\d+\s*/", "", $line);
		mysqli_query($con,"INSERT INTO `hits`(`start_time`, `result`) VALUES ('$time[1]', '$transc')");
	}

	mysqli_close($con);
?>