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
		if($_GET["bateau"] == "Porte-Avion(5case)"){$boucle = 5;}
		//faire le chemin des case $case[]  A0 E0   B4 A2
		$TabCo1 = str_split($_GET['co1']); //02
		$TabCo2 = str_split($_GET['co2']); //42
		$Chemin = $TabCo1[0].$TabCo1[1];
		for($j = 0;$j<$boucle-2;$j++){
			if($TabCo1[0] != $TabCo2[0]){
				$TabCo1[0]++;
				$Res = $TabCo1[0].$TabCo1[1];
			}else{
				if($TabCo1[1] != $TabCo2[1]){
					$TabCo1[1]++;
					$Res = $TabCo1[0].$TabCo1[1];
				}
			}
			$Chemin = $Chemin.$Res;
		}
		$Chemin = $Chemin.$TabCo2[0].$TabCo2[1];
		$Split_Chemin = str_split($Chemin);
		for($i = 1;$i<($boucle+1);$i++){
			$reponse = $bdd->query('SELECT valeur FROM '.$_GET['idpartie'].'_'.$_GET['nom'].' WHERE ID = '.$Split_Chemin[($i*2)-2].$Split_Chemin[($i*2)-1]);
			$donnees = $reponse->fetch();
			$reponse->closeCursor();
			if($donnees[0] == '0'){
				$bdd->query('UPDATE '.$_GET['idpartie'].'_'.$_GET['nom'].' SET valeur="1" WHERE ID='.$Split_Chemin[($i*2)-2].$Split_Chemin[($i*2)-1]);
			}else{
				header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
			}
		}
		header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=vrai');			
	}
?>