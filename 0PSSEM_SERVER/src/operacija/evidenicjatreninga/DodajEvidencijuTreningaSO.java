/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidenicjatreninga;

import domen.EvidencijaTreninga;
import domen.StavkaEvidencijeTreninga;
import operacija.ApstraktnaGenerickaOperacija;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author lazar
 */
public class DodajEvidencijuTreningaSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof EvidencijaTreninga)){
            throw new Exception("UBACITI GRESKU IZ DOKUMENTACIJE!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        EvidencijaTreninga et = (EvidencijaTreninga) objekat;


        int noviIdEvidencije = ((DbRepositoryGeneric) broker).addReturnId(et);
        et.setIdEvidencijaTreninga(noviIdEvidencije);


        for (StavkaEvidencijeTreninga st : et.getStavke()) {
            st.setEvidencija(et);
            broker.add(st);
        }
    }
    
}
