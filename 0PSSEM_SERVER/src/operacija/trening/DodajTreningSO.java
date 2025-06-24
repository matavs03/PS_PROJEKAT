/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.trening;

import domen.Trening;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author lazar
 */
public class DodajTreningSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Trening)){
            throw new Exception("UBACITI GRESKU IZ DOKUMENTACIJE!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add(objekat);
    }

    
    
}
