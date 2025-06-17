/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.NivoForme;
import domen.Trkac;
import forme.DodajTrkacaForma;
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
    
    public void pripremiFormu(){
        dtf.getCbxNivoForme().removeAllItems();
        List<NivoForme> nivoiForme = komunikacija.Komunikacija.getInstance().ucitajNivoForme();
        for(NivoForme nf : nivoiForme){
            dtf.getCbxNivoForme().addItem(nf);
        }
        
    }

    public void otvoriFormu() {
        dtf.setLocationRelativeTo(null);
        dtf.setVisible(true);
        pripremiFormu();
        
    }

    private void addActionListeners() {
        dtf.addDodajIgracaActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dtf.getTxtIme().equals("") || dtf.getTxtPrezime().equals("") || dtf.getTxtEmail().equals("")){
                    JOptionPane.showMessageDialog(null, "Popunite sva polja");
                }
                else{
                    try {
                        String ime = dtf.getTxtIme().getText().trim();
                        String preime = dtf.getTxtPrezime().getText().trim();
                        String email = dtf.getTxtEmail().getText().trim();
                        NivoForme nf = (NivoForme) dtf.getCbxNivoForme().getSelectedItem();
                        Trkac trkac = new Trkac(ime, preime, email, nf);
                        komunikacija.Komunikacija.getInstance().dodajTrkaca(trkac);
                        JOptionPane.showMessageDialog(null, "Usepsno sacuvan trkac");
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
