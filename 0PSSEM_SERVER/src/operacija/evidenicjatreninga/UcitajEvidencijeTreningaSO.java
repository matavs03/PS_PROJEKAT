/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidenicjatreninga;

import domen.EvidencijaTreninga;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author lazar
 */
public class UcitajEvidencijeTreningaSO extends ApstraktnaGenerickaOperacija{

    private List<EvidencijaTreninga> lista;

    public List<EvidencijaTreninga> getLista() {
        return lista;
    }

    
    
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN trener ON evidencijatreninga.trener = trener.idTrener JOIN trkac ON evidencijatreninga.trkac = trkac.idTrkac JOIN nivoforme ON trkac.nivoForme = nivoforme.idNivoForme";
        lista = broker.getAll(objekat, uslov);
    }
    
}
