/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.trenersertifikat;

import domen.TrenerSertifikat;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author lazar
 */
public class DodajTrenerSertifikatSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof TrenerSertifikat)){
            throw new Exception("UBACITI GRESKU IZ DOKUMENTACIJE!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add(objekat);
    }
    
}
