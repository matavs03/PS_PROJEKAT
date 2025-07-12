/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import forme.DodajTreneraForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     
    public void otvoriFormu() {
        dtf.setLocationRelativeTo(null);
        dtf.setVisible(true);
        
    }

    private void addActionListeners() {
        dtf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String ime = dtf.getTxtIme().getText().strip();
                String preime = dtf.getTxtPrezime().getText().strip();
                String username = dtf.getTxtUsername().getText().strip();
                String password = String.valueOf(dtf.getPassPassword().getPassword()).strip();
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
    }
}
