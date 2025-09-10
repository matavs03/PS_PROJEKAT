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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MataVS
 */
public class PrikazNivoaFormeController {

    private final PrikazNivoaFormeForma pnnf;

    List<NivoForme> nivoi;
    List<NivoForme> nivoiFilter;

    public PrikazNivoaFormeController(PrikazNivoaFormeForma pnnf) {
        this.pnnf = pnnf;
        addActionListeners();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pnnf.setLocationRelativeTo(null);
        pnnf.setVisible(true);
    }

    public void pripremiFormu() {
        nivoi = komunikacija.Komunikacija.getInstance().ucitajNivoeForme();
        pnnf.getjTable1().setModel(new ModelTabeleNivoiForme(nivoi));
        pnnf.getBtnPretraziNivo().setVisible(false);
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
                            JOptionPane.showMessageDialog(null, "Sistem je obrisao nivo forme");
                            pripremiFormu();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Sistem ne može da obriše nivo forme");
                        }
                    }
                }
            }
        });

        pnnf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rec = pnnf.getTxtPretrazi().getText().toLowerCase().strip();
                nivoiFilter = new ArrayList<>();
                for (NivoForme nivoForme : nivoi) {
                    if (nivoForme.getOpis().toLowerCase().contains(rec)) {
                        nivoiFilter.add(nivoForme);
                    }
                }
                if (nivoiFilter.size() != 0) {
                    JOptionPane.showMessageDialog(null, "Sistem je pronašao nivoe forme po zadatim kriterijumima");
                    pripremiFormuFiltered(nivoiFilter);
                    pnnf.getBtnPretraziNivo().setVisible(true);
                    if (rec.equals("")) {
                        pnnf.getBtnPretraziNivo().setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sistem nije pronašao nivoe forme po zadatim kriterijumima");
                    pnnf.getBtnPretraziNivo().setVisible(false);
                }
            }

        });

        pnnf.pretraziNivoAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rec = pnnf.getTxtPretrazi().getText().toLowerCase().strip();
                nivoiFilter = new ArrayList<>();
                for (NivoForme nivoForme : nivoi) {
                    if (nivoForme.getOpis().toLowerCase().contains(rec)) {
                        nivoiFilter.add(nivoForme);
                        break;
                    }
                }
                if (nivoiFilter.size() != 0) {
                    JOptionPane.showMessageDialog(null, "Sistem je pronašao nivo forme po zadatim parametrima");
                    pripremiFormuFiltered(nivoiFilter);
                    if (rec.equals("")) {
                        pnnf.getBtnPretraziNivo().setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sistem nije pronašao nivo forme po zadatim parametrima");
                }
            }
        });

        pnnf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pnnf.getjTable1().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste izabrali red");
                    return;
                } else {
                    ModelTabeleNivoiForme mtnf = (ModelTabeleNivoiForme) pnnf.getjTable1().getModel();
                    NivoForme nf = mtnf.getLista().get(red);
                    cordinator.Cordinator.getInstance().dodajParam("nivoForme", nf);
                    cordinator.Cordinator.getInstance().otvoriIzmeniNivoFormeFormu();
                }
            }
        });
    }

    private void pripremiFormuFiltered(List<NivoForme> nivoiFiltered) {
        pnnf.getjTable1().setModel(new ModelTabeleNivoiForme(nivoiFiltered));
    }
}
