package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
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
	
	private JLabel labNorth = new JLabel("North");
	private JButton butJoueurHumain = new JButton("Joueur Humain");
	private JLabel labAdd = new JLabel("  Host");
	public JTextField tfAdd = new JTextField();
	public JButton butIA = new JButton("Joueur IA");
	private JLabel labIdPartie = new JLabel("  Id Partie");
	private JLabel labPlacerBateau = new JLabel("Done");
	public JTextField tfIdPartie = new JTextField();
	private JButton butValiderPlacement = new JButton("Valider");
	private JButton butJouer = new JButton("Jouer");
	private JTextField tfBatCo1 = new JTextField();
	private JTextField tfBatCo2 = new JTextField();
	private JButton butArret = new JButton("Arret");
	
	private JLabel[][] CaseW = new JLabel[11][11];
	private JLabel[][] CaseE = new JLabel[11][11];
	
	private MouseListener mouseListener;
	
	private int caseLigCur = 0;
	private int caseColCur = 0;
	
	private Jeux jeux;
	
	public IHM() {
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		north();
		center();
		south();
		init();
		Listener();
		butArret.addActionListener(this);
		butIA.addActionListener(this);
		butJouer.addActionListener(this);
		butJoueurHumain.addActionListener(this);
		butValiderPlacement.addActionListener(this);
		//labNorth.addMouseListener(mouseListener);
		
		for(int lig=0;lig<11;lig++){
			for(int col=0;col<11;col++){
				CaseE[lig][col].addMouseListener(mouseListener);
				CaseE[lig][col].setName(""+lig+col);
				CaseW[lig][col].setName(""+lig+col);
				
			}
		}
		
		butArret.setEnabled(false);
		butJouer.setEnabled(false);
		butValiderPlacement.setEnabled(false);
		butJoueurHumain.setEnabled(false);
		tfAdd.setBackground(new Color(255,255,255));
		tfIdPartie.setBackground(new Color(255,255,255));
		
		jeux = new Jeux(this);
	}
	
	public void Listener(){
		mouseListener = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {	}
			@Override
			public void mouseExited(MouseEvent e)  {
				/*System.out.println("sort");*/
				Case(e);
				CaseE[caseLigCur][caseColCur].setBackground(new Color(0,0,100));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				/*System.out.println("Entre");*/
				Case(e);
				CaseE[caseLigCur][caseColCur].setBackground(new Color(255,255,255));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Case(e);
				System.out.printf("Click : %d - %d\n",caseLigCur,caseColCur);
			}
		};
	}
	
	public void Case(MouseEvent e){
		int lig = e.getXOnScreen();
		int col = e.getYOnScreen();
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
					caseColCur = colone;
					caseLigCur = ligne;
					j = 1000;
					i = 1000;
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==butArret) {
			System.out.println("Arret");
		}
		if (e.getSource()==butIA) {
			System.out.println("IA");
			jeux.joueurIA(tfAdd.getText(),tfIdPartie.getText());
		}
		if (e.getSource()==butJouer) {
			System.out.println("Jouer");
		}
		if (e.getSource()==butJoueurHumain) {
			System.out.println("JoueurHumain");
		}
		if (e.getSource()==butValiderPlacement) {
			System.out.println("ValiderPlacement");
		}
		if (e.getSource()==labNorth) {
			System.out.println("North");
		}
		
	}
	
	public void init(){
		initGrilles();
		initlabels();
		initTextFields();
	}
	
	public void initTextFields(){
		tfAdd.setBackground(new Color(0,0,0));
		tfBatCo2.setBackground(new Color(0,0,0));
		tfBatCo1.setBackground(new Color(0,0,0));
		tfIdPartie.setBackground(new Color(0,0,0));
	}
	
	public void initlabels(){
		labAdd.setHorizontalAlignment(10); //0 : Center / 11 : Right / 10 : Left
		labIdPartie.setHorizontalAlignment(10);
		labNorth.setHorizontalAlignment(0);
		labPlacerBateau.setHorizontalAlignment(0);
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
	
	public void initGrilles(){
		char[] Alpha = new char[10];
		Alpha[0] = 'A';Alpha[1] = 'B';Alpha[2] = 'C';Alpha[3] = 'D';Alpha[4] = 'E';
		Alpha[5] = 'F';Alpha[6] = 'G';Alpha[7] = 'H';Alpha[8] = 'I';Alpha[9] = 'J';
		CaseW[0][0].setText("");
		CaseE[0][0].setText("");
		CaseW[0][0].setBackground(new Color(0, 0, 0));
		CaseW[0][0].setOpaque(true);
		CaseE[0][0].setBackground(new Color(0, 0, 0));
		CaseE[0][0].setOpaque(true);
		CaseW[0][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		CaseE[0][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		for(int lig=1;lig<11;lig++){
			CaseW[lig][0].setText(""+Alpha[lig-1]);
			CaseE[lig][0].setText(""+Alpha[lig-1]);
			CaseW[lig][0].setForeground(new Color(255,255,255));
			CaseE[lig][0].setForeground(new Color(255,255,255));
			CaseW[lig][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			CaseE[lig][0].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			
			for(int col=1;col<11;col++){
				CaseW[lig][col].setText("");
				CaseE[lig][col].setText("");
				CaseW[lig][col].setBackground(new Color(0, 0, 100));
				CaseW[lig][col].setOpaque(true);
				CaseE[lig][col].setBackground(new Color(0, 0, 100));
				CaseE[lig][col].setOpaque(true);
				CaseW[lig][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
				CaseE[lig][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			}
			
		}
		for(int col=1;col<11;col++){
			CaseW[0][col].setText(""+col);
			CaseE[0][col].setText(""+col);
			CaseW[0][col].setForeground(new Color(255,255,255));
			CaseE[0][col].setForeground(new Color(255,255,255));
			CaseW[0][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
			CaseE[0][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		}
	}
	
	//North
	public void north(){
		JPanel north = new JPanel();
		this.add("North",north);
		north.setBackground(new Color(0,0,0));
		north.setBackground(new Color(0,0,0));
		north.setOpaque(true);
		north.setLayout(new BorderLayout());
		north.add(labNorth);
	}
		
	//Center
	public void center(){
		JPanel center = new JPanel();
		this.add("Center",center);
		center.setBackground(new Color(0,0,0));
		center.setOpaque(true);
		center.setLayout(new BorderLayout());
		centerWest(center);
		centerEast(center);
	}
	
		
	//South
	public void south(){
		JPanel south = new JPanel();
		this.add("South",south);
		south.setLayout(new GridLayout(2,6));
		south.setBackground(new Color(0,0,0));
		south.setOpaque(true);
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
	
	//CenterWest
	public void centerWest(JPanel center){
		JPanel centerWest = new JPanel();
		center.add("West",centerWest);
		centerWest.setLayout(new GridLayout(11,11));
		centerWest.setBorder(BorderFactory.createTitledBorder("Votre Flotte"));
		centerWest.setPreferredSize(new Dimension(400,400));
		centerWest.setBackground(new Color(0,0,0));
		centerWest.setOpaque(true);
		for(int lig=0;lig<11;lig++){
			for(int col=0;col<11;col++){
				CaseW[lig][col] = new JLabel(" "+lig+col+" ");
				CaseW[lig][col].setBorder(BorderFactory.createLineBorder(null));
				centerWest.add(CaseW[lig][col]);
			}
		}
	}
	
	//CenterEast
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
