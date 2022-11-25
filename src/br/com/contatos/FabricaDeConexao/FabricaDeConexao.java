package br.com.contatos.FabricaDeConexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexao {

	public static final String USERNAME = "root";
	
	public static final String PASSWORD = "";
	
	public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	
	
	//crinado a conexão
	
	public static Connection createConnectionToMySQL() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
		
	}
	
	public static void main(String[] args) throws Exception {
		Connection conn = createConnectionToMySQL();
		
		if (conn != null) {
			System.out.println("Conexão obtida com sucesso");
			System.out.println("");
			System.out.println("");
			conn.close();
		}
	}
}
