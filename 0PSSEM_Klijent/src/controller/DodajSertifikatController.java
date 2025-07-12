/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Sertifikat;
import forme.DodajSertifikatForma;
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
    
    public void otvoriFormu(){
        dsf.setLocationRelativeTo(null);
        dsf.setVisible(true);
    }

    private void addActionListeners() {
        dsf.addDodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = dsf.getTxtNaziv().getText().strip();
                String institucija = dsf.getTxtInstitucija().getText().strip();
                if(naziv.equals("") || institucija.equals("")){
                    JOptionPane.showMessageDialog(null, "Unesite tekst");
                    return;
                }
                Sertifikat s = new Sertifikat(-1, naziv, institucija);
                try {
                    komunikacija.Komunikacija.getInstance().dodajSertifikat(s);
                    JOptionPane.showMessageDialog(null, "nivo forme sertifikat");
                    dsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti sertifikat");
                }
            }
        });
    }
}
