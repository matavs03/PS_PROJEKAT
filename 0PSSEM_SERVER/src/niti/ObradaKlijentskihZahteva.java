/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.NivoForme;
import domen.Trener;
import domen.Trkac;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

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
        while(!kraj){
            try{
                Zahtev zahtev = (Zahtev) prim.primi();
                System.out.println("KLASA OKZ(PARAMETAR OD KLIJENTA): "+zahtev.getParametar());
                System.out.println("KLASA OKZ(OPERACIJA OD KLIJENTA): "+zahtev.getOperacija());
                
                Odgovor odgovor = new Odgovor();

                switch(zahtev.getOperacija()){
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
                            Controller.getInstance().dodajTrkaca((Trkac)zahtev.getParametar());
                            odgovor.setOdgovor(null);
                        } catch (Exception e) {
                            odgovor.setOdgovor(e);
                        }
                        break;
                    default:
                        System.out.println("Operacija ne postoji");
                }
                System.out.println("KLASA OBRADAKLZAHTEVA: "+odgovor.getOdgovor());
                pos.posalji(odgovor);
            }catch(Exception ex){
            
            }    
        }
    }
    
    public void prekiniNit(){
        kraj=true;
        try {
            s.close();
            interrupt();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
