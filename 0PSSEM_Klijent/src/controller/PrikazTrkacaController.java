/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trkac;
import forme.PrikazTrkacaForma;
import forme.model.ModelTabeleTrkac;
import java.util.List;

/**
 *
 * @author MataVS
 */
public class PrikazTrkacaController {
    private final PrikazTrkacaForma ptf;

    public PrikazTrkacaController(PrikazTrkacaForma ptf) {
        this.ptf = ptf;
        addActionListeners();
    }

    private void addActionListeners() {
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptf.setLocationRelativeTo(null);
        ptf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Trkac> trkaci = komunikacija.Komunikacija.getInstance().ucitajTrkace();
        ptf.getTblTrkaci().setModel(new ModelTabeleTrkac(trkaci));
    }
    
    
}
