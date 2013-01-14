package Jeux;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import IHM.IHM;

public class Communication implements Runnable {
	
	/////////////////////////////////Déclarations
	private Jeux jeux;
	private Xml xml;
	private Actualiser actualiser;
	
	private Document document;
	private Thread thread;
	private Attendre Att = new Attendre() ;
	
	private String requete = "";
	public int action = 0;
	private boolean attendre = false;
	
	/////////////////////////////////Constucteur
	public Communication(Jeux jeux,IHM ihm){
		this.jeux = jeux;
		xml = new Xml();
		actualiser = new Actualiser(ihm);
	}
	
	/////////////////////////////////Fonctions
	
	//Démarre le Thread
	public void envoiReception(){
		//Création du Thread
		thread = new Thread(this);
		thread.start();
	}
	
	///////////////////
	
	//Envoi requete, création partie pour joueur contre IA
	public void reqJoueurIA(){
		//http://127.0.0.1/Serveur.php?action=joueurIA&idpartie=1
		requete = "action=joueurIA&idpartie=" + jeux.getIdPartie();		
		envoiReception();
	}
	
	//Envoi requete, attente pour simuler le joueur IA ou attendre l'autre joueur
	public void reqAttente(){
		//http://127.0.0.1/Serveur.php?action=attente&idpartie=1&nom=Joueur1$tour=0
		attendre = true;
		requete = "action=attente&idpartie=" + jeux.getIdPartie() + "&tour=" + xml.getTour() + "&nom=" + xml.getNomJoueur();
		Att.req = requete;
		envoiReception();
	}
	
	//Envoi requete, placement d'un bateau
	public void reqValiderPlacement(String Bateau,String Co1,String Co2){
		//http://127.0.0.1/Serveur.php?action=placement&idpartie=1&nom=Joueur1&bateau=Porte-Avion(5case)&co1=A2&co2=D7
		requete = "action=placement&idpartie=" + jeux.getIdPartie() + "&nom=" + xml.getNomJoueur() + "&bateau=" + Bateau + "&co1=" + Co1 + "&co2=" + Co2;
		envoiReception();
	}
	
	public void reqAJoue(String Co1){
		//http://127.0.0.1/Serveur.php?action=joue&idpartie=1&nom=Joueur1&tour=0&co=A2
		requete = "action=joue&idpartie=" + jeux.getIdPartie() + "&nom=" + xml.getNomJoueur() + "&tour=" + xml.getTour() + "&co=" + Co1;
		envoiReception();
	}

	///////////////////
	
	//Fonction executé lors du démarage du Thread
	@Override
	public void run() {
		if(attendre){
			Att.start();
			while( Att.isAlive() ) {
				System.out.println("Attente Rep ...");
			      try {
			        // et faire une pause
			        thread.sleep(1000);
			      }
			      catch (InterruptedException ex) {}
			}
			 System.out.printf("Doc : %s",document);
			 //Lecture du XML
			 xml.Parse(Att.doc);
			 //Actualisation de l'affichage
			
		}else{
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
		}
		 actualiser.actu(xml,thread);
	}
}
