/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import edu.whs.dba.entity.Modul;
import java.util.Objects;

/**
 *
 * @author Alex
 */
public class ModulWhs implements Modul{
    
    private String kuerzel;
    private String name;
    private int vorlesung;
    private int uebung;
    private int praktikum;
    private int credits;
    private String kategorie;
    
    public ModulWhs(String kuerzel, String name, int vorlesung, int uebung, int praktikum, int credits, String kategorie){
        this.kuerzel = kuerzel;
        this.name = name;
        this.vorlesung = vorlesung;
        this.uebung = uebung;
        this.praktikum = praktikum;
        this.credits = credits;
        this.kategorie = kategorie;
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
    public int getVorlesung() {
        return vorlesung;
    }

    @Override
    public int getUebung() {
       return uebung;
    }

    @Override
    public int getPraktikum() {
        return praktikum;
    }

    @Override
    public int getCredits() {
        return credits;
    }
    
    public String getKategorie(){
        return this.kategorie;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean istGleich = false;
        
        if(obj instanceof ModulWhs){
            ModulWhs modul = (ModulWhs) obj;
            
            istGleich = this.kuerzel.equals(modul.kuerzel) && this.name.equals(modul.name) 
                    && this.vorlesung == modul.vorlesung && this.uebung == modul.uebung 
                    && this.praktikum == modul.praktikum && this.credits == modul.credits;
        }
        return istGleich;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.kuerzel);
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + this.vorlesung;
        hash = 17 * hash + this.uebung;
        hash = 17 * hash + this.praktikum;
        hash = 17 * hash + this.credits;
        return hash;
    }
    
    @Override
    public String toString(){
        return "MKuerzel:" + this.kuerzel + ", Name:" + this.name + ", V:" + this.vorlesung + ", Ü:" + this.uebung + ", P:" + this.praktikum + ", Credits:" + this.credits + ", KKuerzel:" +this.kategorie; 
    }
    
}
