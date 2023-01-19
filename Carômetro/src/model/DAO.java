package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {

	// criando variaveis encapsuladas para acesso ao banco
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.49.107:3306/carometro";
	private String user = "dba";
	private String password = "123@senac";

	/**
	 * Metodo responsavel por abrir uma conecao com o banco
	 * 
	 * @return con
	 */
	public Connection conectar() {

		// criar um objeto
		Connection con = null;
		// tratamento de excecoes
		try {
			// logica principal para abrir a conex�o
			// Uso do driver
			Class.forName(driver);
			// Obter os parametros da conexao (Informacoes do servidor)
			con = DriverManager.getConnection(url, user, password); // conectar
			// retornar a conexao ("abrir a porta da geladeira")
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
}
