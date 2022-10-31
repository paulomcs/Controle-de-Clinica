package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String host;
    private static String db;
    private static String url;
    private static String user;
    private static String senha;
    private static boolean instancia = false;

    private static Connection connection;

    private Conexao(){
        loadDriver();
        instancia = true;
        host = "clinica.czdelsj0pim3.us-east-1.rds.amazonaws.com";
        db = "postgres";
        url = "jdbc:postgresql://"+host+"/"+db;
        user = "postgres";
        senha = "postgres";
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Carregou o driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    public static Connection getConnection() {
        if (!instancia) {
            new Conexao();
            try {
                connection = DriverManager.getConnection(url, user, senha);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
        return connection;
    }
}
