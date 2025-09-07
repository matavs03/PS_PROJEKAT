/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Sertifikat;
import domen.StavkaEvidencijeTreninga;
import domen.Trener;
import domen.TrenerSertifikat;
import forme.PrikaziTrenerSertifikatForma;
import forme.model.ModelTabeleTrenerSertifikat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class PrikaziTrenerSertifikatController {
    
    private final PrikaziTrenerSertifikatForma ptsf;

    public PrikaziTrenerSertifikatController(PrikaziTrenerSertifikatForma ptsf) {
        this.ptsf = ptsf;
        addActionListeners();
    }
    
    public void pripremiFormu(){
        List<Trener> treneri = komunikacija.Komunikacija.getInstance().ucitajTrenere();
        List<Sertifikat> sertifikati = komunikacija.Komunikacija.getInstance().ucitajSertifikate();
        ptsf.getCbxSertifikati().removeAllItems();
        for (Sertifikat sertifikat : sertifikati) {
            ptsf.getCbxSertifikati().addItem(sertifikat);
        }
        ptsf.getCbxSertifikati().addItem(null);
        
        ptsf.getCbxSertifikati().setSelectedItem(null);
        
        osveziTabelu();
    }
    
    public void osveziTabelu(){
        List<TrenerSertifikat> ts = komunikacija.Komunikacija.getInstance().ucitajTrenerSertifikat();
        ModelTabeleTrenerSertifikat mtsf = new ModelTabeleTrenerSertifikat(ts);
        ptsf.getTblTrenerSertifikat().setModel(mtsf);
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        ptsf.setLocationRelativeTo(null);
        ptsf.setVisible(true);
    }

    private void addActionListeners() {
        ptsf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = ptsf.getTblTrenerSertifikat().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                int izbor = JOptionPane.showConfirmDialog(null, "Da li stvarno želite da obrišete trener sertifikat?");
                if(izbor == JOptionPane.NO_OPTION){
                    return;
                }
                else{
                    ModelTabeleTrenerSertifikat mtts = (ModelTabeleTrenerSertifikat) ptsf.getTblTrenerSertifikat().getModel();
                    TrenerSertifikat ts = mtts.getLista().get(red);
                    try {
                        komunikacija.Komunikacija.getInstance().obrisiTrenerSertifikat(ts);
                        osveziTabelu();
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao trener sertifikat");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem nije mogao da obriše trener sertifikat");
                    }
                }
            }
        });
        
        
        ptsf.pretraziSertifikateAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trener = ptsf.getTxtTrener().getText().strip();
                Sertifikat sertifikat = (Sertifikat) ptsf.getCbxSertifikati().getSelectedItem();
                String sNaziv = "";
                if(sertifikat!=null){
                    sNaziv=sertifikat.getNaziv();
                }
                ModelTabeleTrenerSertifikat mtts = (ModelTabeleTrenerSertifikat) ptsf.getTblTrenerSertifikat().getModel();
                boolean naslo = mtts.pretrazi(trener, sNaziv);
                if(trener.equals("") && sertifikat==null){
                    ptsf.getBtnPretraziSertifikat().setVisible(false);
                }
                else{
                    ptsf.getBtnPretraziSertifikat().setVisible(naslo);
                }
            }
        });
        
        ptsf.pretraziSertifikatAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trener = ptsf.getTxtTrener().getText().strip();
                Sertifikat sertifikat = (Sertifikat) ptsf.getCbxSertifikati().getSelectedItem();
                String sNaziv = "";
                if(sertifikat!=null){
                    sNaziv=sertifikat.getNaziv();
                }
                ModelTabeleTrenerSertifikat mtts = (ModelTabeleTrenerSertifikat) ptsf.getTblTrenerSertifikat().getModel();
                TrenerSertifikat naslo = mtts.pretraziJedan(trener, sNaziv);
                if(trener.equals("") && sertifikat==null){
                    ptsf.getBtnPretraziSertifikat().setVisible(false);
                }
                
            }
        });
    }
    
}
