<?php
	if($_GET["action"] == "placement"){
		if($_GET['bateau'] == 'Porte-Avion (5 case)'){
			//faire la verification
			header('location:Ecriture_BDD.php?action='.$_GET["action"].'&idpartie='.$_GET["idpartie"].'&nom='.$_GET['nom'].'&bateau='.$_GET['bateau'].'&co1'.$_GET['co1'].'&co2'.$_GET['co2']);
		}
	}
?>