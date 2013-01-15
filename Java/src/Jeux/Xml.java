package Jeux;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

public class Xml {
	
	///////////////////////////////Déclarations
	
	//Elements
	private Element ePartie;
	private Element eNomJoueur;
	private Element eTour;
	private Element eEtat;
	private Element ePlateau;
	private Element eFlotte;
	private Element eTampFlotte;
	private Element eTactique;
	private Element eTampTactique;
	
	//Listes d'Elements
	private List<Element> eSousMenus;
	private List<Element> eSousMenusGrilles;
	private List<Element> eSousMenusGrillesFlotte;
	private List<Element> eSousMenusGrillesTactique;
	
	//String
	private String idPartie;
	private String nomJoueur;
	private String tour;
	private String etat;
	
	//Tableaux de String
	private String[] grilleFlotte = new String[100];
	private String[] grilleTactique = new String[100];
	
	
	///////////////////////////////////////Constucteur
	public Xml(){
		
	}
	
	///////////////////////////////////////Fonctions
	
	//Lecture XML
	public void Parse(Document Doc){
		ePartie = Doc.getRootElement();
		idPartie = ePartie.getAttributeValue("id");
			eSousMenus = ePartie.getChildren();
			eNomJoueur = eSousMenus.get(0);
			nomJoueur = eNomJoueur.getTextTrim();
			eTour = eSousMenus.get(1);
			tour = eTour.getTextTrim();
			eEtat = eSousMenus.get(2);
			etat = eEtat.getTextTrim();
			ePlateau = eSousMenus.get(3);
				eSousMenusGrilles = ePlateau.getChildren();
				eFlotte = eSousMenusGrilles.get(0);
					eSousMenusGrillesFlotte = eFlotte.getChildren();
					for(int i=0;i<100;i++){
						eTampFlotte = eSousMenusGrillesFlotte.get(i);
						grilleFlotte[i] = eTampFlotte.getTextTrim();
					}
				eTactique = eSousMenusGrilles.get(1);
					eSousMenusGrillesTactique = eTactique.getChildren();
					for(int j=0;j<100;j++){
						eTampTactique = eSousMenusGrillesTactique.get(j);
						grilleTactique[j] = eTampTactique.getTextTrim();
					}		
					//Afficher();
	}
	
//////////////////////////////////
	
	//Afficher le XML dans la console
	public void Afficher(){
		System.out.println("Partieid : "+idPartie);
		System.out.println("\t NomJoueur : "+nomJoueur);
		System.out.println("\t tour : "+tour);
		System.out.println("\t etat : "+etat);
		System.out.println("\t\t Grille Flotte :");
		for(int i=0;i<100;i++){
			System.out.println("\t\t\t\t "+i+" : "+grilleFlotte[i]);
		}
		System.out.println(" ");
		System.out.println("\t\t Grille tactique :");
		System.out.println(" ");
		for(int j=0;j<100;j++){
			System.out.println("\t\t\t\t "+j+" : "+grilleTactique[j]);
		}
	}
	
/////////////////////////////////////////Accesseurs
	
	public String getIdPartie() {
		return idPartie;
	}
	
	public String getNomJoueur() {
		return nomJoueur;
	}
	
	public String getTour() {
		return tour;
	}
	
	public String getEtat() {
		return etat;
	}
	
	public String[] getGrilleFlotte() {
		return grilleFlotte;
	}
	
	public String[] getGrilleTactique() {
		return grilleTactique;
	}

}
