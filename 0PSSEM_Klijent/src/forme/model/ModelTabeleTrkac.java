/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.NivoForme;
import domen.Trkac;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author MataVS
 */
public class ModelTabeleTrkac extends AbstractTableModel {
    private List<Trkac> lista;
    private List<Trkac> kopijaOriginalaLista;
    private String[] kolone = {"ID Trkaca","Ime","Prezime","E-Mail", "Nivo forme"};

    public ModelTabeleTrkac(List<Trkac> lista) {
        this.lista = lista;
        this.kopijaOriginalaLista = new ArrayList<>(lista);
    }

    public List<Trkac> getLista() {
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
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trkac t = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIdTrkac();
                
            case 1:
                return t.getIme();
                
            case 2:
                return t.getPrezime();
             
            case 3:
                return t.getEmail();
                
            case 4:
                return t.getNivoForme().getOpis();
              
            default:
                System.out.println("Greska u modelu tabele prikazi trkaca");
                return "N/A";
        }
        
    }

    public void pretrazi(String ime, String prezime, String email, NivoForme nf) {
        List<Trkac> filteredList = (List<Trkac>) kopijaOriginalaLista.stream()
            .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
            .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
            .filter(p -> (email == null || email.isEmpty() || p.getEmail().toLowerCase().contains(email.toLowerCase())))
            .filter(p -> (nf == null || p.getNivoForme().equals(nf)))
            .collect(Collectors.toList());
        if(filteredList.size()==0){
            JOptionPane.showMessageDialog(null, "Sistem nije uspeo na nadje trkače po zadatim parametrima");
        }
        else{
            JOptionPane.showMessageDialog(null, "Sistem je našao trkače po zadatim parametrima");
            this.lista = filteredList;
            fireTableDataChanged();
        }
//        this.lista = kopijaOriginalaLista;
        
    }
    
}
