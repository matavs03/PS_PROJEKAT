/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Sertifikat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lazar
 */
public class ModeltabeleSertifikati extends AbstractTableModel{

    List<Sertifikat> lista;
    private List<Sertifikat> kopijaOriginalaLista;
    List<Sertifikat> filteredList;
    String[] kolone = {"Naziv", "Institucija"};

    public ModeltabeleSertifikati(List<Sertifikat> lista) {
        this.lista = lista;
        this.kopijaOriginalaLista = new ArrayList<>(lista);
    }

    public List<Sertifikat> getLista() {
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
        Sertifikat s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getNaziv();
            case 1:
                return s.getInstitucija();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public boolean pretrazi(String naziv, String institucija) {
        filteredList = (List<Sertifikat>) kopijaOriginalaLista.stream()
            .filter(p -> (naziv == null || naziv.isEmpty() || p.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
            .filter(p -> (institucija == null || institucija.isEmpty() || p.getInstitucija().toLowerCase().contains(institucija.toLowerCase())))     
            .collect(Collectors.toList());
        if(filteredList.size()==0){
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo na nadje sertifikate po zadatim parametrima");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(null, "Sistem je našao sertifikate po zadatim parametrima");
            this.lista = filteredList;
            fireTableDataChanged();
            return true;
        }
//        this.lista = kopijaOriginalaLista;
        
    }

    public Sertifikat pretraziSertifikat(String naziv, String institucija) {
        filteredList = (List<Sertifikat>) this.filteredList.stream()
            .filter(p -> (naziv == null || naziv.isEmpty() || p.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
            .filter(p -> (institucija == null || institucija.isEmpty() || p.getInstitucija().toLowerCase().contains(institucija.toLowerCase())))     
            .collect(Collectors.toList());
        if(filteredList.size()==0){
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo na nadje sertifikat po zadatim parametrima");
            return null;
        }
        else{
            JOptionPane.showMessageDialog(null, "Sistem je našao sertifikat po zadatim parametrima");
            List<Sertifikat> sertifikat = new ArrayList<>();
            sertifikat.add(filteredList.get(0));
            this.lista = sertifikat;
            fireTableDataChanged();
            return sertifikat.get(0);
        }
    }
    
}
