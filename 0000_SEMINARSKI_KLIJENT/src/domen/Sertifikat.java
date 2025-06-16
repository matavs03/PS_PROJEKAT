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
public class Sertifikat implements ApstraktniDomenskiObjekat{
    private int idSertifikat;
    private String naziv;
    private String institucija;

    public Sertifikat() {
    }

    public Sertifikat(int idSertifikat, String naziv, String institucija) {
        this.idSertifikat = idSertifikat;
        this.naziv = naziv;
        this.institucija = institucija;
    }

    public int getIdSertifikat() {
        return idSertifikat;
    }

    public void setIdSertifikat(int idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Sertifikat other = (Sertifikat) obj;
        if (this.idSertifikat != other.idSertifikat) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.institucija, other.institucija);
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        while(rs.next()){
            int idSertifikat1 = rs.getInt("sertifikat.idSertifikat");
            String naziv1 = rs.getString("sertifikat.naziv");
            String institucija1 = rs.getString("sertifikat.institucija");
            Sertifikat s = new Sertifikat(idSertifikat1, naziv1, institucija1);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,institucija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"','"+institucija+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "sertifikat.idSertifikat="+idSertifikat;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='"+naziv+"',institucija='"+institucija+"'";
    }
    
    
    
}
