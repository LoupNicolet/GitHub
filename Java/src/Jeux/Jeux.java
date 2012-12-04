package Jeux;

import java.awt.Color;

import IHM.IHM;

public class Jeux {
	
	/////////////////////////Déclarations
	
	private Communication Com;
	public static IHM Ihm;
	
	private String Serveur="127.0.0.1";
	private String IdPartie = "0";
	
	/////////////////////////Constructeur
	public Jeux(IHM Ihm){
		Com = new Communication(this);
		this.Ihm = Ihm;
	}
	
	/////////////////////////Fonctions
	
	//Lors de l'appui sur le bouton JoueurIA
	public void joueurIA(String Adr, String IdPartie){
		Serveur = Adr;
		this.IdPartie = IdPartie;
		Ihm.labNorth.setText("Connexion...");
		Ihm.butIA.setEnabled(false);
		Ihm.tfAdd.setEnabled(false);
		Ihm.tfIdPartie.setEnabled(false);
		Ihm.tfAdd.setBackground(new Color(0,0,0));
		Ihm.tfIdPartie.setBackground(new Color(0,0,0));
		//Envoi requette pour création nouvelle partie contre joueur IA
		Com.reqJoueurIA();
	}

	/////////////////////////////////////Accesseurs
	
	public String getServeur() {
		return Serveur;
	}

	public String getIdPartie() {
		return IdPartie;
	}
}
