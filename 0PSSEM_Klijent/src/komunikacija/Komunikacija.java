/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.EvidencijaTreninga;
import domen.NivoForme;
import domen.Sertifikat;
import domen.StavkaEvidencijeTreninga;
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

    public void dodajNivoForme(NivoForme nf) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_NIVO_FORME, nf);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }


    public List<Trener> ucitajTrenere() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_TRENERE, null);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        return (List<Trener>) odg.getOdgovor();
    }

    public void obrisiTrenera(Trener trenerZaBrisanje) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_TRENERA, trenerZaBrisanje);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void dodajTrenera(Trener noviTrener) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_TRENERA, noviTrener);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<Sertifikat> ucitajSertifikate() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SERTIFIKATE, null);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        return (List<Sertifikat>) odg.getOdgovor();
    }

    public void obrisiSertifikat(Sertifikat s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_SERTIFIKAT, s);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void dodajSertifikat(Sertifikat s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SERTIFIKAT, s);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void azurirajTrkaca(Trkac trkac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_TRKACA, trkac);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void azurirajTrening(Trening noviTrening) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_TRENING, noviTrening);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    

    public void azurirajNivoForme(NivoForme nf) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_NIVO_FORME, nf);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void azurirajTrenera(Trener t) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_TRENERA, t);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void azurirajSertifikat(Sertifikat s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_SERTIFIKAT, s);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public List<EvidencijaTreninga> ucitajEvidencijeTreninga() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_EVIDENCIJE_TRENINGA, null);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        List<EvidencijaTreninga> lista = (List<EvidencijaTreninga>) odg.getOdgovor();
        return lista;
    }

    public List<StavkaEvidencijeTreninga> ucitajStavkeEvidencijeTreninga(int idEvidencijaTreninga) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE_EVIDENCIJE_TRENINGA, idEvidencijaTreninga);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        List<StavkaEvidencijeTreninga> lista = (List<StavkaEvidencijeTreninga>) odg.getOdgovor();
        return lista;
    }

    public void obrisiEvidencijuTreninga(EvidencijaTreninga et) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_EVIDENCIJU_TRENINGA, et);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void dodajEvidencijuTreninga(EvidencijaTreninga et) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_EVIDENCIJU_TRENINGA, et);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void azurirajEvidencijuTreninga(EvidencijaTreninga et) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_EVIDENCIJU_TRENINGA, et);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void dodajStavkuEvidencijeTreninga(StavkaEvidencijeTreninga stavka) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_STAVKU_EVIDENCIJE_TRENINGA, stavka);
        pos.posalji(zahtev);
        Odgovor odg = (Odgovor) prim.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }
        else{
            throw (Exception) odg.getOdgovor();
        }
    }

    public void obrisiStavkuEvidencijeTreninga(StavkaEvidencijeTreninga set) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_STAVKU_EVIDENCIJE_TRENINGA, set);
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
