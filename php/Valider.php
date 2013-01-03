<?php
	if($_GET["action"] == "placement"){
		if($_GET['bateau'] == 'Porte-Avion (5 case)'){
			if(strlen($_GET['co1']) > 2){
				//Si c'est pas le bon format
				header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
			}else{
				if(strlen($_GET['co2']) > 2){
					//Si c'est pas le bon format
					header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
				}else
				{
					$TabCo1 = str_split($_GET['co1']);
					$TabCo2 = str_split($_GET['co2']);
					if($TabCo1[1] > 9 || $TabCo2[1] > 9){
						header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
					}else{
					
						if($TabCo1[0] == 'A'){$TabCo1[0] = 0;}
						
						else if($TabCo1[0] == 'B'){$TabCo1[0] = 1;}
						else if($TabCo1[0] == 'C'){$TabCo1[0] = 2;}
						else if($TabCo1[0] == 'D'){$TabCo1[0] = 3;}
						else if($TabCo1[0] == 'E'){$TabCo1[0] = 4;}
						else if($TabCo1[0] == 'F'){$TabCo1[0] = 5;}
						else if($TabCo1[0] == 'G'){$TabCo1[0] = 6;}
						else if($TabCo1[0] == 'H'){$TabCo1[0] = 7;}
						else if($TabCo1[0] == 'I'){$TabCo1[0] = 8;}
						else if($TabCo1[0] == 'J'){$TabCo1[0] = 9;}
						
						if($TabCo2[0] == 'A'){$TabCo2[0] = 0;}
						else if($TabCo2[0] == 'B'){$TabCo2[0] = 1;}
						else if($TabCo2[0] == 'C'){$TabCo2[0] = 2;}
						else if($TabCo2[0] == 'D'){$TabCo2[0] = 3;}
						else if($TabCo2[0] == 'E'){$TabCo2[0] = 4;}
						else if($TabCo2[0] == 'F'){$TabCo2[0] = 5;}
						else if($TabCo2[0] == 'G'){$TabCo2[0] = 6;}
						else if($TabCo2[0] == 'H'){$TabCo2[0] = 7;}
						else if($TabCo2[0] == 'I'){$TabCo2[0] = 8;}
						else if($TabCo2[0] == 'J'){$TabCo2[0] = 9;}
						echo $TabCo2[0];
						if($_GET["bateau"] == "Porte-Avion (5 case)"){$Case = 4;}
						if($TabCo1[0]+$Case == $TabCo2[0]){
							if($TabCo1[1] == $TabCo2[1]){
								//echo '1bonne case';
								header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&bateau='.$_GET['bateau'].'&co1='.$TabCo1[0].$TabCo1[1].'&co2='.$TabCo2[0].$TabCo2[1]);
							}else{
								//echo '2mauvaise case';
								header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
							}
						}else if($TabCo1[0]-$Case == $TabCo2[0]){
							if($TabCo1[1] == $TabCo2[1]){
								//echo '3bonne case';
								header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&bateau='.$_GET['bateau'].'&co1='.$TabCo1[0].$TabCo1[1].'&co2='.$TabCo2[0].$TabCo2[1]);
							}else{
								//echo '4mauvaise case';
								header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
							}
						}else if($TabCo1[0] == $TabCo2[0]){
							if($TabCo1[1]+$Case == $TabCo2[1]){
								//echo '5bonne case';
								header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&bateau='.$_GET['bateau'].'&co1='.$TabCo1[0].$TabCo1[1].'&co2='.$TabCo2[0].$TabCo2[1]);
							}else if($TabCo1[1]-$Case == $TabCo2[1]){
								//echo '5bonne case';
								header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&bateau='.$_GET['bateau'].'&co1='.$TabCo1[0].$TabCo1[1].'&co2='.$TabCo2[0].$TabCo2[1]);
							}else if($TabCo1[1] == $TabCo2[1]){
								//echo '5mauvaise case';
								header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
							}else{
								//echo '5mauvaise case';
								header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
							}
						}else{
							//echo '5mauvaise case';
							header('location:Gen_XML.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&valide=faux');
						}
						
						
						
						//header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&bateau='.$_GET['bateau'].'&co1='.$TabCo1[0].$TabCo1[1].'&co2='.$TabCo2[0].$TabCo2[1]);
					}
				}
			}
			
		}
	}
?>