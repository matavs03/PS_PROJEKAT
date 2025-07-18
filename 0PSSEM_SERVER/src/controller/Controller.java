/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.NivoForme;
import domen.Sertifikat;
import domen.Trener;
import domen.Trening;
import domen.Trkac;
import java.util.List;
import operacija.nivoforme.UcitajNivoFormeSO;
import operacija.LoginOperacija;
import operacija.nivoforme.DodajNivoFormeSO;
import operacija.nivoforme.ObrisiNivoFormeSO;
import operacija.sertifikat.DodajSertifikatSO;
import operacija.sertifikat.ObrisiSertifikatSO;
import operacija.sertifikat.UcitajSertifikateSO;
import operacija.trener.DodajTreneraSO;
import operacija.trener.ObrisiTreneraSO;
import operacija.trener.UcitajTrenereSO;
import operacija.trening.DodajTreningSO;
import operacija.trening.ObrisiTreningSO;
import operacija.trening.UcitajTreningeSO;
import operacija.trkac.AzurirajTrkacaSO;
import operacija.trkac.DodajTrkacaSO;
import operacija.trkac.ObrisiTrkacaSO;
import operacija.trkac.UcitajTrkaceSO;

/**
 *
 * @author MataVS
 */
public class Controller {
    private static Controller instance;
    
    private Controller(){
        
    }
    
    public static Controller getInstance(){
        if(instance==null)
            instance = new Controller();
        return instance;
    }

    public Trener login(Trener t) throws Exception {
        LoginOperacija operacija = new LoginOperacija();
        operacija.izvrsi(t, null);
        return operacija.getTrener();
    }

    public List<Trkac> ucitajTrkace() throws Exception {
        UcitajTrkaceSO operacija = new UcitajTrkaceSO();
        Trkac t=new Trkac();
        operacija.izvrsi(t, null);
        
        
        return operacija.getTrkaci();
    }

    public void obrisiTrkaca(Trkac trkac) throws Exception {
        
        ObrisiTrkacaSO operacija = new ObrisiTrkacaSO();
        operacija.izvrsi(trkac, null);
    }

    public List<NivoForme> ucitajNivoForme() throws Exception {
        NivoForme nf = new NivoForme();
        UcitajNivoFormeSO operacija = new UcitajNivoFormeSO();
        operacija.izvrsi(nf, null);
        return operacija.getLista();
    }

    public void dodajTrkaca(Trkac trkac) throws Exception {
        DodajTrkacaSO operacija = new DodajTrkacaSO();
        operacija.izvrsi(trkac, null);
        
    }

    public List<Trening> ucitajTreninge() throws Exception {
        UcitajTreningeSO operacija = new UcitajTreningeSO();
        operacija.izvrsi(new Trening(), null);
        return operacija.getLista();
    }

    public void obrisiTrening(Trening treningZaBrisanje) throws Exception {
        ObrisiTreningSO operacija = new ObrisiTreningSO();
        operacija.izvrsi(treningZaBrisanje, null);
    }

    public void dodajTrening(Trening noviTrening) throws Exception {
        DodajTreningSO operacija = new DodajTreningSO();
        operacija.izvrsi(noviTrening, null);
    }

    public void obrisiNivoForme(NivoForme nf) throws Exception {
        ObrisiNivoFormeSO operacija = new ObrisiNivoFormeSO();
        operacija.izvrsi(nf, null);
    }

    public void dodajNivoForme(NivoForme nf) throws Exception {
        DodajNivoFormeSO operacija = new DodajNivoFormeSO();
        operacija.izvrsi(nf, null);
    }

    public List<Trener> ucitajTrenere() throws Exception {
        UcitajTrenereSO operacija = new UcitajTrenereSO();
        operacija.izvrsi(new Trener(), null);
        return operacija.getLista();
    }

    public void obrisiTrenera(Trener trenerZaBrisanje) throws Exception {
        ObrisiTreneraSO operacija = new ObrisiTreneraSO();
        operacija.izvrsi(trenerZaBrisanje, null);
    }

    public void dodajTrenera(Trener noviTrener) throws Exception {
        DodajTreneraSO operacija = new DodajTreneraSO();
        operacija.izvrsi(noviTrener, null);
    }

    public List<Sertifikat> ucitajSertifikate() throws Exception {
        UcitajSertifikateSO operacija = new UcitajSertifikateSO();
        operacija.izvrsi(new Sertifikat(), null);
        return operacija.getLista();
    }

    public void obrisiSertifikat(Sertifikat s) throws Exception {
        ObrisiSertifikatSO operacija = new ObrisiSertifikatSO();
        operacija.izvrsi(s, null);
    }

    public void dodajSertifikat(Sertifikat ser) throws Exception {
        DodajSertifikatSO operacija = new DodajSertifikatSO();
        operacija.izvrsi(ser, null);
    }

    public void azurirajTrkaca(Trkac trkac) throws Exception {
        AzurirajTrkacaSO operacija = new AzurirajTrkacaSO();
        operacija.izvrsi(trkac, null);
    }

    
}
