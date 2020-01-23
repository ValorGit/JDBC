/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.whs.dba.entity.Studienrichtung;
import entitys.Studienrichtung_imp;
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
    test.add(new Studienrichtung_imp("MI", "Medieninformatik"));
    test.add(new Studienrichtung_imp("WI", "Wirtschaftsinformatik"));
    test.add(new Studienrichtung_imp("PI", "Praktische Informatik"));
    test.add(new Studienrichtung_imp("TI", "Technische Informatik"));
    
    assertEquals(test, impl.getAllStudienrichtung());
    
}
    
}
