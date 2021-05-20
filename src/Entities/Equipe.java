/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author PC
 */
public class Equipe {

  
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
    private int idG;
    private String nom;
    private String logo_equipe; 
    private String stade;
    private ImageView logoimage ;
    

    public Equipe() {
    }
    
      public Equipe(int idG, String nom, String logo_equipe, String stade, ImageView logoimage) {
        this.idG = idG;
        this.nom = nom;
        this.logo_equipe = logo_equipe;
        this.stade = stade;
        this.logoimage = logoimage;
    }

    public Equipe(int idG, String nom, String logo_equipe, String stade) {
        this.idG = idG;
        this.nom = nom;
        this.logo_equipe = logo_equipe;
        this.stade = stade;
    }
    
     public Equipe(String nom, String logo_equipe, String stade) {
        this.idG = idG;
        this.nom = nom;
        this.logo_equipe = logo_equipe;
        this.stade = stade;
    }

  

    public int getId() {
        return idG;
    }

    public String getNom() {
        return nom;
    }

    public String getStade() {
        return stade;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public void setLogo_equipe(String logo_equipe) {
        this.logo_equipe = logo_equipe;
    }

    public String getLogo_equipe() {
        return logo_equipe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setstade(String stade) {
        this.stade = stade;
    }

    public void setLogoimage(ImageView logoimage) {
        this.logoimage = logoimage;
    }

    public ImageView getLogoimage() {
        return logoimage;
    }

    @Override
    public String toString() {
        return "Equipe{" + "idG=" + idG + ", nom=" + nom + ", logo_equipe=" + logo_equipe + ", stade=" + stade + '}';
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Equipe other = (Equipe) obj;
//        if (this.idG != other.idG) {
//            return false;
//        }
//        return true;
//    }

   
    
    
}

    

