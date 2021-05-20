/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Equipe;
import Entities.RatingJoueur;
import Utils.MyConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServiceRating {
    public boolean resultOK;
    public static ServiceEquipe instance;
    private ConnectionRequest req;
    public ArrayList<Equipe> equipes;
    Equipe e = new Equipe();
    int moy;

    public ServiceRating() {
        req = new ConnectionRequest();
    }

    public static ServiceEquipe getInstance() {
        if (instance == null) {
            instance = new ServiceEquipe();
        }
        return instance;
    }

    public boolean addRates(RatingJoueur jr) {
        String url = MyConnection.BASE_URL + "/AddRatesJson/" + jr.getId_joueur()+ "/" + jr.getRatingValue();
        req.setUrl(url);
     
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;

                req.removeResponseCodeListener(this);

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;

    }

  

  public int parseRating(String json){
     int e = 0;
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> equipeJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
            for (Map.Entry<String, Object> entry : equipeJson.entrySet()) {
//             if (entry.getKey().equalsIgnoreCase("moy")){
//                 e = ((int)Float.parseFloat(entry.getValue().toString()));
//             }
                float id = Float.parseFloat(entry.getValue().toString());
                e = (int)id;
            
            } 
        } catch (IOException ex) {
            System.out.println(ex);
        }
       
        return e;
    }
    
     public int displayRates(int id){
      
        String url= MyConnection.BASE_URL+"/DisplayRatesJson/"+id;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              moy = parseRating(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        return moy;
        
    }


}
