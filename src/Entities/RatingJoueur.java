/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author PC
 */
public class RatingJoueur {

    private int id_rating;
    private double ratingValue;
    private int id_user;
    private int id_joueur;

    public RatingJoueur() {
    }

    public RatingJoueur(int id_rating, double ratingValue, int id_user, int id_joueur) {
        this.id_rating = id_rating;
        this.ratingValue = ratingValue;
        this.id_user = id_user;
        this.id_joueur = id_joueur;
    }

    public RatingJoueur(int id_rating, double ratingValue, int id_joueur) {
        this.id_rating = id_rating;
        this.ratingValue = ratingValue;
        this.id_joueur = id_joueur;
    }
    

    public int getId_rating() {
        return id_rating;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_joueur() {
        return id_joueur;
    }

    public void setId_rating(int id_rating) {
        this.id_rating = id_rating;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    @Override
    public String toString() {
        return "Rating{" + "id_rating=" + id_rating + ", ratingValue=" + ratingValue + ", id_user=" + id_user + ", id_joueur=" + id_joueur + '}';
    }
    

}
