/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CommentaireJr;
import Entities.Equipe;
import Services.ServiceCommentaireJr;
import Services.ServiceEquipe;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class UpdateComm extends Form {

    Form current;
    ServiceCommentaireJr SC = new ServiceCommentaireJr();
     Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

    public UpdateComm(Resources res, CommentaireJr eq) {
        current = this;
        setTitle("Fantasy Ligue One");
        
        int mm = Display.getInstance().convertToPixels(3);
        Container list = new Container(BoxLayout.y());
        Label lab = new Label("Modifier Votre Commentaire");
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
       
        Image img = null;
        try {
            img = Image.createImage("/co.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        img.scale(mm * 25, mm * 10);
        ImageViewer imp = new ImageViewer(img);
        list.addAll(FlowLayout.encloseCenter(lab), imp);
        setLayout(BoxLayout.y());
        TextField tfName = new TextField(eq.getMessage());

        Button btnValid = new Button("Modifier");

        btnValid.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent evt) {

                if (tfName.getText().length() == 0) {
                    Dialog.show("Alerte", "Please fill all fields", new Command("OK"));
                } else {
                    try {
                        int id = eq.getId_commentaire();
                        int idJ = eq.getId_joueur();
                        CommentaireJr comm = new CommentaireJr(id, tfName.getText(), idJ);

                        if (SC.updateCommentaire(comm)) {
                            Dialog.show("Commentaire", "Commentaire Modifié", new Command("OK"));
                        } else {
                            Dialog.show("Alerte", "Erreur de Modification", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }

                }
            }
        });

        addAll(list,tfName, btnValid);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> current.showBack());
    }

}
