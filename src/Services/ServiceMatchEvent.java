/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entities.MatchEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mahdi
 */
public class ServiceMatchEvent {
    public static ServiceMatchEvent instance=null;
    public ArrayList<MatchEvent> lm;
    public boolean resultOK;
    private ConnectionRequest req ;
    private String url1="";
    int moy;
   
    private ServiceMatchEvent() {
         req = new ConnectionRequest();
    }
    
     public static ServiceMatchEvent getInstance() {
        if (instance == null) {
            instance = new ServiceMatchEvent();
        }
        return instance;
    }
      public ArrayList<MatchEvent> parseMatch(String json){
        try {
            lm= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> listJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)listJson.get("root");
            for(Map<String,Object> obj: list){
                MatchEvent me = new MatchEvent();
                float id =Float.parseFloat(obj.get("idmatch").toString());
                  me.setIdMatch((int)id);
                  me.setNomEquipeA(((Map<String,Object>)obj.get("idEquipea")).get("nomEquipe").toString());
                  me.setNomEquipeB(((Map<String,Object>)obj.get("idEquipeb")).get("nomEquipe").toString());
                  me.setTitre(obj.get("titre").toString());
                  me.setLogoimageA(obj.get("imageA").toString());
                  me.setLogoimageB(obj.get("imageB").toString());
                  me.setDateMatch(obj.get("datematch").toString());
                  lm.add(me);
                     
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return lm;
    }
    
    public ArrayList<MatchEvent> displayMatchEvent(Boolean fav,int idu){
        if (fav){
            String url1= MyConnection.BASE_URL+"/DisplayfavJson/"+idu;
            req.setUrl(url1);
        }else{
            String url1= MyConnection.BASE_URL+"/DisplayMatchJson";
            req.setUrl(url1);
        }
        
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lm= parseMatch(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lm;   
    }
    
     public boolean addMatchEvent(int idu,MatchEvent em) {
        String url = MyConnection.BASE_URL + "/addfavmatchJson/" + idu + "/" + em.getIdMatch();
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
     public boolean Deletefav(int idu,MatchEvent em) {
        String url = MyConnection.BASE_URL+"/DeletefavJson/"+idu+"/"+em.getIdMatch() ;
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
     public int parseNotif(String json) {
        int e = 0;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> equipeJson = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println(equipeJson);
            for (Map.Entry<String, Object> entry : equipeJson.entrySet()) {

                float id = Float.parseFloat(entry.getValue().toString());
                e = (int) id;

            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return e;
    }

    public int displayNotif() {

        String url = MyConnection.BASE_URL + "/DisplayNotif";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                moy = parseNotif(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return moy;

    }
}
