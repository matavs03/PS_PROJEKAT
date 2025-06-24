/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.trening;

import domen.Trening;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author lazar
 */
public class UcitajTreningeSO extends ApstraktnaGenerickaOperacija {
    List<Trening> lista;

    public List<Trening> getLista() {
        return lista;
    }
    
    
        
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista = broker.getAll(objekat,  kljuc);
    }
    
}
