/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.NivoForme;
import domen.Trener;
import domen.Trening;
import domen.Trkac;
import java.util.List;
import operacija.nivoforme.UcitajNivoFormeSO;
import operacija.LoginOperacija;
import operacija.trening.DodajTreningSO;
import operacija.trening.ObrisiTreningSO;
import operacija.trening.UcitajTreningeSO;
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

    
}
