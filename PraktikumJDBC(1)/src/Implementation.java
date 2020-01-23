
import edu.whs.dba.ApplicationException;
import edu.whs.dba.DataAccessObject;
import edu.whs.dba.entity.Modul;
import edu.whs.dba.entity.Praktikumsteilnahme;
import edu.whs.dba.entity.Student;
import edu.whs.dba.entity.Studienrichtung;
import entitys.Studienrichtung_imp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class Implementation implements DataAccessObject  {
    
    private Connection con = null;
    
    public void startCon(String url, String username, String password) throws SQLException{
        
        
        try{
            
            con = DriverManager.getConnection(url, username, username);
            System.out.println("Verbindung erfolgreich");

            
        }
        catch (SQLException ex){
            ex.getMessage();
        }
        catch (Exception ex2){
            System.out.println("Keine Verbindung");
        }
    }

    @Override
    public List<List<String>> getStudienverlaufsplan(Studienrichtung s) throws ApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addStudent(String string, String string1, String string2, String string3, Studienrichtung s) throws ApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Student> getAllStudent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean enroll(String string, String string1, String string2, String string3, Studienrichtung s, Modul modul, String string4) throws ApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBescheinigungen(Collection<Praktikumsteilnahme> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JPanel getChart(int i, Object o, Object o1) throws ApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Studienrichtung> getAllStudienrichtung() {
        ArrayList<Studienrichtung> getAllStudienrichtung = new ArrayList();
        Studienrichtung_imp sr;
        ResultSet result;
        
        try{
            Statement select = con.createStatement();
            result = select.executeQuery("select SKUERZEL, NAME from STUDIENRICHTUNG");
            
            while(result.next()){
                String kuerzel;
                String name;
                
                kuerzel = result.getString(1);
                name = result.getString(2);
                if(result.wasNull()){
                    name = null;
                }
                
                sr = new Studienrichtung_imp(kuerzel, name);
                System.out.println(sr);
                getAllStudienrichtung.add(sr);
                
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
        return getAllStudienrichtung;  
        }
        
    

    @Override
    public Collection<Modul> getAllModul() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Praktikumsteilnahme> getAllPraktikumsteilnahme(Modul modul, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws ApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
