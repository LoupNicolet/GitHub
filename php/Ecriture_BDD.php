<?php

$Bdd_host = "localhost";
$Bdd_bdd  = "bataille_navale";
$Bdd_user = "root";
$Bdd_pass = "";

try
	{
	    $bdd = new PDO('mysql:host='.$Bdd_host.';dbname='.$Bdd_bdd, $Bdd_user, $Bdd_pass);
	}
	catch(Exception $e)
	{
        die('Erreur : '.$e->getMessage());
    }

if($_GET["action"] == "joueurIA"){
	$reponse = $bdd->query('SELECT * FROM partie WHERE idpartie = '.$_GET["idpartie"]);
	$donnees = $reponse->fetch();
	$reponse->closeCursor();
	if($donnees == null){
		$bdd->exec('INSERT INTO partie(idpartie,type,nomjoueur) VALUES ('.$_GET["idpartie"].',"CIA","Joueur1")');
		$bdd->exec('CREATE TABLE '.$_GET["idpartie"].'_Joueur1 (ID INT not null, valeur INT not null , PRIMARY KEY (ID))');
		$bdd->exec('CREATE TABLE '.$_GET["idpartie"].'_Joueur2 (ID INT not null, valeur INT not null , PRIMARY KEY (ID))');
		for($i = 0 ; $i < 100 ; $i++){
			$bdd->exec('INSERT INTO '.$_GET["idpartie"].'_Joueur1 (ID,valeur) VALUES ('.$i.',0)');
			$bdd->exec('INSERT INTO '.$_GET["idpartie"].'_Joueur2 (ID,valeur) VALUES ('.$i.',0)');
		}
		header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=vrai');
	}else{
		header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
	}
}
else if($_GET["action"] == "placement"){
	if($_GET["bateau"] == "Porte-Avion (5 case)"){$boucle = 5;}
	//faire le chemin des case $case[]  A0 E0   B4 A2
	for($j = 0;$i<$boucle;$i++){
		
	}
	for($i = 0;$i<$boucle;$i++){
		$reponse = $bdd->query('SELECT valeur FROM '.$_GET['idpartie'].'_'.$_GET['nom'].' WHERE ID = '.$case[$i]);
		$donnees = $reponse->fetch();
		$reponse->closeCursor();
		if($donnees == '0'){
			//Modifer la case sur 1
		}else{
			//Renvoi mauvais
		}
	}
}
?>