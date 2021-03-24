package lib;
import java.awt.EventQueue;
import java.sql.*;

import clases.login;

public class Conexion {
	
	
/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					conectar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	**/
	
	
public static Connection conectar()
{
	try {
		Connection cn = DriverManager.getConnection("jdbc:mysql://cloud.c5kk0t2zhzns.us-east-1.rds.amazonaws.com/baseComputadores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","admin","12345678");
		//Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/basecomputadores","root","");
		System.out.println("conexion ok" );
		return cn;
	} 
	catch (SQLException e) {
		System.out.println("conexion error " + e);
	}
	return (null);
}

}

