package Main;

import javax.swing.JFrame;

import IHM.IHM;

public class Main {
	
	///////////////////////////////////////////////Main
	public static void main(String [] args){
		//Création de l'Ihm
		IHM ihm= new IHM();
		ihm.pack();
		ihm.setVisible(true);
		ihm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
}
}
