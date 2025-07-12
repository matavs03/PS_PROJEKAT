/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MataVS
 */
public class NivoForme implements ApstraktniDomenskiObjekat{
    private int idNivoForme;
    private String opis;

    public NivoForme() {
    }

    public NivoForme(int idNivoForme, String opis) {
        this.idNivoForme = idNivoForme;
        this.opis = opis;
    }

    public NivoForme(String opis) {
        this.opis = opis;
    }
    
    

    public int getIdNivoForme() {
        return idNivoForme;
    }

    public void setIdNivoForme(int idNivoForme) {
        this.idNivoForme = idNivoForme;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return opis;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NivoForme other = (NivoForme) obj;
        if (this.idNivoForme != other.idNivoForme) {
            return false;
        }
        return Objects.equals(this.opis, other.opis);
    }

    @Override
    public String vratiNazivTabele() {
        return "nivoforme";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        while(rs.next()){
            int idNivoForme1 = rs.getInt("nivoforme.idNivoForme");
            String opis1 = rs.getString("nivoforme.opis");
            NivoForme nf = new NivoForme(idNivoForme1, opis1);
            lista.add(nf);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+opis+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "nivoforme.idNivoForme="+idNivoForme;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "opis='"+opis+"'";
    }
    
    
    
}
