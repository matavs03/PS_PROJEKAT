/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Sertifikat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lazar
 */
public class ModeltabeleSertifikati extends AbstractTableModel{

    List<Sertifikat> lista;
    String[] kolone = {"Naziv", "Institucija"};

    public ModeltabeleSertifikati(List<Sertifikat> lista) {
        this.lista = lista;
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
    
    
}
