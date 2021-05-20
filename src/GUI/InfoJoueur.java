/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CommentaireJr;
import Services.ServiceCommentaireJr;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class InfoJoueur extends Form {

    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
    Resources res;
    Form current;
    ServiceCommentaireJr SC = new ServiceCommentaireJr();

    public InfoJoueur(int id, String n, String pre, String pos, int s, String l, int p, String e) {
        current = this;
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);

        setTitle("Fantasy Ligue One");

        int mm = Display.getInstance().convertToPixels(3);
        Image img = null;
        try {
            img = Image.createImage("/detail.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        img.scale(mm * 25, mm * 15);
        ImageViewer imp = new ImageViewer(img);
        String urlimage = "http://127.0.0.1/FantasyWeb/public/uploads/" + l;
        res = UIManager.initFirstTheme("/theme2");
        EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("dog.jpg"), true);
        Image image = URLImage.createToStorage(enc3, "joueur" + n + ".png", urlimage);

        add(LayeredLayout.encloseIn(
                imp,
                BorderLayout.south(
                        GridLayout.encloseIn(1,
                                FlowLayout.encloseCenter(
                                        new Label(image, "PictureWhiteBackgrond"))
                        )
                )
        ));

        MultiButton mb = new MultiButton("Nom :" + n + " " + pre);

        SpanLabel r = new SpanLabel("Position :" + pos);
        Label sc = new Label("Score :" + s);
        SpanLabel prix = new SpanLabel("Prix :" + p);
        SpanLabel eq = new SpanLabel("Equipe :" + e);

        Image imgComm = null;
        try {
            imgComm = Image.createImage("/commn.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        imgComm.scale(mm * 28, mm * 5);
        ImageViewer imgComme = new ImageViewer(imgComm);

        imgComme.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddCommJr(id).show();
            }
        });

        ArrayList<CommentaireJr> lg = SC.getCommentaire(id);

        Container con = new Container();
        for (int i = 0; i < lg.size(); i++) {

            con.add(addItem(lg.get(i)));

        }

//        Container ct = new Container(BorderLayout.center());
//        Button btnJ = new Button("bn");
//        Image icon = null;
//        try {
//            icon = Image.createImage("/ico.png");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//        icon.scale(mm * 1, mm * 1);
//        btnJ.setIcon(icon);
//        ct.add(BorderLayout.EAST, btnJ);
        list.addAll(mb, r, sc, prix, eq, imgComme, con);
        this.add(list);
    }

    public Container addItem(CommentaireJr cm) {
        Container ctn = new Container(BoxLayout.y());
        
        Container cell = new Container(BoxLayout.x());
        int mm = Display.getInstance().convertToPixels(3);
        Image imgUs = null;
        try {
            imgUs = Image.createImage("/ftUser.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        imgUs.scale(mm * 3, mm * 3);
        ImageViewer imgUsr = new ImageViewer(imgUs);
        Label lbU = new Label(cm.getNom_user());
        lbU.getStyle().setFont(mediumPlainSystemFont);
        Container art = new Container(BoxLayout.x());
        res = UIManager.initFirstTheme("/theme");
        Button btnadd = new Button();
        Image tr = null;
        try {
            tr = Image.createImage("/edit.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       tr.scale(mm * 1, mm * 1);
        btnadd.setIcon(tr);
        btnadd.addActionListener(e->new UpdateComm(res,cm).show());
        Button btnsupp = new Button();
         Image trs = null;
        try {
            trs = Image.createImage("/trash.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       trs.scale(mm * 1, mm * 1);
        btnsupp.setIcon(trs);
        int id = cm.getId_commentaire();
        btnsupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                
                    if(SC.deleteCommentaire(id)) {
                        Dialog.show("Equipe supprimé", "Server Success", new Command("OK"));
                    } else {
                        Dialog.show("Alerte", "Connection failed", new Command("OK"));
                    }

                }
            }
        );
        if (cm.getId_user()==1){
        art.addAll(btnadd, btnsupp);}

        cell.addAll(imgUsr, lbU, BorderLayout.east(art));
        SpanLabel lbMsg = new SpanLabel(cm.getMessage());

        Image sep = null;
        try {
            sep = Image.createImage("/separator.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        sep.scale(mm * 30, mm * 3);
        ImageViewer sepr = new ImageViewer(sep);
  
        ctn.addAll(cell, lbMsg, sepr);

        return ctn;
    }

}
