/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

//import java.sql.Date;
//import javafx.scene.image.ImageView;

/**
 *
 * @author Mahdi
 */
public class MatchEvent {

    private int idMatch;
    private String titre;
    private String dateMatch;
    private int id_equipeA;
    private int id_equipeB;
    private String nomEquipeA;
    private String nomEquipeB;
    private String logoimageA;
    private String logoimageB;

    public MatchEvent() {
    }

    public MatchEvent(int idMatch, String titre, String dateMatch, int id_equipeA, int id_equipeB, String nomEquipeA, String nomEquipeB, String logoimageA, String logoimageB) {
        this.idMatch = idMatch;
        this.titre = titre;
        this.dateMatch = dateMatch;
        this.id_equipeA = id_equipeA;
        this.id_equipeB = id_equipeB;
        this.nomEquipeA = nomEquipeA;
        this.nomEquipeB = nomEquipeB;
        this.logoimageA = logoimageA;
        this.logoimageB = logoimageB;
    }

    public String getLogoimageA() {
        return logoimageA;
    }

    public void setLogoimageA(String logoimageA) {
        this.logoimageA = logoimageA;
    }

    public String getLogoimageB() {
        return logoimageB;
    }

    public void setLogoimageB(String logoimageB) {
        this.logoimageB = logoimageB;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public int getId_equipeA() {
        return id_equipeA;
    }

    public void setId_equipeA(int id_equipeA) {
        this.id_equipeA = id_equipeA;
    }

    public int getId_equipeB() {
        return id_equipeB;
    }

    public void setId_equipeB(int id_equipeB) {
        this.id_equipeB = id_equipeB;
    }

    public String getNomEquipeA() {
        return nomEquipeA;
    }

    public void setNomEquipeA(String nomEquipeA) {
        this.nomEquipeA = nomEquipeA;
    }

    public String getNomEquipeB() {
        return nomEquipeB;
    }

    public void setNomEquipeB(String nomEquipeB) {
        this.nomEquipeB = nomEquipeB;
    }

  

    @Override
    public String toString() {
        return "MatchEvent{" + "idMatch=" + idMatch + ", titre=" + titre + ", date=" + dateMatch + ", id_equipeA=" + id_equipeA + ", id_equipeB=" + id_equipeB + ", nomEquipeA=" + nomEquipeA + ", nomEquipeB=" + nomEquipeB + '}';
    }

}
