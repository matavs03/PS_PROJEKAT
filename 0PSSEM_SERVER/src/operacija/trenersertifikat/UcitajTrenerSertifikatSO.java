/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.trenersertifikat;

import domen.TrenerSertifikat;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author lazar
 */
public class UcitajTrenerSertifikatSO extends ApstraktnaGenerickaOperacija{

    public List<TrenerSertifikat> lista;

    public List<TrenerSertifikat> getLista() {
        return lista;
    }
    
    
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista = broker.getAll(objekat, " JOIN trener ON trenersertifikat.trener = trener.idTrener JOIN sertifikat ON trenersertifikat.sertifikat = sertifikat.idSertifikat");
    }
    
}
