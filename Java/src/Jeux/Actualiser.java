package Jeux;

import java.awt.Color;

import IHM.IHM;

public class Actualiser{
	
	private IHM Ihm;
	
	////////////////////////////////////////Constucteur
	public Actualiser(IHM Ihm){
		this.Ihm = Ihm;
	}
	
	///////////////////////////////////////Fonctions
	
	//Actualise l'affichage / active,d�sactive des composants / Les Modifies
	public void actu(Xml xml, Thread thread){
		//Etat de la partie
		String etat = xml.getEtat();
		
		//Si JoueurIA et Cr�ation Nouvelle partie OK
		if(etat.equals("vrai")){
			Ihm.labNorth.setForeground(new Color(255,0,0));
			Ihm.labNorth.setText("Placez votre flotte, pour cela entrez les coordonn�es des extr�mit�es de chaque bateau");
			Ihm.labPlacerBateau.setText("Porte-Avion (5 case)");
			Ihm.butValiderPlacement.setEnabled(true);
			Ihm.tfBatCo1.setEnabled(true);
			Ihm.tfBatCo2.setEnabled(true);
			Ihm.tfBatCo1.setBackground(new Color(255,255,255));
			Ihm.tfBatCo2.setBackground(new Color(255,255,255));
		}
		
		//Si JoueurIA et Cr�ation Nouvelle partie Pas OK
		if(etat.equals("faux")){
			Ihm.labNorth.setForeground(new Color(255,0,0));
			Ihm.labNorth.setText("Votre num�ro de partie est d�j� utilis�, veuillez en choisir un autre.");
			Ihm.butIA.setEnabled(true);
			Ihm.tfAdd.setEnabled(true);
			Ihm.tfIdPartie.setEnabled(true);
			Ihm.tfAdd.setBackground(new Color(255,255,255));
			Ihm.tfIdPartie.setBackground(new Color(255,255,255));
			
		}
	}
}
