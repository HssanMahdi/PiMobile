/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Equipe;
import Utils.MyConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 *
 */
public class ServiceEquipe {

    public boolean resultOK;
    public static ServiceEquipe instance;
    private ConnectionRequest req;
    public ArrayList<Equipe> equipes;
    Equipe e = new Equipe();

    public ServiceEquipe() {
        req = new ConnectionRequest();
    }

    public static ServiceEquipe getInstance() {
        if (instance == null) {
            instance = new ServiceEquipe();
        }
        return instance;
    }

    public boolean addEquipe(Equipe jr) {
        String url = MyConnection.BASE_URL + "/AddEquipeJson/" + jr.getNom() + "/" + jr.getStade() + "/" + jr.getLogo_equipe();
        req.setUrl(url);
        System.out.println(url);
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

    public ArrayList<Equipe> parseEquipe(String jsonText) {

        try {
            equipes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> equipeList = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) equipeList.get("root");
            for (Map<String, Object> obj : list) {
                Equipe eq = new Equipe();
                float id = Float.parseFloat(obj.get("idEquipe").toString());
                eq.setIdG((int) id);
                eq.setNom(obj.get("nomEquipe").toString());
                eq.setstade(obj.get("stade").toString());
                eq.setLogo_equipe(obj.get("logoEquipe").toString());
                equipes.add(eq);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return equipes;

    }

    public ArrayList<Equipe> getAllEquipes() {
        String url = MyConnection.BASE_URL + "/DisplayEquipeJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                equipes = parseEquipe(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return equipes;
    }

  public Equipe parseonEquipe(String json){
      Equipe e = new Equipe();
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> equipeJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            for (Map.Entry<String, Object> entry : equipeJson.entrySet()) {
             if (entry.getKey().equalsIgnoreCase("idEquipe")){
                 e.setIdG((int)Float.parseFloat(entry.getValue().toString()));
             }
             if (entry.getKey().equalsIgnoreCase("nomEquipe")){
                 e.setNom(entry.getValue().toString());
             }
            } 
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(e);
        return e;
    }
    
     public Equipe displayoneequipe(String nom){
      
        String url= MyConnection.BASE_URL+"/DisplayEquipeByNameJson/"+nom;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              e = parseonEquipe(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        return e;
        
    }

      public boolean deleteEquipe(int id) {
        String url = MyConnection.BASE_URL+"/DeleteEquipeJson/"+id ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
         public boolean updateEquipe(Equipe t) {
        String url = MyConnection.BASE_URL +"/UpdateEquipeJson/"+t.getId()+"/"+t.getNom()+"/"+t.getStade()+"/"+t.getLogo_equipe();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }  
}
