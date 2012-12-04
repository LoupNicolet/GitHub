package Jeux;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Communication implements Runnable {
	
	private Jeux jeux;
	private Document document;
	private String requete = "";
	private Xml xml;
	private Thread thread;
	private Actualiser actualiser;
	
	public Communication(Jeux jeux){
		this.jeux = jeux;
		xml = new Xml();
		thread = new Thread(this);
		actualiser = new Actualiser();
	}
	
	public void envoiReception(){
		thread.start();
	}
	
	public void reqJoueurIA(){
		requete = "action=joueurIA" + "&idpartie=" + jeux.getIdPartie();		
		envoiReception();
	}

	@Override
	public void run() {
		SAXBuilder sxb = new SAXBuilder();
		 try {
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
		 xml.Parse(document);
		 actualiser.actu();
	}
}
