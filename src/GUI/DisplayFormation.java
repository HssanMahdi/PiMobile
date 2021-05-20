/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceFormation;
import Services.ServiceMatchEvent;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
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
import Entities.Joueur;
import com.codename1.io.Storage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mahdi
 */
public class DisplayFormation extends Form{
    ArrayList<Joueur> lj;
    int idu=1;
    Resources res,res1;
    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
    public DisplayFormation(){
        setTitle("Fantasy Ligue One");
        lj=ServiceFormation.getInstance().displayFormation(idu);
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
        // Affichage 3efch
//        this.setLayout(new BorderLayout());
//        Container list= new Container(BoxLayout.y());
//        list.setScrollableY(true);
//        for (int i=0;i<lj.size();i++){
//            Joueur j2 = lj.get(i);
//            Container ct= new Container(BorderLayout.center());
//             Button btnv = new Button("Vendre");
//             btnv.addActionListener((e) -> {
//               if(ServiceFormation.getInstance().deleteplayerformation(idu, j2.getIdJoueur()))
//            {
//               Dialog.show("Success","Joueur supprimeé",new Command("OK"));
//               new DisplayFormation().show();
//             
//            }else{
//                Dialog.show("ERROR", "Server error", new Command("OK"));
//            }
//            });
//             Label l= new Label("Position : " +lj.get(i).getPosition());
//             ct.add(BorderLayout.WEST,l);
//            ct.add(BorderLayout.EAST,btnv);
//            MultiButton mb=new MultiButton(lj.get(i).getPrenomJoueur()+" "+lj.get(i).getNomJoueur());  
//            Label l1= new Label("Prix : " +lj.get(i).getPrixJoueur());
//            list.addAll(mb,ct,l1);
//        }
//        this.add(CENTER,list);

// Affichage jdid
Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
         int mm = Display.getInstance().convertToPixels(3);

           Label lab = new Label("Formation");
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
        Image img = null;
        try {
            img = Image.createImage("/formation.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Button btndfav = new Button("Liste des favoris");
        btndfav.addActionListener((e) -> {
                new DisplayMatchEvent(true).show();
            });
        img.scale(mm * 20, mm * 10);
        ImageViewer imp = new ImageViewer(img);
        list.addAll(FlowLayout.encloseCenter(lab), imp);
        Label espc = new Label("  ");
        this.setLayout(new BorderLayout());
        if(lj.size()>0){
        for (int i = 0; i < lj.size(); i++) {
            list.addAll(addItem(lj.get(i)));

        }
        this.add(CENTER, list);}

    }
    
    public Container addItem (Joueur j){
        res = UIManager.initFirstTheme("/theme");
        Container cell = new Container(BoxLayout.x());  
        Container cellY = new Container(BoxLayout.y());
        String urlimage = "http://127.0.0.1/FantasyWeb/public/uploads/" + j.getLogoJoueur();
        System.out.println(j.getLogoJoueur());
        EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("dog.png"), true);
        Image image = URLImage.createToStorage(enc3, "Player" + j.getIdJoueur(), urlimage);
        ImageViewer img = new ImageViewer(image.scaled(280, 300));
        Label nom = new Label(j.getPrenomJoueur()+" " + j.getNomJoueur());
        Label pos = new Label("Position : "+j.getPosition());
        nom.getAllStyles().setFgColor(0xe50003);
        Label lbes = new Label ("  ");
      Label lbe = new Label ("  "); 
             Button btnv = new Button("Vendre");
             btnv.addActionListener((e) -> {
               if(ServiceFormation.getInstance().deleteplayerformation(idu, j.getIdJoueur()))
            {
               Dialog.show("Success","Joueur supprimeé",new Command("OK"));
               new DisplayFormation().show();
             
            }else{
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
            });
        cellY.addAll(nom,pos,btnv);
         cell.addAll(lbes,img,lbe,cellY);
        
            
        return cell;
    }
}
