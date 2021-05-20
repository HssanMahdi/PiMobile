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
public class CommentaireJr {
     private int id_commentaire;
    private String message;
    private int id_user;
    private int id_joueur;
private String nom_user;
    public CommentaireJr(int id_commentaire, String message, int id_joueur) {
        this.id_commentaire = id_commentaire;
        this.message = message;
        this.id_joueur = id_joueur;
    }

    public CommentaireJr(int id_commentaire, String message, int id_user, int id_joueur) {
        this.id_commentaire = id_commentaire;
        this.message = message;
        this.id_user = id_user;
        this.id_joueur = id_joueur;
    }

    public CommentaireJr() {
    }
    

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    @Override
    public String toString() {
        return "CommentaireJr{" + "id_commentaire=" + id_commentaire + ", message=" + message + ", id_user=" + id_user + ", id_joueur=" + id_joueur + '}';
    }
    
    

}
