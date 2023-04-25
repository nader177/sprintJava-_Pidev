/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.commandes;
import entities.produits;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.Servicecommandes;
import services.Serviceproduits;

/**
 *
 * @author dot
 */
public class test {
     public static void main(String[] args) {
        
 //System.out.println("verification crude date "); 
 //servicedate P =new servicecommandes();
//date_r O = new commandes( 100,Date.valueOf("2000-01-01"));
//date_r I = new commandes( 101,Date.valueOf("2000-01-01"));
//date_r U = new commandes( 102,Date.valueOf("2000-01-01"));


//P.ajouter(O);
//P.ajouter(I);
//P.ajouter(U);s



//P.modifier(new commandes( 102,Date.valueOf("2222-01-01")));
//P.supprimer(I);
//System.out.println(P.afficher());

        
        


System.out.println("verification crude produits "); 
         Serviceproduits z =new Serviceproduits();
                  Servicecommandes c =new Servicecommandes();
produits v = new produits(10,"100","prod1","adulte", "22222222","44","");
//produits a = new produits( 11,100,"hello","hello", 22222222,"bnui" ,28,"yekhdmklchy");
//produits n = new produits( 12,102,"hello","hello", 22222222,"coucou" ,29,"autre");


z.ajouter(v);
//z.ajouter(a);
//z.ajouter(n);
//z.ajouter(q);
z.modifier(new produits("100","prod2","adulte2", "22222222","tchawtchaw",""));
z.supprimer(v);

         commandes c1=new commandes("fsdsdfsdgom", "dfsfsdffd", "qwsxdcfvgbh", "5555", 65);

System.out.println("verification crude commandes "); 
 
z.ajouter(v);
    c.ajouter(c1);
         try {
             System.out.println(z.afficher());
             //_____________________________________________________________________________________________________________________________      
         } catch (SQLException ex) {
             Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
         }
    }   
     
 
}
      


    
     
    

