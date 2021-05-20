/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.MatchEvent;
import Services.ServiceMatchEvent;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mahdi
 */
public class DisplayMatchEvent extends Form {
    int idu=1;
    ArrayList<MatchEvent> lm;
    Resources res,res1;
    Label lab;
    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

    public DisplayMatchEvent(Boolean fav) {
        
        setTitle("Fantasy Ligue One");
        System.out.println(SessionManager.getIdUser());
                res1 = UIManager.initFirstTheme("/theme");
Toolbar tb = this.getToolbar();
Image icon = res1.getImage("2.png");
Image icon1 = res1.getImage("3.png");
Container topBar = BorderLayout.east(new Label(icon));
topBar.add(BorderLayout.SOUTH, new Label("Fantasy ligue one", "SidemenuTagline"));
topBar.setUIID("SideCommand");
tb.addComponentToSideMenu(topBar);
tb.addCommandToRightBar("",icon1, (e) -> {});

tb.addMaterialCommandToSideMenu("Joueurs", FontImage.MATERIAL_GROUPS, e -> {new DisplayPlayer().show();});
tb.addMaterialCommandToSideMenu("Equipes", FontImage.MATERIAL_SPORTS_SOCCER, e -> {new DisplayEquipe().show();});
tb.addMaterialCommandToSideMenu("Groupes", FontImage.MATERIAL_PEOPLE, e -> {new DisplayGroupes().show();});
tb.addMaterialCommandToSideMenu("Formation", FontImage.MATERIAL_STORE, e -> {new DisplayFormation().show();});
tb.addMaterialCommandToSideMenu("Matchs", FontImage.MATERIAL_EMOJI_EVENTS, e -> {new DisplayMatchEvent(false).show();});
tb.addMaterialCommandToSideMenu("Magasin", FontImage.MATERIAL_STORE, e -> {});
tb.addMaterialCommandToSideMenu("Actualités", FontImage.MATERIAL_ARTICLE, e -> {new displayact().show();});
tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_ACCOUNT_CIRCLE, e -> {new EditProfile(this).show();});
tb.addMaterialCommandToSideMenu("Déconnexion", FontImage.MATERIAL_LOGOUT, e -> {
    SessionManager.pref.clearAll();
             Storage.getInstance().clearStorage();
             Storage.getInstance().clearCache();
    new Login(this).show(); });
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
         int mm = Display.getInstance().convertToPixels(3);
        if(fav){
           
             lab = new Label("Liste des favoris");
        }else{    
            lab = new Label("Liste Des Matchs");}
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
        Image img = null;
        try {
            img = Image.createImage("/match.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Button btndfav = new Button("Liste des favoris");
        btndfav.addActionListener((e) -> {
                new DisplayMatchEvent(true).show();
            });
        img.scale(mm * 20, mm * 10);
        ImageViewer imp = new ImageViewer(img);
        
        if(fav){
            list.addAll(FlowLayout.encloseCenter(lab), imp);
        }else{
        list.addAll(FlowLayout.encloseCenter(lab), imp,FlowLayout.encloseCenter(btndfav));
        }
        Label espc = new Label("  ");
        this.setLayout(new BorderLayout());
        lm = ServiceMatchEvent.getInstance().displayMatchEvent(fav,idu);
        if(lm.size()>0){
        for (int i = 0; i < lm.size(); i++) {
            list.addAll(addItem(lm.get(i),fav));

        }
        this.add(CENTER, list);}

    }

    public Container addItem(MatchEvent p,Boolean fav1) {

        res = UIManager.initFirstTheme("/theme");
        Container ct= new Container(BorderLayout.absolute());
        Container cell = new Container(BoxLayout.x());
        Container cellY = new Container(BoxLayout.y());
        String urlimage = "http://127.0.0.1/FantasyWeb/public/uploads/" + p.getLogoimageA();
        String urlimageA = "http://127.0.0.1/FantasyWeb/public/uploads/" + p.getLogoimageB();
        EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("dog.png"), true);
        Image image = URLImage.createToStorage(enc3, "testA" + p.getIdMatch(), urlimage);
        Image imageA = URLImage.createToStorage(enc3, "test" + p.getIdMatch(), urlimageA);
        ImageViewer img = new ImageViewer(image.scaled(280, 300));
        ImageViewer imgA = new ImageViewer(imageA.scaled(280, 300));
        Label titre = new Label("  " + p.getTitre() + "  ");
        titre.addPointerPressedListener((e) ->{
          new TimeLeft(p.getIdMatch()).show();
        });
        titre.getAllStyles().setFgColor(0xe50003);
        Label escp = new Label("  ");
        Label escp2 = new Label("  ");
        if(fav1){
             Button btnsupp = new Button("Supprimer du favoris");
        btnsupp .addActionListener((e) -> {
                if(ServiceMatchEvent.getInstance().Deletefav(idu, p)){
                            Dialog.show("Success","Match supprimer du favoris",new Command("OK"));
                            new DisplayMatchEvent(true).show();
                        }else{
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });
        ct.add(BorderLayout.CENTER,btnsupp);
        }else{
        Button btnfav = new Button("Ajouter au favoris");
        btnfav .addActionListener((e) -> {
                if(ServiceMatchEvent.getInstance().addMatchEvent(idu, p)){
                            Dialog.show("Success","Match ajouter au favoris",new Command("OK"));
                        new DisplayMatchEvent(true).show();
                        }else{
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            });
        ct.add(BorderLayout.CENTER,btnfav);
        }
//        cell.addAll(BorderLayout.centerEastWest(titre, imgA, img));
        cell.add(BorderLayout.west(img));
        cell.add(BorderLayout.center(titre));
        cell.add(BorderLayout.east(imgA));
        cellY.addAll(escp2,cell,ct, escp);

        return cellY;
    }
}
