/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.NivoForme;
import domen.Trener;
import domen.Trening;
import domen.Trkac;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MataVS
 */
public class Komunikacija {
    private Socket s;
    private Posiljalac pos;
    private Primalac prim;
    private static Komunikacija instance;
    
    private Komunikacija(){
        
    }
    
    public static Komunikacija getInstance(){
        if(instance==null)
            instance=new Komunikacija();
        return instance;
    }
    
    public void konekcija(){
        try {
            s = new Socket("localhost", 9000);
            pos = new Posiljalac(s);
            prim = new Primalac(s);
        } catch (IOException ex) {
            System.out.println("Server nije povezan");
        }
    }

    public Trener login(String username, String password) {
        Trener t = new Trener();
        t.setUsername(username);
        t.setPassword(password);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, t);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        t = (Trener) odg.getOdgovor();
        System.out.println(t);
        return t;
    }

    public List<Trkac> ucitajTrkace() {
        List<Trkac> trkaci = new ArrayList<>();        
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TRKACE, null);
//        zahtev.setOperacija(Operacija.UCITAJ_TRKACE);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        trkaci = (List<Trkac>) odg.getOdgovor();
        return trkaci;
    }

    public void obrisiTrkaca(Trkac t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_TRKACA, t);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<NivoForme> ucitajNivoForme() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_NIVO_FORME, null);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        List<NivoForme> lista = (List<NivoForme>) odg.getOdgovor();
        return lista;
    }

    public void dodajTrkaca(Trkac trkac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TRKACA, trkac);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<Trening> ucitajTreninge() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TRENINGE, null);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        return (List<Trening>) odg.getOdgovor();
    }

    public void obrisiTrening(Trening treningZaBrisanje) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_TRENING, treningZaBrisanje);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void dodajTrening(Trening noviTrening) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TRENING, noviTrening);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<NivoForme> ucitajNivoeForme() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_NIVO_FORME, null);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        return (List<NivoForme>) odg.getOdgovor();
    }

    public void obrisiNivoForme(NivoForme nf) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_NIVO_FORME, nf);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }
    
}
