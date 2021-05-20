/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.io.Preferences;
import com.codename1.ui.TextField;

/**
 *
 * @author MediaStudio
 */
public class SessionManager {
     public static Preferences pref ;
      private static int idUser ; 
    private static String nomUser ; 
    private static String email; 
    private static String passowrd ;
    
     public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getIdUser() {
        return pref.get("idUser",idUser);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setIdUser(int idUser) {
        pref.set("idUser",idUser);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNomUser() {
        return pref.get("nomUser",nomUser);
    }

    public static void setNomUser(String nomUser) {
         pref.set("nomUser",nomUser);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    
    }

}
