/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import domen.Trener;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author MataVS
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {

    Trener trener;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Trener)){
            throw new Exception("UBACITI GRESKU IZ DOKUMENTACIJE!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Trener> sviTreneri = broker.getAll((Trener) objekat, null);
        System.out.println("KLASA LoginOperacija SO "+sviTreneri);
        
        if(sviTreneri.contains((Trener) objekat)){
            for(Trener t: sviTreneri){
                if(t.equals((Trener) objekat)){
                    trener=t;
                    return;
                    }
                }
        }
        trener=null;
    }

    public Trener getTrener() {
        return trener;
    }
    
    
    
}
