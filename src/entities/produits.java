/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author nader
 */
public class produits {
    private int id;
    private String id_p;
    private String nom_p;
    private String type_p;
    private String prix_p;
    private String stock_p;
    private String img;

    public produits() {
    }

    public produits(String nom_p, String type_p, String prix_p, String stock_p) {
        this.nom_p = nom_p;
        this.type_p = type_p;
        this.prix_p = prix_p;
        this.stock_p = stock_p;
    }
    

    public produits(int id, String id_p, String nom_p, String type_p, String prix_p, String stock_p, String img) {
        this.id = id;
        this.id_p = id_p;
        this.nom_p = nom_p;
        this.type_p = type_p;
        this.prix_p = prix_p;
        this.stock_p = stock_p;
        this.img = img;
    }

    public produits(String nom_p) {
        this.nom_p = nom_p;
    }

    public produits(int id, String nom_p, String type_p, String prix_p, String stock_p) {
        this.id = id;
        this.nom_p = nom_p;
        this.type_p = type_p;
        this.prix_p = prix_p;
        this.stock_p = stock_p;
    }

    
    
    public produits(String nom_p, String type_p, String prix_p, String stock_p, String img) {
        this.nom_p = nom_p;
        this.type_p = type_p;
        this.prix_p = prix_p;
        this.stock_p = stock_p;
        this.img = img;
    }
    

    public produits(String id_p, String nom_p, String type_p, String prix_p, String stock_p, String img) {
        this.id_p = id_p;
        this.nom_p = nom_p;
        this.type_p = type_p;
        this.prix_p = prix_p;
        this.stock_p = stock_p;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_p() {
        return id_p;
    }

    public void setId_p(String id_p) {
        this.id_p = id_p;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public String getType_p() {
        return type_p;
    }

    public void setType_p(String type_p) {
        this.type_p = type_p;
    }

    public String getPrix_p() {
        return prix_p;
    }

    public void setPrix_p(String prix_p) {
        this.prix_p = prix_p;
    }

    public String getStock_p() {
        return stock_p;
    }

    public void setStock_p(String stock_p) {
        this.stock_p = stock_p;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        final produits other = (produits) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_p != other.id_p) {
            return false;
        }
        if (this.nom_p != other.nom_p) {
            return false;
        }
        if (!Objects.equals(this.type_p, other.type_p)) {
            return false;
        }
        if (!Objects.equals(this.prix_p, other.prix_p)) {
            return false;
        }
        if (!Objects.equals(this.stock_p, other.stock_p)) {
            return false;
        }
        if (!Objects.equals(this.img, other.img)) {
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return nom_p;
    }}

 
    

   
    
   

   
    
    
    
    
    

