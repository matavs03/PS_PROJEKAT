/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.NivoForme;
import domen.Trkac;
import forme.DodajTrkacaForma;
import forme.mod.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class DodajTrkacaController {

    private final DodajTrkacaForma dtf;

    public DodajTrkacaController(DodajTrkacaForma dtf) {
        this.dtf = dtf;
        addActionListeners();
        
    }

    public void pripremiFormu(FormaMod mod) {
        if (mod.equals(FormaMod.DODAJ)) {
            dtf.getBtnAzuriraj().setVisible(false);
            dtf.getBtnSacuvaj().setVisible(true);
            dtf.getCbxNivoForme().removeAllItems();
            List<NivoForme> nivoiForme = komunikacija.Komunikacija.getInstance().ucitajNivoForme();
            for (NivoForme nf : nivoiForme) {
                dtf.getCbxNivoForme().addItem(nf);
            }
        }
        else{
            dtf.getBtnSacuvaj().setVisible(false);
            dtf.getBtnAzuriraj().setVisible(true);
            Trkac t = (Trkac) cordinator.Cordinator.getInstance().vratiParam("trkac");
            dtf.getTxtIme().setText(t.getIme());
            dtf.getTxtPrezime().setText(t.getPrezime());
            dtf.getTxtEmail().setText(t.getEmail());
            List<NivoForme> nivoiForme = komunikacija.Komunikacija.getInstance().ucitajNivoForme();
            for (NivoForme nf : nivoiForme) {
                dtf.getCbxNivoForme().addItem(nf);
            }
            dtf.getCbxNivoForme().setSelectedItem(t.getNivoForme());
        }
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dtf.setLocationRelativeTo(null);
        dtf.setVisible(true);
        

    }

    private void addActionListeners() {
        dtf.addDodajIgracaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dtf.getTxtIme().equals("") || dtf.getTxtPrezime().equals("") || dtf.getTxtEmail().equals("")) {
                    JOptionPane.showMessageDialog(null, "Popunite sva polja");
                } else {
                    try {
                        String ime = dtf.getTxtIme().getText().trim();
                        String preime = dtf.getTxtPrezime().getText().trim();
                        String email = dtf.getTxtEmail().getText().trim();
                        NivoForme nf = (NivoForme) dtf.getCbxNivoForme().getSelectedItem();
                        Trkac trkac = new Trkac(ime, preime, email, nf);
                        komunikacija.Komunikacija.getInstance().dodajTrkaca(trkac);
                        JOptionPane.showMessageDialog(null, "Sistem je zapamtio trkača");
                        dtf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trkača");
                        ex.printStackTrace();
                    }
                }
            }
            
            
        });
        
        dtf.addIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dtf.getTxtIme().equals("") || dtf.getTxtPrezime().equals("") || dtf.getTxtEmail().equals("")) {
                    JOptionPane.showMessageDialog(null, "Popunite sva polja");
                } else {
                    try {
                        Trkac t = (Trkac) cordinator.Cordinator.getInstance().vratiParam("trkac");
                        String ime = dtf.getTxtIme().getText().trim();
                        String preime = dtf.getTxtPrezime().getText().trim();
                        String email = dtf.getTxtEmail().getText().trim();
                        NivoForme nf = (NivoForme) dtf.getCbxNivoForme().getSelectedItem();
                        Trkac trkac = new Trkac(ime, preime, email, nf);
                        trkac.setIdTrkac(t.getIdTrkac());
                        komunikacija.Komunikacija.getInstance().azurirajTrkaca(trkac);
                        JOptionPane.showMessageDialog(null, "Uspešno ažuriran trkač");
                        cordinator.Cordinator.getInstance().osveziPrikazTrkacaFormu();
                        dtf.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "GRESKA");
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
