package csv;

import java.sql.*;

public class Conexion {
	static String bd="tienda_virtual2";
	static String Login="root";
	static String password = "root";
	static String url = "jdbc:mysql://localhost/"+bd;
	
	Connection conex=null;
	
	public Conexion() {
		System.out.println("Inicio de conexion");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conex = DriverManager.getConnection(url, Login, password);
			if(conex!=null) {
				System.out.println("Conexión con la BD: "+bd+" OK");	
			}
		}catch(SQLException e) {
			System.out.println(e);
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public Connection getConex() {
		return conex;
	}
	
	public void desconectar() {
		conex=null;
	}

}
