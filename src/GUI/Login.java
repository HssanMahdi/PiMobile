/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Adherent;
import Services.ServiceAdherent;
import com.codename1.components.ImageViewer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.template.SignUpForm;



/**
 *
 * @author MediaStudio
 */
public class Login extends Form{
     Form current;
     Resources res;
     public Login(Form previous){
       
         res = UIManager.initFirstTheme("/theme");
          setTitle(" Login ");
        setLayout(BoxLayout.y());
        ImageViewer image = new ImageViewer(res.getImage("login.png"));
        TextField nomUser = new TextField("", "Entrer Votre Nom");
        TextField password = new TextField("", "Entrer Votre MDP");
        password.setConstraint(TextField.PASSWORD);
        Button btnValider = new Button("Se connecter");
     // btnValider.addActionListener(e-> new EditProfile(previous).show());
         Button signUp = new Button("Inscription");
         
      Label lb = new Label("Vous n'avez aucun compte?");
        Button signUP = new Button("Ajouter Compte");
        signUP.addActionListener(e -> new AddAdherent(previous).show());
        signUP.setUIID("CenterLink");
        
        //mp oublié
        Button mp= new Button("Mot de passe oublié?", "CenterLabel");
        
          btnValider.addActionListener((e)-> {
              if ((nomUser.getText().length()==0) || (password.getText().length()==0))
                    Dialog.show("Alert", "Champ vide", new Command("OK"));
             else{ServiceAdherent.getInstance().signin(nomUser, password, res);}
              
             
             
          });
                 
           mp.addActionListener((e)-> {
               new ActivateForm(previous).show();
           }
           );
       addAll(image, nomUser, password, btnValider,lb,signUP,mp);

    
}
}
