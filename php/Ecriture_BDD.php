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
		else if($_GET["bateau"] == "Cuirasse(4case)"){$boucle = 4;}
		else if($_GET["bateau"] == "Destroyer(3case)"){$boucle = 3;}
		else if($_GET["bateau"] == "Sous-Marin(3case)"){$boucle = 3;}
		else if($_GET["bateau"] == "Torpilleur(2case)"){$boucle = 2;}
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
			//echo $donnees[0];
			if($donnees[0]){
				header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
				exit();
			}
			$reponse->closeCursor();
		}
		for($i = 1;$i<($boucle+1);$i++){
			$bdd->query('UPDATE '.$_GET['idpartie'].'_'.$_GET['nom'].' SET valeur="1" WHERE ID='.$Split_Chemin[($i*2)-2].$Split_Chemin[($i*2)-1]);
		}
		header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=vrai');			
	}
	
	else if($_GET["action"] == "attente"){
		
		if($_GET["tour"] == 0){
		if($_GET["nom"] == "joueur1"){
			$nom = "joueur2";
		}else{
			$nom = "joueur1";
		}
		echo "salut";
			for($a=0;$a<5;$a++){
				if($a == 0){$boucle = 5;}
				else if($a == 1){$boucle = 4;}
				else if($a == 2){$boucle = 3;}
				else if($a == 3){$boucle = 3;}
				else if($a == 4){$boucle = 2;}
				//faire le chemin des case $case[]  A0 E0   B4 A2
				
				$col = rand(0,9);
				$ver = rand(0,9);
				$sens = rand(0,1);
				$verR = $ver;
				$colV = $col;
				$Chemin = $ver.$col;
				for($j = 0;$j<$boucle-1;$j++){
					if($sens == 1){
						if($verR < ($boucle+1)){
							$ver++;
						}else{
							$ver--;
						}
						$Res = $ver.$col;
					}else{
						if($colV < ($boucle+1)){
							$col++;
						}else{
							$col--;
						}
						$Res = $ver.$col;
					}
					$Chemin = $Chemin.$Res;
				}
				$Split_Chemin = str_split($Chemin);
				$faux = false;
				for($i = 1;$i<($boucle+1);$i++){
					//echo "i:".$i."a:".$a."faux:".$faux."<br>";
					$reponse = $bdd->query('SELECT valeur FROM '.$_GET['idpartie'].'_'.$nom.' WHERE ID = '.$Split_Chemin[($i*2)-2].$Split_Chemin[($i*2)-1]);
					$donnees = $reponse->fetch();
					//echo $donnees[0];
					if($donnees[0]){
						$faux = true;
						
						/*header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&valide=faux');
						exit();*/
					}
					$reponse->closeCursor();
				}
				if(!$faux){
					for($i = 1;$i<($boucle+1);$i++){
						$bdd->query('UPDATE '.$_GET['idpartie'].'_'.$nom.' SET valeur="1" WHERE ID='.$Split_Chemin[($i*2)-2].$Split_Chemin[($i*2)-1]);
					}
				}else{
					$a--;
				}
				//header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=vrai');
			}
			header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"].'&valide=true');
			exit();
		}else{
		//echo "au revoir";
		}
	}
	
	else if($_GET["action"] == "joue"){
		if($_GET["nom"] == "joueur1"){
			$nom = "joueur2";
		}else{
			$nom = "joueur1";
		}
		$Split_Case = str_split($_GET["co"]);
		$reponse = $bdd->query('SELECT valeur FROM '.$_GET['idpartie'].'_'.$nom.' WHERE ID = '.$Split_Case[0].$Split_Case[1]);
		$donnees = $reponse->fetch();
		if($donnees[0] == 0){
			$bdd->query('UPDATE '.$_GET['idpartie'].'_'.$nom.' SET valeur="2" WHERE ID='.$Split_Case[0].$Split_Case[1]);
			header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"].'&valide=true');
		}else if($donnees[0] == 1){
			$bdd->query('UPDATE '.$_GET['idpartie'].'_'.$nom.' SET valeur="3" WHERE ID='.$Split_Case[0].$Split_Case[1]);
			header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"].'&valide=true');
		}else if($donnees[0] == 2){
			header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"].'&valide=false');
		}else{
			header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"].'&valide=true');
		}		
	}
	
	else if($_GET["action"] == "attentejeu"){
		$co = rand(0,99);
		$Split_Case = str_split($co);
		$reponse = $bdd->query('SELECT valeur FROM '.$_GET['idpartie'].'_'.$_GET["nom"].' WHERE ID = '.$Split_Case[0].$Split_Case[1]);
		$donnees = $reponse->fetch();
		if($donnees[0] == 0){
			$bdd->query('UPDATE '.$_GET['idpartie'].'_'.$_GET["nom"].' SET valeur="2" WHERE ID='.$Split_Case[0].$Split_Case[1]);
			header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"].'&valide=true');
		}else if($donnees[0] == 1){
			$bdd->query('UPDATE '.$_GET['idpartie'].'_'.$_GET["nom"].' SET valeur="3" WHERE ID='.$Split_Case[0].$Split_Case[1]);
			header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&tour='.$_GET["tour"].'&nom='.$_GET["nom"].'&valide=true');
		}else if($donnees[0] == 2){
			header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&tour='.$_GET['tour']);	
		}else{
			header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&tour='.$_GET['tour']);	
		}	
	}
?>