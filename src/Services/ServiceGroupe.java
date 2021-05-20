/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entites.Groupe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Utils.MyConnection;
import Entities.Equipe;
import Entities.Adherent;
/**
 *
 * @author Mahdi
 */
public class ServiceGroupe {
    public static ServiceGroupe instance=null;
    public ArrayList<Groupe> g;
    public ArrayList<Adherent> a;
    public Adherent u;
    public Equipe e;
    public String s;
    public boolean resultOK;
    private ConnectionRequest req ;
    private ServiceGroupe() {
         req = new ConnectionRequest();
    }

    public static ServiceGroupe getInstance() {
        if (instance == null) {
            instance = new ServiceGroupe();
        }
        return instance;
    }
    
    public boolean addGroupe(Groupe g) {
        String url = MyConnection.BASE_URL + "/AddGroupeJson/" + g.getNom() + "/" + g.getOwner(); 
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
    
    public boolean deleteGroupe(int id) {
        String url = MyConnection.BASE_URL + "/DeleteGroupeJson/"+id; 
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
       public boolean updateGroupe(Groupe g) {
        String url = MyConnection.BASE_URL + "/UpdateGroupeJson/"+g.getId()+"/"+g.getNom(); 
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
    
    
    public ArrayList<Groupe> parseGroupes(String json){
        try {
            g = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> groupelistJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)groupelistJson.get("root");
            for(Map<String,Object> obj: list){
                Groupe g1 = new Groupe();
                float id =Float.parseFloat(obj.get("idGroupe").toString());
                float owner =Float.parseFloat(obj.get("owner").toString());
                g1.setId((int)id);
                g1.setNom(obj.get("nomGroupe").toString());
                g1.setOwner((int)owner);
                g.add(g1);
                     
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return g;
    }
    
    public ArrayList<Groupe> displayownedGroupe(){
        String url= MyConnection.BASE_URL+"/DisplayGroupesJson";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                g= parseGroupes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(g);
        return g;
        
    }
    
     public ArrayList<Groupe> DisplayGroupesmem(int id){
        String url= MyConnection.BASE_URL+"/DisplayGroupesMemJson/"+id;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                g= parseGroupes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(g);
        return g;
        
    }
    
// Partie GroupeUser    
    public ArrayList<Adherent> parsemem(String json){
        try {
            a = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> memlistJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)memlistJson.get("root");
            for(Map<String,Object> obj: list){
                Adherent a1 = new Adherent();
                float score =Float.parseFloat(obj.get("scoreUser").toString());
                a1.setNom_user(obj.get("nomUser").toString());
                a1.setScore_user((int)score);
                a.add(a1);
                     
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(a);
        return a;
    }
    
    public ArrayList<Adherent> displaymem(int id){
        String url= MyConnection.BASE_URL+"/DisplayMembersJson/"+id;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                a= parsemem(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return a;
        
    }
    
// partie quitter groupe



    
// partie LeaveGroupe définitivement   
    
     public boolean leaveGroupe(int idu,Groupe g2,String rep){
        String url= MyConnection.BASE_URL+"/quitjson/"+idu+"/"+g2.getId()+"/"+rep;
        req.setUrl(url);
        req.setPost(true);
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

//partie ajout membre
     
        public String parsereponse(String json){
        Groupe g1 = new Groupe(); 
            try {
            a = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> rep = j.parseJSON(new CharArrayReader(json.toCharArray()));
            for (Map.Entry<String, Object> entry : rep.entrySet()) {
             if (entry.getKey().equalsIgnoreCase("nomgroupe")){
                 g1.setNom(entry.getValue().toString());
             }
            
            } 
        } catch (IOException ex) {
            System.out.println(ex);
        }
       return g1.getNom();
    }
    
    public String ajoutmembre(Groupe g2,String e){
        String url= MyConnection.BASE_URL+"/AddMemberJson/"+g2.getId()+"/"+e;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                s = parsereponse(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return s;
        
    }
//    public Equipe parseonEquipe(String json){
//        try {
//            e = new Equipe();
//            JSONParser j = new JSONParser();
//            Map<String,Object> equipeJson = j.parseJSON(new CharArrayReader(json.toCharArray()));
//            for (Map.Entry<String, Object> entry : equipeJson.entrySet()) {
//             if (entry.getKey().equalsIgnoreCase("idEquipe")){
//                 e.setIdG((int)Float.parseFloat(entry.getValue().toString()));
//             }
//             if (entry.getKey().equalsIgnoreCase("nomequipe")){
//                 e.setNom(entry.getValue().toString());
//             }
//            } 
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        System.out.println(e);
//        return e;
//    }
//    
//     public Equipe displayoneequipe(String nom){
//        String url= MyConnection.BASE_URL+"/equipenomjson/"+nom;
//        req.setUrl(url);
//        req.setPost(true);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                e= parseonEquipe(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        System.out.println(g);
//        return e;
//        
//    }
}
