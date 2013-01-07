package Jeux;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import IHM.IHM;

public class Communication implements Runnable {
	
	/////////////////////////////////D�clarations
	private Jeux jeux;
	private Xml xml;
	private Actualiser actualiser;
	
	private Document document;
	private Thread thread;
	
	private String requete = "";
	public int action = 0;
	
	/////////////////////////////////Constucteur
	public Communication(Jeux jeux,IHM ihm){
		this.jeux = jeux;
		xml = new Xml();
		actualiser = new Actualiser(ihm);
	}
	
	/////////////////////////////////Fonctions
	
	//D�marre le Thread
	public void envoiReception(){
		//Cr�ation du Thread
		thread = new Thread(this);
		thread.start();
	}
	
	///////////////////
	
	//Envoi requete, cr�ation partie pour joueur contre IA
	public void reqJoueurIA(){
		//http://127.0.0.1/Serveur.php?action=joueurIA&idpartie=1
		requete = "action=joueurIA&idpartie=" + jeux.getIdPartie();		
		envoiReception();
	}
	
	//Envoi requete, placement d'un bateau
	public void reqValiderPlacement(String Bateau,String Co1,String Co2){
		//http://127.0.0.1/Serveur.php?action=placement&idpartie=1&nom=Joueur1&bateau=Porte-Avion(5case)&co1=A2&co2=D7
		requete = "action=placement&idpartie=" + jeux.getIdPartie() + "&nom=" + xml.getNomJoueur() + "&bateau=" + Bateau + "&co1=" + Co1 + "&co2=" + Co2;
		envoiReception();
	}

	///////////////////
	
	//Fonction execut� lors du d�marage du Thread
	@Override
	public void run() {
		SAXBuilder sxb = new SAXBuilder();
		 try {
			 //Envoi et Reception du document
			//document = new Document();
			document = null;
			document = sxb.build(new URL("http://" + jeux.getServeur() + "/" + "php" + "/" + "Serveur.php" + "?" + requete ));
			System.out.println("Envoi Requette : " + "http://" + jeux.getServeur() + "/" + "php" + "/" +"Serveur.php" + "?" +requete);
		} catch (MalformedURLException e) {
			System.out.println("Mauvais Url");
			e.printStackTrace();
		} catch (JDOMException e) {
			System.out.println("Erreur Document");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erreur Reception");
			e.printStackTrace();
		}
		 System.out.printf("Doc : %s",document);
		 //Lecture du XML
		 xml.Parse(document);
		 //Actualisation de l'affichage
		 actualiser.actu(xml,thread);
	}
}
