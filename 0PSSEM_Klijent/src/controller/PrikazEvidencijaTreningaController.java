/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.EvidencijaTreninga;
import domen.StavkaEvidencijeTreninga;
import domen.Trening;
import forme.PrikazEvidencijaTreningaForma;
import forme.model.ModelTabeleEvidencijeTreninga;
import forme.model.ModelTabeleStavke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class PrikazEvidencijaTreningaController {

    private final PrikazEvidencijaTreningaForma petf;

    public PrikazEvidencijaTreningaController(PrikazEvidencijaTreningaForma petf) {
        this.petf = petf;
        addActionListeners();
    }

    public void pripremiFormu() {
        List<EvidencijaTreninga> lista = komunikacija.Komunikacija.getInstance().ucitajEvidencijeTreninga();
        ModelTabeleEvidencijeTreninga mtet = new ModelTabeleEvidencijeTreninga(lista);
        petf.getTblEvidencije().setModel(mtet);
        for (EvidencijaTreninga et : lista) {
            List<StavkaEvidencijeTreninga> stavke = komunikacija.Komunikacija.getInstance().ucitajStavkeEvidencijeTreninga(et.getIdEvidencijaTreninga());
            et.setStavke(stavke);
        }
        List<Trening> treninzi = komunikacija.Komunikacija.getInstance().ucitajTreninge();
        petf.getCbxTrening().removeAllItems();
        petf.getCbxTrening().addItem(null);
        for (Trening t : treninzi) {
            petf.getCbxTrening().addItem(t);
        }
    }

    public void otvoriFormu() {
        pripremiFormu();
        petf.setLocationRelativeTo(null);
        petf.setVisible(true);
    }

    public void osveziTabeluStavki(List<StavkaEvidencijeTreninga> stavke) {
        ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
        petf.getTblStavke().setModel(mts);
    }

    private void addActionListeners() {

        petf.stavkeAddActinListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = petf.getTblEvidencije().getSelectedRow();
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) petf.getTblEvidencije().getModel();
                EvidencijaTreninga et = mte.getLista().get(red);
                System.out.println(et);
                List<StavkaEvidencijeTreninga> stavke = et.getStavke();
                ModelTabeleStavke mts = new ModelTabeleStavke(stavke);
                petf.getTblStavke().setModel(mts);
                cordinator.Cordinator.getInstance().dodajParam("evidencijaTreninga", et);
            }

        });

        petf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = petf.getTblEvidencije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                int potvrda = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete nivo forme?");
                if (potvrda == JOptionPane.YES_OPTION) {
                    ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) petf.getTblEvidencije().getModel();
                    EvidencijaTreninga et = mte.getLista().get(red);
                    try {
                        komunikacija.Komunikacija.getInstance().obrisiEvidencijuTreninga(et);
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao evidenciju treninga");
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem nije obrisao evidenciju treninga");
                    }
                }

            }
        });

        petf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = petf.getTblEvidencije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) petf.getTblEvidencije().getModel();
                EvidencijaTreninga et = mte.getLista().get(red);
                cordinator.Cordinator.getInstance().dodajParam("evidencijaTreninga", et);
                cordinator.Cordinator.getInstance().otvoriIzmeniEvidencijuTreningaFormu();
            }
        });

        petf.pretraziEvidencijeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trener = petf.getTxtTrener().getText().strip();
                String trkac = petf.getTxtTrkac().getText().strip();
                Trening trening = (Trening) petf.getCbxTrening().getSelectedItem();
                ModelTabeleEvidencijeTreninga mtet = (ModelTabeleEvidencijeTreninga) petf.getTblEvidencije().getModel();
                boolean naslo = mtet.pretrazi(trener, trkac, trening);

                petf.getBtnPretraziEvidenciju().setVisible(naslo);

                if (trener.equals("") && trkac.equals("") && trening == null) {
                    petf.getBtnPretraziEvidenciju().setVisible(false);
                }

            }
        });

        petf.pretraziEvidencijuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trener = petf.getTxtTrener().getText().strip();
                String trkac = petf.getTxtTrkac().getText().strip();
                Trening trening = (Trening) petf.getCbxTrening().getSelectedItem();
                ModelTabeleEvidencijeTreninga mtet = (ModelTabeleEvidencijeTreninga) petf.getTblEvidencije().getModel();
                EvidencijaTreninga naslo = mtet.pretraziEvidenciju(trener, trkac, trening);

                if (trener.equals("") && trkac.equals("") && trening == null) {
                    petf.getBtnPretraziEvidenciju().setVisible(false);
                }

            }
        });

        petf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = petf.getTblEvidencije().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                ModelTabeleEvidencijeTreninga mte = (ModelTabeleEvidencijeTreninga) petf.getTblEvidencije().getModel();
                EvidencijaTreninga et = mte.getLista().get(red);
                cordinator.Cordinator.getInstance().dodajParam("evidencijaTreninga", et);
                cordinator.Cordinator.getInstance().otvoriDodajStavkuEvidencijeTreningaFormu();
            }
        });
        
        petf.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = petf.getTblStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                ModelTabeleStavke mts = (ModelTabeleStavke) petf.getTblStavke().getModel();
                StavkaEvidencijeTreninga set = mts.getLista().get(red);
                try {
                    komunikacija.Komunikacija.getInstance().obrisiStavkuEvidencijeTreninga(set);
                    JOptionPane.showMessageDialog(null, "Sistem je obrisao stavku evidencije treninga");
                    cordinator.Cordinator.getInstance().osveziPrikazEvidencijeTreningaFormu();
                    EvidencijaTreninga et = (EvidencijaTreninga) cordinator.Cordinator.getInstance().vratiParam("evidencijaTreninga");
                    et.getStavke().remove(set);
                    cordinator.Cordinator.getInstance().osveziPrikazStavki(et.getStavke());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Sistem nije mogao da obriše stavku evidencije treninga");
                }
                
                
            }
        });

    }
}
