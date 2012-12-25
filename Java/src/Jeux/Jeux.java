package Jeux;

import java.awt.Color;

import IHM.IHM;

public class Jeux extends IHM {
	
	/////////////////////////Déclarations
	
	private Communication Com;
	//public static IHM Ihm;
	
	private String Serveur="127.0.0.1";
	private String IdPartie = "0";
	
	/////////////////////////Constructeur
	public Jeux(){
		Com = new Communication(this);
	}
	
	/////////////////////////Fonctions
	
	//Lors de l'appui sur le bouton JoueurIA
	public void joueurIA(String Adr, String IdPartie){
		Serveur = Adr;
		this.IdPartie = IdPartie;
		labNorth.setText("Connexion...");
		butIA.setEnabled(false);
		tfAdd.setEnabled(false);
		tfIdPartie.setEnabled(false);
		tfAdd.setBackground(new Color(0,0,0));
		tfIdPartie.setBackground(new Color(0,0,0));
		//Envoi requette pour création nouvelle partie contre joueur IA
		Com.reqJoueurIA();
	}
	
	//Lors de l'appui sur le bouton ValiderPlacement	
	public void ValiderPlacement(String BatCo1, String BatCo2){
		labNorth.setText("Placement de "+labPlacerBateau.getText()+" ...");
		tfBatCo1.setBackground(new Color(0,0,0));
		tfBatCo2.setBackground(new Color(0,0,0));
		butValiderPlacement.setEnabled(false);
		Com.reqValiderPlacement(labPlacerBateau.getText(),tfBatCo1.getText(),tfBatCo2.getText());
	}

	/////////////////////////////////////Accesseurs
	
	public String getServeur() {
		return Serveur;
	}

	public String getIdPartie() {
		return IdPartie;
	}
}
