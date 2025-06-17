/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Trkac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author MataVS
 */
public class ModelTabeleTrkac extends AbstractTableModel {
    private List<Trkac> lista;
    private String[] kolone = {"ID Trkaca","Ime","Prezime","E-Mail", "Nivo forme"};

    public ModelTabeleTrkac(List<Trkac> lista) {
        this.lista = lista;
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
    
}
