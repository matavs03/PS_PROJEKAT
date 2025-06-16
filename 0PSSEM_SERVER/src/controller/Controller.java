/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import domen.Trkac;
import java.util.List;
import operacija.LoginOperacija;
import operacija.UcitajTrkaceSO;

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
}
