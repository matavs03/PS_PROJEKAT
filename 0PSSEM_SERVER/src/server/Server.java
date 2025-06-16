/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author MataVS
 */
public class Server extends Thread{
    
    boolean kraj = false;
    ServerSocket ss;
    List<ObradaKlijentskihZahteva> klijenti = new LinkedList<>();

    @Override
    public void run() {
       pokreniServer();
    }
    
    private int uzmiBrojPorta(){
        return Integer.parseInt(konfiguracija.Konfiguracija.getInstance().getProperty("port"));
    }
    
    
    public void pokreniServer(){
        try {
            ss = new ServerSocket(uzmiBrojPorta());
            while(!kraj){
            Socket s = ss.accept();
                System.out.println("Klijent povezan");    
                
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
        }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void zaustaviServer(){
        kraj=true;
        try {
            for(ObradaKlijentskihZahteva k: klijenti){
                k.prekiniNit();
            }
            ss.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
