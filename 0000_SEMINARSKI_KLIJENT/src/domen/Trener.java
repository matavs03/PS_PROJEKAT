/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MataVS
 */
public class Trener implements ApstraktniDomenskiObjekat{
    private int idTrener;
    private String ime;
    private String prezime;
    private String username;
    private String password;

    public Trener() {
    }

    public Trener(int idTrener, String ime, String prezime, String username, String password) {
        this.idTrener = idTrener;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public int getIdTrener() {
        return idTrener;
    }

    public void setIdTrener(int idTrener) {
        this.idTrener = idTrener;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
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
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

   
    @Override
    public String vratiNazivTabele() {
        return "trener";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        while(rs.next()){
            int idTrener1 = rs.getInt("trener.idTrener");
            String ime1 = rs.getString("trener.ime");
            String prezime1 = rs.getString("trener.prezime");
            String username1 = rs.getString("trener.username");
            String password1 = rs.getString("trener.password");
            Trener t = new Trener(idTrener1, ime1, prezime1, username1, password1);
            lista.add(t);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,username,password";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+username+"','"+password+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "trener.idTrener="+idTrener;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='"+ime+"',prezime='"+prezime+"',username='"+username+"',password='"+password+"'";
    }
    
    
}
