/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CommentaireJr;
import Entities.Equipe;
import Entities.Joueur;
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
public class ServiceCommentaireJr {

    public boolean resultOK;
    public static ServiceEquipe instance;
    private ConnectionRequest req;
    public ArrayList<CommentaireJr> commentaires;
    Equipe e = new Equipe();

    public ServiceCommentaireJr() {
        req = new ConnectionRequest();
    }

    public static ServiceEquipe getInstance() {
        if (instance == null) {
            instance = new ServiceEquipe();
        }
        return instance;
    }

    public boolean addCommentaire(CommentaireJr jr) {
        String url = MyConnection.BASE_URL + "/AddCommentaireJson/" + jr.getMessage() + "/" + jr.getId_joueur();
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

    public ArrayList<CommentaireJr> parseJoueur(String jsonText) {
        commentaires = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> joueurList = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println(joueurList);

            List<Map<String, Object>> list = (List<Map<String, Object>>) joueurList.get("root");
//           System.out.println(list);
            for (Map<String, Object> obj : list) {
                CommentaireJr j1 = new CommentaireJr();
                float id = Float.parseFloat(obj.get("idCommentaire").toString());
                j1.setId_commentaire((int) id);
                j1.setMessage(obj.get("message").toString());
                float idJ = Float.parseFloat(obj.get("idJoueur").toString());
                j1.setId_joueur((int) idJ);
                float idUs = Float.parseFloat(obj.get("idUser").toString());
                j1.setId_user((int) idUs);
                j1.setNom_user(obj.get("nomUser").toString());

                commentaires.add(j1);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return commentaires;

    }

    public ArrayList<CommentaireJr> getCommentaire(int id) {
        String url = MyConnection.BASE_URL + "/DisplayCommentaireJson/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseJoueur(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentaires;
    }
    
    
      public boolean deleteCommentaire(int id) {
        String url = MyConnection.BASE_URL+"/DeleteCommentaireJson/"+id ;
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
      
      public boolean updateCommentaire(CommentaireJr t) {
        String url = MyConnection.BASE_URL +"/UpdateCommentaireJson/"+t.getId_commentaire()+"/"+t.getMessage()+"/"+t.getId_joueur();
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
