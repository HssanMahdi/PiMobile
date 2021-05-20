/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Services.ServiceAdherent;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.Storage;
import com.codename1.share.EmailShare;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MediaStudio
 */
public class EditProfile extends Form {
     Form current;
     Resources res;
     private static String i;

     
     public EditProfile(Form previous){
          res = UIManager.initFirstTheme("/theme");
          setTitle(" EditProfile ");
          setLayout(BoxLayout.y());
           ImageViewer image = new ImageViewer(res.getImage("useredit.png"));
         Button modiff = new Button("Modifier");
         Button precedent  = new Button("Precedent");
         Button Deconexion =new Button("Deconexion");
         Deconexion.addActionListener((e)->{
             new Login(previous).show();
             SessionManager.pref.clearAll();
             Storage.getInstance().clearStorage();
             Storage.getInstance().clearCache();
             System.out.println(SessionManager.getNomUser());
         }
         );
         
         precedent.addActionListener(e-> new Login(previous).show());
       
        String us = SessionManager.getNomUser();
        System.out.println(us);

        TextField nomUser = new TextField(us);
       
        TextField password = new TextField(SessionManager.getPassowrd(), "password");
 
        TextField email = new TextField(SessionManager.getEmail(), "email");
     
        modiff.addActionListener((edit)-> {
                 InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg    = ip.showInifiniteBlocking();
            ServiceAdherent.EditUser(nomUser.getText(), password.getText(), email.getText());
            SessionManager.setNomUser(nomUser.getText());
            SessionManager.setPassowrd(password.getText());
            SessionManager.setEmail(email.getText());
            Dialog.show("Succès", "Modifications des coordonnées avec succès", "Ok", null);
            ipDlg.dispose();
            refreshTheme();
            
        });
         addAll(image,nomUser,email,password,modiff,precedent,Deconexion);
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    } 

  
      
   
}
