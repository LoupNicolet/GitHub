package Jeux;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Attendre extends Thread {
		private String Serveur="127.0.0.1";
		private String IdPartie = "0";
		public String req = "";
		public String rep = "";
		public Document doc;
	  public void run() {
	    // faire quelque chose
		  SAXBuilder sxb = new SAXBuilder();
			 try {
				 //Envoi et Reception du document
				//document = new Document();
				doc = null;
				System.out.println("Envoi Requette : " + "http://" + Serveur + "/" + "php" + "/" +"Serveur.php" + "?" +req);
				doc = sxb.build(new URL("http://" + Serveur + "/" + "php" + "/" + "Serveur.php" + "?" + req ));
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
	  }
	}
