/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MataVS
 */
public class TrenerSertifikat implements ApstraktniDomenskiObjekat{
    private Trener trener;
    private Sertifikat sertifikat;
    private Date datumIzdavanja;

    public TrenerSertifikat() {
    }

    public TrenerSertifikat(Trener trener, Sertifikat sertifikat, Date datumIzdavanja) {
        this.trener = trener;
        this.sertifikat = sertifikat;
        this.datumIzdavanja = datumIzdavanja;
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
        final TrenerSertifikat other = (TrenerSertifikat) obj;
        if (!Objects.equals(this.trener, other.trener)) {
            return false;
        }
        if (!Objects.equals(this.sertifikat, other.sertifikat)) {
            return false;
        }
        return Objects.equals(this.datumIzdavanja, other.datumIzdavanja);
    }

    @Override
    public String vratiNazivTabele() {
        return "trenersertifikat";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "trener,sertifikat,datumIzdavanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return trener.getIdTrener()+","+sertifikat.getIdSertifikat()+",'"+datumIzdavanja+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "trenersertifikat.trener="+trener.getIdTrener()+" AND "+"trenersertifikat.sertifikat="+sertifikat.getIdSertifikat();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumIzdavanja='"+datumIzdavanja+"'";
    }
    
    
}
