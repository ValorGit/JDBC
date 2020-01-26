
import edu.whs.dba.GUIFactory;
import edu.whs.dba.DataAccessObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class GUI {

    public static void main(String[] args) throws SQLException {
        
//       try{
//           Connection con = null;
//           
//            con = DriverManager.getConnection("jdbc:derby:C:\\Users\\Alex\\Desktop\\DBA JDBC\\DBA-Praktikum-JavaDB\\dba-praktikum;upgrade=true", "", "");
//            System.out.println("Connection erfolgreich");
//            
//            
//            
//            con.close();
//             } catch (SQLException ex) {
//            ex.getMessage();
//                   
//       }
        
        

        String title = "GUI Test";
        Implementation dao = new Implementation();
        
        dao.startCon("jdbc:derby:C:\\Users\\Alex\\Desktop\\DBA JDBC\\DBA-Praktikum-JavaDB\\dba-praktikum", "", "");

        GUIFactory init;
        GUIFactory.createMainFrame(title, dao).setVisible(true);

        

    }

}
