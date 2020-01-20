package entitys;


import edu.whs.dba.entity.Studienrichtung;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class Studienrichtung_imp implements Studienrichtung {
    
    
    private String kuerzel;
    private String name;
    
    public Studienrichtung_imp(String kuerzel, String name){
        this.kuerzel = kuerzel;
        this.name = name;
    }
    
    public void setKuerzel(String kuerzel){
        this.kuerzel = kuerzel;
    }
    
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String getKuerzel() {
        return kuerzel;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int hashCode(){
        return kuerzel.hashCode() + name.hashCode();
    }
    
    @Override
    public boolean equals(Object obj){
        boolean istGleich = false;
           
        if(obj instanceof Studienrichtung){
            Studienrichtung_imp studien = (Studienrichtung_imp) obj;
            
            istGleich = (studien.kuerzel == this.kuerzel) && (studien.name == this.name);
            
        }
        return istGleich;
    }
    
    
    
}
