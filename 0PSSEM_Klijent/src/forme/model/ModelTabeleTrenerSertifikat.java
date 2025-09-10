/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.TrenerSertifikat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lazar
 */
public class ModelTabeleTrenerSertifikat extends AbstractTableModel{
    
    private List<TrenerSertifikat> lista;
    private List<TrenerSertifikat> kopijaOriginalaLista;
    private List<TrenerSertifikat> filteredList;
    private String[] kolone = {"Trener", "Sertifikat", "Datum izdavanja"};

    public ModelTabeleTrenerSertifikat(List<TrenerSertifikat> lista) {
        this.lista = lista;
        this.kopijaOriginalaLista = new ArrayList<>(lista);
    }

    public List<TrenerSertifikat> getLista() {
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
        TrenerSertifikat ts = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy.");
        switch (columnIndex) {
            case 0:
                return ts.getTrener().toString();
            case 1:
                return ts.getSertifikat().getNaziv();
            case 2:
                return sdf.format(ts.getDatumIzdavanja());
                
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    public boolean pretrazi(String trenerTekst, String sertifikatNaziv) {
        filteredList = kopijaOriginalaLista.stream()
            .filter(ts -> (trenerTekst == null || trenerTekst.isEmpty() ||
                    ts.getTrener().getIme().toLowerCase().contains(trenerTekst.toLowerCase()) ||
                    ts.getTrener().getPrezime().toLowerCase().contains(trenerTekst.toLowerCase()) ||
                    (ts.getTrener().getIme() + " " + ts.getTrener().getPrezime()).toLowerCase().contains(trenerTekst.toLowerCase())
            ))
            .filter(ts -> (sertifikatNaziv == null || sertifikatNaziv.isEmpty() ||
                    ts.getSertifikat().getNaziv().equalsIgnoreCase(sertifikatNaziv)))
            .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo da nadje trener sertifikate po zadatim parametrima");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Sistem je našao trener sertifikate po zadatim parametrima");
            this.lista = filteredList;
            fireTableDataChanged();
            return true;
        }
    }

    public TrenerSertifikat pretraziJedan(String trenerTekst, String sertifikatNaziv) {
        filteredList = (this.filteredList != null ? this.filteredList : this.kopijaOriginalaLista).stream()
            .filter(ts -> (trenerTekst == null || trenerTekst.isEmpty() ||
                    ts.getTrener().getIme().toLowerCase().contains(trenerTekst.toLowerCase()) ||
                    ts.getTrener().getPrezime().toLowerCase().contains(trenerTekst.toLowerCase()) ||
                    (ts.getTrener().getIme() + " " + ts.getTrener().getPrezime()).toLowerCase().contains(trenerTekst.toLowerCase())
            ))
            .filter(ts -> (sertifikatNaziv == null || sertifikatNaziv.isEmpty() ||
                    ts.getSertifikat().getNaziv().equalsIgnoreCase(sertifikatNaziv)))
            .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo da nadje trener sertifikat po zadatim kriterijumima");
            return null;
        } else {
            JOptionPane.showMessageDialog(null, "Sistem je našao trener sertifikat po zadatim kriterijumima");
            TrenerSertifikat ts = filteredList.get(0);
            this.lista = new ArrayList<>();
            this.lista.add(ts);
            fireTableDataChanged();
            return ts;
        }
    }
    
}
