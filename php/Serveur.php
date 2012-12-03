<?php
/*if(isset($_GET["a"])){
	$a = $_GET["a"];
}else{
	$a = "";
}

if($a = ""){*/
	header("Content-type:text/xml");
	$xml = simplexml_load_file("XML.xml");
	echo $xml->asXml();
//}
?>