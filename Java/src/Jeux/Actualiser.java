package Jeux;

import java.awt.Color;

import IHM.IHM;

public class Actualiser extends IHM{
	
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
			labNorth.setForeground(new Color(255,0,0));
			labNorth.setText("Placez votre flotte, pour cela entrez les coordonnées des extrémitées de chaque bateau");
			labPlacerBateau.setText("Porte-Avion (5 case)");
			butValiderPlacement.setEnabled(true);
			tfBatCo1.setEnabled(true);
			tfBatCo2.setEnabled(true);
			tfBatCo1.setBackground(new Color(255,255,255));
			tfBatCo2.setBackground(new Color(255,255,255));
		}
		
		//Si JoueurIA et Création Nouvelle partie Pas OK
		if(etat.equals("faux")){
			labNorth.setForeground(new Color(255,0,0));
			labNorth.setText("Votre numéro de partie est déjà utilisé, veuillez en choisir un autre.");
			butIA.setEnabled(true);
			tfAdd.setEnabled(true);
			tfIdPartie.setEnabled(true);
			tfAdd.setBackground(new Color(255,255,255));
			tfIdPartie.setBackground(new Color(255,255,255));
			
		}
	}
}
