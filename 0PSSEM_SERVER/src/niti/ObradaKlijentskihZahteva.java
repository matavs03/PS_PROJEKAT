/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.NivoForme;
import domen.Sertifikat;
import domen.Trener;
import domen.Trening;
import domen.Trkac;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import java.sql.SQLException;
/**
 *
 * @author MataVS
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket s;
    Posiljalac pos;
    Primalac prim;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        this.pos = new Posiljalac(s);
        this.prim = new Primalac(s);
    }

    @Override
    public void run() {
        try {
            while (!kraj) {

                Zahtev zahtev = (Zahtev) prim.primi();
                System.out.println("KLASA OKZ(PARAMETAR OD KLIJENTA): " + zahtev.getParametar());
                System.out.println("KLASA OKZ(OPERACIJA OD KLIJENTA): " + zahtev.getOperacija());

                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        Trener t = (Trener) zahtev.getParametar();

                        t = Controller.getInstance().login(t);

                        odgovor.setOdgovor(t);
                        break;

                    case UCITAJ_TRKACE:
                        List<Trkac> trkaci = Controller.getInstance().ucitajTrkace();

                        odgovor.setOdgovor(trkaci);
                        break;

                    case OBRISI_TRKACA:
                        try {
                            Trkac trkac = (Trkac) zahtev.getParametar();
                            Controller.getInstance().obrisiTrkaca(trkac);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }

                        break;

                    case UCITAJ_NIVO_FORME:
                        List<NivoForme> nivoiForme = Controller.getInstance().ucitajNivoForme();

                        odgovor.setOdgovor(nivoiForme);
                        break;

                    case DODAJ_TRKACA:
                        try {
                            Controller.getInstance().dodajTrkaca((Trkac) zahtev.getParametar());
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case UCITAJ_TRENINGE:
                        List<Trening> treninzi = Controller.getInstance().ucitajTreninge();
                        odgovor.setOdgovor(treninzi);
                        break;

                    case OBRISI_TRENING:

                        try {
                            Trening treningZaBrisanje = (Trening) zahtev.getParametar();
                            Controller.getInstance().obrisiTrening(treningZaBrisanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;

                    case DODAJ_TRENING:
                        try {
                            Trening noviTrening = (Trening) zahtev.getParametar();
                            Controller.getInstance().dodajTrening(noviTrening);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case OBRISI_NIVO_FORME:
                        try {
                            NivoForme nf = (NivoForme) zahtev.getParametar();
                            Controller.getInstance().obrisiNivoForme(nf);
                            odgovor.setOdgovor(null);
                        }catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case DODAJ_NIVO_FORME:
                        try {
                            NivoForme nf = (NivoForme) zahtev.getParametar();
                            Controller.getInstance().dodajNivoForme(nf);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case UCITAJ_TRENERE:
                        List<Trener> treneri = Controller.getInstance().ucitajTrenere();
                        odgovor.setOdgovor(treneri);
                        break;
                        
                    case OBRISI_TRENERA:
                        Trener trenerZaBrisanje = (Trener) zahtev.getParametar();
                        try {
                            Controller.getInstance().obrisiTrenera(trenerZaBrisanje);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case DODAJ_TRENERA:
                        Trener noviTrener = (Trener) zahtev.getParametar();
                        try {
                            Controller.getInstance().dodajTrenera(noviTrener);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    
                    case UCITAJ_SERTIFIKATE:
                        List<Sertifikat> sertifikati = Controller.getInstance().ucitajSertifikate();
                        odgovor.setOdgovor(sertifikati);
                        break;
                        
                    case OBRISI_SERTIFIKAT:
                        Sertifikat s = (Sertifikat) zahtev.getParametar();
                        try {
                            Controller.getInstance().obrisiSertifikat(s);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    
                    case DODAJ_SERTIFIKAT:
                        Sertifikat ser = (Sertifikat) zahtev.getParametar();
                        try {
                            Controller.getInstance().dodajSertifikat(ser);
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case AZURIRAJ_TRKACA:
                        try {
                            Controller.getInstance().azurirajTrkaca((Trkac) zahtev.getParametar());
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                        
                    case AZURIRAJ_TRENING:
                        try {
                            Controller.getInstance().azurirajTrening((Trening) zahtev.getParametar());
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    default:
                        System.out.println("Operacija ne postoji");
                }
                System.out.println("KLASA OBRADAKLZAHTEVA: " + odgovor.getOdgovor());
                pos.posalji(odgovor);
            }
        } catch (SocketException ex) {
            System.out.println("Klijent naglo zatvorio vezu");

        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        
        finally {
            try {
                s.close();
                System.out.println("Zatvorena konekcija sa klijentom.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void prekiniNit() {
        kraj = true;
        try {
            s.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
