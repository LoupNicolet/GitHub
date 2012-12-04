package Jeux;

import java.awt.Color;
import java.awt.event.ActionEvent;

import IHM.IHM;

public class Jeux {
	
	private String Serveur="127.0.0.1";
	private String IdPartie = "0";
	private Communication Com;
	private IHM Ihm;
	
	public Jeux(IHM Ihm){
		Com = new Communication(this);
		this.Ihm = Ihm;
	}
	
	public void joueurIA(String Adr, String IdPartie){
		this.setServeur(Adr);
		this.setIdPartie(IdPartie);
		Ihm.butIA.setEnabled(false);
		Ihm.tfAdd.setBackground(new Color(0,0,0));
		Ihm.tfIdPartie.setBackground(new Color(0,0,0));
		Ihm.tfAdd.setEnabled(false);
		Ihm.tfIdPartie.setEnabled(false);
		Com.reqJoueurIA();
		System.out.println("Jeux");
	}

	public String getServeur() {
		return Serveur;
	}

	public void setServeur(String serveur) {
		Serveur = serveur;
	}

	public String getIdPartie() {
		return IdPartie;
	}

	public void setIdPartie(String idPartie) {
		IdPartie = idPartie;
	}
}
