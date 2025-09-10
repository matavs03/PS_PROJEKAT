/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import forme.LoginForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author MataVS
 */
public class LoginController {
    private final LoginForma lf;

    public LoginController(LoginForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String username = lf.getTxtUsrname().getText().trim();
                String password = String.valueOf(lf.getPassPassword().getPassword());
                System.out.println(username);
                System.out.println(password);
                Komunikacija.getInstance().konekcija();
                Trener t = Komunikacija.getInstance().login(username, password);
                
                if(t==null){
                    JOptionPane.showMessageDialog(null, "Korisničko ime i šifra nisu ispravni");
                }
                else{
                    cordinator.Cordinator.getInstance().setUlogovani(t);
                    JOptionPane.showMessageDialog(null, "Korisničko ime i šifra su ispravni");
                    cordinator.Cordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                    
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setLocationRelativeTo(null);
        lf.setVisible(true);
        
    }

    

    
    
    
}
