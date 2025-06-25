/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.NivoForme;
import forme.PrikazNivoaFormeForma;
import forme.model.ModelTabeleNivoiForme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MataVS
 */
public class PrikazNivoaFormeController {

    private final PrikazNivoaFormeForma pnnf;

    public PrikazNivoaFormeController(PrikazNivoaFormeForma pnnf) {
        this.pnnf = pnnf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pnnf.setLocationRelativeTo(null);
        pnnf.setVisible(true);
    }

    private void pripremiFormu() {
        List<NivoForme> nivoi = komunikacija.Komunikacija.getInstance().ucitajNivoeForme();
        pnnf.getjTable1().setModel(new ModelTabeleNivoiForme(nivoi));
    }

    private void addActionListeners() {
        pnnf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pnnf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste izabrali red");
                } else {
                    int potvrda = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete nivo forme?");
                    if (potvrda == JOptionPane.YES_OPTION) {
                        ModelTabeleNivoiForme mtnf = (ModelTabeleNivoiForme) pnnf.getjTable1().getModel();
                        NivoForme nf = mtnf.getLista().get(red);
                        try {
                            komunikacija.Komunikacija.getInstance().obrisiNivoForme(nf);
                            JOptionPane.showMessageDialog(null, "USPEH");
                            pripremiFormu();
                        }catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "GRESKA");
                        }
                    }
                }
            }
        });
    }
}
