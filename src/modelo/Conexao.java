
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private static Connection connection;
    
    //metodo para conexao com o banco
    public static Connection getConnection(){
        
        if(connection == null){
            try {
                // passando os dados para conexão do banco
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bancozoologico","root","socratesff10");
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        return connection;
    }
}
