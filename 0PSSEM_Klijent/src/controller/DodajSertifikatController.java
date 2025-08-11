/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Sertifikat;
import forme.DodajSertifikatForma;
import forme.mod.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class DodajSertifikatController {

    private final DodajSertifikatForma dsf;

    public DodajSertifikatController(DodajSertifikatForma dsf) {
        this.dsf = dsf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        if (mod.equals(FormaMod.DODAJ)) {
            dsf.setLocationRelativeTo(null);
            dsf.setVisible(true);
            dsf.getBtnAzuriraj().setVisible(false);
        }
        else{
            dsf.setLocationRelativeTo(null);
            dsf.setVisible(true);
            dsf.getBtnAzuriraj().setVisible(true);
            dsf.getBtnDodaj().setVisible(false);
            Sertifikat s = (Sertifikat) cordinator.Cordinator.getInstance().vratiParam("sertifikat");
            dsf.getTxtNaziv().setText(s.getNaziv());
            dsf.getTxtInstitucija().setText(s.getInstitucija());
        }

    }

    private void addActionListeners() {
        dsf.addDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dsf.getTxtNaziv().getText().strip();
                String institucija = dsf.getTxtInstitucija().getText().strip();
                if (naziv.equals("") || institucija.equals("")) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti sertifikat");
                    return;
                }
                Sertifikat s = new Sertifikat(-1, naziv, institucija);
                try {
                    komunikacija.Komunikacija.getInstance().dodajSertifikat(s);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio sertifikat");
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti sertifikat");
                }
            }
        });
        
        dsf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dsf.getTxtNaziv().getText().strip();
                String institucija = dsf.getTxtInstitucija().getText().strip();
                if (naziv.equals("") || institucija.equals("")) {
                    JOptionPane.showMessageDialog(null, "Unesite tekst");
                    return;
                }
                Sertifikat s1 = (Sertifikat) cordinator.Cordinator.getInstance().vratiParam("sertifikat");
                Sertifikat s = new Sertifikat(s1.getIdSertifikat(), naziv, institucija);
                try {
                    komunikacija.Komunikacija.getInstance().azurirajSertifikat(s);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio sertifikat");
                    cordinator.Cordinator.getInstance().osveziPrikazSertifikataFormu();
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti sertifikat");
                }
            }
        });
    }
}
