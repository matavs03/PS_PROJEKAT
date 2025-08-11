/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import forme.DodajTreneraForma;
import forme.mod.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class DodajTreneraController {
    private final DodajTreneraForma dtf;

    public DodajTreneraController(DodajTreneraForma dtf) {
        this.dtf = dtf;
        addActionListeners();
    }    
     
    public void otvoriFormu(FormaMod mod) {
        if(mod.equals(FormaMod.DODAJ)){
            dtf.setLocationRelativeTo(null);
            dtf.setVisible(true);
            dtf.getBtnAzuriraj().setVisible(false);
        }
        else{
            dtf.setLocationRelativeTo(null);
            dtf.setVisible(true);
            dtf.getBtnAzuriraj().setVisible(true);
            dtf.getBtnDodaj().setVisible(false);
            Trener t = (Trener) cordinator.Cordinator.getInstance().vratiParam("trener");
            dtf.getTxtIme().setText(t.getIme());
            dtf.getTxtPrezime().setText(t.getPrezime());
            dtf.getTxtUsername().setText(t.getUsername());
            dtf.getPassPassword().setVisible(false);
            dtf.getLblpass().setVisible(false);
        }
        
        
    }

    private void addActionListeners() {
        dtf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ime = dtf.getTxtIme().getText().strip();
                String preime = dtf.getTxtPrezime().getText().strip();
                String username = dtf.getTxtUsername().getText().strip();
                String password = String.valueOf(dtf.getPassPassword().getPassword()).strip();
                if(ime.equals("") || preime.equals("") || username.equals("") || password.equals("")){
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trenera");
                    return;
                }
                Trener noviTrener = new Trener(ime, preime, username, password);
                try {
                    komunikacija.Komunikacija.getInstance().dodajTrenera(noviTrener);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio trenera");
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trenera");
                }
                
                
            }
        });
        
        dtf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = dtf.getTxtIme().getText().strip();
                String preime = dtf.getTxtPrezime().getText().strip();
                String username = dtf.getTxtUsername().getText().strip();
                if(ime.equals("") || preime.equals("") || username.equals("")){
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trenera");
                    return;
                }
                Trener t1 = (Trener) cordinator.Cordinator.getInstance().vratiParam("trener");
                Trener t = new Trener();
                t.setIdTrener(t1.getIdTrener());
                t.setIme(ime);
                t.setPrezime(preime);
                t.setUsername(username);
                t.setPassword(t1.getPassword());
                try {
                    komunikacija.Komunikacija.getInstance().azurirajTrenera(t);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio trenera");
                    cordinator.Cordinator.getInstance().osveziPrikazTreneraFormu();
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti trenera");
                }
            }
        });
    }
}
