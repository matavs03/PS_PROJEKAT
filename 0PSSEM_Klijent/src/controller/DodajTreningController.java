/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trening;
import forme.DodajTreningForma;
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

    public void otvoriFormu() {
        dtf.setLocationRelativeTo(null);
        dtf.setVisible(true);
    }

    private void addActionListeners() {
        dtf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dtf.getTxtNaziv().getText().strip();
                String opis = dtf.getTxtaOpis().getText().strip();
                Trening noviTrening = new Trening(naziv, opis);
                dtf.getTxtNaziv().setText("");
                dtf.getTxtaOpis().setText("");
                try {
                    komunikacija.Komunikacija.getInstance().dodajTrening(noviTrening);
                    JOptionPane.showMessageDialog(null, "Uspešno sačuvan trening");
                    dtf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "GRESKA IZ KONTROLERA");
                }
            }
        });
    }
}
