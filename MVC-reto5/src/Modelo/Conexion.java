
package Modelo;

/**
 *
 * @author David Tellez
 */

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {

    String url="jdbc:mysql://localhost:3306/retodb";
    String user="root",pass="Genius7589867";    
    Connection con;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {            
        }
        return con;
    }

}
