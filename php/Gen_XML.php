<?php

$Bdd_host = "localhost";
$Bdd_bdd  = "bataille_navale";
$Bdd_user = "root";
$Bdd_pass = "";

try{
	$bdd = new PDO('mysql:host='.$Bdd_host.';dbname='.$Bdd_bdd, $Bdd_user, $Bdd_pass);
}
catch(Exception $e){
	die('Erreur : '.$e->getMessage());
}

if(isset($_GET["action"])){$Action = $_GET["action"];}else{$Action = "";}
if(isset($_GET["idpartie"])){$IdPartie = $_GET["idpartie"];}else{$IdPartie = "";}
if(isset($_GET["valide"])){$Valide = $_GET["valide"];}else{$Valide = "";}

if($Action == "joueurIA"){
	if($Valide == "vrai"){
		$etat="vrai";
		$Nom = 'joueur1';
		$Tour = 0;
	}else if($Valide == "faux"){
		$etat = "faux";
		$Nom = 'joueur1';
		$Tour = 0;
	}
	for($i = 0 ; $i < 100 ; $i++){
	 
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur1 WHERE ID = '.$i);
	  $donnees = $reponse->fetch();
	  $CaseFlotte[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	   
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur2 WHERE ID = '.$i);
	  $donnees= $reponse->fetch();
	  $CaseTactique[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	}
}

if($Action == "placement"){
	if($Valide == "vrai"){
		$etat = "vraiPlacement";
		$Nom = 'joueur1';
		$Tour = 0;
	}else if($Valide == "faux"){
		$etat = "fauxPlacement";
		$Nom = 'joueur1';
		$Tour = 0;
	}
	for($i = 0 ; $i < 100 ; $i++){
	 
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur1 WHERE ID = '.$i);
	  $donnees = $reponse->fetch();
	  $CaseFlotte[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	   
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur2 WHERE ID = '.$i);
	  $donnees= $reponse->fetch();
	  $CaseTactique[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	}
}

if($Action == "attente"){
		$etat = "pres";
		$Nom = $_GET['nom'];
		$Tour = $_GET['tour'];
	for($i = 0 ; $i < 100 ; $i++){
	 
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur1 WHERE ID = '.$i);
	  $donnees = $reponse->fetch();
	  $CaseFlotte[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	   
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur2 WHERE ID = '.$i);
	  $donnees= $reponse->fetch();
	  $CaseTactique[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	}
}

if($Action == "joue"){
		if($Valide == "Win"){
			$etat = "joueWin";
		}else{
			$etat = "joue";
		}
		$Nom = $_GET['nom'];
		$Tour = $_GET['tour'];
	for($i = 0 ; $i < 100 ; $i++){
	 
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur1 WHERE ID = '.$i);
	  $donnees = $reponse->fetch();
	  $CaseFlotte[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	   
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur2 WHERE ID = '.$i);
	  $donnees= $reponse->fetch();
	  $CaseTactique[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	}
}

if($Action == "attentejeu"){
		if($Valide == "Win"){
			$etat = "finWin";
		}else{
			$etat = "fin";
		}
		$Nom = $_GET['nom'];
		$Tour = $_GET['tour'];
	for($i = 0 ; $i < 100 ; $i++){
	 
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur1 WHERE ID = '.$i);
	  $donnees = $reponse->fetch();
	  $CaseFlotte[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	   
	  $reponse = $bdd->query('SELECT valeur FROM '.$IdPartie.'_joueur2 WHERE ID = '.$i);
	  $donnees= $reponse->fetch();
	  $CaseTactique[$i] = $donnees['valeur'];
	  $reponse->closeCursor();
	}
}

$xml = <<<XML
<?xml version="1.0" encoding="utf-8" ?>
<partie id='$IdPartie'>
	<nomjoueur>$Nom</nomjoueur>
	<tour>$Tour</tour>
	<etat>$etat</etat>
	<plateau>
		<flotte>
			<case id="00">$CaseFlotte[0]</case>
			<case id="01">$CaseFlotte[1]</case>
			<case id="02">$CaseFlotte[2]</case>
			<case id="03">$CaseFlotte[3]</case>
			<case id="04">$CaseFlotte[4]</case>
			<case id="05">$CaseFlotte[5]</case>
			<case id="06">$CaseFlotte[6]</case>
			<case id="07">$CaseFlotte[7]</case>
			<case id="08">$CaseFlotte[8]</case>
			<case id="09">$CaseFlotte[9]</case>
			<case id="10">$CaseFlotte[10]</case>
			<case id="11">$CaseFlotte[11]</case>
			<case id="12">$CaseFlotte[12]</case>
			<case id="13">$CaseFlotte[13]</case>
			<case id="14">$CaseFlotte[14]</case>
			<case id="15">$CaseFlotte[15]</case>
			<case id="16">$CaseFlotte[16]</case>
			<case id="17">$CaseFlotte[17]</case>
			<case id="18">$CaseFlotte[18]</case>
			<case id="19">$CaseFlotte[19]</case>
			<case id="20">$CaseFlotte[20]</case>
			<case id="21">$CaseFlotte[21]</case>
			<case id="22">$CaseFlotte[22]</case>
			<case id="23">$CaseFlotte[23]</case>
			<case id="24">$CaseFlotte[24]</case>
			<case id="25">$CaseFlotte[25]</case>
			<case id="26">$CaseFlotte[26]</case>
			<case id="27">$CaseFlotte[27]</case>
			<case id="28">$CaseFlotte[28]</case>
			<case id="29">$CaseFlotte[29]</case>
			<case id="30">$CaseFlotte[30]</case>
			<case id="31">$CaseFlotte[31]</case>
			<case id="32">$CaseFlotte[32]</case>
			<case id="33">$CaseFlotte[33]</case>
			<case id="34">$CaseFlotte[34]</case>
			<case id="35">$CaseFlotte[35]</case>
			<case id="36">$CaseFlotte[36]</case>
			<case id="37">$CaseFlotte[37]</case>
			<case id="38">$CaseFlotte[38]</case>
			<case id="39">$CaseFlotte[39]</case>
			<case id="40">$CaseFlotte[40]</case>
			<case id="41">$CaseFlotte[41]</case>
			<case id="42">$CaseFlotte[42]</case>
			<case id="43">$CaseFlotte[43]</case>
			<case id="44">$CaseFlotte[44]</case>
			<case id="45">$CaseFlotte[45]</case>
			<case id="46">$CaseFlotte[46]</case>
			<case id="47">$CaseFlotte[47]</case>
			<case id="48">$CaseFlotte[48]</case>
			<case id="49">$CaseFlotte[49]</case>
			<case id="50">$CaseFlotte[50]</case>
			<case id="51">$CaseFlotte[51]</case>
			<case id="52">$CaseFlotte[52]</case>
			<case id="53">$CaseFlotte[53]</case>
			<case id="54">$CaseFlotte[54]</case>
			<case id="55">$CaseFlotte[55]</case>
			<case id="56">$CaseFlotte[56]</case>
			<case id="57">$CaseFlotte[57]</case>
			<case id="58">$CaseFlotte[58]</case>
			<case id="59">$CaseFlotte[59]</case>
			<case id="60">$CaseFlotte[60]</case>
			<case id="61">$CaseFlotte[61]</case>
			<case id="62">$CaseFlotte[62]</case>
			<case id="63">$CaseFlotte[63]</case>
			<case id="64">$CaseFlotte[64]</case>
			<case id="65">$CaseFlotte[65]</case>
			<case id="66">$CaseFlotte[66]</case>
			<case id="67">$CaseFlotte[67]</case>
			<case id="68">$CaseFlotte[68]</case>
			<case id="69">$CaseFlotte[69]</case>
			<case id="70">$CaseFlotte[70]</case>
			<case id="71">$CaseFlotte[71]</case>
			<case id="72">$CaseFlotte[72]</case>
			<case id="73">$CaseFlotte[73]</case>
			<case id="74">$CaseFlotte[74]</case>
			<case id="75">$CaseFlotte[75]</case>
			<case id="76">$CaseFlotte[76]</case>
			<case id="77">$CaseFlotte[77]</case>
			<case id="78">$CaseFlotte[78]</case>
			<case id="79">$CaseFlotte[79]</case>
			<case id="80">$CaseFlotte[80]</case>
			<case id="81">$CaseFlotte[81]</case>
			<case id="82">$CaseFlotte[82]</case>
			<case id="83">$CaseFlotte[83]</case>
			<case id="84">$CaseFlotte[84]</case>
			<case id="85">$CaseFlotte[85]</case>
			<case id="86">$CaseFlotte[86]</case>
			<case id="87">$CaseFlotte[87]</case>
			<case id="88">$CaseFlotte[88]</case>
			<case id="89">$CaseFlotte[89]</case>
			<case id="90">$CaseFlotte[90]</case>
			<case id="91">$CaseFlotte[91]</case>
			<case id="92">$CaseFlotte[92]</case>
			<case id="93">$CaseFlotte[93]</case>
			<case id="94">$CaseFlotte[94]</case>
			<case id="95">$CaseFlotte[95]</case>
			<case id="96">$CaseFlotte[96]</case>
			<case id="97">$CaseFlotte[97]</case>
			<case id="98">$CaseFlotte[98]</case>
			<case id="99">$CaseFlotte[99]</case>
		</flotte>
		<cartetactique>
			<case id="00">$CaseTactique[0]</case>
			<case id="01">$CaseTactique[1]</case>
			<case id="02">$CaseTactique[2]</case>
			<case id="03">$CaseTactique[3]</case>
			<case id="04">$CaseTactique[4]</case>
			<case id="05">$CaseTactique[5]</case>
			<case id="06">$CaseTactique[6]</case>
			<case id="07">$CaseTactique[7]</case>
			<case id="08">$CaseTactique[8]</case>
			<case id="09">$CaseTactique[9]</case>
			<case id="10">$CaseTactique[10]</case>
			<case id="11">$CaseTactique[11]</case>
			<case id="12">$CaseTactique[12]</case>
			<case id="13">$CaseTactique[13]</case>
			<case id="14">$CaseTactique[14]</case>
			<case id="15">$CaseTactique[15]</case>
			<case id="16">$CaseTactique[16]</case>
			<case id="17">$CaseTactique[17]</case>
			<case id="18">$CaseTactique[18]</case>
			<case id="19">$CaseTactique[19]</case>
			<case id="20">$CaseTactique[20]</case>
			<case id="21">$CaseTactique[21]</case>
			<case id="22">$CaseTactique[22]</case>
			<case id="23">$CaseTactique[23]</case>
			<case id="24">$CaseTactique[24]</case>
			<case id="25">$CaseTactique[25]</case>
			<case id="26">$CaseTactique[26]</case>
			<case id="27">$CaseTactique[27]</case>
			<case id="28">$CaseTactique[28]</case>
			<case id="29">$CaseTactique[29]</case>
			<case id="30">$CaseTactique[30]</case>
			<case id="31">$CaseTactique[31]</case>
			<case id="32">$CaseTactique[32]</case>
			<case id="33">$CaseTactique[33]</case>
			<case id="34">$CaseTactique[34]</case>
			<case id="35">$CaseTactique[35]</case>
			<case id="36">$CaseTactique[36]</case>
			<case id="37">$CaseTactique[37]</case>
			<case id="38">$CaseTactique[38]</case>
			<case id="39">$CaseTactique[39]</case>
			<case id="40">$CaseTactique[40]</case>
			<case id="41">$CaseTactique[41]</case>
			<case id="42">$CaseTactique[42]</case>
			<case id="43">$CaseTactique[43]</case>
			<case id="44">$CaseTactique[44]</case>
			<case id="45">$CaseTactique[45]</case>
			<case id="46">$CaseTactique[46]</case>
			<case id="47">$CaseTactique[47]</case>
			<case id="48">$CaseTactique[48]</case>
			<case id="49">$CaseTactique[49]</case>
			<case id="50">$CaseTactique[50]</case>
			<case id="51">$CaseTactique[51]</case>
			<case id="52">$CaseTactique[52]</case>
			<case id="53">$CaseTactique[53]</case>
			<case id="54">$CaseTactique[54]</case>
			<case id="55">$CaseTactique[55]</case>
			<case id="56">$CaseTactique[56]</case>
			<case id="57">$CaseTactique[57]</case>
			<case id="58">$CaseTactique[58]</case>
			<case id="59">$CaseTactique[59]</case>
			<case id="60">$CaseTactique[60]</case>
			<case id="61">$CaseTactique[61]</case>
			<case id="62">$CaseTactique[62]</case>
			<case id="63">$CaseTactique[63]</case>
			<case id="64">$CaseTactique[64]</case>
			<case id="65">$CaseTactique[65]</case>
			<case id="66">$CaseTactique[66]</case>
			<case id="67">$CaseTactique[67]</case>
			<case id="68">$CaseTactique[68]</case>
			<case id="69">$CaseTactique[69]</case>
			<case id="70">$CaseTactique[70]</case>
			<case id="71">$CaseTactique[71]</case>
			<case id="72">$CaseTactique[72]</case>
			<case id="73">$CaseTactique[73]</case>
			<case id="74">$CaseTactique[74]</case>
			<case id="75">$CaseTactique[75]</case>
			<case id="76">$CaseTactique[76]</case>
			<case id="77">$CaseTactique[77]</case>
			<case id="78">$CaseTactique[78]</case>
			<case id="79">$CaseTactique[79]</case>
			<case id="80">$CaseTactique[80]</case>
			<case id="81">$CaseTactique[81]</case>
			<case id="82">$CaseTactique[82]</case>
			<case id="83">$CaseTactique[83]</case>
			<case id="84">$CaseTactique[84]</case>
			<case id="85">$CaseTactique[85]</case>
			<case id="86">$CaseTactique[86]</case>
			<case id="87">$CaseTactique[87]</case>
			<case id="88">$CaseTactique[88]</case>
			<case id="89">$CaseTactique[89]</case>
			<case id="90">$CaseTactique[90]</case>
			<case id="91">$CaseTactique[91]</case>
			<case id="92">$CaseTactique[92]</case>
			<case id="93">$CaseTactique[93]</case>
			<case id="94">$CaseTactique[94]</case>
			<case id="95">$CaseTactique[95]</case>
			<case id="96">$CaseTactique[96]</case>
			<case id="97">$CaseTactique[97]</case>
			<case id="98">$CaseTactique[98]</case>
			<case id="99">$CaseTactique[99]</case>
		</cartetactique>
	</plateau>
</partie>       
XML;

$Partie = new SimpleXMLElement($xml);

if($Action == "joueurIA"){
	$Action = 'RetJoueurIA';
	header('location:Serveur.php?action='.$Action);
}
else if($Action == "placement"){
	$Action = 'Retplacement';
	header('location:Serveur.php?action='.$Action);
}
else if($Action == "attente"){
	$Action = 'Retattente';
	header('location:Serveur.php?action='.$Action);
}
else if($Action == "joue"){
	$Action = 'Retjoue';
	header('location:Serveur.php?action='.$Action);	
}
else if($Action == "attentejeu"){
	$Action = 'Retattentejeu';
	header('location:Serveur.php?action='.$Action);
}

$File = fopen("XML.xml", 'w+');
fputs($File, $Partie->asXML());
fclose($File);
?>