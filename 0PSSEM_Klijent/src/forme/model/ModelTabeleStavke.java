/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaEvidencijeTreninga;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lazar
 */
public class ModelTabeleStavke extends AbstractTableModel{

    private List<StavkaEvidencijeTreninga> lista;
    private String[] kolone = {"RB", "Datum", "Trening", "Ocena"};

    public List<StavkaEvidencijeTreninga> getLista() {
        return lista;
    }

    public ModelTabeleStavke(List<StavkaEvidencijeTreninga> lista) {
        this.lista = lista;
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
        StavkaEvidencijeTreninga s = lista.get(rowIndex);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return format.format(s.getDatumPrisustva());
            case 2:
                return s.getTrening().getNaziv();
            case 3:
                return s.getOcena();
                
                
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
}
