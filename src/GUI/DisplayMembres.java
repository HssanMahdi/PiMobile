/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceGroupe;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import Entities.Adherent;
import com.codename1.io.Storage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entites.Groupe;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mahdi
 */
public class DisplayMembres extends Form{
    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
    Resources res1;
    public DisplayMembres(Groupe g){
    ArrayList<Adherent> lg=ServiceGroupe.getInstance().displaymem(g.getId());
    setTitle("Fantasy Ligue One");
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
    //affichage 3efch
//    setTitle("Membre de "+g.getNom());
//    this.setLayout(new BorderLayout());
//     Container list= new Container(BoxLayout.y());
//        list.setScrollableY(true);
//        for (int i=0;i<lg.size();i++){
//            MultiButton mb=new MultiButton("Nom : "+lg.get(i).getNom_user());
//            Label l=new Label("Score : "+lg.get(i).getScore_user());
//            list.addAll(mb,l);
//        }
//        
//this.add(CENTER,list);
// affichage jdid
Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
         int mm = Display.getInstance().convertToPixels(3);

           Label lab = new Label("Membre de "+g.getNom());
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
        Image img = null;
        try {
            img = Image.createImage("/members.jpg");
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
        this.setLayout(new BorderLayout());
        if(lg.size()>0){
        for (int i = 0; i < lg.size(); i++) {
            list.addAll(addItem(lg.get(i)));
            
        }
        this.add(CENTER, list);}
    }
     public Container addItem (Adherent u){
     Container cell = new Container(BoxLayout.x());
            Container cellY = new Container(BoxLayout.y());
            Label nom=new Label("Nom : "+u.getNom_user());
            nom.getAllStyles().setFgColor(0xe50003);
            Label l=new Label("Score : "+u.getScore_user());
            cellY.addAll(nom,l);
            int mm1 = Display.getInstance().convertToPixels(3);
            Image img1 = null;
        try {
            img1 = Image.createImage("/person.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            img1.scale(mm1 * 6, mm1 * 3);
        ImageViewer imp1 = new ImageViewer(img1);
                Label espc = new Label("  ");
        Label espc1 = new Label("  ");
         cell.addAll(espc,imp1,espc1,cellY);
     return cell;
     }
}
