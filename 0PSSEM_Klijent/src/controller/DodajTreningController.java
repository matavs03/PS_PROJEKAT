/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trening;
import forme.DodajTreningForma;
import forme.mod.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class DodajTreningController {

    private DodajTreningForma dtf;

    public DodajTreningController(DodajTreningForma dtf) {
        this.dtf = dtf;
        addActionListeners();
    }

    public void otvoriFormu(FormaMod mod) {
        if(mod.equals(FormaMod.DODAJ)){
            dtf.getBtnAzuriraj().setVisible(false);
            dtf.getBtnDodaj().setVisible(true);
        }
        else{
            dtf.getBtnAzuriraj().setVisible(true);
            dtf.getBtnDodaj().setVisible(false);
            Trening t = (Trening) cordinator.Cordinator.getInstance().vratiParam("trening");
            dtf.getTxtNaziv().setText(t.getNaziv());
            dtf.getTxtaOpis().setText(t.getOpis());
        }
        dtf.setLocationRelativeTo(null);
        dtf.setVisible(true);
    }

    private void addActionListeners() {
        dtf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dtf.getTxtNaziv().getText().strip();
                String opis = dtf.getTxtaOpis().getText().strip();
                if(naziv.equals("") || opis.equals("")){
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trening");
                    return;
                }
                Trening noviTrening = new Trening(naziv, opis);
                dtf.getTxtNaziv().setText("");
                dtf.getTxtaOpis().setText("");
                try {
                    komunikacija.Komunikacija.getInstance().dodajTrening(noviTrening);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio trening");
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trening");
                }
            }
        });
        
        dtf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dtf.getTxtNaziv().getText().strip();
                String opis = dtf.getTxtaOpis().getText().strip();
                if(naziv.equals("") || opis.equals("")){
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trening");
                    return;
                }
                Trening noviTrening = new Trening(naziv, opis);
                Trening t = (Trening) cordinator.Cordinator.getInstance().vratiParam("trening");
                noviTrening.setIdTrening(t.getIdTrening());
                try {
                    komunikacija.Komunikacija.getInstance().azurirajTrening(noviTrening);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio trening");
                    cordinator.Cordinator.getInstance().osveziPrikazTreningaFormu();
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trening");
                }
            }
        });
    }
}
