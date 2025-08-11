/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.NivoForme;
import forme.DodajNivoFormeForma;
import forme.mod.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class DodajNivoFormeController {

    private DodajNivoFormeForma dnff;

    public DodajNivoFormeController(DodajNivoFormeForma dnff) {
        this.dnff = dnff;
        addActionListeners();
    }

    private void addActionListeners() {
        dnff.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dnff.getTxtNaziv().getText().strip();
                System.out.println(naziv);
                NivoForme nf = new NivoForme(naziv);

                System.out.println(nf.getOpis());
                try {
                    komunikacija.Komunikacija.getInstance().dodajNivoForme(nf);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio nivo forme");
                    dnff.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti nivo forme");
                }
            }
        });
        
        dnff.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dnff.getTxtNaziv().getText().strip();
                System.out.println(naziv);
                NivoForme nf = new NivoForme(naziv);
                NivoForme nf1 = (NivoForme) cordinator.Cordinator.getInstance().vratiParam("nivoForme");
                nf.setIdNivoForme(nf1.getIdNivoForme());
                System.out.println(nf.getOpis());
                try {
                    komunikacija.Komunikacija.getInstance().azurirajNivoForme(nf);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio nivo forme");
                    cordinator.Cordinator.getInstance().osveziPrikazNivoaFormiFormu();
                    dnff.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti nivo forme");
                }
            }
        });
    }

    public void otvoriFormu(FormaMod mod) {
        if (mod.equals(FormaMod.DODAJ)) {
            dnff.setLocationRelativeTo(null);
            dnff.setVisible(true);
            
        }
        else{
            dnff.getBtnAzuriraj().setVisible(true);
            dnff.getBtnDodaj().setVisible(false);
            NivoForme nf = (NivoForme) cordinator.Cordinator.getInstance().vratiParam("nivoForme");
            dnff.getTxtNaziv().setText(nf.getOpis());
            dnff.setLocationRelativeTo(null);
            dnff.setVisible(true);
            
        }

    }

}
