/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Adherent;
import GUI.SessionManager;
import Utils.MyConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author MediaStudio
 */
public class ServiceAdherent {
     public boolean resultOK;
    public static ServiceAdherent instance;
    private ConnectionRequest req;
    public ArrayList<Adherent> adherents;
     Adherent a = new Adherent();
     String json;

    public ServiceAdherent() {
        req = new ConnectionRequest();
    }

    public static ServiceAdherent getInstance() {
        if (instance == null) {
            instance = new ServiceAdherent();
        }
        return instance;
    }

    public boolean addAdherent(Adherent t) {
        String url = MyConnection.BASE_URL + "/AddAdherentJson/"+t.getNom_user()+"/"+t.getEmail()+"/"
                +t.getPassword();
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

  public void signin (TextField nomUser,TextField password,Resources res){
        String url = MyConnection.BASE_URL + "/user/signin?nomUser="+nomUser.getText().toString()+"&password="
                +password.getText().toString();
        req=new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e)->{
            JSONParser j = new JSONParser();
            String json= new String(req.getResponseData()) +"";
            System.out.println(nomUser.getText().toString()+"     "+password.getText().toString());
            System.out.println(json);
            if (json.equals("password not found")){
                Dialog.show("Echec d'authentification", "mot de passe éronné", "Ok", null);
            }
            else if (json.equals("user not found")){
                Dialog.show("Echec d'authentification", "Nom  éronné", "Ok", null);
            }
            else{
                try {
                    System.out.println("data==="+json);
                     Dialog.show("Succès", "Connexion effectuée ", "Ok", null);
                    System.out.println(nomUser.getText().toString()+"        "+password.getText().toString());
                    Map<String,Object> user =j.parseJSON(new CharArrayReader(json.toCharArray()));
                   
                    
                    // add session user
                    
                    float idUser = Float.parseFloat(user.get("idUser").toString());
                SessionManager.setIdUser((int)idUser);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                 SessionManager.setNomUser(user.get("nomUser").toString());
                 SessionManager.setEmail(user.get("email").toString());
                SessionManager.setPassowrd(user.get("password").toString()); 
                    System.out.println("current user =====>"+SessionManager.getNomUser()+", "+SessionManager.getEmail()+", "+SessionManager.getPassowrd());
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                
        }
        
        
        
        
                });
        
          NetworkManager.getInstance().addToQueueAndWait(req);
      
  }
  
  
  
   public String getPasswordByEmail(String email,Resources res){
        String url = MyConnection.BASE_URL + "/user/getPasswordByEmail?email="+email;
        req=new ConnectionRequest(url, false);
        req.setUrl(url);
        req.addResponseListener((e)->{
            JSONParser j = new JSONParser();
             json= new String(req.getResponseData()) +"";
            
                try {
                    System.out.println("data==="+json);
                  
                    Map<String,Object> password =j.parseJSON(new CharArrayReader(json.toCharArray()));
                   
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                });
        
          NetworkManager.getInstance().addToQueueAndWait(req);
          return json;
      
  }
   
   public static void EditUser(String nomUser, String password, String email){
        
    String url = MyConnection.BASE_URL + "/user/editUser?nomUser="+nomUser+"&email="
                +email+"&password="+password;
                MultipartRequest req = new MultipartRequest();
                
                req.setUrl(url);
                req.setPost(true);
                req.addArgument("idUser", String.valueOf(SessionManager.getIdUser()));
                req.addArgument("nomUser", nomUser);
                req.addArgument("password", password);
                req.addArgument("email", email);
                System.out.println(email);
                req.addResponseListener((response)-> {
                    
                    byte[] data = (byte[]) response.getMetaData();
                    String s = new String(data);
                    System.out.println(s);
                                      
                });
             
                NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
   
   
   
   
   
}
