/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.whs.dba.ApplicationException;
import edu.whs.dba.entity.Student;
import edu.whs.dba.entity.Studienrichtung;
import entities.StudentWhs;
import entities.StudienrichtungWhs;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class implementationTest {
    
    Implementation impl;
    
    
    @Before
    public void setUp() throws SQLException {
        impl = new Implementation();
//        impl.startCon("jdbc:derby:C:/Users/Alex/Desktop/DBA JDBC/DBA-Praktikum-JavaDB/dba-praktikum", "", "");
        impl.startCon("jdbc:derby:C:\\Users\\Alex\\Desktop\\DBA JDBC\\DBA-Praktikum-JavaDB\\dba-praktikum", "", "");
        
        
    }
    
    
    
    @Test
    public void testGetAllStudienrichtung() throws SQLException{
        
//    impl.startCon("jdbc:derby:C:\\Users\\Alex\\Desktop\\DBA JDBC\\DBA-Praktikum-JavaDB\\dba-praktikum", "", "");
    
    ArrayList<Studienrichtung> test = new ArrayList<Studienrichtung>();
    test.add(new StudienrichtungWhs("MI", "Medieninformatik"));
    test.add(new StudienrichtungWhs("WI", "Wirtschaftsinformatik"));
    test.add(new StudienrichtungWhs("PI", "Praktische Informatik"));
    test.add(new StudienrichtungWhs("TI", "Technische Informatik"));
        System.out.println(test);
    
    ArrayList<Studienrichtung> test2 = new ArrayList<Studienrichtung>();
    test2.add(new StudienrichtungWhs("MI", "Medieninformatik"));
    test2.add(new StudienrichtungWhs("WI", "Wirtschaftsinformatik"));
    test2.add(new StudienrichtungWhs("PI", "Praktische Informatik"));
    test2.add(new StudienrichtungWhs("TI", "Technische Informatik"));
    
    
    
    assertEquals(test, impl.getAllStudienrichtung());
//    
}
    
    @Test
    public void testAddStudent() throws ApplicationException{
        
        impl.getAllStudienrichtung();
        
        StudienrichtungWhs testSr = new StudienrichtungWhs("KP", "Kein Plan");
        try{
            impl.addStudent("0123", "Hans", "Peter", "Musterstadt", testSr);
            fail();
        } catch (IllegalArgumentException ex){
            assertEquals(ex.getMessage(), "Das angegebene Kürzel für die Studienrichtung referenziert keinen Datensatz aus der Tabelle STUDIENRICHTUNG");
        }
        
    }
//    @Test
//    public void testAddStudent1() throws ApplicationException{
//        
//        impl.getAllStudienrichtung();
//        
//        StudienrichtungWhs testSr = new StudienrichtungWhs("WI", "Wirtschaftsinformatik");
//       
//        impl.addStudent("0124", "Hans", "Peter", "Musterstadt", testSr);
//        
//        
//    }
    @Test
    public void testAddStudent2() throws ApplicationException{
        
        impl.getAllStudienrichtung();
        
        StudienrichtungWhs testSr = new StudienrichtungWhs("WI", "Wirtschaftsinformatik");
        try{
            impl.addStudent("0124", "Hans", "Peter", "Musterstadt", testSr);
            fail();
        } catch (IllegalArgumentException ex){
            assertEquals(ex.getMessage(), "Die übergebene Matrikelnummer des Studenten existiert bereits.");
        }
        
    }
    
    @Test
    public void testGetAllStudent(){
        
        impl.getAllStudienrichtung();
        StudienrichtungWhs sr = new StudienrichtungWhs("WI", "Wirtschaftsinformatik");
        ArrayList<Student> test = new ArrayList<Student>();
        StudentWhs student = new StudentWhs("0124", "Hans", "Peter", "Musterstadt", sr);
        test.add(student);
        
        assertEquals(test, impl.getAllStudent());
    }
    
    
    @Test
    public void testGetAllModul(){
        assertEquals("egal", impl.getAllModul());
    }
    
//    @Test
//    public void testEnroll(){
//        
//        impl.getAllStudienrichtung();
//        
//        StudienrichtungWhs sr = new StudienrichtungWhs("WI", "Wirtschaftsinformatik");
//        impl.enroll("0126", "Tankengine", "Thomas", "Beim dicken Schaffner", sr, , semester)
//        
//    }
    
    
}
