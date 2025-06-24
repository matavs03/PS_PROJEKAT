/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trening;
import forme.PrikazTreningaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class PrikazTreningaController {
     private final PrikazTreningaForma ptf;

    public PrikazTreningaController(PrikazTreningaForma ptf) {
        this.ptf = ptf;
        addActionListeners();
        pripremiFormu();
    }

    private void addActionListeners() {
        ptf.izaberiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trening t = (Trening) ptf.getCbxTreninzi().getSelectedItem();
                if(t==null){
                    return;
                }
                ptf.getTxtaOpis().setText(t.getOpis());
            }
        });
        
        ptf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int potvrda = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete trening?");

                if (potvrda == JOptionPane.YES_OPTION){
                    try {
                        Trening treningZaBrisanje = (Trening) ptf.getCbxTreninzi().getSelectedItem();
                        komunikacija.Komunikacija.getInstance().obrisiTrening(treningZaBrisanje);
                        JOptionPane.showMessageDialog(null, "Uspešno obrisan trening");
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "GRESKA IZ DOKUMENTACIJE");
                    }
                }
                
                
            }
        });
    }

    private void pripremiFormu() {
        ptf.getCbxTreninzi().removeAllItems();
        List<Trening> lista = komunikacija.Komunikacija.getInstance().ucitajTreninge();
        for(Trening t: lista){
            ptf.getCbxTreninzi().addItem(t);
        }
    }

    public void otvoriFormu() {
        ptf.setLocationRelativeTo(null);
        ptf.setVisible(true);
    }
}
