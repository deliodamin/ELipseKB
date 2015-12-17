
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scherer
 */
public class ConectarBD {

    private Connection conn;
    // string de conexão...usando Windows Authentication
    // String connectionUrl = "jdbc:sqlserver://london.elipse.com.br;databaseName=elipseDB;user=supportUser;password=Abcd1234";

    //Statement stmt = null;
    //ResultSet rs;
    public ConectarBD(String BD) {

        tentaCont(BD, 0);

        // System.out.println("criou Conexão");
    }

    public int tentaCont(String BD, int controleParada) {
        String stringCon = null;
        String user = null;
        String password = null;

        if (BD.equalsIgnoreCase("London")) {
            stringCon = "jdbc:sqlserver://london.elipse.com.br;databaseName=elipseDB";
            user = "supportUser";
            password = "Abcd1234";
        } else if (BD.equalsIgnoreCase("Westminster")) {
            stringCon = "jdbc:sqlserver://westminster.elipse.com.br;databaseName=FogBugz80";
            user = "elipseCrmReaderUser";
            password = "Rff93770345";

        } else if (BD.equalsIgnoreCase("Juba")) {
            stringCon = "jdbc:sqlserver://liverpool.elipse.com.br;databaseName=ElipseKB";
            user = "KbUser";
            password = "Panteras2016";
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            // Abre a conexão
            conn = DriverManager.getConnection(stringCon, user, password);
            // imprime na tela
            //System.out.println("Conexão obtida com sucesso.");

        } catch (SQLException ex) {
            // se ocorrem erros de conexão
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
   

            //Retenta conexão 1- vzs a cada 5.5s
            controleParada++;
            System.out.println("Tentativa SQLException = " + controleParada);
            try {
                Thread.sleep(2500);

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            if (controleParada <= 10) {
                tentaCont(BD, controleParada);

            } else {
                conn = null;
                return 10;
            }
        } catch (Exception e) {
            // se ocorrerem outros erros
            System.out.println("Problemas ao tentar conectar com o banco de dados: " + e);

            //Retenta conexão 1- vzs a cada 5.5s
            controleParada++;
            System.out.println("Tentativa Exception = " + controleParada);
            try {
                Thread.sleep(5500);

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            if (controleParada <= 10) {
                tentaCont(BD, controleParada);

            } else {
                conn = null;
                return 10;
            }
        }

        return 10;
    }

    public Connection getConn() {

        return conn;

    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void killConn() {
        try {
            this.conn.close();
            // System.out.println("fechou conexão");
        } catch (SQLException ex) {
            Logger.getLogger(ConectarBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
