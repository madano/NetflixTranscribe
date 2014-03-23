<!DOCTYPE html>
<html>
<body>

<h1>My First Heading</h1>

<p>My first paragraph.</p>
<?php
putenv('PYTHONPATH = /home/ugrads/m/madano/web_project/Python27/Lib/site-packages');
$output = shell_exec('python amt_connect.py');
echo $output;
$fh = fopen('test.txt','r');
while ($line = fgets($fh))
{
	echo $line;
	echo nl2br("\n");

}
fclose($fh);
echo "PASSED";
?>

</body>
</html>