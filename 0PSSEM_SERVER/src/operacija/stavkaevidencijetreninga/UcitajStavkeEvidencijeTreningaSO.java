/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.stavkaevidencijetreninga;

import domen.StavkaEvidencijeTreninga;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author lazar
 */
public class UcitajStavkeEvidencijeTreningaSO extends ApstraktnaGenerickaOperacija{

    List<StavkaEvidencijeTreninga> lista = new ArrayList();

    public List<StavkaEvidencijeTreninga> getLista() {
        return lista;
    }
    
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN evidencijatreninga ON stavkaevidencijetreninga.evidencija = evidencijatreninga.idEvidencijaTreninga JOIN trening ON stavkaevidencijetreninga.trening = trening.idTrening WHERE evidencija="+kljuc;
        lista = broker.getAll(new StavkaEvidencijeTreninga(), uslov);
    }
    
}
