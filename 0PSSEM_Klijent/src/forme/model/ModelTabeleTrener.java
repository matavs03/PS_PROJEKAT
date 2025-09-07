/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Trener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lazar
 */
public class ModelTabeleTrener extends AbstractTableModel{
    
    List<Trener> lista;
    private List<Trener> kopijaOriginalaLista;
    List<Trener> filteredList;
    
    String[] kolone= {"Ime","Prezime"};

    public ModelTabeleTrener(List<Trener> lista) {
        this.lista = lista;
        this.kopijaOriginalaLista = new ArrayList<>(lista);
    }

    public List<Trener> getLista() {
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
        Trener t = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIme();
            case 1:
                return t.getPrezime();
            
               
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public boolean pretrazi(String ime, String prezime, String username) {
        filteredList = (List<Trener>) kopijaOriginalaLista.stream()
            .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(p -> (username == null || username.isEmpty() || p.getUsername().toLowerCase().contains(username.toLowerCase())))
            .collect(Collectors.toList());
        if(filteredList.size()==0){
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo na nadje trenere po zadatim parametrima");
            return false;
        }
        else{
            JOptionPane.showMessageDialog(null, "Sistem je našao trenere po zadatim parametrima");
            this.lista = filteredList;
            fireTableDataChanged();
            return true;
        }
//        this.lista = kopijaOriginalaLista;
        
    }

    public Trener pretraziTrenera(String ime, String prezime, String username) {
        filteredList = (List<Trener>) this.filteredList.stream()
            .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(p -> (username == null || username.isEmpty() || p.getUsername().toLowerCase().contains(username.toLowerCase())))      
            .collect(Collectors.toList());
        if(filteredList.size()==0){
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo na nadje trenere po zadatim parametrima");
            return null;
        }
        else{
            JOptionPane.showMessageDialog(null, "Sistem je našao trenere po zadatim parametrima");
  
              List<Trener> trener = new ArrayList<>();
            trener.add(filteredList.get(0));
            this.lista = trener;
            fireTableDataChanged();
            return trener.get(0);
        }
    }
    
    
}
