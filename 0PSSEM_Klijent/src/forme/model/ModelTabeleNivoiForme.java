/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.NivoForme;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author MataVS
 */
public class ModelTabeleNivoiForme extends AbstractTableModel {
    List<NivoForme> lista;
    String[] kolone = {"Naziv"};

    public ModelTabeleNivoiForme(List<NivoForme> lista) {
        this.lista = lista;
    }

    public List<NivoForme> getLista() {
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
        NivoForme nf = lista.get(rowIndex);
        return nf.getOpis();
    }

    @Override
    public String getColumnName(int column) {
        return kolone[0];
    }
    
    
    
}
