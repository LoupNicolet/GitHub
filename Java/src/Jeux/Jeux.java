package Jeux;

import java.awt.Color;

import IHM.IHM;

public class Jeux {
	
	/////////////////////////Déclarations
	
	public Communication Com;
	public IHM Ihm;
	
	private String Serveur="127.0.0.1";
	private String IdPartie = "0";
	
	/////////////////////////Constructeur
	public Jeux(IHM Ihm){
		this.Ihm = Ihm;
		Com = new Communication(this, Ihm);
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
		System.out.println("Partie Joueur IA");
		Com.reqJoueurIA();
	}
	
	//Lors de l'appui sur le bouton ValiderPlacement	
	public void ValiderPlacement(String BatCo1, String BatCo2){
		Ihm.tfBatCo1.setBackground(new Color(0,0,0));
		Ihm.tfBatCo2.setBackground(new Color(0,0,0));
		Ihm.butValiderPlacement.setEnabled(false);
		if(Ihm.labNorth.getText().equals("Jouez")){
			Ihm.labNorth.setText("Attente Resultat ...");
			System.out.println("Valide");
			Ihm.tfBatCo1.setText("");
			Ihm.tfBatCo2.setText("");
			Com.reqAJoue(BatCo1);
		}else{
			Ihm.tfBatCo1.setText("");
			Ihm.tfBatCo2.setText("");
			System.out.println("Valide Placement");
			Ihm.labNorth.setText("Placement de "+Ihm.labPlacerBateau.getText()+" ...");
			Com.reqValiderPlacement(Ihm.labPlacerBateau.getText(),BatCo1,BatCo2);
		}
	}

	/////////////////////////////////////Accesseurs
	
	public String getServeur() {
		return Serveur;
	}

	public String getIdPartie() {
		return IdPartie;
	}
}
