/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Services.ServiceGroupe.instance;
import Utils.MyConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.Joueur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahdi
 */
public class ServiceFormation {
    public static ServiceFormation instance=null;
    public ArrayList<Joueur> lj;
    public boolean resultOK;
    private ConnectionRequest req ;
    private ServiceFormation() {
         req = new ConnectionRequest();
    }
     public static ServiceFormation getInstance() {
        if (instance == null) {
            instance = new ServiceFormation();
        }
        return instance;
    }
    
    public ArrayList<Joueur> parseJoueurs(String json){
        try {
            lj = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> joueurlistJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)joueurlistJson.get("root");
            for(Map<String,Object> obj: list){
                Joueur j1 = new Joueur();
                float id =Float.parseFloat(obj.get("idJoueur").toString());
                float prix =Float.parseFloat(obj.get("prixJoueur").toString());
                  j1.setIdJoueur((int)id);
                  j1.setNomJoueur(obj.get("nomJoueur").toString());
                  j1.setPrenomJoueur(obj.get("prenomJoueur").toString());
                  j1.setPosition(obj.get("position").toString());
                  j1.setPrixJoueur((int)prix);
                  j1.setLogoJoueur(obj.get("logoJoueur").toString());
                  lj.add(j1);
                     
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return lj;
    }
    
    public ArrayList<Joueur> displayFormation(int idu){
        String url= MyConnection.BASE_URL+"/formationJson/"+idu;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lj= parseJoueurs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lj;   
    }
    
        public boolean deleteplayerformation(int idu,int idj) {
        String url = MyConnection.BASE_URL + "/DeleteJoueurJson/" + idu + "/" + idj ; 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
