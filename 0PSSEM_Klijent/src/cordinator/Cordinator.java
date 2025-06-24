/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import controller.DodajTreningController;
import controller.DodajTrkacaController;
import controller.GlavnaFormaController;
import controller.LoginController;
import controller.PrikazTreningaController;
import controller.PrikazTrkacaController;
import domen.Trener;
import forme.DodajTreningForma;
import forme.DodajTrkacaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazTreningaForma;
import forme.PrikazTrkacaForma;

/**
 *
 * @author MataVS
 */
public class Cordinator {
    private static Cordinator instance;
    private Trener ulogovani;
    private LoginController loginController;
    private GlavnaFormaController gfController;
    private PrikazTrkacaController ptController;
    private DodajTrkacaController dtController;
    private PrikazTreningaController ptrController;
    private DodajTreningController dtfController;
    
    private Cordinator(){
        
    }
    
    public static Cordinator getInstance(){
        if(instance==null)
            instance = new Cordinator();
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }
    
    public void otvoriGlavnuFormu(){
        gfController = new GlavnaFormaController(new GlavnaForma());
        gfController.otvoriFormu();
    }
    
    public void otvoriPrikazTrkacaFormu() {
        ptController = new PrikazTrkacaController(new PrikazTrkacaForma());
        ptController.otvoriFormu();
    }

    public void setUlogovani(Trener ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Trener getUlogovani() {
        return ulogovani;
    }

    public void otvoriDodajTrkacaFormu() {
        dtController = new DodajTrkacaController(new DodajTrkacaForma());
        dtController.otvoriFormu();
    }
    
    public void otvoriPrikazTreningaFormu() {
        ptrController = new PrikazTreningaController(new PrikazTreningaForma());
        ptrController.otvoriFormu();
    }

    public void otvoriDodajTreningFormu() {
        dtfController = new DodajTreningController(new DodajTreningForma());
        dtfController.otvoriFormu();
    }

    
    
    
    
}
