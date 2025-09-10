/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author MataVS
 */
public class EvidencijaTreninga implements ApstraktniDomenskiObjekat {

    private int idEvidencijaTreninga;
    private Date datumOd;
    private Date datumDo;
    private int brojTreninga;
    private double prosecnaOcena;
    private Trener trener;
    private Trkac trkac;
    private List<StavkaEvidencijeTreninga> stavke = new LinkedList<>();

    public EvidencijaTreninga() {
    }

    public EvidencijaTreninga(int idEvidencijaTreninga, Date datumOd, Date datumDo, int brojTreninga, double prosecnaOcena, Trener trener, Trkac trkac) {
        this.idEvidencijaTreninga = idEvidencijaTreninga;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.brojTreninga = brojTreninga;
        this.prosecnaOcena = prosecnaOcena;
        this.trener = trener;
        this.trkac = trkac;
    }

    public List<StavkaEvidencijeTreninga> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaEvidencijeTreninga> stavke) {
        this.stavke = stavke;
    }

    public int getIdEvidencijaTreninga() {
        return idEvidencijaTreninga;
    }

    public void setIdEvidencijaTreninga(int idEvidencijaTreninga) {
        this.idEvidencijaTreninga = idEvidencijaTreninga;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public int getBrojTreninga() {
        return brojTreninga;
    }

    public void setBrojTreninga(int brojTreninga) {
        this.brojTreninga = brojTreninga;
    }

    public double getProsecnaOcena() {
        return prosecnaOcena;
    }

    public void setProsecnaOcena(double prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Trkac getTrkac() {
        return trkac;
    }

    public void setTrkac(Trkac trkac) {
        this.trkac = trkac;
    }

    @Override
    public String toString() {
        return String.valueOf(idEvidencijaTreninga) + " trkac: " + trkac.getIme() + " " + trkac.getPrezime() + " trener: " + trener.getIme() + " " + trener.getPrezime();
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
        final EvidencijaTreninga other = (EvidencijaTreninga) obj;
        if (this.idEvidencijaTreninga != other.idEvidencijaTreninga) {
            return false;
        }
        if (!Objects.equals(this.datumOd, other.datumOd)) {
            return false;
        }
        if (!Objects.equals(this.datumDo, other.datumDo)) {
            return false;
        }
        if (!Objects.equals(this.trener, other.trener)) {
            return false;
        }
        return Objects.equals(this.trkac, other.trkac);
    }

    @Override
    public String vratiNazivTabele() {
        return "evidencijatreninga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            int nivoFormeId = rs.getInt("nivoforme.idNivoForme");
            String nivoFormeOpis = rs.getString("nivoforme.opis");
            NivoForme nf = new NivoForme(nivoFormeId, nivoFormeOpis);

            int trkacId = rs.getInt("trkac.idTrkac");
            String trkacIme = rs.getString("trkac.ime");
            String trkacPrezime = rs.getString("trkac.prezime");
            String trkacEmail = rs.getString("trkac.email");
            Trkac trkac1 = new Trkac(trkacId, trkacIme, trkacPrezime, trkacEmail, nf);

            int trenerId = rs.getInt("trener.idTrener");
            String trenerIme = rs.getString("trener.ime");
            String trenerPrezime = rs.getString("trener.prezime");
            String trenerUsername = rs.getString("trener.username");
            String trenerPassword = rs.getString("trener.password");
            Trener trener1 = new Trener(trenerId, trenerIme, trenerPrezime, trenerUsername, trenerPassword);

            int evidencijaId = rs.getInt("evidencijatreninga.idEvidencijaTreninga");
            Date datumOd1 = rs.getDate("evidencijatreninga.datumOd");
            Date datumDo1 = rs.getDate("evidencijatreninga.datumDo");
            int brojTreninga1 = rs.getInt("evidencijatreninga.brojTreninga");
            double prosecnaOcena1 = rs.getDouble("evidencijatreninga.prosecnaOcena");

            EvidencijaTreninga evidencija = new EvidencijaTreninga(
                    evidencijaId, datumOd1, datumDo1, brojTreninga1, prosecnaOcena1, trener1, trkac1);

            lista.add(evidencija);
        }

        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumOd,datumDo,brojTreninga,prosecnaOcena,trener,trkac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // format za DATE
        String datumOdStr = "'" + sdf.format(datumOd) + "'";
        String datumDoStr = null;
        if (datumDo != null) {
            datumDoStr = "'" + sdf.format(datumDo) + "'";
        }

        return datumOdStr + ", " + (datumDoStr != null ? datumDoStr : "null") + ", " + brojTreninga + "," + prosecnaOcena + "," + trener.getIdTrener() + "," + trkac.getIdTrkac();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "evidencijatreninga.idEvidencijaTreninga=" + idEvidencijaTreninga;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String datumOdStr = "'" + sdf.format(datumOd) + "'";
        String datumDoStr = null;
        if (datumDo != null) {
            datumDoStr = "'" + sdf.format(datumDo) + "'";
        }

        return "datumOd=" + datumOdStr
                + ", datumDo=" + (datumDoStr != null ? datumDoStr : "null")
                + ", brojTreninga=" + brojTreninga
                + ", prosecnaOcena=" + prosecnaOcena
                + ", trener=" + trener.getIdTrener()
                + ", trkac=" + trkac.getIdTrkac();
    }

}
