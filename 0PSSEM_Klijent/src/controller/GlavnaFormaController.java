/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Trener;
import forme.GlavnaForma;
import javax.swing.JFrame;

/**
 *
 * @author MataVS
 */
public class GlavnaFormaController {
    private final GlavnaForma glavnaForma;

    public GlavnaFormaController(GlavnaForma glavnaForma) {
        this.glavnaForma = glavnaForma;
        addActionListeners();
    }
    
    public void otvoriFormu(){
        glavnaForma.setLocationRelativeTo(null);
//        glavnaForma.setExtendedState(JFrame.MAXIMIZED_BOTH);
        glavnaForma.setVisible(true);
        Trener ulogovani = cordinator.Cordinator.getInstance().getUlogovani();
        glavnaForma.getLblUlogovani().setText(ulogovani.getIme() + " "+ ulogovani.getPrezime() + " :)");
    }

    private void addActionListeners() {
        
    }
    
}
