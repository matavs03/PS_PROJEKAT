/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.EvidencijaTreninga;
import domen.NivoForme;
import domen.Sertifikat;
import domen.StavkaEvidencijeTreninga;
import domen.Trener;
import domen.TrenerSertifikat;
import domen.Trening;
import domen.Trkac;
import java.util.List;
import operacija.nivoforme.UcitajNivoFormeSO;
import operacija.trener.PrijaviTreneraSO;
import operacija.evidenicjatreninga.AzurirajEvidencijuTreningaSO;
import operacija.evidenicjatreninga.DodajEvidencijuTreningaSO;
import operacija.evidenicjatreninga.ObrisiEvidencijuTreningaSO;
import operacija.evidenicjatreninga.UcitajEvidencijeTreningaSO;
import operacija.nivoforme.AzurirajNivoFormeSO;
import operacija.nivoforme.DodajNivoFormeSO;
import operacija.nivoforme.ObrisiNivoFormeSO;
import operacija.sertifikat.AzurirajSertifikatSO;
import operacija.sertifikat.DodajSertifikatSO;
import operacija.sertifikat.ObrisiSertifikatSO;
import operacija.sertifikat.UcitajSertifikateSO;
import operacija.trener.AzurirajTreneraSO;
import operacija.trener.DodajTreneraSO;
import operacija.trener.ObrisiTreneraSO;
import operacija.trener.UcitajTrenereSO;
import operacija.trenersertifikat.DodajTrenerSertifikatSO;
import operacija.trenersertifikat.ObrisiTrenerSertifikatSO;
import operacija.trenersertifikat.UcitajTrenerSertifikatSO;
import operacija.trening.AzurirajTreningSO;
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
        PrijaviTreneraSO operacija = new PrijaviTreneraSO();
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

    public void azurirajTrening(Trening trening) throws Exception {
        AzurirajTreningSO operacija = new AzurirajTreningSO();
        operacija.izvrsi(trening, null);
    }

    public void azurirajNivoForme(NivoForme nivoForme) throws Exception {
        AzurirajNivoFormeSO operacija = new AzurirajNivoFormeSO();
        operacija.izvrsi(nivoForme, null);
    }

    public void azurirajTrenera(Trener trener) throws Exception {
        AzurirajTreneraSO operacija = new AzurirajTreneraSO();
        operacija.izvrsi(trener, null);
    }

    public void azurirajSertifikat(Sertifikat sertifikat) throws Exception {
        AzurirajSertifikatSO operacija = new AzurirajSertifikatSO();
        operacija.izvrsi(sertifikat, null);
    }

    public List<EvidencijaTreninga> ucitajEvidencijeTreninga() throws Exception {
        UcitajEvidencijeTreningaSO operacija = new UcitajEvidencijeTreningaSO();
        operacija.izvrsi(new EvidencijaTreninga(), null);
        return operacija.getLista();
    }

    

    public void obrisiEvidencijuTreninga(EvidencijaTreninga evidencijaTreninga) throws Exception {
        ObrisiEvidencijuTreningaSO operacija = new ObrisiEvidencijuTreningaSO();
        operacija.izvrsi(evidencijaTreninga, null);
    }

    public void dodajEvidencijuTreninga(EvidencijaTreninga evidencijaTreninga) throws Exception {
        DodajEvidencijuTreningaSO operacija = new DodajEvidencijuTreningaSO();
        operacija.izvrsi(evidencijaTreninga, null);
    }

    public void azurirajEvidencijuTreninga(EvidencijaTreninga evidencijaTreninga) throws Exception {
        AzurirajEvidencijuTreningaSO operacija = new AzurirajEvidencijuTreningaSO();
        operacija.izvrsi(evidencijaTreninga, null);
    }


    public List<TrenerSertifikat> ucitajTrnerSertifikat() throws Exception {
        UcitajTrenerSertifikatSO operacija = new UcitajTrenerSertifikatSO();
        operacija.izvrsi(new TrenerSertifikat(), null);
        return operacija.getLista();
    }

    public void obrisiTrenerSertifikat(TrenerSertifikat trenerSertifikat) throws Exception {
        ObrisiTrenerSertifikatSO operacija = new ObrisiTrenerSertifikatSO();
        operacija.izvrsi(trenerSertifikat, null);
    }

    public void dodajTrenerSertifikat(TrenerSertifikat trenerSertifikat) throws Exception {
        DodajTrenerSertifikatSO operacija = new DodajTrenerSertifikatSO();
        operacija.izvrsi(trenerSertifikat, null);
    }

    

    
}
