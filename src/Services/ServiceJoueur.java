/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
public class ServiceJoueur {

    public boolean resultOK;
    public static ServiceJoueur instance;
    private final ConnectionRequest req;
    public ArrayList<Joueur> joueurs;
    Joueur jr = new Joueur();
    int moy;

    public ServiceJoueur() {
        req = new ConnectionRequest();
    }

    public static ServiceJoueur getInstance() {
        if (instance == null) {
            instance = new ServiceJoueur();
        }
        return instance;
    }

    public boolean addJoueur(Joueur jr) {
        String url = MyConnection.BASE_URL + "/AddJoueurJson/" + jr.getNomJoueur() + "/" + jr.getPrenomJoueur() + "/" + jr.getPosition() + "/" + jr.getScoreJoueur() + "/" + jr.getLogoJoueur() + "/" + jr.getPrixJoueur() + "/" + jr.getIdG();
        req.setUrl(url);
//        System.out.println(url);
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

    public boolean deleteJoueur(int id) {
        String url = MyConnection.BASE_URL + "/DeleteJoueurJson/" + id;
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

    public boolean updateJoueur(Joueur t) {
        String url = MyConnection.BASE_URL + "/UpdateEquipeJson/" + t.getIdJoueur() + "/" + t.getNomJoueur() + "/" + t.getPrenomJoueur() + "/" + t.getPosition() + "/" + t.getScoreJoueur() + "/" + t.getLogoJoueur() + "/" + t.getPrixJoueur();
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

    public ArrayList<Joueur> parseJoueur(String jsonText) {
        joueurs = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();
            Map<String, Object> joueurList = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println(joueurList);

            List<Map<String, Object>> list = (List<Map<String, Object>>) joueurList.get("root");
//           System.out.println(list);
            for (Map<String, Object> obj : list) {
                Joueur j1 = new Joueur();
                float id = Float.parseFloat(obj.get("idJoueur").toString());
                j1.setIdJoueur((int) id);
                j1.setNomJoueur(obj.get("nomJoueur").toString());
                j1.setPrenomJoueur(obj.get("prenomJoueur").toString());

                j1.setPosition(obj.get("position").toString());

                float score = Float.parseFloat(obj.get("scoreJoueur").toString());
                j1.setScoreJoueur((int) score);

                j1.setLogoJoueur(obj.get("logoJoueur").toString());
//                System.out.println(jr);

                float prix = Float.parseFloat(obj.get("prixJoueur").toString());
                j1.setPrixJoueur((int) prix);
                int a = (int) Float.parseFloat(((Map<String, Object>) obj.get("idEquipe")).get("idEquipe").toString());
//                Float idFloat = a);
                j1.setIdG(a);

                String nomEq = ((Map<String, Object>) obj.get("idEquipe")).get("nomEquipe").toString();
                j1.setNomEquipe(nomEq);

                joueurs.add(j1);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return joueurs;

    }

    public ArrayList<Joueur> getAllJoueur() {
        String url = MyConnection.BASE_URL + "/DisplayJoueurJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                joueurs = parseJoueur(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return joueurs;
    }

    public ArrayList<Joueur> getJoueurEquipe(Equipe p) {
        String url = MyConnection.BASE_URL + "/DisplayJoueurEquipeJson/" + p.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                joueurs = parseJoueur(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return joueurs;
    }


}
