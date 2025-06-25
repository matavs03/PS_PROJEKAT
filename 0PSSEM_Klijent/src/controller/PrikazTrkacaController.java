/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trkac;
import forme.PrikazTrkacaForma;
import forme.model.ModelTabeleTrkac;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        ptf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptf.getTblTrkaci().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste izabrali red");
                } else {
                    int potvrda = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete trkača?");

                    if (potvrda == JOptionPane.YES_OPTION) {
                        ModelTabeleTrkac mtt = (ModelTabeleTrkac) ptf.getTblTrkaci().getModel();
                        Trkac t = mtt.getLista().get(red);
                        try {
                            komunikacija.Komunikacija.getInstance().obrisiTrkaca(t);
                            JOptionPane.showMessageDialog(null, "Uspešno obrisan trkač");
                            pripremiFormu();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "UBACITI GRESKU IZ DOKUMENTACIJE!");
                        }
                    }

                }
            }
        });
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
