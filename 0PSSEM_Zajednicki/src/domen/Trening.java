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
public class Trening implements ApstraktniDomenskiObjekat{
    private int idTrening;
    private String naziv;
    private String opis;

    public Trening() {
    }

    public Trening(int idTrening, String naziv, String opis) {
        this.idTrening = idTrening;
        this.naziv = naziv;
        this.opis = opis;
    }

    public Trening(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
    }

    


    public int getIdTrening() {
        return idTrening;
    }

    public void setIdTrening(int idTrening) {
        this.idTrening = idTrening;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Trening other = (Trening) obj;
        if (this.idTrening != other.idTrening) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "trening";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        while(rs.next()){
            int idTrening1 = rs.getInt("trening.idTrening");
            String naziv1 = rs.getString("trening.naziv");
            String opis1 = rs.getString("trening.opis");
            Trening t = new Trening(idTrening1, naziv1, opis1);
            lista.add(t);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"','"+opis+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "trening.idTrening="+idTrening;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv='"+naziv+"',opis='"+opis+"'";
    }
    
    
    
}
