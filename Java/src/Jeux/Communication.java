package Jeux;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Communication implements Runnable {
	
	/////////////////////////////////Déclarations
	private Jeux jeux;
	private Xml xml;
	private Actualiser actualiser;
	
	private Document document;
	private Thread thread;
	
	private String requete = "";
	public int action = 0;
	
	/////////////////////////////////Constucteur
	public Communication(Jeux jeux){
		this.jeux = jeux;
		xml = new Xml();
		actualiser = new Actualiser();
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
		requete = "action=joueurIA" + "&idpartie=" + jeux.getIdPartie();		
		envoiReception();
	}

	///////////////////
	
	//Fonction executé lors du démarage du Thread
	@Override
	public void run() {
		SAXBuilder sxb = new SAXBuilder();
		 try {
			 //Envoi et Reception du document
			document = sxb.build(new URL("http://" + jeux.getServeur()+ "/" + "php" + "/" + "Serveur.php" + "?" + requete ));
			System.out.println("Envoi Requette : " + "http://" + jeux.getServeur()+ "/" + "Serveur.php" + "?" +requete);
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
