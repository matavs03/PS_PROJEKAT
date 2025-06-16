/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MataVS
 */
public class Trkac implements ApstraktniDomenskiObjekat{
    private int idTrkac;
    private String ime;
    private String prezime;
    private String email;
    private NivoForme nivoForme;

    public Trkac() {
    }

    public Trkac(int idTrkac, String ime, String prezime, String email, NivoForme nivoForme) {
        this.idTrkac = idTrkac;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.nivoForme = nivoForme;
    }

    public int getIdTrkac() {
        return idTrkac;
    }

    public void setIdTrkac(int idTrkac) {
        this.idTrkac = idTrkac;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NivoForme getNivoForme() {
        return nivoForme;
    }

    public void setNivoForme(NivoForme nivoForme) {
        this.nivoForme = nivoForme;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;       
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
        final Trkac other = (Trkac) obj;
        if (this.idTrkac != other.idTrkac) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.nivoForme, other.nivoForme);
    }

    @Override
    public String vratiNazivTabele() {
        return "trkac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int idTrkac1 = rs.getInt("trkac.idTrkac");
            String ime1 = rs.getString("trkac.ime");
            String prezime1 = rs.getString("trkac.prezime");
            String email1 = rs.getString("trkac.email");
            int nivoFormeId1 = rs.getInt("trkac.nivoForme");
            NivoForme nf = new NivoForme(nivoFormeId1, null);
            Trkac t = new Trkac(idTrkac1, ime1, prezime1, email1, nf);
            lista.add(t);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,nivoForme";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+email+"',"+nivoForme.getIdNivoForme();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "trkac.idTrkac="+idTrkac;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "ime='"+ime+"',prezime='"+prezime+"',email='"+email+"',nivoForme="+nivoForme.getIdNivoForme();
    }
    
    
}
