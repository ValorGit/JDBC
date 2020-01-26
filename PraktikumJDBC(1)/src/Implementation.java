
import edu.whs.dba.ApplicationException;
import edu.whs.dba.DataAccessObject;
import edu.whs.dba.entity.Modul;
import edu.whs.dba.entity.Praktikumsteilnahme;
import edu.whs.dba.entity.Student;
import edu.whs.dba.entity.Studienrichtung;
import entities.ModulWhs;
import entities.StudentWhs;
import entities.StudienrichtungWhs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
public class Implementation implements DataAccessObject {

    private Connection con = null;
    private ArrayList<Studienrichtung> allStudienrichtung;
    private ArrayList<Modul> allModul;
    private ArrayList<Student> allStudent;

    public void startCon(String url, String username, String password) throws SQLException {

        try {

            con = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung erfolgreich");

        } catch (SQLException ex) {
            ex.getMessage();
        } catch (Exception ex2) {
            System.out.println("Keine Verbindung");
        }
    }

    @Override
    public List<List<String>> getStudienverlaufsplan(Studienrichtung s) throws ApplicationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addStudent(String string, String string1, String string2, String string3, Studienrichtung s) throws ApplicationException {

        int insertion;
        ResultSet result;

        if (allStudienrichtung.contains(s)) {

            try {

                String sql = "INSERT INTO Student (Matrikel, Name, Vorname, Adresse, Skuerzel) values('" + string + "', '" + string1 + "', '" + string2 + "', '" + string3 + "', '" + s.getKuerzel() + "')";
                Statement insert = con.createStatement();
                result = insert.executeQuery("Select Matrikel from Student Where Matrikel = '" + string + "'");

                if (!result.next()) {

                    result.close();

//            insertion = insert.executeUpdate("insert into Student (Matrikel, Name, Vorname, Adresse, Skuerzel)" + "values(" + string +", " +string1 +", " +string2 + ", " + string3 + ", " + s + ")");
                    insertion = insert.executeUpdate(sql);
                    if (insertion > 0) {
                        System.out.println("Tupel erfolgreich hinzugefügt");
                    }

                } else {
                    throw new IllegalArgumentException("Die übergebene Matrikelnummer des Studenten existiert bereits.");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            throw new IllegalArgumentException("Das angegebene Kürzel für die Studienrichtung referenziert keinen Datensatz aus der Tabelle STUDIENRICHTUNG");
        }
    }

    @Override
    public Collection<Student> getAllStudent() {
        ResultSet result;
        allStudent = new ArrayList<Student>();
        StudentWhs student = null;

        try {
            String sql = "Select * from Student";
            Statement select = con.createStatement();
            result = select.executeQuery(sql);

            while (result.next()) {
                String matrikel;
                String name;
                String vorname;
                String adresse;
                String srkuerzel;
                Studienrichtung sr = null;

                matrikel = result.getString(1);
                name = result.getString(2);
                if (result.wasNull()) {
                    name = null;
                }
                vorname = result.getString(3);
                if (result.wasNull()) {
                    vorname = null;
                }
                adresse = result.getString(4);
                if (result.wasNull()) {
                    adresse = null;
                }
                srkuerzel = result.getString(5);

                Iterator<Studienrichtung> it = allStudienrichtung.iterator();

                while (it.hasNext()) {
                    Studienrichtung srIt = it.next();
                    if (srIt.getKuerzel().equals(srkuerzel)) {
                        sr = srIt;
                    }
                }
                student = new StudentWhs(matrikel, name, vorname, adresse, sr);
                allStudent.add(student);
            }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(allStudent);
        return allStudent;

    }

    @Override
    public boolean enroll(String matrikel, String nachname, String vorname, String adresse, Studienrichtung studienrichtung, Modul modul, String semester) throws ApplicationException {

        int insertion;
        ResultSet result;
        Student einzuschreibenderStudent = new StudentWhs(matrikel, nachname, vorname, adresse, studienrichtung);
        boolean enroll = false;

        if (!allStudent.contains(einzuschreibenderStudent)) {
            this.addStudent(matrikel, nachname, vorname, adresse, studienrichtung);
            System.out.println("Neuer Student wurde angelegt");
        }

        try {
            Statement select = con.createStatement();
            result = select.executeQuery("Select SKuerzel from Verlaufsplan where MKuerzel = '" + modul.getKuerzel() + "' and SKuerzel = '" + einzuschreibenderStudent.getStudienrichtung().getKuerzel() + "'");

            if (!result.next()) {
                throw new IllegalArgumentException("Das übergebene Modul ist nicht Bestandteil der Studienrichtung des anzumeldenden Studenten.");
            } else {
                result.close();

                if (modul.getPraktikum() == 0) {
                    throw new IllegalArgumentException("Das übergebene Modul sieht kein Praktikum vor.");

                } else {
                    result = select.executeQuery("Select * from Praktikumsteilnahme where Matrikel = '" + matrikel + "' and Mkuerzel = '" + modul.getKuerzel() + "' and Semester = '" + semester + "'");

                    if (result.next()) {
                        throw new IllegalArgumentException("Es existiert bereits eine Anmeldung des übergebenen Studenten am übergebenen Modul im übergebenen Semester.");
                    } else {
                        result.close();

                        String sql = "Insert into Praktikumsteilnahme(Matrikel, MKuerzel, Semester) values ('" + matrikel + "', '" + modul.getKuerzel() + "', '" + semester + "')";
                        insertion = select.executeUpdate(sql);

                        if (insertion > 0) {
                            enroll = true;
                        }

                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enroll;
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
        allStudienrichtung = new ArrayList<Studienrichtung>();
        StudienrichtungWhs sr;
        ResultSet result;

        try {
            Statement select = con.createStatement();
            result = select.executeQuery("select SKUERZEL, NAME from STUDIENRICHTUNG");

            while (result.next()) {
                String kuerzel;
                String name;

                kuerzel = result.getString(1);
                name = result.getString(2);
                if (result.wasNull()) {
                    name = null;
                }

                sr = new StudienrichtungWhs(kuerzel, name);
//                System.out.println(sr);
                allStudienrichtung.add(sr);

            }
            result.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return allStudienrichtung;
    }

    @Override
    public Collection<Modul> getAllModul() {
        ResultSet result;
        allModul = new ArrayList<Modul>();
        ModulWhs modul;

        try {
            Statement select = con.createStatement();
            result = select.executeQuery("select * from Modul");

            while (result.next()) {

                String kuerzel;
                String name;
                int vorlesung;
                int uebung;
                int praktikum;
                int credits;
                String kategorie;

                kuerzel = result.getString(1);

                name = result.getString(2);
                if (result.wasNull()) {
                    name = null;
                }
                vorlesung = result.getInt(3);
                if (result.wasNull()) {
                    vorlesung = 0;
                }
                uebung = result.getInt(4);
                if (result.wasNull()) {
                    uebung = 0;
                }
                praktikum = result.getInt(5);
                if (result.wasNull()) {
                    praktikum = 0;
                }
                credits = result.getInt(6);
                if (result.wasNull()) {
                    credits = 0;
                }
                kategorie = result.getString(7);
                if (result.wasNull()) {
                    kategorie = null;
                }
                modul = new ModulWhs(kuerzel, name, vorlesung, uebung, praktikum, credits, kategorie);
                allModul.add(modul);

            }
            result.close();

        } catch (SQLException ex) {
            Logger.getLogger(Implementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(allModul);
        return allModul;
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
