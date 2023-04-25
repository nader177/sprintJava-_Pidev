/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author nader
 */
public class commandes {
    private int id;
    private String date;
    private String nom;
    private String prenom;
    private String adress;
    private String tel;
    private produits prod;
    private int idprod;
    private String status ;

    public int getIdprod() {
        return idprod;
    }

    public void setIdprod(int idprod) {
        this.idprod = idprod;
    }

    
    public commandes() {
    }

    public commandes(String date, String nom, String prenom, String adress, String tel, produits prod, int idprod, String status) {
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.tel = tel;
        this.prod = prod;
        this.idprod = idprod;
        this.status = status;
    }

    public commandes(String nom, String prenom, String adress, String tel, int idprod) {    
        
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.tel = tel;
        this.idprod = idprod;
    }

    public commandes(int id, String date, String nom, String prenom, String adress, String tel, produits prod, int idprod, String status) {
        this.id = id;
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.tel = tel;
        this.prod = prod;
        this.idprod = idprod;
        this.status = status;
    }

    public commandes(int id, String date, String nom, String prenom, String adress, String tel, String status) {
        this.id = id;
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.tel = tel;
        
        this.status = status;
    }

    public commandes(String nom) {
        this.nom = nom;
    }

    public commandes(String date, String nom, String prenom, String adress, String tel, produits prod, String status) {
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.tel = tel;
        this.prod = prod;
        this.status = status;
    }

    public commandes(int id, String date, String nom, String prenom, String adress, String tel, produits prod, String status) {
        this.id = id;
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.tel = tel;
        this.prod = prod;
        this.status = status;
    }

    public commandes(String date, String nom, String prenom, String adress, String tel, String status) {
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
        this.adress = adress;
        this.tel = tel;
       
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

 
  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public produits getProd() {
        return prod;
    }

    public void setProd(produits prod) {
        this.prod = prod;
    }
 
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        final commandes other = (commandes) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.date != other.date) {
            return false;
        }
        if (this.nom != other.nom) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
       
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "commandes{" + "id=" + id + ", date=" + date + ", nom=" + nom + ", prenom=" + prenom + ", adress=" + adress + ", tel=" + tel + ", prod=" + prod + ", status=" + status + '}';
    }
    

}
    
    
    
    

