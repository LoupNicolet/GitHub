package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Jeux.Jeux;

public class IHM extends JFrame implements ActionListener {
	
	///////////////////////////Déclaration des Composants
	//Texts
	public JLabel labNorth = new JLabel("Veuillez vous connecter a un serveur et selectionner votre mode de jeu.");
	private JLabel labAdd = new JLabel("  Host");
	private JLabel labIdPartie = new JLabel("  Id Partie");
	public JLabel labPlacerBateau = new JLabel("");
	//Boutons
	public JButton butJoueurHumain = new JButton("Joueur Humain");
	public JButton butIA = new JButton("Joueur IA");
	public JButton butValiderPlacement = new JButton("Valider");
	public JButton butJouer = new JButton("Jouer");
	public JButton butArret = new JButton("Arret");
	//Zone de Texts
	public JTextField tfAdd = new JTextField("127.0.0.1");
	public JTextField tfIdPartie = new JTextField();
	public JTextField tfBatCo1 = new JTextField();
	public JTextField tfBatCo2 = new JTextField();
	//Grilles
	public JLabel[][] CaseW = new JLabel[11][11];
	public JLabel[][] CaseE = new JLabel[11][11];
	//Souris
	private MouseListener mouseListener;
	//Variables
	private int caseLigCur = 0;
	private int caseColCur = 0;
	private boolean vrai = true;
	private int nbclic = 0;
	//Objet
	public Jeux jeux = new Jeux(this);
	//Couleur
	private Color couleur = new Color(0,0,0);
	private Color couleur2 = new Color(0,0,0);
	private Color couleur3 = new Color(0,0,0);
	private Color back = new Color(0,0,0);
	
	////////////////////////////////////////////Constructeur
	public IHM() {
		//Layout Principal
		this.setLayout(new BorderLayout());
		//Taille fixe de la fenêtre
		this.setResizable(false);
		//Definitions des Differents panels
		north();
		center();
		south();
		//Initialisation des composants
		initGrilles();
		initlabels();
		initTextFields();
		//Création des Listeners de la souris
		MouseListener();
		//Ajout des actions sur les boutons
		butArret.addActionListener(this);
		butIA.addActionListener(this);
		butJouer.addActionListener(this);
		butJoueurHumain.addActionListener(this);
		butValiderPlacement.addActionListener(this);
		
		//Ajout d'action sur les labels des grilles
		for(int lig=0;lig<11;lig++){
			for(int col=0;col<11;col++){
				CaseE[lig][col].addMouseListener(mouseListener);
				CaseW[lig][col].addMouseListener(mouseListener);
				CaseE[lig][col].setName(""+lig+col);
				CaseW[lig][col].setName(""+lig+col);
				
			}
		}
		
		//Configuration de la situation de départ de l'ihm
		butArret.setEnabled(false);
		butJouer.setEnabled(false);
		butJoueurHumain.setEnabled(false);
		butValiderPlacement.setEnabled(false);
		tfAdd.setBackground(new Color(255,255,255));
		tfIdPartie.setBackground(new Color(255,255,255));
		labNorth.setForeground(new Color(255,0,0));
	}
	
	
////////////////////////////////////////////////////Fonctions	
	
	//Listeners de la souris
	public void MouseListener(){
		mouseListener = new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e)  {
				//Recuperation de la case
				Case(e);
				//Changement de sa couleur
				if(vrai){
					couleur = new Color(0,0,200);
					couleur2 = new Color(200,200,200);
					couleur3 = new Color(255,0,0);
					back = CaseE[caseLigCur+1][caseColCur+1].getBackground();
					if(back.equals(couleur)){
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,100));
					}else if(back.equals(couleur2)){
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(100,100,100));
					}else if(back.equals(couleur3)){
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(175,0,0));
					}else{
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,200));
					}
				}else{
					couleur = new Color(0,0,200);
					couleur2 = new Color(200,200,200);
					couleur3 = new Color(255,0,0);
					back = CaseW[caseLigCur+1][caseColCur+1].getBackground();
					if(back.equals(couleur)){
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,100));
					}else if(back.equals(couleur2)){
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(100,100,100));
					}else if(back.equals(couleur3)){
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(175,0,0));
					}else{
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,200));
					}
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				//Recuperation de la case
				Case(e);
				//Changement de sa couleur
				if(vrai){
					couleur = new Color(100,100,100);
					couleur2 = new Color(0,0,100);
					couleur3 = new Color(175,0,0);
					back = CaseE[caseLigCur+1][caseColCur+1].getBackground();
					if(back.equals(couleur)){
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(200,200,200));
					}else if(back.equals(couleur2)){
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,200));
					}else if(back.equals(couleur3)){
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(255,0,0));
					}else{
						CaseE[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,255));
					}
				}else{
					couleur = new Color(100,100,100);
					couleur2 = new Color(0,0,100);
					couleur3 = new Color(175,0,0);
					back = CaseW[caseLigCur+1][caseColCur+1].getBackground();
					if(back.equals(couleur)){
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(200,200,200));
					}else if(back.equals(couleur2)){
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,200));
					}else if(back.equals(couleur3)){
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(255,0,0));
					}else{
						CaseW[caseLigCur+1][caseColCur+1].setBackground(new Color(0,0,255));
					}
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				//Recuperation de la case
				Case(e);
				//ajout dans les tf
				System.out.printf("Click1 : %d - %d\n",caseLigCur,caseColCur);
				nbclic++;
				if(labPlacerBateau.getText() == "Case"){
					tfBatCo1.setText(""+caseLigCur+caseColCur);
					nbclic = 0;
				}else{
					if(nbclic == 1){
						tfBatCo1.setText(""+caseLigCur+caseColCur);
					}else if(nbclic == 2){
						tfBatCo2.setText(""+caseLigCur+caseColCur);
						nbclic = 0;
					}
				}
			}
		};
	}
	
/////////////////////////	
	
	//Recuperation de la case 1
	public void Case(MouseEvent e){
		//déclaration de variable
		int ligne = 0;
		int colone = 0;
		for(int i=1;i<11;i++){
			ligne++;
			for(int j=1;j<11;j++){
				if(colone<10){
					colone++;
				}else{
					colone = 1;
				}
				if(e.getSource() == CaseE[i][j]){
					caseColCur = colone-1;
					caseLigCur = ligne-1;
					j = 1000;
					i = 1000;
					vrai = true;
				}else if(e.getSource() == CaseW[i][j]){
					caseColCur = colone-1;
					caseLigCur = ligne-1;
					j = 1000;
					i = 1000;
					vrai = false;
				}
			}
		}
		
	}

/////////////////////////	
	
	//Lorqu'un bouton est pressé, on execute cette fonction
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==butArret) {}
		if (e.getSource()==butIA) {
			jeux.joueurIA(tfAdd.getText(),tfIdPartie.getText());
		}
		if (e.getSource()==butJouer) {}
		if (e.getSource()==butJoueurHumain) {}
		if (e.getSource()==butValiderPlacement) {
			jeux.ValiderPlacement(tfBatCo1.getText(),tfBatCo2.getText() );
		}
		if (e.getSource()==labNorth) {}
	}
	
//////////////////////////
	
	//Initialisation des Couleurs des Zones de Text
	public void initTextFields(){
		tfAdd.setBackground(new Color(0,0,0));
		tfBatCo2.setBackground(new Color(0,0,0));
		tfBatCo1.setBackground(new Color(0,0,0));
		tfIdPartie.setBackground(new Color(0,0,0));
	}
	
//////////////////////////
	
	//Initialisation des Alignements/Couleurs des labels
	public void initlabels(){
		labAdd.setHorizontalAlignment(10); //0 : Center / 11 : Right / 10 : Left
		labIdPartie.setHorizontalAlignment(10);
		labNorth.setHorizontalAlignment(0);
		labPlacerBateau.setHorizontalAlignment(0);
		
		//Labels des grilles
		for(int lig=0;lig<11;lig++){
			for(int col=0;col<11;col++){
				CaseW[lig][col].setHorizontalAlignment(0);
				CaseE[lig][col].setHorizontalAlignment(0);
				
			}
		}
		
		labAdd.setForeground(new Color(255,255,255));
		labNorth.setForeground(new Color(255,255,255));
		labPlacerBateau.setForeground(new Color(255,255,255));
		labIdPartie.setForeground(new Color(255,255,255));
		
	}
	
///////////////////////////
	
	//initialisation des valeurs/Couleurs/Bordure/Ligne/Colonne des Grilles
	public void initGrilles(){
		char[] Alpha = new char[10];
		Alpha[0] = 'A';Alpha[1] = 'B';Alpha[2] = 'C';Alpha[3] = 'D';Alpha[4] = 'E';
		Alpha[5] = 'F';Alpha[6] = 'G';Alpha[7] = 'H';Alpha[8] = 'I';Alpha[9] = 'J';
		
		//Initialisation des Cases 0-0
		CaseW[0][0].setText("");
		CaseE[0][0].setText("");
		CaseE[0][0].setOpaque(true);
		CaseW[0][0].setOpaque(true);
		CaseE[0][0].setBackground(new Color(0, 0, 0));
		CaseW[0][0].setBackground(new Color(0, 0, 0));
		CaseW[0][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		CaseE[0][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		
		for(int lig=1;lig<11;lig++){
			//Pour la première ligne
			CaseW[lig][0].setText(""+Alpha[lig-1]);
			CaseE[lig][0].setText(""+Alpha[lig-1]);
			CaseW[lig][0].setForeground(new Color(255,255,255));
			CaseE[lig][0].setForeground(new Color(255,255,255));
			CaseW[lig][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			CaseE[lig][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			
			for(int col=1;col<11;col++){
				//Pour toutes les lignes,colones des coordonnées 1-1 à 10-10
				CaseW[lig][col].setText("");
				CaseE[lig][col].setText("");
				CaseW[lig][col].setBackground(new Color(0, 0, 100));
				CaseE[lig][col].setBackground(new Color(0, 0, 100));
				CaseW[lig][col].setOpaque(true);
				CaseE[lig][col].setOpaque(true);
				CaseW[lig][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
				CaseE[lig][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}	
		}
		//Pour la première colone
		for(int col=1;col<11;col++){
			CaseW[0][col].setText(""+(col-1));
			CaseE[0][col].setText(""+(col-1));
			CaseW[0][col].setForeground(new Color(255,255,255));
			CaseE[0][col].setForeground(new Color(255,255,255));
			CaseW[0][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			CaseE[0][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		}
	}
	
//////////////////////////
	
	//layout North
	public void north(){
		JPanel north = new JPanel();
		this.add("North",north);
		north.setBackground(new Color(0,0,0));
		north.setBackground(new Color(0,0,0));
		north.setOpaque(true);
		north.setLayout(new BorderLayout());
		//Ajout des composants
		north.add(labNorth);
	}
	
//////////////////////////
	
	//layout Center
	public void center(){
		JPanel center = new JPanel();
		this.add("Center",center);
		center.setBackground(new Color(0,0,0));
		center.setOpaque(true);
		center.setLayout(new BorderLayout());
		//Layout Fils
		centerWest(center);
		centerEast(center);
	}
	
//////////////////////////
		
	//layout South
	public void south(){
		JPanel south = new JPanel();
		this.add("South",south);
		south.setLayout(new GridLayout(2,6));
		south.setBackground(new Color(0,0,0));
		south.setOpaque(true);
		//ajouts des Composants
		south.add(butJoueurHumain);
		south.add(tfAdd);
		south.add(labAdd);
		south.add(labPlacerBateau);
		south.add(butValiderPlacement);
		south.add(butJouer);
		south.add(butIA);
		south.add(tfIdPartie);
		south.add(labIdPartie);
		south.add(tfBatCo1);
		south.add(tfBatCo2);
		south.add(butArret);
	}
	
//////////////////////////
	
	//layout CenterWest
	public void centerWest(JPanel center){
		JPanel centerWest = new JPanel();
		center.add("West",centerWest);
		centerWest.setLayout(new GridLayout(11,11));
		centerWest.setBorder(BorderFactory.createTitledBorder("Votre Flotte"));
		centerWest.setPreferredSize(new Dimension(400,400));
		centerWest.setBackground(new Color(0,0,0));
		centerWest.setOpaque(true);
		//Création des Bordures
		for(int lig=0;lig<11;lig++){
			for(int col=0;col<11;col++){
				//ajout des Noms aux Labels
				CaseW[lig][col] = new JLabel(" "+lig+col+" ");
				//Création des Bordures
				CaseW[lig][col].setBorder(BorderFactory.createLineBorder(null));
				//Ajouts des Composants
				centerWest.add(CaseW[lig][col]);
			}
		}
	}
	
//////////////////////////
	
	//layout CenterEast
	public void centerEast(JPanel center){
		JPanel centerEast = new JPanel();
		center.add("East",centerEast);
		centerEast.setLayout(new GridLayout(11,11));
		centerEast.setBorder(BorderFactory.createTitledBorder("Carte Tactique"));
		centerEast.setPreferredSize(new Dimension(400,400));
		centerEast.setBackground(new Color(0,0,0));
		centerEast.setOpaque(true);
		centerEast.setSize(1000, 1000);
		for(int lig=0;lig<11;lig++){
			for(int col=0;col<11;col++){
				CaseE[lig][col] = new JLabel(" "+lig+col+" ");
				CaseE[lig][col].setBorder(BorderFactory.createLineBorder(null));
				centerEast.add(CaseE[lig][col]);
			}
		}
	}
}
