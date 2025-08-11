/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MataVS
 */
public class Primalac {
    private Socket soket;

    public Primalac(Socket soket) {
        this.soket = soket;
    }
    
    public Object primi(){
        try {
            ObjectInputStream in = new ObjectInputStream(soket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
            System.out.println("IOSExeption: zatvorena konekcija sa klijentom");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
