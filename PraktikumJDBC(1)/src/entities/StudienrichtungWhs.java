package entities;


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
public class StudienrichtungWhs implements Studienrichtung {
    
    
    private String kuerzel;
    private String name;
    
    public StudienrichtungWhs(String kuerzel, String name){
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
            StudienrichtungWhs studien = (StudienrichtungWhs) obj;
            
            istGleich = (studien.kuerzel.equals(this.kuerzel)) && (studien.name.equals(this.name));
            
        }
        return istGleich;
    }
    
    @Override
    public String toString(){
        return this.kuerzel +", " + this.name;
    }
    
    
    
}
