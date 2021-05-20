/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Adherent;
import Services.ServiceAdherent;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author MediaStudio
 */
public class AddAdherent extends Form{
    
        Form current;
        Resources res;
     public AddAdherent(Form previous) {
         res = UIManager.initFirstTheme("/theme");
        setTitle(" Inscrption ");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("", "Entrer Votre Nom");
        TextField tfEmail = new TextField("", "Entrer Votre Email");
        TextField tfPassword = new TextField("", "Entrer Votre MDP");
        tfPassword.setConstraint(TextField.PASSWORD);
        Button btnValider = new Button("Valider ");
        btnValider.addActionListener(e-> new Login(previous).show());
        ImageViewer image = new ImageViewer(res.getImage("sign up.png"));

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfEmail.getText().length() == 0) || (tfPassword.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("Ok"));
                } else {
                    
                        Adherent t = new Adherent(15,tfName.getText(), tfEmail.getText(), tfPassword.getText());
                        if ( ServiceAdherent.getInstance().addAdherent(t)) {
                            Dialog.show("Success", "Connection Accepted", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server Error", new Command("OK"));
                        }
              
                }

            }
        });
        addAll(tfName, tfEmail, tfPassword, btnValider, image);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    
}
