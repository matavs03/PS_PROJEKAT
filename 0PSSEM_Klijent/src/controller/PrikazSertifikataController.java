/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Sertifikat;
import forme.PrikazSertifikataForma;
import forme.model.ModeltabeleSertifikati;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lazar
 */
public class PrikazSertifikataController {

    private final PrikazSertifikataForma psf;

    public PrikazSertifikataController(PrikazSertifikataForma psf) {
        this.psf = psf;
        addActionListeners();
    }

    public void pripremiFormu() {
        List<Sertifikat> lista = komunikacija.Komunikacija.getInstance().ucitajSertifikate();
        ModeltabeleSertifikati mts = new ModeltabeleSertifikati(lista);
        psf.getTblSertifikati().setModel(mts);
    }

    public void otvoriFormu() {
        pripremiFormu();
        psf.setLocationRelativeTo(null);
        psf.setVisible(true);
    }

    private void addActionListeners() {
        psf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = psf.getTblSertifikati().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Odaberite red");
                    return;
                }
                int potvrda = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete sertifikat?");

                if (potvrda == JOptionPane.YES_OPTION) {

                    ModeltabeleSertifikati mts = (ModeltabeleSertifikati) psf.getTblSertifikati().getModel();
                    Sertifikat s = mts.getLista().get(red);
                    try {
                        komunikacija.Komunikacija.getInstance().obrisiSertifikat(s);
                        JOptionPane.showMessageDialog(null, "Sistem je obrisao sertifikat");
                        pripremiFormu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sistem ne može da obriše sertifikat");
                    }
                } else {
                    return;
                }
            }
        });

        psf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = psf.getTxtNaziv().getText().strip();
                String inst = psf.getTxtInstitucija().getText().strip();
                ModeltabeleSertifikati mtt = (ModeltabeleSertifikati) psf.getTblSertifikati().getModel();
                boolean naslo = mtt.pretrazi(naziv, inst);
                if (naziv.equals("") && inst.equals("")) {
                    psf.getBtnPretraziSertifikat().setVisible(false);
                } else {
                    psf.getBtnPretraziSertifikat().setVisible(naslo);
                }
            }
        });

        psf.pretraziSertifikatAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = psf.getTxtNaziv().getText().strip();
                String inst = psf.getTxtInstitucija().getText().strip();
                ModeltabeleSertifikati mtt = (ModeltabeleSertifikati) psf.getTblSertifikati().getModel();
                Sertifikat naslo = mtt.pretraziSertifikat(naziv, inst);
                if (naziv.equals("") && inst.equals("")) {
                    psf.getBtnPretraziSertifikat().setVisible(false);
                }
            }
        });

        psf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = psf.getTblSertifikati().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Niste odabrali red");
                    return;
                }
                ModeltabeleSertifikati mts = (ModeltabeleSertifikati) psf.getTblSertifikati().getModel();
                Sertifikat s = mts.getLista().get(red);
                cordinator.Cordinator.getInstance().dodajParam("sertifikat", s);
                cordinator.Cordinator.getInstance().otvoriIzmeniSertifikatFormu();
            }
        });
    }
}
