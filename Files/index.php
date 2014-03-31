<?php
	include 'gethits.php';

	$con = mysqli_connect("localhost","USERNAME","PASSWORD","DATABASE");

	if (mysqli_connect_errno())
	{
		echo "index.php COULD NOT CONNECT TO MYSQL: ".mysqli_connect_error()."<br /><br />";
	}

	$hits = mysqli_query($con, "SELECT * FROM hits ORDER BY start_time DESC");
?>

<!DOCTYPE html>

<html>

<head>
	<title>TransCryptors</title>
	<link rel="stylesheet" type="text/css" href="twocolumn.css">
</head>

<body>
	<div id="outerbox">
		<div id="head">
			<div class="spacer">
				<h1>TransCryptors: 438 Crowdsourcing Project</h1>
			</div>
		</div>
		<div id="columnsbox">
			<div id="leftcolumn">
				<div class="spacer">
					<br />
					<b>Ferris Bueller's Day Off Dialog Transcript</b><br />
					<br />
					From <a href="http://www.script-o-rama.com/movie_scripts/f/ferris-buellers-day-off-script.html" target="_blank">script-o-rama.com</a>.<br />
					Modified from original form as needed.<br />
					Presented under <a href="http://www.copyright.gov/title17/92chap1.html#107" target="_blank">fair use</a> for educational and research purposes.<br />
					<br /><hr>
					<?
						//$script_array = file("fbScript.txt");
						$script_array = file("fbTranscript.txt");
						foreach($script_array as $line)
						{
							//if (preg_match("/^\s{12}|^\s*$/", $line) and !(preg_match("/^\s*\(.+\)\s*$/", $line)))
							echo "<br />".$line;
						}
					?>
				</div>
			</div>
			<div id="rightcolumn">
				<div class="spacer">
					<br />
					<b>Crowdsourced Transcription Results</b><br />
					<br />
					Check out our <a href="https://github.com/madano/NetflixTranscribe" target="_blank">GitHub repo</a>!<br />
					Part of a <a href="http://courses.cse.tamu.edu/caverlee/csce438/hw/hw3.html" target="_blank">project</a> for <a href="http://courses.cse.tamu.edu/caverlee/csce438/" target="_blank">CSCE 438</a> at Texas A&M University.<br />
					Crowdsourced via <a href="https://www.mturk.com/" target="_blank">Amazon Mechanical Turk</a>.<br />
					<br /><hr>------------------------------------------------------------------<br /><br />
					<?
						while ($hit = mysqli_fetch_array($hits))
						{
							//echo $hit['start_time']."<br />";
							echo $hit['result']."<br /><br />------------------------------------------------------------------<br /><br />";
						}

						mysqli_close($con);
					?>
				</div>
			</div>
		</div>
	</div>
</body>

</html>