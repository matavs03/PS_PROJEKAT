/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.EvidencijaTreninga;
import domen.StavkaEvidencijeTreninga;
import domen.Trener;
import domen.Trening;
import domen.Trkac;
import forme.DodajEvidencijuTreningaForma;
import forme.mod.FormaMod;
import forme.model.ModelTabeleEvidencijeTreninga;
import forme.model.ModelTabeleStavke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    EvidencijaTreninga et;
    private List<StavkaEvidencijeTreninga> stavke;

    public DodajEvidencijuTreningaController(DodajEvidencijuTreningaForma detf) {
        this.detf = detf;
        addActionListeners();
    }

    public List<StavkaEvidencijeTreninga> getStavke() {
        return stavke;
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
            detf.getSkrol().setVisible(false);
            detf.getCbxTrening().removeAllItems();
            detf.getBtnAzuriraj().setVisible(false);
            detf.getTblStavke().setVisible(false);
            detf.getBtnDodajStavku().setVisible(false);
            detf.getBtnIzmeniStavku().setVisible(false);
            detf.getBtnObrisiStavku().setVisible(false);
            detf.remove(detf.getSkrol());
            detf.revalidate();
            detf.repaint();
            List<Trening> tren = komunikacija.Komunikacija.getInstance().ucitajTreninge();
            for (Trening trening : tren) {
                detf.getCbxTrening().addItem(trening);
            }
        } else {

            detf.getLblOcena().setVisible(false);
            detf.getLblTrening().setVisible(false);
            detf.getCbxTrening().setVisible(false);
            detf.getTxtOcena().setVisible(false);
            detf.getBtnAzuriraj().setVisible(true);
            detf.getBtnDodaj().setVisible(false);

            et = (EvidencijaTreninga) cordinator.Cordinator.getInstance().vratiParam("evidencijaTreninga");
            stavke = et.getStavke();
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
            stavke = et.getStavke();
            osveziTabeluStavki();
        }
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        detf.setLocationRelativeTo(null);
        detf.setVisible(true);
    }

    public void osveziTabeluStavki() {

        ModelTabeleStavke mts = new ModelTabeleStavke(new ArrayList<>(stavke));
        detf.getTblStavke().setModel(mts);

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
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                    return;
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
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
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                        return;
                    } catch (DateTimeException ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                        return;
                    }
                }

                LocalDate danas = LocalDate.now();

                if (datumOd.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                    return;
                }
                if (datumDo != null && datumDo.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                    return;
                }

                if (datumDo != null && datumDo.isBefore(datumOd)) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                    return;
                }

                Trener t = (Trener) detf.getCbxTrener().getSelectedItem();
                Trkac tr = (Trkac) detf.getCbxTrkac().getSelectedItem();

                Date utilDatumOd = Date.from(datumOd.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date utilDatumDo = null;
                if (datumDo != null) {
                    utilDatumDo = Date.from(datumDo.atStartOfDay(ZoneId.systemDefault()).toInstant());
                }

                int ocena;
                try {
                    ocena = Integer.parseInt(detf.getTxtOcena().getText().strip());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ocena mora biti broj.");
                    return;
                }
                if (ocena < 1 || ocena > 10) {
                    JOptionPane.showMessageDialog(null, "Ocena mora biti između 1 i 10.");
                    return;
                }

                Trening trening = (Trening) detf.getCbxTrening().getSelectedItem();

                EvidencijaTreninga et1 = new EvidencijaTreninga(-1, utilDatumOd, utilDatumDo, 0, 0, t, tr);

                StavkaEvidencijeTreninga set = new StavkaEvidencijeTreninga(et1, ocena, utilDatumOd, ocena, trening);
                List<StavkaEvidencijeTreninga> stavkeT = new ArrayList<>();
                stavkeT.add(set);

                et1.setStavke(stavkeT);

                try {
                    komunikacija.Komunikacija.getInstance().dodajEvidencijuTreninga(et1);
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
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                    return;
                } catch (DateTimeException ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
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
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                        return;
                    } catch (DateTimeException ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                        return;
                    }
                }

                LocalDate danas = LocalDate.now();

                if (datumOd.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                    return;
                }
                if (datumDo != null && datumDo.isAfter(danas)) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                    return;
                }

                if (datumDo != null && datumDo.isBefore(datumOd)) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
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
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                        return;
                    }
                }

                if (!et1.getStavke().isEmpty()) {
                    Date datumPoslednjeStavkeDate = et1.getStavke().get(et1.getStavke().size() - 1).getDatumPrisustva();
                    LocalDate datumPoslednjeStavke;

                    if (datumPoslednjeStavkeDate instanceof java.sql.Date) {
                        datumPoslednjeStavke = ((java.sql.Date) datumPoslednjeStavkeDate).toLocalDate();
                    } else {
                        datumPoslednjeStavke = datumPoslednjeStavkeDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                    }

                    if (datumDo != null && datumDo.isBefore(datumPoslednjeStavke)) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                        return;
                    }
                }

                EvidencijaTreninga et = new EvidencijaTreninga(et1.getIdEvidencijaTreninga(), utilDatumOd, utilDatumDo, et1.getBrojTreninga(), et1.getProsecnaOcena(), t, tr);
                et.setStavke(new ArrayList<>(stavke));
                try {
                    komunikacija.Komunikacija.getInstance().azurirajEvidencijuTreninga(et);
                    JOptionPane.showMessageDialog(null, "Sistem je zapamtio evidenciju treninga");
                    cordinator.Cordinator.getInstance().osveziPrikazEvidencijeTreningaFormu();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem ne može da zapamti evidenciju treninga");
                }
            }
        });

        detf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cordinator.Cordinator.getInstance().dodajParam("evidencijaTreninga", et);
                cordinator.Cordinator.getInstance().otvoriDodajStavkuEvidencijeTreningaFormu();

            }
        });

        detf.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = detf.getTblStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }

                ModelTabeleStavke mts = (ModelTabeleStavke) detf.getTblStavke().getModel();
                StavkaEvidencijeTreninga set = mts.getLista().get(red);
                try {

                    for (StavkaEvidencijeTreninga stavkaEvidencijeTreninga : stavke) {
                        if (stavkaEvidencijeTreninga.getEvidencija().getIdEvidencijaTreninga() == set.getEvidencija().getIdEvidencijaTreninga() && stavkaEvidencijeTreninga.getRb() == set.getRb()) {
                            stavke.remove(stavkaEvidencijeTreninga);
                            break;
                        }
                    }
                    osveziTabeluStavki();
                    JOptionPane.showMessageDialog(null, "Sistem je obrisao stavku evidencije treninga");

                    osveziTabeluStavki();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem nije mogao da obriše stavku evidencije treninga");
                }

            }
        });

        detf.izmeniStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = detf.getTblStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }

                ModelTabeleStavke mts = (ModelTabeleStavke) detf.getTblStavke().getModel();
                StavkaEvidencijeTreninga set = mts.getLista().get(red);
                cordinator.Cordinator.getInstance().dodajParam("stavkaEvidencijaTreninga", set);
                cordinator.Cordinator.getInstance().otvoriIzmeniStavkuEvidencijeTreninga();

            }
        });

    }

}
