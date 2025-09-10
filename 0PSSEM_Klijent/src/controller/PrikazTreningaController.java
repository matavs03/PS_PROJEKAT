/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trening;
import forme.PrikazTreningaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class PrikazTreningaController {
     private final PrikazTreningaForma ptf;
     private List<Trening> listaT;
     List<Trening> filtrirano;
     
    public PrikazTreningaController(PrikazTreningaForma ptf) {
        this.ptf = ptf;
        addActionListeners();
        
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
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao trening");
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da obriše trening");
                    }
                }  
            }
        });
        
        ptf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trening t = (Trening) ptf.getCbxTreninzi().getSelectedItem();
                cordinator.Cordinator.getInstance().dodajParam("trening", t);
                cordinator.Cordinator.getInstance().otvoriIzmeniTreningFormu();
            }
        });
        
        ptf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = ptf.getTxtNaziv().getText().trim();
                filtrirano = new ArrayList<>();
                for (Trening trening : listaT) {
                    if(trening.getNaziv().toLowerCase().contains(naziv.toLowerCase())){
                        filtrirano.add(trening);
                    }
                }
                if(filtrirano.size()!=0){
                    JOptionPane.showMessageDialog(null, "Sistem je pronašao treninge po zadatim kriterijumima");
                    pripremiFormuFiltered(filtrirano);
                    ptf.getBtnPretraziTrining().setVisible(true);
                    if(naziv.equals("")){
                        ptf.getBtnPretraziTrining().setVisible(false);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Sistem nije pronašao treninge po zadatim kriterijumima");
                    ptf.getBtnPretraziTrining().setVisible(false);
                    return;
                }
                
            }
        });
        
        ptf.pretraziTreningAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = ptf.getTxtNaziv().getText().trim();
                filtrirano = new ArrayList<>();
                for (Trening trening : listaT) {
                    if(trening.getNaziv().toLowerCase().contains(naziv.toLowerCase())){
                        filtrirano.add(trening);
                        break;
                    }
                }
                if(filtrirano.size()!=0){
                    JOptionPane.showMessageDialog(null, "Sistem je pronašao trening po zadatim kriterijumima");
                    pripremiFormuFiltered(filtrirano);
                    ptf.getBtnPretraziTrining().setVisible(true);
                    if(naziv.equals("")){
                        ptf.getBtnPretraziTrining().setVisible(false);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Sistem nije pronašao trening po zadatim kriterijumima");
                    return;
                }
            }
        });
    }

    public void pripremiFormu() {
        ptf.getCbxTreninzi().removeAllItems();
        List<Trening> lista = komunikacija.Komunikacija.getInstance().ucitajTreninge();
        listaT=lista;
        for(Trening t: lista){
            ptf.getCbxTreninzi().addItem(t);
        }
    }
    
    public void pripremiFormuFiltered(List<Trening> fil){
        ptf.getCbxTreninzi().removeAllItems();
        for(Trening t: fil){
            ptf.getCbxTreninzi().addItem(t);
        }
        ptf.getBtnPretraziTrining().setVisible(false);
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptf.setLocationRelativeTo(null);
        ptf.setVisible(true);
    }
}
