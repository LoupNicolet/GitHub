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
	
	
if(isset($_GET["action"])){
	$Action = $_GET["action"];
}else{
	$Action = "";
}

if(isset($_GET["idpartie"])){
	$IdPartie = $_GET["idpartie"];
}else{
	$IdPartie = "";
}

if($Action == "joueurIA"){
	$reponse = $bdd->query('SELECT * FROM partie WHERE idpartie = '.$IdPartie);
	$donnees = $reponse->fetch();
	$reponse->closeCursor();
	if($donnees == null){
		$bdd->exec('INSERT INTO partie(idpartie,type,nomjoueur) VALUES ('.$IdPartie.',"CIA","Joueur1")');
		$bdd->exec('CREATE TABLE '.$IdPartie.'_Joueur1 (ID INT not null, valeur INT not null , PRIMARY KEY (ID))');
		$bdd->exec('CREATE TABLE '.$IdPartie.'_Joueur2 (ID INT not null, valeur INT not null , PRIMARY KEY (ID))');
		for($i = 0 ; $i < 100 ; $i++){
			$bdd->exec('INSERT INTO '.$IdPartie.'_Joueur1 (ID,valeur) VALUES ('.$i.',0)');
			$bdd->exec('INSERT INTO '.$IdPartie.'_Joueur2 (ID,valeur) VALUES ('.$i.',0)');
		}
		header('location:Gen_XML.php?action='.$Action.'&idpartie='.$IdPartie.'&valide=vrai');
		//$Action = 'RetJoueurIA';
		//header('location:Serveur.php?action='.$Action.'&valide=vrai');
	}else{
		header('location:Gen_XML.php?action='.$Action.'&idpartie='.$IdPartie.'&valide=faux');
		//$Action = 'RetJoueurIA';
		//header('location:Serveur.php?action='.$Action'&valide=faux');
	}
}

?>