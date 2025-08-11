/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import forme.DetaljanPrikazTreneraForma;
import forme.PrikaziTreneraForma;
import forme.model.ModelTabeleTrener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.CodingErrorAction;
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
        
        ptf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = ptf.getTxtIme().getText().strip();
                String prezime = ptf.getTxtPrezime().getText().strip();
                String user = ptf.getTxtUser().getText().strip();
                ModelTabeleTrener mtt = (ModelTabeleTrener) ptf.getTblTreneri().getModel();
                boolean naslo = mtt.pretrazi(ime, prezime, user);
                if(ime.equals("") && prezime.equals("") && user.equals("")){
                    ptf.getBtnPretraziTrenera().setVisible(false);
                }
                else{
                    ptf.getBtnPretraziTrenera().setVisible(naslo);
                }
            }
        });
        
        ptf.addPretraziTreneraActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ime = ptf.getTxtIme().getText().strip();
                String prezime = ptf.getTxtPrezime().getText().strip();
                String user = ptf.getTxtUser().getText().strip();
                
                ModelTabeleTrener mtt = (ModelTabeleTrener) ptf.getTblTreneri().getModel();
                Trener naslo = mtt.pretraziTrenera(ime, prezime, user);
                if(ime.equals("") && prezime.equals("") && user.equals("")){
                    ptf.getBtnPretraziTrenera().setVisible(false);
                }
                if(naslo!=null){
                    DetaljanPrikazTreneraForma dotf = new DetaljanPrikazTreneraForma(naslo);
                    dotf.setLocationRelativeTo(null);
                    dotf.setVisible(true);
//                    ptf.getBtnPretraziTrkaca().setVisible(false);
                }
                
            }
        });
        
        ptf.addIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptf.getTblTreneri().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                ModelTabeleTrener mtt = (ModelTabeleTrener) ptf.getTblTreneri().getModel();
                Trener t = mtt.getLista().get(red);
                cordinator.Cordinator.getInstance().dodajParam("trener", t);
                cordinator.Cordinator.getInstance().otvoriIzmeniTreneraFormu();
            }
        });
        
        ptf.detaljiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptf.getTblTreneri().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                ModelTabeleTrener mtt = (ModelTabeleTrener) ptf.getTblTreneri().getModel();
                Trener t = mtt.getLista().get(red);
                DetaljanPrikazTreneraForma dotf = new DetaljanPrikazTreneraForma(t);
                    dotf.setLocationRelativeTo(null);
                    dotf.setVisible(true);
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
