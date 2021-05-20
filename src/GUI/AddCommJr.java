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
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class AddCommJr extends Form {

    private Resources res;
    ServiceCommentaireJr SC = new ServiceCommentaireJr();
    Form current;
    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

    public AddCommJr(int id) {

        current = this;
        setTitle("Fantasy Ligue One");
        res = UIManager.initFirstTheme("/theme");
        
        int mm = Display.getInstance().convertToPixels(3);
        Container list = new Container(BoxLayout.y());
        Label lab = new Label("Votre Commentaire");
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
        TextField tf =  new TextField("","Votre commentaire");
    
        list.addAll(FlowLayout.encloseCenter(lab), imp);

        Button btnValid = new Button("Commenter");
        btnValid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (tf.getText().length() == 0) {
                    Dialog.show("Alerte", "Remplir les Champs", new Command("OK"));
                } else {
                    try {
                        CommentaireJr comm = new CommentaireJr(5, tf.getText(), id);

                        if (SC.addCommentaire(comm)) {
                            Dialog.show("Commentaire", "Commentaire Ajouté", new Command("OK"));
                            new DisplayPlayer(current).show();
                        } else {
                            Dialog.show("Alerte", "Erreur D'ajout", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }

                }
            }
        });

        list.addAll(tf, btnValid);
        add(list);

    }
}
