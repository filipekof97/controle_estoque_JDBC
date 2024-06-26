package application;

import db.DB;
import view.ControleEstoqueTerminal;

public class Program {

	public static void main(String[] args) {
		
		DB.getConnection();
		ControleEstoqueTerminal.executaMenuTerminal();
		DB.closeConnection();
		
	}
		

}
