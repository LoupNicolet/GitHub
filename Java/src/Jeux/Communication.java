package Jeux;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Communication {
	
	private Jeux jeux;
	private Document document;
	private String requete = "";
	
	public Communication(Jeux jeux){
		this.jeux = jeux;
	}
	
	public void envoiReception(){
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
	}
	
	public void reqJoueurIA(){
		requete = "action=joueurIA" + "&idpartie=" + jeux.getIdPartie();		
		envoiReception();
	}
}
