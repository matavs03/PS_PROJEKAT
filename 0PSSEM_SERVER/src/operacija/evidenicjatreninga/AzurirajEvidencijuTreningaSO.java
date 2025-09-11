/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.evidenicjatreninga;

import domen.EvidencijaTreninga;
import domen.StavkaEvidencijeTreninga;
import java.util.ArrayList;
import java.util.List;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author lazar
 */
public class AzurirajEvidencijuTreningaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof EvidencijaTreninga)) {
            throw new Exception("UBACITI GRESKU IZ DOKUMENTACIJE!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        EvidencijaTreninga et = (EvidencijaTreninga) objekat;
        String uslov2 = " JOIN evidencijatreninga ON stavkaevidencijetreninga.evidencija = evidencijatreninga.idEvidencijaTreninga JOIN trening ON stavkaevidencijetreninga.trening = trening.idTrening WHERE evidencija=" + et.getIdEvidencijaTreninga() + " ORDER BY stavkaevidencijetreninga.datumPrisustva";
        List<StavkaEvidencijeTreninga> stavkeUBazi = broker.getAll(new StavkaEvidencijeTreninga(), uslov2);
        List<StavkaEvidencijeTreninga> noveStavke = et.getStavke();
        List<StavkaEvidencijeTreninga> zaDodavanje = new ArrayList<>();
        List<StavkaEvidencijeTreninga> zaBrisanje = new ArrayList<>();
        List<StavkaEvidencijeTreninga> zaIzmenu = new ArrayList<>();

        
        for (StavkaEvidencijeTreninga nova : noveStavke) {
            boolean postoji = false;
            for (StavkaEvidencijeTreninga stara : stavkeUBazi) {
                if (nova.getEvidencija().getIdEvidencijaTreninga() == stara.getEvidencija().getIdEvidencijaTreninga()
                        && nova.getRb() == stara.getRb()) {
                    postoji = true;
                    zaIzmenu.add(nova);
                    break;
                }
            }
            if (!postoji) {
                zaDodavanje.add(nova);
            }
        }


        for (StavkaEvidencijeTreninga stara : stavkeUBazi) {
            boolean postoji = false;
            for (StavkaEvidencijeTreninga nova : noveStavke) {
                if (stara.getEvidencija().getIdEvidencijaTreninga() == nova.getEvidencija().getIdEvidencijaTreninga()
                        && stara.getRb() == nova.getRb()) {
                    postoji = true;
                    break;
                }
            }
            if (!postoji) {
                zaBrisanje.add(stara);
            }
        }


        broker.edit(et);


        for (StavkaEvidencijeTreninga nova : zaDodavanje) {
            broker.add(nova);
        }


        for (StavkaEvidencijeTreninga izmena : zaIzmenu) {
            broker.edit(izmena);
        }


        for (StavkaEvidencijeTreninga brisanje : zaBrisanje) {
            broker.delete(brisanje);
        }

    }
}
