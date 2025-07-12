/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import forme.PrikaziTreneraForma;
import forme.model.ModelTabeleTrener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class PrikaziTrenereController {
    private final PrikaziTreneraForma ptf;

    public PrikaziTrenereController(PrikaziTreneraForma ptf) {
        this.ptf = ptf;
        addActionListeners();
    }

    private void addActionListeners() {
        ptf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int potvrda = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrišete trenera?");
                if(potvrda==JOptionPane.YES_OPTION){
                    int red = ptf.getTblTreneri().getSelectedRow();
                    if(red==-1){
                        JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    }
                    else{
                        ModelTabeleTrener mtb = (ModelTabeleTrener) ptf.getTblTreneri().getModel();
                        Trener trenerZaBrisanje= mtb.getLista().get(red);
                        if(trenerZaBrisanje.equals(cordinator.Cordinator.getInstance().getUlogovani())){
                            JOptionPane.showMessageDialog(null, "Sistem ne može da obriše trenera");
                            return;
                        }
                        try {
                            komunikacija.Komunikacija.getInstance().obrisiTrenera(trenerZaBrisanje);
                            JOptionPane.showMessageDialog(null, "Sistem je obrisao trenera");
                            pripremiFormu();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Sistem ne može da obriše trenera");
                        }
                    }
                    
                }
            }
        });
    }
    
    public void pripremiFormu(){
        List<Trener> lista = komunikacija.Komunikacija.getInstance().ucitajTrenere();
        ModelTabeleTrener mtt = new ModelTabeleTrener(lista);
        ptf.getTblTreneri().setModel(mtt);
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        ptf.setLocationRelativeTo(null);
        ptf.setVisible(true);
    }
}
