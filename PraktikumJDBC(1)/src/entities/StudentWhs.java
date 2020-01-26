/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import edu.whs.dba.entity.Student;
import edu.whs.dba.entity.Studienrichtung;
import java.util.Objects;

/**
 *
 * @author Alex
 */
public class StudentWhs implements Student{
    
    private String matrikel;
    private String nachname;
    private String vorname;
    private String adresse;
    private Studienrichtung studienrichtung;
    
    public StudentWhs(String matrikel, String nachname, String vorname, String adresse, Studienrichtung studienrichtung){
        this.matrikel = matrikel;
        this.nachname = nachname;
        this.vorname = vorname;
        this.adresse = adresse;
        this.studienrichtung = studienrichtung;
    }
    

    @Override
    public String getMatrikel() {
        return matrikel;
    }

    @Override
    public String getName() {
        return nachname;
    }

    @Override
    public String getVorname() {
        return vorname;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    @Override
    public Studienrichtung getStudienrichtung() {
        return studienrichtung;
    }
    
    public void setMatrikel(String matrikel){
        this.matrikel = matrikel;
    }
    
    public void setName(String nachname){
        this.nachname = nachname;
    }
    
    public void setVorname(String vorname){
        this.vorname = vorname;       
    }
    
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    
    public void setStudienrichtung(StudienrichtungWhs studienrichtung){
        this.studienrichtung = studienrichtung;
    }
    
    @Override
    public int hashCode(){
        return matrikel.hashCode() + nachname.hashCode() + vorname.hashCode() + adresse.hashCode() + studienrichtung.hashCode();
        
    }

    @Override
    public boolean equals(Object obj) {
       boolean istGleich = false;
       
       if(obj instanceof Student){
           StudentWhs student = (StudentWhs) obj;
           
           istGleich = this.matrikel.equals(student.matrikel) && this.vorname.equals(student.vorname) 
                   && this.nachname.equals(student.nachname) && this.adresse.equals(student.adresse) 
                   && this.studienrichtung.equals(student.studienrichtung);
       }
       return istGleich;
    }
    
    @Override
    public String toString(){
        return "Matrikel:" + this.matrikel + ", Nachname:" + this.nachname + ", Vorname:" 
                + this.vorname + ", Adresse:" + this.adresse + ", Studienrichtung:" + this.studienrichtung.toString();
    }
    
}
