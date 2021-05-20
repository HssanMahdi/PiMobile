/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Mahdi
 */
public class Formation {
    private int id;
    private int idAdherent;
    
    public Formation() {
    }
    
    public Formation(int id, int idAdherent) {
        this.id = id;
        this.idAdherent = idAdherent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", idAdherent=" + idAdherent + '}';
    }

    
}
