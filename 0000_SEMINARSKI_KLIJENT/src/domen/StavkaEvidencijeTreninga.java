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
public class StavkaEvidencijeTreninga implements ApstraktniDomenskiObjekat{
    private EvidencijaTreninga evidencija;
    private int rb;
    private Date datumPrisustva;
    private int ocena;
    private Trening trening;

    public StavkaEvidencijeTreninga() {
    }

    public StavkaEvidencijeTreninga(EvidencijaTreninga evidencija, int rb, Date datumPrisustva, int ocena, Trening trening) {
        this.evidencija = evidencija;
        this.rb = rb;
        this.datumPrisustva = datumPrisustva;
        this.ocena = ocena;
        this.trening = trening;
    }

    public EvidencijaTreninga getEvidencija() {
        return evidencija;
    }

    public void setEvidencija(EvidencijaTreninga evidencija) {
        this.evidencija = evidencija;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumPrisustva() {
        return datumPrisustva;
    }

    public void setDatumPrisustva(Date datumPrisustva) {
        this.datumPrisustva = datumPrisustva;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

    @Override
    public String toString() {
        return "1."+String.valueOf(rb)+" datum: "+datumPrisustva+" trening: "+trening.getNaziv();
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
        final StavkaEvidencijeTreninga other = (StavkaEvidencijeTreninga) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (this.ocena != other.ocena) {
            return false;
        }
        if (!Objects.equals(this.evidencija, other.evidencija)) {
            return false;
        }
        if (!Objects.equals(this.datumPrisustva, other.datumPrisustva)) {
            return false;
        }
        return Objects.equals(this.trening, other.trening);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaevidencijetreninga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb,evidencija,datumPrisustva,ocena,trening";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb+","+evidencija.getIdEvidencijaTreninga()+",'"+datumPrisustva+"',"+ocena+","+trening.getIdTrening();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkaevidencijetreninga.rb="+rb+" AND "+"stavkaevidencijetreninga.evidencija="+evidencija.getIdEvidencijaTreninga();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumPrisustva='"+datumPrisustva+"',ocena="+ocena+",trening="+trening.getIdTrening();
    }
    
    
}
