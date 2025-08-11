/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.EvidencijaTreninga;
import domen.StavkaEvidencijeTreninga;
import domen.Trening;
import forme.DodajStavkuEvidencijeTreningaForma;
import forme.mod.FormaMod;
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
public class DodajStavkuEvidencijeTreningaController {

    private final DodajStavkuEvidencijeTreningaForma dsetf;

    public DodajStavkuEvidencijeTreningaController(DodajStavkuEvidencijeTreningaForma dsetf) {
        this.dsetf = dsetf;
        addActionListeners();
    }

    public void pripremiFormu(FormaMod mod) {
        List<Trening> treninzi = komunikacija.Komunikacija.getInstance().ucitajTreninge();
        dsetf.getCbxTrening().removeAllItems();
        for (Trening trening : treninzi) {
            dsetf.getCbxTrening().addItem(trening);
        }
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dsetf.setLocationRelativeTo(null);
        dsetf.setVisible(true);
    }

    private void addActionListeners() {

        dsetf.dodajAddActionLisener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EvidencijaTreninga et = (EvidencijaTreninga) cordinator.Cordinator.getInstance().vratiParam("evidencijaTreninga");

               
                LocalDate datumPrisustva;
                try {
                    int dan = Integer.parseInt(dsetf.getTxtDan().getText().strip());
                    int mesec = Integer.parseInt(dsetf.getTxtMesec().getText().strip());
                    int godina = Integer.parseInt(dsetf.getTxtGodina().getText().strip());

                    datumPrisustva = LocalDate.of(godina, mesec, dan);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Dan, mesec i godina za datum prisustva moraju biti brojevi.");
                    return;
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(null, "Unet nevalidan datum prisustva.");
                    return;
                }

                
                LocalDate datumOd;
                if (et.getDatumOd() instanceof java.sql.Date) {
                    datumOd = ((java.sql.Date) et.getDatumOd()).toLocalDate();
                } else {
                    datumOd = et.getDatumOd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }

                LocalDate datumDo;
                if (et.getDatumDo() != null) {
                    if (et.getDatumDo() instanceof java.sql.Date) {
                        datumDo = ((java.sql.Date) et.getDatumDo()).toLocalDate();
                    } else {
                        datumDo = et.getDatumDo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    }
                } else {
                    datumDo = LocalDate.now();
                }

                if (datumPrisustva.isBefore(datumOd)) {
                    JOptionPane.showMessageDialog(null, "Datum prisustva ne sme biti pre početnog datuma evidencije.");
                    return;
                }
                if (datumPrisustva.isAfter(datumDo)) {
                    JOptionPane.showMessageDialog(null, "Datum prisustva ne sme biti posle krajnjeg datuma evidencije.");
                    return;
                }

                
                int ocena;
                try {
                    ocena = Integer.parseInt(dsetf.getTxtOcena().getText().strip());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ocena mora biti broj.");
                    return;
                }
                if (ocena < 1 || ocena > 10) {
                    JOptionPane.showMessageDialog(null, "Ocena mora biti između 1 i 10.");
                    return;
                }

                
                Trening trening = (Trening) dsetf.getCbxTrening().getSelectedItem();

                
                Date utilDatumPrisustva = Date.from(datumPrisustva.atStartOfDay(ZoneId.systemDefault()).toInstant());
                
                int rb = et.getStavke().size()+1;
               
                StavkaEvidencijeTreninga stavka = new StavkaEvidencijeTreninga(et, rb, utilDatumPrisustva, ocena, trening);
                et.getStavke().add(stavka);
                
                try {
                    komunikacija.Komunikacija.getInstance().dodajStavkuEvidencijeTreninga(stavka);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio stavku evidencije treninga");
                    cordinator.Cordinator.getInstance().osveziPrikazEvidencijeTreningaFormu();
                    cordinator.Cordinator.getInstance().osveziPrikazStavki(et.getStavke());
                    dsetf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti stavku evidencije treninga");
                }
            }
        });

    }

}
