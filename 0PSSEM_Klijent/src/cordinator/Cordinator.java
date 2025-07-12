/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import controller.DodajNivoFormeController;
import controller.DodajSertifikatController;
import controller.DodajTreneraController;
import controller.DodajTreningController;
import controller.DodajTrkacaController;
import controller.GlavnaFormaController;
import controller.LoginController;
import controller.PrikazNivoaFormeController;
import controller.PrikazSertifikataController;
import controller.PrikazTreningaController;
import controller.PrikazTrkacaController;
import controller.PrikaziTrenereController;
import domen.Trener;
import forme.DodajNivoFormeForma;
import forme.DodajSertifikatForma;
import forme.DodajTreneraForma;
import forme.DodajTreningForma;
import forme.DodajTrkacaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazNivoaFormeForma;
import forme.PrikazSertifikataForma;
import forme.PrikazTreningaForma;
import forme.PrikazTrkacaForma;
import forme.PrikaziTreneraForma;
import forme.mod.FormaMod;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MataVS
 */
public class Cordinator {
    private static Cordinator instance;
    private Map<String, Object> parametri;
    
    private Trener ulogovani;
    private LoginController loginController;
    private GlavnaFormaController gfController;
    private PrikazTrkacaController ptController;
    private DodajTrkacaController dtController;
    private PrikazTreningaController ptrController;
    private DodajTreningController dtfController;
    private PrikazNivoaFormeController pnnfController;
    private DodajNivoFormeController dnfController;
    private PrikaziTrenereController ptrenereController;
    private DodajTreneraController dtreneraController;
    private PrikazSertifikataController psController;
    private DodajSertifikatController dsController;
    
    private Cordinator(){
        parametri = new HashMap<>();
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
        dtController.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void otvoriPrikazTreningaFormu() {
        ptrController = new PrikazTreningaController(new PrikazTreningaForma());
        ptrController.otvoriFormu();
    }

    public void otvoriDodajTreningFormu() {
        dtfController = new DodajTreningController(new DodajTreningForma());
        dtfController.otvoriFormu();
    }

    public void otvoriPrikazNivoaFormeFormu() {
        pnnfController = new PrikazNivoaFormeController(new PrikazNivoaFormeForma());
        pnnfController.otvoriFormu();
    }

    public void otvoriDodajNivoFormeFormu() {
        dnfController = new DodajNivoFormeController(new DodajNivoFormeForma());
        dnfController.otvoriFormu();
    }

    public void otvoriPrikaziTrenereFormu() {
        ptrenereController = new PrikaziTrenereController(new PrikaziTreneraForma());
        ptrenereController.otvoriFormu();
    }

    public void otvoriDodajTreneraFormu() {
        dtreneraController = new DodajTreneraController(new DodajTreneraForma());
        dtreneraController.otvoriFormu();
    }

    public void otvoriPrikazSertifikataFormu() {
        psController = new PrikazSertifikataController(new PrikazSertifikataForma());
        psController.otvoriFormu();
    }
    
    public void otvoriDodajSertifikatFormu(){
        dsController = new DodajSertifikatController(new DodajSertifikatForma());
        dsController.otvoriFormu();
    }
    
    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriIzmeniTrkacaFormu() {
        dtController = new DodajTrkacaController(new DodajTrkacaForma());
        dtController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazTrkacaFormu() {
        ptController.pripremiFormu();
    }
    
}
