/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Trener;
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
        try {
            s.setSoTimeout(5000);
        } catch (SocketException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        Odgovor odg = (Odgovor) prim.primi();
        trkaci = (List<Trkac>) odg.getOdgovor();
        return trkaci;
    }
    
}
