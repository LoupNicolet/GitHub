package Jeux;

import java.awt.Color;

public class Actualiser {
	
	////////////////////////////////////////Constucteur
	public Actualiser(){
		
	}
	
	///////////////////////////////////////Fonctions
	
	//Actualise l'affichage / active,désactive des composants / Les Modifies
	public void actu(Xml xml, Thread thread){
		//Etat de la partie
		String etat = xml.getEtat();
		
		//Si JoueurIA et Création Nouvelle partie OK
		if(etat.equals("vrai")){
			Jeux.Ihm.labNorth.setForeground(new Color(255,0,0));
			Jeux.Ihm.labNorth.setText("Placez votre flotte, pour cela entrez les coordonnées des extrémitées de chaque bateau");
			Jeux.Ihm.labPlacerBateau.setText("Porte-Avion (5 case)");
			Jeux.Ihm.butValiderPlacement.setEnabled(true);
			Jeux.Ihm.tfBatCo1.setEnabled(true);
			Jeux.Ihm.tfBatCo2.setEnabled(true);
			Jeux.Ihm.tfBatCo1.setBackground(new Color(255,255,255));
			Jeux.Ihm.tfBatCo2.setBackground(new Color(255,255,255));
		}
		
		//Si JoueurIA et Création Nouvelle partie Pas OK
		if(etat.equals("faux")){
			Jeux.Ihm.labNorth.setForeground(new Color(255,0,0));
			Jeux.Ihm.labNorth.setText("Votre numéro de partie est déjà utilisé, veuillez en choisir un autre.");
			Jeux.Ihm.butIA.setEnabled(true);
			Jeux.Ihm.tfAdd.setEnabled(true);
			Jeux.Ihm.tfIdPartie.setEnabled(true);
			Jeux.Ihm.tfAdd.setBackground(new Color(255,255,255));
			Jeux.Ihm.tfIdPartie.setBackground(new Color(255,255,255));
			
		}
	}
}
