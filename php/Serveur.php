<?php
if(isset($_GET["action"])){$Action = $_GET["action"];}else{$Action = "";}
if(isset($_GET["idpartie"])){$IdPartie = $_GET["idpartie"];}else{$IdPartie = "";}
if(isset($_GET["valide"])){$Valide = $_GET["valide"];}else{$Valide = "";}

if($Action == "joueurIA"){
	 header('location:Ecriture_BDD.php?action='.$Action.'&idpartie='.$IdPartie);
}
else if($Action == "RetJoueurIA"){
		header("Content-type:text/xml");
		$xml = simplexml_load_file("XML.xml");
		echo $xml->asXml();
}
?>