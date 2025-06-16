/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import domen.Trkac;
import java.util.List;

/**
 *
 * @author MataVS
 */
public class UcitajTrkaceSO extends ApstraktnaGenerickaOperacija {
    private List<Trkac> trkaci;

    public List<Trkac> getTrkaci() {
        return trkaci;
    }
    
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
     
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        
        trkaci = broker.getAll(objekat, kljuc);
        
    }
    
}
