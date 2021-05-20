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
public class Joueur {

    private int idJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private String position;
    private int scoreJoueur;
    private String logoJoueur;
    private int prixJoueur;
    private int idG;
    private String nomEquipe;
    private ImageView logoimage ;


    public void setLogoimage(ImageView logoimage) {
        this.logoimage = logoimage;
    }

    public ImageView getLogoimage() {
        return logoimage;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

   

    public Joueur() {
    }

    public Joueur(int idJoueur, String nomJoueur, String prenomJoueur, String position, int scoreJoueur, String logoJoueur, int prixJoueur, int idG) {
        this.idJoueur = idJoueur;

        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.position = position;
        this.scoreJoueur = scoreJoueur;
        this.logoJoueur = logoJoueur;
        this.prixJoueur = prixJoueur;
        this.idG = idG;
    }

    @Override
    public String toString() {
        return "Joueur{" + "idJoueur=" + idJoueur + ", nomJoueur=" + nomJoueur + ", prenomJoueur=" + prenomJoueur + ", position=" + position + ", scoreJoueur=" + scoreJoueur + ", logoJoueur=" + logoJoueur + ", prixJoueur=" + prixJoueur + ", idG=" + idG + ", nomEquipe=" + nomEquipe + '}';
    }

  

    public int getIdJoueur() {
        return idJoueur;
    }

    public int getIdG() {
        return idG;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public String getPosition() {
        return position;
    }

    public int getScoreJoueur() {
        return scoreJoueur;
    }

    public String getLogoJoueur() {
        return logoJoueur;
    }

    public int getPrixJoueur() {
        return prixJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setScoreJoueur(int scoreJoueur) {
        this.scoreJoueur = scoreJoueur;
    }

    public void setLogoJoueur(String logoJoueur) {
        this.logoJoueur = logoJoueur;
    }

    public void setPrixJoueur(int prixJoueur) {
        this.prixJoueur = prixJoueur;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
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
//        final Joueur other = (Joueur) obj;
//        if (this.idJoueur != other.idJoueur) {
//            return false;
//        }
//        return true;
//    }

}
