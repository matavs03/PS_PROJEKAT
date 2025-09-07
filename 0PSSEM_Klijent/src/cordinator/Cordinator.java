/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import controller.DodajEvidencijuTreningaController;
import controller.DodajNivoFormeController;
import controller.DodajSertifikatController;
import controller.DodajStavkuEvidencijeTreningaController;
import controller.DodajTrenerSertifikatController;
import controller.DodajTreneraController;
import controller.DodajTreningController;
import controller.DodajTrkacaController;
import controller.GlavnaFormaController;
import controller.LoginController;
import controller.PrikazEvidencijaTreningaController;
import controller.PrikazNivoaFormeController;
import controller.PrikazSertifikataController;
import controller.PrikazTreningaController;
import controller.PrikazTrkacaController;
import controller.PrikaziTrenerSertifikatController;
import controller.PrikaziTrenereController;
import domen.StavkaEvidencijeTreninga;
import domen.Trener;
import forme.DodajEvidencijuTreningaForma;
import forme.DodajNivoFormeForma;
import forme.DodajSertifikatForma;
import forme.DodajStavkuEvidencijeTreningaForma;
import forme.DodajTrenerSertifikatForma;
import forme.DodajTreneraForma;
import forme.DodajTreningForma;
import forme.DodajTrkacaForma;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazEvidencijaTreningaForma;
import forme.PrikazNivoaFormeForma;
import forme.PrikazSertifikataForma;
import forme.PrikazTreningaForma;
import forme.PrikazTrkacaForma;
import forme.PrikaziTrenerSertifikatForma;
import forme.PrikaziTreneraForma;
import forme.mod.FormaMod;
import java.util.HashMap;
import java.util.List;
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
    private PrikazEvidencijaTreningaController petController;
    private DodajEvidencijuTreningaController detController;
    private DodajStavkuEvidencijeTreningaController dsetController;
    private PrikaziTrenerSertifikatController ptsController;
    private DodajTrenerSertifikatController dtsController;

    private Cordinator() {
        parametri = new HashMap<>();
    }

    public static Cordinator getInstance() {
        if (instance == null) {
            instance = new Cordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
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
        dtfController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikazNivoaFormeFormu() {
        pnnfController = new PrikazNivoaFormeController(new PrikazNivoaFormeForma());
        pnnfController.otvoriFormu();
    }

    public void otvoriDodajNivoFormeFormu() {
        dnfController = new DodajNivoFormeController(new DodajNivoFormeForma());
        dnfController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikaziTrenereFormu() {
        ptrenereController = new PrikaziTrenereController(new PrikaziTreneraForma());
        ptrenereController.otvoriFormu();
    }

    public void otvoriDodajTreneraFormu() {
        dtreneraController = new DodajTreneraController(new DodajTreneraForma());
        dtreneraController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikazSertifikataFormu() {
        psController = new PrikazSertifikataController(new PrikazSertifikataForma());
        psController.otvoriFormu();
    }

    public void otvoriDodajSertifikatFormu() {
        dsController = new DodajSertifikatController(new DodajSertifikatForma());
        dsController.otvoriFormu(FormaMod.DODAJ);
    }

    public void dodajParam(String s, Object o) {
        parametri.put(s, o);
    }

    public Object vratiParam(String s) {
        return parametri.get(s);
    }

    public void otvoriIzmeniTrkacaFormu() {
        dtController = new DodajTrkacaController(new DodajTrkacaForma());
        dtController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazTrkacaFormu() {
        ptController.pripremiFormu();
    }

    public void otvoriIzmeniTreningFormu() {
        dtfController = new DodajTreningController(new DodajTreningForma());
        dtfController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazTreningaFormu() {
        ptrController.pripremiFormu();
    }

    public void otvoriIzmeniNivoFormeFormu() {
        dnfController = new DodajNivoFormeController(new DodajNivoFormeForma());
        dnfController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazNivoaFormiFormu() {
        pnnfController.pripremiFormu();
    }

    public void otvoriIzmeniTreneraFormu() {
        dtreneraController = new DodajTreneraController(new DodajTreneraForma());
        dtreneraController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazTreneraFormu() {
        ptrenereController.pripremiFormu();
    }

    public void otvoriIzmeniSertifikatFormu() {
        dsController = new DodajSertifikatController(new DodajSertifikatForma());
        dsController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazSertifikataFormu() {
        psController.pripremiFormu();
    }

    public void otvoriPrikazEvidencijaTreningaFormu() {
        petController = new PrikazEvidencijaTreningaController(new PrikazEvidencijaTreningaForma());
        petController.otvoriFormu();
    }

    public void otvoriDodajEvidencijuTreningaFormu() {
        detController = new DodajEvidencijuTreningaController(new DodajEvidencijuTreningaForma());
        detController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriIzmeniEvidencijuTreningaFormu() {
        detController = new DodajEvidencijuTreningaController(new DodajEvidencijuTreningaForma());
        detController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziPrikazEvidencijeTreningaFormu() {
        petController.pripremiFormu();
    }

    public void otvoriDodajStavkuEvidencijeTreningaFormu() {
        dsetController = new DodajStavkuEvidencijeTreningaController(new DodajStavkuEvidencijeTreningaForma());
        dsetController.otvoriFormu(FormaMod.DODAJ);
    }

    public void osveziPrikazStavki(List<StavkaEvidencijeTreninga> stavke) {
        petController.osveziTabeluStavki();
    }

    public void dodajUListuDodatih(StavkaEvidencijeTreninga stavka) {
        petController.getStavke().add(stavka);
        petController.getNoveStavke().add(stavka);
        petController.osveziTabeluStavki();

    }

    public void otvoriIzmeniStavkuEvidencijeTreninga() {
        dsetController = new DodajStavkuEvidencijeTreningaController(new DodajStavkuEvidencijeTreningaForma());
        dsetController.otvoriFormu(FormaMod.IZMENI);
    }

    public void dodajUListuIzmenjenih(StavkaEvidencijeTreninga stavka) {
    List<StavkaEvidencijeTreninga> stavke = petController.getStavke();

    for (int i = 0; i < stavke.size(); i++) {
        StavkaEvidencijeTreninga st = stavke.get(i);
        if (st.getEvidencija().getIdEvidencijaTreninga()==stavka.getEvidencija().getIdEvidencijaTreninga() && st.getRb() == stavka.getRb()) {
            // menjaš objekat direktno u glavnoj listi
            st.setDatumPrisustva(stavka.getDatumPrisustva());
            st.setOcena(stavka.getOcena());
            st.setTrening(stavka.getTrening());
            petController.getIzmenjeneStavke().add(st);
            break;
        }
    }
    petController.osveziTabeluStavki();
    
    
}

    public void otvoriPrikaziTrenerSertifikatFormu() {
        ptsController = new PrikaziTrenerSertifikatController(new PrikaziTrenerSertifikatForma());
        ptsController.otvoriFormu();
    }

    public void otvoriDodajTrenerSertifikatFormu() {
        dtsController = new DodajTrenerSertifikatController(new DodajTrenerSertifikatForma());
        dtsController.otvoriFormu();
    }

}
