/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.EvidencijaTreninga;
import domen.Trening;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lazar
 */
public class ModelTabeleEvidencijeTreninga extends AbstractTableModel {

    private List<EvidencijaTreninga> lista;
    private List<EvidencijaTreninga> kopijaOriginalaLista;
    List<EvidencijaTreninga> filteredList;

    private String[] kolone = {"ID", "Datum od", "Datum do", "Broj treninga", "Prosečna ocena", "Trener", "Trkač"};

    public ModelTabeleEvidencijeTreninga(List<EvidencijaTreninga> lista) {
        this.lista = lista;
        this.kopijaOriginalaLista = new ArrayList<>(lista);
    }

    public List<EvidencijaTreninga> getLista() {
        return lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        EvidencijaTreninga et = lista.get(rowIndex);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        switch (columnIndex) {
            case 0:
                return et.getIdEvidencijaTreninga();
            case 1:
                return format.format(et.getDatumOd());
            case 2:
                if (et.getDatumDo() != null) {
                    return format.format(et.getDatumDo());
                }
                return "NIJE ZAVRŠENA";
            case 3:
                return et.getBrojTreninga();
            case 4:
                return String.format("%.2f", et.getProsecnaOcena());
            case 5:
                return et.getTrener().getIme() + " " + et.getTrener().getPrezime();
            case 6:
                return et.getTrkac().getIme() + " " + et.getTrkac().getPrezime();

            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public boolean pretrazi(String trenerImePrezime, String trkacImePrezime, Trening izabraniTrening) {

        List<EvidencijaTreninga> filteredList = kopijaOriginalaLista.stream()
                
                .filter(et -> {
                    if (trenerImePrezime == null || trenerImePrezime.isEmpty()) {
                        return true;
                    }
                    String t = trenerImePrezime.toLowerCase();
                    return et.getTrener().getIme().toLowerCase().contains(t)
                            || et.getTrener().getPrezime().toLowerCase().contains(t);
                })
                
                .filter(et -> {
                    if (trkacImePrezime == null || trkacImePrezime.isEmpty()) {
                        return true;
                    }
                    String t = trkacImePrezime.toLowerCase();
                    return et.getTrkac().getIme().toLowerCase().contains(t)
                            || et.getTrkac().getPrezime().toLowerCase().contains(t);
                })
                
                .filter(et -> {
                    if (izabraniTrening == null) {
                        return true;
                    }
                    return et.getStavke().stream()
                            .anyMatch(stavka -> stavka.getTrening().equals(izabraniTrening));
                })
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sistem ne može da nadje evidencije treninga po zadatim kriterijumima");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Sistem je našao evidencije treninga po zadatim kriterijumima");
            this.lista = filteredList;
            fireTableDataChanged();
            return true;
        }
    }

    public EvidencijaTreninga pretraziEvidenciju(String trenerImePrezime, String trkacImePrezime, Trening izabraniTrening) {
        List<EvidencijaTreninga> filteredList = kopijaOriginalaLista.stream()
                .filter(et -> {
                    if (trenerImePrezime == null || trenerImePrezime.isEmpty()) {
                        return true;
                    }
                    String t = trenerImePrezime.toLowerCase();
                    return et.getTrener().getIme().toLowerCase().contains(t)
                            || et.getTrener().getPrezime().toLowerCase().contains(t);
                })
                .filter(et -> {
                    if (trkacImePrezime == null || trkacImePrezime.isEmpty()) {
                        return true;
                    }
                    String t = trkacImePrezime.toLowerCase();
                    return et.getTrkac().getIme().toLowerCase().contains(t)
                            || et.getTrkac().getPrezime().toLowerCase().contains(t);
                })
                .filter(et -> {
                    if (izabraniTrening == null) {
                        return true;
                    }
                    return et.getStavke().stream()
                            .anyMatch(stavka -> stavka.getTrening().equals(izabraniTrening));
                })
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sistem ne može da nadje evidenciju treninga");
            return null;
        } else {
            JOptionPane.showMessageDialog(null, "Sistem je našao evidenciju treninga");
            EvidencijaTreninga prva = filteredList.get(0);
            this.lista = Collections.singletonList(prva);
            fireTableDataChanged();
            return prva;
        }
    }

}
