/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MataVS
 */
public class TrenerSertifikat implements ApstraktniDomenskiObjekat {

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

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
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
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {

            int trenerId = rs.getInt("trener.idTrener");
            String trenerIme = rs.getString("trener.ime");
            String trenerPrezime = rs.getString("trener.prezime");
            String trenerUsername = rs.getString("trener.username");
            String trenerPassword = rs.getString("trener.password");
            Trener trener1 = new Trener(trenerId, trenerIme, trenerPrezime, trenerUsername, trenerPassword);

            int sertifikatId = rs.getInt("sertifikat.idSertifikat");
            String naziv = rs.getString("sertifikat.naziv");
            String institucija = rs.getString("sertifikat.institucija");
            Sertifikat sertifikat1 = new Sertifikat(sertifikatId, naziv, institucija);

            Date datumIzdavanja1 = rs.getDate("trenersertifikat.datumIzdavanja");

            TrenerSertifikat ts = new TrenerSertifikat(trener1, sertifikat1, datumIzdavanja1);
            lista.add(ts);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "trener,sertifikat,datumIzdavanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
//        return trener.getIdTrener()+","+sertifikat.getIdSertifikat()+",'"+datumIzdavanja+"'";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumStr = "'" + sdf.format(datumIzdavanja) + "'";

        return trener.getIdTrener() + ", "
                + sertifikat.getIdSertifikat() + ", "
                + datumStr;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "trenersertifikat.trener=" + trener.getIdTrener() + " AND " + "trenersertifikat.sertifikat=" + sertifikat.getIdSertifikat();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumIzdavanja='" + datumIzdavanja + "'";
    }

}
