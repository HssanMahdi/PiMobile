/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MediaStudio
 */
public class Personne {
      private int id_user;
    private String nom_user;
    private String email;
    private String password;
    private String type_user;

    public Personne() {
    }

    public Personne(int id_user, String nom_user, String email, String password, String type_user) {
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.email = email;
        this.password = password;
        this.type_user = type_user;

    } 
 public Personne(int id_user, String nom_user, String email, String password) {
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.email = email;
        this.password = password;
      

    } 
    public Personne(String nom_user, String email, String password) {
        this.nom_user = nom_user;
        this.email = email;
        this.password = password;
    }
    public Personne(String nom_user, String password) {
        this.nom_user = nom_user;
        this.password = password;
    }
    

    public int getId_user() {
        return id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType_user() {
        return type_user;
    }

    public void setIduser(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }

    @Override
    public String toString() {
        return "Personne{" + "Id_user=" + id_user + ", Nom_user=" + nom_user + ", Email=" + email
                + ", Password=" + password + ", Type_user=" + type_user + '}';
    }

    
}
