/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.nivoforme;

import domen.NivoForme;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;
import operacija.trkac.DodajTrkacaSO;

/**
 *
 * @author lazar
 */
public class UcitajNivoFormeSO extends ApstraktnaGenerickaOperacija{

    List<NivoForme> lista;

    public List<NivoForme> getLista() {
        return lista;
    }
    
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista=broker.getAll(objekat, kljuc);
        
    }
    
}
