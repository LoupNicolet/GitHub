<?php
//if(isset($_GET["valide"])){$Valide = $_GET["valide"];}else{$Valide = "";}

if($_GET["action"] == "joueurIA"){
	 header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"]);
	 
}
else if($_GET["action"] == "RetJoueurIA"){
		header("Content-type:text/xml");
		$xml = simplexml_load_file("XML.xml");
		echo $xml->asXml();
}
else if($_GET["action"] == "placement"){
	header('location:Valider.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&bateau='.$_GET['bateau'].'&co1='.$_GET['co1'].'&co2='.$_GET['co2']);	
}
else if($_GET["action"] == "Retplacement"){
		header("Content-type:text/xml");
		$xml = simplexml_load_file("XML.xml");
		echo $xml->asXml();
}
else if($_GET["action"] == "attente"){
		 header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"]);
}
else if($_GET["action"] == "Retattente"){
		header("Content-type:text/xml");
		$xml = simplexml_load_file("XML.xml");
		echo $xml->asXml();
}
else if($_GET["action"] == "joue"){
		header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&tour='.$_GET['tour'].'&co='.$_GET['co']);	
}
else if($_GET["action"] == "Retjoue"){
		header("Content-type:text/xml");
		$xml = simplexml_load_file("XML.xml");
		echo $xml->asXml();
}
else if($_GET["action"] == "attente"){
		header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&tour='.$_GET['tour'];	
}
?>