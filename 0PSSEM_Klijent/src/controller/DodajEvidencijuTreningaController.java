/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.EvidencijaTreninga;
import domen.Trener;
import domen.Trkac;
import forme.DodajEvidencijuTreningaForma;
import forme.mod.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class DodajEvidencijuTreningaController {

    private final DodajEvidencijuTreningaForma detf;

    public DodajEvidencijuTreningaController(DodajEvidencijuTreningaForma detf) {
        this.detf = detf;
        addActionListeners();
    }

    public void pripremiFormu(FormaMod mod) {

        List<Trener> treneri = komunikacija.Komunikacija.getInstance().ucitajTrenere();
        List<Trkac> trkaci = komunikacija.Komunikacija.getInstance().ucitajTrkace();

        detf.getCbxTrener().removeAllItems();
        detf.getCbxTrkac().removeAllItems();

        for (Trkac trkac : trkaci) {
            detf.getCbxTrkac().addItem(trkac);
        }
        for (Trener trener : treneri) {
            detf.getCbxTrener().addItem(trener);
        }

        if (mod.equals(FormaMod.DODAJ)) {
            detf.getBtnAzuriraj().setVisible(false);
        } else {
            detf.getBtnAzuriraj().setVisible(true);
            detf.getBtnDodaj().setVisible(false);
            EvidencijaTreninga et = (EvidencijaTreninga) cordinator.Cordinator.getInstance().vratiParam("evidencijaTreninga");
            Function<Date, LocalDate> toLocalDateSafe = date -> {
                if (date == null) {
                    return null;
                }
                if (date instanceof java.sql.Date) {
                    return ((java.sql.Date) date).toLocalDate();
                }
                return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            };

            LocalDate datumOdLocal = toLocalDateSafe.apply(et.getDatumOd());
            LocalDate datumDoLocal = toLocalDateSafe.apply(et.getDatumDo());

            if (datumOdLocal != null) {
                detf.getTxtDanOd().setText(String.valueOf(datumOdLocal.getDayOfMonth()));
                detf.getTxtMesecOd().setText(String.valueOf(datumOdLocal.getMonthValue()));
                detf.getTxtGodinaOd().setText(String.valueOf(datumOdLocal.getYear()));
            }

            if (datumDoLocal != null) {
                detf.getTxtDanDo().setText(String.valueOf(datumDoLocal.getDayOfMonth()));
                detf.getTxtMesecDo().setText(String.valueOf(datumDoLocal.getMonthValue()));
                detf.getTxtGodinaDo().setText(String.valueOf(datumDoLocal.getYear()));
            }
            detf.getCbxTrener().setSelectedItem(et.getTrener());
            detf.getCbxTrkac().setSelectedItem(et.getTrkac());

        }
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        detf.setLocationRelativeTo(null);
        detf.setVisible(true);
    }

    private void addActionListeners() {

        detf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate datumOd;
                try {
                    int danOd = Integer.parseInt(detf.getTxtDanOd().getText().strip());
                    int mesecOd = Integer.parseInt(detf.getTxtMesecOd().getText().strip());
                    int godinaOd = Integer.parseInt(detf.getTxtGodinaOd().getText().strip());

                    datumOd = LocalDate.of(godinaOd, mesecOd, danOd);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Dan, mesec i godina za datum od moraju biti brojevi.");
                    return;
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(null, "Unet nevalidan datum od.");
                    return;
                }

                LocalDate datumDo = null;

                if (!detf.getTxtGodinaDo().getText().strip().isEmpty()
                        || !detf.getTxtMesecDo().getText().strip().isEmpty()
                        || !detf.getTxtDanDo().getText().strip().isEmpty()) {
                    try {
                        int danDo = Integer.parseInt(detf.getTxtDanDo().getText().strip());
                        int mesecDo = Integer.parseInt(detf.getTxtMesecDo().getText().strip());
                        int godinaDo = Integer.parseInt(detf.getTxtGodinaDo().getText().strip());

                        datumDo = LocalDate.of(godinaDo, mesecDo, danDo);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Dan, mesec i godina za datum do moraju biti brojevi.");
                        return;
                    } catch (DateTimeException ex) {
                        JOptionPane.showMessageDialog(null, "Unet nevalidan datum do.");
                        return;
                    }
                }

                LocalDate danas = LocalDate.now();

                if (datumOd.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Datum od ne može biti u budućnosti.");
                    return;
                }
                if (datumDo != null && datumDo.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Datum do ne može biti u budućnosti.");
                    return;
                }

                if (datumDo != null && datumDo.isBefore(datumOd)) {
                    JOptionPane.showMessageDialog(null, "Datum do ne sme biti pre datuma od.");
                    return;
                }

                Trener t = (Trener) detf.getCbxTrener().getSelectedItem();
                Trkac tr = (Trkac) detf.getCbxTrkac().getSelectedItem();

                Date utilDatumOd = Date.from(datumOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date utilDatumDo = null;
                if (datumDo != null) {
                    utilDatumDo = Date.from(datumDo.atStartOfDay(ZoneId.systemDefault()).toInstant());
                }

                EvidencijaTreninga et = new EvidencijaTreninga(-1, utilDatumOd, utilDatumDo, 0, 0, t, tr);

                try {
                    komunikacija.Komunikacija.getInstance().dodajEvidencijuTreninga(et);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio evidenciju treninga");

                    detf.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                }
            }
        });

        detf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate datumOd;
                try {
                    int danOd = Integer.parseInt(detf.getTxtDanOd().getText().strip());
                    int mesecOd = Integer.parseInt(detf.getTxtMesecOd().getText().strip());
                    int godinaOd = Integer.parseInt(detf.getTxtGodinaOd().getText().strip());

                    datumOd = LocalDate.of(godinaOd, mesecOd, danOd);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Dan, mesec i godina za datum od moraju biti brojevi.");
                    return;
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(null, "Unet nevalidan datum od.");
                    return;
                }

                LocalDate datumDo = null;

                if (!detf.getTxtGodinaDo().getText().strip().isEmpty()
                        || !detf.getTxtMesecDo().getText().strip().isEmpty()
                        || !detf.getTxtDanDo().getText().strip().isEmpty()) {
                    try {
                        int danDo = Integer.parseInt(detf.getTxtDanDo().getText().strip());
                        int mesecDo = Integer.parseInt(detf.getTxtMesecDo().getText().strip());
                        int godinaDo = Integer.parseInt(detf.getTxtGodinaDo().getText().strip());

                        datumDo = LocalDate.of(godinaDo, mesecDo, danDo);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Dan, mesec i godina za datum do moraju biti brojevi.");
                        return;
                    } catch (DateTimeException ex) {
                        JOptionPane.showMessageDialog(null, "Unet nevalidan datum do.");
                        return;
                    }
                }

                LocalDate danas = LocalDate.now();

                if (datumOd.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Datum od ne može biti u budućnosti.");
                    return;
                }
                if (datumDo != null && datumDo.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Datum do ne može biti u budućnosti.");
                    return;
                }

                if (datumDo != null && datumDo.isBefore(datumOd)) {
                    JOptionPane.showMessageDialog(null, "Datum do ne sme biti pre datuma od.");
                    return;
                }

                Trener t = (Trener) detf.getCbxTrener().getSelectedItem();
                Trkac tr = (Trkac) detf.getCbxTrkac().getSelectedItem();

                Date utilDatumOd = Date.from(datumOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date utilDatumDo = null;
                if (datumDo != null) {
                    utilDatumDo = Date.from(datumDo.atStartOfDay(ZoneId.systemDefault()).toInstant());
                }
                EvidencijaTreninga et1 = (EvidencijaTreninga) cordinator.Cordinator.getInstance().vratiParam("evidencijaTreninga");

                if (!et1.getStavke().isEmpty()) {
                    Date datumPrisustvaDate = et1.getStavke().get(0).getDatumPrisustva();
                    LocalDate datumPrveStavke;

                    if (datumPrisustvaDate instanceof java.sql.Date) {
                        datumPrveStavke = ((java.sql.Date) datumPrisustvaDate).toLocalDate();
                    } else {
                        datumPrveStavke = datumPrisustvaDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    }

                    if (datumOd.isAfter(datumPrveStavke)) {
                        JOptionPane.showMessageDialog(null, "Datum početka ne može biti posle prve stavke u evidenciji.");
                        return;
                    }
                }

                EvidencijaTreninga et = new EvidencijaTreninga(et1.getIdEvidencijaTreninga(), utilDatumOd, utilDatumDo, et1.getBrojTreninga(), et1.getProsecnaOcena(), t, tr);

                try {
                    komunikacija.Komunikacija.getInstance().azurirajEvidencijuTreninga(et);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio evidenciju treninga");
                    cordinator.Cordinator.getInstance().osveziPrikazEvidencijeTreningaFormu();
                    detf.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                }
            }
        });

    }

}
