package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		Statement st = null;
		
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
				
				DatabaseMetaData metadados = conn.getMetaData();		
				
				if (!metadados.getTables(null, null, "fornecedor", null).next()) {
					st = conn.createStatement();			
					st.executeUpdate("CREATE TABLE fornecedor ("
										 + " id int(11) NOT NULL AUTO_INCREMENT,"
							             + " nome varchar(60) NOT NULL,"
										 + " email varchar(60) NOT NULL,"
										 + " telefone varchar(30) NOT NULL,"
										 + " endereco varchar(100) NOT NULL,"
							             + " datacadastro datetime NOT NULL,"						            
										 + " PRIMARY KEY (id)"						            
							             + ");");
					System.out.println("Criado a tabela FORNECEDOR!");
				}
				
				if (!metadados.getTables(null, null, "produto", null).next()) {
					st = conn.createStatement();			
					st.executeUpdate("CREATE TABLE produto ("
										 + " id int(11) NOT NULL AUTO_INCREMENT,"
							             + " descricao varchar(60) NOT NULL,"
										 + " unidademedida varchar(30) NOT NULL,"
							             + " quantidade double NOT NULL,"
							             + " fornecedorid int(11) NOT NULL," 
							             + " datacadastro datetime NOT NULL,"
							             + " precocusto double NOT NULL,"
							             + " precovenda double NOT NULL,"
							             + " marca varchar(30) NOT NULL,"
										 + " PRIMARY KEY (id),"
							             + " FOREIGN KEY (fornecedorid) REFERENCES fornecedor (id)"
							             + ");");
					System.out.println("Criado a tabela PRODUTO!");
				}
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		
		
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
