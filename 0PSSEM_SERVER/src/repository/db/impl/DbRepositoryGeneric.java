/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.sql.*;
import domen.ApstraktniDomenskiObjekat;
import java.util.LinkedList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author MataVS
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat>{

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new LinkedList<>();
        String upit = "select * from "+param.vratiNazivTabele();
        if(uslov!=null){
            upit+=uslov;
        }
        System.out.println("UPIT GET ALL: "+upit);
        
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        
        rs.close();
        st.close();
        
        return lista;
        
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "insert into "+param.vratiNazivTabele()+"("+param.vratiKoloneZaUbacivanje()+") values("+param.vratiVrednostiZaUbacivanje()+")";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "update "+param.vratiNazivTabele()+" set "+param.vratiVrednostZaIzmenu()+" where "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "delete from "+param.vratiNazivTabele()+" where "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public int addReturnId(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele()
               + "(" + param.vratiKoloneZaUbacivanje() + ") VALUES(" + param.vratiVrednostiZaUbacivanje() + ")";
       Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
       st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
       ResultSet rs = st.getGeneratedKeys();
       int id = -1;
       if (rs.next()) {
           id = rs.getInt(1);
       }
        rs.close();
       st.close();
       return id;
    }


    
}
