package ConfigDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnMySQL {
    public static Connection conectar(){
        String server = "localhost";
        String port = "3306";
        String database = "teste";
        String user = "root";
        String pass = "";

        String url = "jdbc:mysql://"+server+":"+port+"/"+database;
        // jdbc:mysql//localhost:3306/teste

        Connection conn = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
            //carregando a classe na memória
            conn = DriverManager.getConnection(url,user,pass);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
