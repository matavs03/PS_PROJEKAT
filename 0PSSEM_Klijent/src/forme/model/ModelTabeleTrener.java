/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Trener;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lazar
 */
public class ModelTabeleTrener extends AbstractTableModel{
    
    List<Trener> lista;
    String[] kolone= {"id","Ime","Prezime","Username"};

    public ModelTabeleTrener(List<Trener> lista) {
        this.lista = lista;
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
                return t.getIdTrener();
            case 1:
                return t.getIme();
            case 2:
                return t.getPrezime();
            case 3:
                return t.getUsername();
               
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
