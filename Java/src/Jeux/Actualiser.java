package Jeux;

import java.awt.Color;

import IHM.IHM;

public class Actualiser{
	
	private IHM Ihm;
	private String tab[] = new String[100];
	
	////////////////////////////////////////Constucteur
	public Actualiser(IHM Ihm){
		this.Ihm = Ihm;
	}
	
	///////////////////////////////////////Fonctions
	
	//Actualise l'affichage / active,désactive des composants / Les Modifies
	public void actu(Xml xml, Thread thread){
		//Etat de la partie
		String etat = xml.getEtat();
		
		//Si JoueurIA et Création Nouvelle partie OK
		if(etat.equals("vrai")){
			for(int i=1;i<11;i++){
				for(int j=1;j<11;j++){
					Ihm.CaseE[i][j].setBackground(new Color(0,0,100));
					Ihm.CaseW[i][j].setBackground(new Color(0,0,100));
				}
			}
			Ihm.labNorth.setForeground(new Color(255,0,0));
			Ihm.labNorth.setText("Placez votre flotte, pour cela entrez ou cliquez sur les coordonnées des extrémitées de chaque bateau");
			Ihm.labPlacerBateau.setText("Porte-Avion(5case)");
			Ihm.butValiderPlacement.setEnabled(true);
			Ihm.tfBatCo1.setEnabled(true);
			Ihm.tfBatCo2.setEnabled(true);
			Ihm.tfBatCo1.setBackground(new Color(255,255,255));
			Ihm.tfBatCo2.setBackground(new Color(255,255,255));
		}
		
		//Si JoueurIA et Création Nouvelle partie Pas OK
		if(etat.equals("faux")){
			Ihm.labNorth.setForeground(new Color(255,0,0));
			Ihm.labNorth.setText("Votre numéro de partie est déjà utilisé, veuillez en choisir un autre.");
			Ihm.butIA.setEnabled(true);
			Ihm.tfAdd.setEnabled(true);
			Ihm.tfIdPartie.setEnabled(true);
			Ihm.tfAdd.setBackground(new Color(255,255,255));
			Ihm.tfIdPartie.setBackground(new Color(255,255,255));
			
		}
		
		if(etat.equals("vraiPlacement")){
			int temp=0;
			float temp2=0;
			int ex = 1;
			tab = xml.getGrilleFlotte();
			for(int i=0;i<100;i++){
				if(tab[i].equals("1")){
					temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseW[(int) temp+1][(int) temp2+1].setBackground(new Color(100,100,100));	
				}
			}
			if(Ihm.labPlacerBateau.getText() == "Porte-Avion(5case)"){
				Ihm.labNorth.setForeground(new Color(255,0,0));
				Ihm.labNorth.setText("Placez votre flotte, pour cela entrez les coordonnées des extrémitées de chaque bateau");
				Ihm.labPlacerBateau.setText("Cuirasse(4case)");
				Ihm.butValiderPlacement.setEnabled(true);
				Ihm.tfBatCo1.setEnabled(true);
				Ihm.tfBatCo2.setEnabled(true);
				Ihm.tfBatCo1.setBackground(new Color(255,255,255));
				Ihm.tfBatCo2.setBackground(new Color(255,255,255));
			}
			else if(Ihm.labPlacerBateau.getText() == "Cuirasse(4case)"){
				Ihm.labNorth.setForeground(new Color(255,0,0));
				Ihm.labNorth.setText("Placez votre flotte, pour cela entrez les coordonnées des extrémitées de chaque bateau");
				Ihm.labPlacerBateau.setText("Destroyer(3case)");
				Ihm.butValiderPlacement.setEnabled(true);
				Ihm.tfBatCo1.setEnabled(true);
				Ihm.tfBatCo2.setEnabled(true);
				Ihm.tfBatCo1.setBackground(new Color(255,255,255));
				Ihm.tfBatCo2.setBackground(new Color(255,255,255));
			}
			else if(Ihm.labPlacerBateau.getText() == "Destroyer(3case)"){
				Ihm.labNorth.setForeground(new Color(255,0,0));
				Ihm.labNorth.setText("Placez votre flotte, pour cela entrez les coordonnées des extrémitées de chaque bateau");
				Ihm.labPlacerBateau.setText("Sous-Marin(3case)");
				Ihm.butValiderPlacement.setEnabled(true);
				Ihm.tfBatCo1.setEnabled(true);
				Ihm.tfBatCo2.setEnabled(true);
				Ihm.tfBatCo1.setBackground(new Color(255,255,255));
				Ihm.tfBatCo2.setBackground(new Color(255,255,255));
			}
			else if(Ihm.labPlacerBateau.getText() == "Sous-Marin(3case)"){
				Ihm.labNorth.setForeground(new Color(255,0,0));
				Ihm.labNorth.setText("Placez votre flotte, pour cela entrez les coordonnées des extrémitées de chaque bateau");
				Ihm.labPlacerBateau.setText("Torpilleur(2case)");
				Ihm.butValiderPlacement.setEnabled(true);
				Ihm.tfBatCo1.setEnabled(true);
				Ihm.tfBatCo2.setEnabled(true);
				Ihm.tfBatCo1.setBackground(new Color(255,255,255));
				Ihm.tfBatCo2.setBackground(new Color(255,255,255));
			}
			else if(Ihm.labPlacerBateau.getText() == "Torpilleur(2case)"){
				Ihm.labNorth.setForeground(new Color(255,0,0));
				Ihm.labNorth.setText("Attente autre Joueur ...");
				Ihm.labPlacerBateau.setText("");
				Ihm.butValiderPlacement.setEnabled(false);
				Ihm.tfBatCo1.setEnabled(false);
				Ihm.tfBatCo2.setEnabled(false);
				Ihm.tfBatCo1.setBackground(new Color(0,0,0));
				Ihm.tfBatCo2.setBackground(new Color(0,0,0));
				Ihm.jeux.Com.reqAttente();
			}
		}
				
		//Si JoueurIA et Création Nouvelle partie Pas OK
		else if(etat.equals("fauxPlacement")){
		
				Ihm.labNorth.setForeground(new Color(255,0,0));
				Ihm.labNorth.setText("Erreur de Placement");
				Ihm.butValiderPlacement.setEnabled(true);
				Ihm.tfBatCo1.setEnabled(true);
				Ihm.tfBatCo2.setEnabled(true);
				Ihm.tfBatCo1.setBackground(new Color(255,255,255));
				Ihm.tfBatCo2.setBackground(new Color(255,255,255));
			
		}
		
		if(etat.equals("pres")){
			////////////////////////////Pour Afficher le joueur 2 (1/2)/////////////////////////////////
			/*int temp=0;
			float temp2=0;
			tab = xml.getGrilleTactique();
			for(int i=0;i<100;i++){
				if(tab[i].equals("1")){
					temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseE[(int) temp+1][(int) temp2+1].setBackground(new Color(100,100,100));	
				}
			}*/
			/////////////////////////////////////////////////////////////////
			Ihm.labNorth.setForeground(new Color(255,255,255));
			Ihm.labNorth.setText("Jouez");
			Ihm.labPlacerBateau.setText("Case");
			Ihm.butValiderPlacement.setEnabled(true);
			Ihm.tfBatCo1.setEnabled(true);
			Ihm.tfBatCo1.setBackground(new Color(255,255,255));
		}
		
		if(etat.equals("joue")){
			int temp=0;
			float temp2=0;
			tab = xml.getGrilleTactique();
			for(int i=0;i<100;i++){
				if(tab[i].equals("1")){
					/////////////////////////////Pour Afficher le joueur 2 (2/2)/////////////////////////////
					/*temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseE[(int) temp+1][(int) temp2+1].setBackground(new Color(100,100,100));*/
					///////////////////////////////////////////////////////////:
				}else if(tab[i].equals("2")){
					temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseE[(int) temp+1][(int) temp2+1].setBackground(new Color(0,0,200));	
				}else if(tab[i].equals("3")){
					temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseE[(int) temp+1][(int) temp2+1].setBackground(new Color(175,0,0));	
				}
			}
			Ihm.labNorth.setForeground(new Color(255,255,255));
			Ihm.labNorth.setText("Joueur2...");
			Ihm.labPlacerBateau.setText("Case");
			Ihm.butValiderPlacement.setEnabled(false);
			Ihm.tfBatCo1.setEnabled(false);
			Ihm.tfBatCo1.setBackground(new Color(0,0,0));
			Ihm.jeux.Com.reqAttente_Jeu();
		}
		

		if(etat.equals("fin")){
			int temp=0;
			float temp2=0;
			tab = xml.getGrilleFlotte();
			for(int i=0;i<100;i++){
				if(tab[i].equals("1")){
					temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseW[(int) temp+1][(int) temp2+1].setBackground(new Color(100,100,100));	
				}else if(tab[i].equals("2")){
					temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseW[(int) temp+1][(int) temp2+1].setBackground(new Color(0,0,200));	
				}else if(tab[i].equals("3")){
					temp = i/10;
					temp2 = (float) ((float) i/10.0);
					temp2 = (float) ((temp2 + 0.01) - temp);	
					temp2 = temp2*10;
					Ihm.CaseW[(int) temp+1][(int) temp2+1].setBackground(new Color(175,0,0));	
				}
			}
			Ihm.labNorth.setForeground(new Color(255,255,255));
			Ihm.labNorth.setText("Jouez");
			Ihm.labPlacerBateau.setText("Case");
			Ihm.butValiderPlacement.setEnabled(true);
			Ihm.tfBatCo1.setEnabled(true);
			Ihm.tfBatCo1.setBackground(new Color(255,255,255));
		}
		
		if(etat.equals("finWin")){
			tab = xml.getGrilleFlotte();
			for(int i=1;i<11;i++){
				for(int j=1;j<11;j++){
					if(Ihm.CaseW[i][j].getBackground().equals(new Color(175,0,0))){
						Ihm.CaseW[i][j].setBackground(new Color(0,0,255));
					}else{
						Ihm.CaseW[i][j].setBackground(new Color(200,200,0));
					}
				}
			}
			Ihm.labNorth.setForeground(new Color(255,0,0));
			Ihm.labNorth.setText("Le Joueur 2 remporte la partie");
			Ihm.labPlacerBateau.setText("");
			Ihm.butValiderPlacement.setEnabled(false);
			Ihm.tfBatCo1.setEnabled(false);
			Ihm.tfBatCo1.setBackground(new Color(0,0,0));
			Ihm.butIA.setEnabled(true);
			Ihm.tfIdPartie.setEnabled(true);
			Ihm.tfIdPartie.setBackground(new Color(255,255,255));
		}
		
		if(etat.equals("joueWin")){
			tab = xml.getGrilleTactique();
			for(int i=1;i<11;i++){
				for(int j=1;j<11;j++){
					if(Ihm.CaseE[i][j].getBackground().equals(new Color(175,0,0))){
						Ihm.CaseE[i][j].setBackground(new Color(0,0,255));
					}else{
						Ihm.CaseE[i][j].setBackground(new Color(200,200,0));
					}
				}
			}
			Ihm.labNorth.setForeground(new Color(255,0,0));
			Ihm.labNorth.setText("Le Joueur 1 remporte la partie");
			Ihm.labPlacerBateau.setText("");
			Ihm.butValiderPlacement.setEnabled(false);
			Ihm.tfBatCo1.setEnabled(false);
			Ihm.tfBatCo1.setBackground(new Color(0,0,0));
			Ihm.butIA.setEnabled(true);
			Ihm.tfIdPartie.setEnabled(true);
			Ihm.tfIdPartie.setBackground(new Color(255,255,255));
		}
		
		
	}
}
