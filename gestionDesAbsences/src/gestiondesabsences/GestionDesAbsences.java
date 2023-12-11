/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestiondesabsences;

/**
 *
 * @author 
 */
public class GestionDesAbsences {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Database db = new Database();
        menu mn = new menu(db);
        mn.firstMenu();

    }

}
