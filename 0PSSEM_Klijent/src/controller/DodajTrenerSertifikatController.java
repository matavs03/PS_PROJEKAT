/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Sertifikat;
import domen.Trener;
import domen.TrenerSertifikat;
import forme.DodajTrenerSertifikatForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class DodajTrenerSertifikatController {
    
    private final DodajTrenerSertifikatForma dtsf;

    public DodajTrenerSertifikatController(DodajTrenerSertifikatForma dtsf) {
        this.dtsf = dtsf;
        addActionListeners();
    }
    
    public void pripremiFormu(){
        List<Trener> treneri = komunikacija.Komunikacija.getInstance().ucitajTrenere();
        List<Sertifikat> sertifikati = komunikacija.Komunikacija.getInstance().ucitajSertifikate();
        dtsf.getCbxSertifikat().removeAllItems();
        dtsf.getCbxTrener().removeAllItems();
        for (Sertifikat sertifikat : sertifikati) {
            dtsf.getCbxSertifikat().addItem(sertifikat);
        }
        for (Trener trener : treneri) {
            dtsf.getCbxTrener().addItem(trener);
        }
        
        
        
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        dtsf.setLocationRelativeTo(null);
        dtsf.setVisible(true);
    }

    private void addActionListeners() {
        dtsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trener t = (Trener) dtsf.getCbxTrener().getSelectedItem();
                Sertifikat s = (Sertifikat) dtsf.getCbxSertifikat().getSelectedItem();
                
                LocalDate datum;
                try {
                    int dan = Integer.parseInt(dtsf.getTxtDan().getText().strip());
                    int mesec = Integer.parseInt(dtsf.getTxtMesec().getText().strip());
                    int godina = Integer.parseInt(dtsf.getTxtGodina().getText().strip());

                    datum = LocalDate.of(godina, mesec, dan);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Dan, mesec i godina za datum moraju biti brojevi.");
                    return;
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(null, "Unet nevalidan datum.");
                    return;
                }
                
                LocalDate danas = LocalDate.now();
                
                if (datum.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Datum ne sme biti u budućnosti.");
                    return;
                }
                Date utilDatum = Date.from(datum.atStartOfDay(ZoneId.systemDefault()).toInstant());
                TrenerSertifikat ts = new TrenerSertifikat(t, s, utilDatum);
                
                try {
                    komunikacija.Komunikacija.getInstance().dodajTrenerSertifikat(ts);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio trener sertifikat");
                    dtsf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem nije mogao da zapamti trener sertifikat");
                }
            }
        });
    }
    
}
