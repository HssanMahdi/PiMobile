/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import Services.ServiceGroupe;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entites.Groupe;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Mahdi
 */
public class DisplayGroupes extends Form{
    ArrayList<Groupe> lg;
    int idu=1;
    Resources res1;
    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
    public DisplayGroupes(){
           lg=ServiceGroupe.getInstance().displayownedGroupe();
           lg.addAll(ServiceGroupe.getInstance().DisplayGroupesmem(1));
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
           Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
         int mm = Display.getInstance().convertToPixels(3);
           // Affichage 3efch
//        setTitle("Mes Groupes");
//        Container ct= new Container(BorderLayout.center());
//        Button btnadd = new Button("Crée un groupe");
//        btnadd.addActionListener((e) -> {
//                new AddGroupe(idu).show();
//            });
//        ct.add(BorderLayout.CENTER,btnadd);
//        this.setLayout(new BorderLayout());
//        Container list= new Container(BoxLayout.y());
//        list.setScrollableY(true);
//        for (int i=0;i<lg.size();i++){
//            MultiButton mb=new MultiButton("Nom de groupe : "+lg.get(i).getNom());
//            Groupe g = lg.get(i);
//            if (lg.get(i).getOwner()==idu){
//            Button btnajout = new Button("Ajouter");
//            Button btnup = new Button("Modifier");
//            Button btnsupp = new Button("Supprimer");
//            Button btnmem = new Button("Membres");
//            
//            btnajout.addActionListener((e) -> {
//                new Ajoutmembre(g,idu).show();
//            });
//            btnup.addActionListener((e) -> {
//                new UpdateGroupe(g).show();
//            });
//            btnsupp.addActionListener((e) -> {
//            if(ServiceGroupe.getInstance().deleteGroupe(g.getId()))
//            {
//                new DisplayGroupes().show();
//               Dialog.show("Success","Groupe supprimeé",new Command("OK"));
//             
//            }else{
//                Dialog.show("ERROR", "Server error", new Command("OK"));
//            }
//            });
//            btnmem.addActionListener((e) -> {
//                new DisplayMembres(g).show();
//            });
//            list.addAll(mb,btnajout,btnup,btnsupp,btnmem);
//            }else{
//                Button btnmem = new Button("Membres");
//                Button btnquit = new Button("Quitter");
//                btnmem.addActionListener((e) -> {
//                new DisplayMembres(g).show();
//            });
//                btnquit.addActionListener((e) -> {
//                new QuitGroupe(g,idu).show();
//            });
//                list.addAll(mb,btnmem,btnquit);
//            }
//        }
//        
//this.add(TOP,ct);
//this.add(CENTER,list);

//affichage jdid
 Label lab = new Label("Mes Groupes");
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
        Image img = null;
        try {
            img = Image.createImage("/groups.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
         Button btnadd = new Button("Crée un groupe");
        btnadd.addActionListener((e) -> {
                new AddGroupe(idu).show();
            });
        img.scale(mm * 20, mm * 10);
        ImageViewer imp = new ImageViewer(img);
        list.addAll(FlowLayout.encloseCenter(lab), imp,FlowLayout.encloseCenter(btnadd));
        this.setLayout(new BorderLayout());
        if(lg.size()>0){
        for (int i = 0; i < lg.size(); i++) {
            list.addAll(addItem(lg.get(i)));

        }
        this.add(CENTER, list);
        }
    }
    public Container addItem (Groupe g){
//        res = UIManager.initFirstTheme("/theme");
        Container cell = new Container(BoxLayout.y());
        Container cellY = new Container(BoxLayout.x());
        Label nom = new Label("Nom de groupe : " + g.getNom());
        nom.getAllStyles().setFgColor(0xe50003);
        Label lbes = new Label ("  ");
      Label lbe = new Label ("  "); 
        if (g.getOwner()==idu){
            Button btnajout = new Button("Ajouter");
            Button btnup = new Button("Modifier");
            Button btnsupp = new Button("Supprimer");
            Button btnmem = new Button("Membres");
            
            btnajout.addActionListener((e) -> {
                new Ajoutmembre(g,idu).show();
            });
            btnup.addActionListener((e) -> {
                new UpdateGroupe(g).show();
            });
            btnsupp.addActionListener((e) -> {
            if(ServiceGroupe.getInstance().deleteGroupe(g.getId()))
            {
                new DisplayGroupes().show();
               Dialog.show("Success","Groupe supprimeé",new Command("OK"));
             
            }else{
                Dialog.show("ERROR", "Server error", new Command("OK"));
            }
            });
            btnmem.addActionListener((e) -> {
                new DisplayMembres(g).show();
            });
            cellY.addAll(btnajout,btnup,btnsupp,btnmem);
            }else{
                Button btnmem = new Button("Membres");
                Button btnquit = new Button("Quitter");
                btnmem.addActionListener((e) -> {
                new DisplayMembres(g).show();
            });
                btnquit.addActionListener((e) -> {
                new QuitGroupe(g,idu).show();
            });
                cellY.addAll(btnmem,btnquit);
            }
    
         cell.addAll(FlowLayout.encloseCenter(lbe,nom,cellY));
        
            
        return cell;
    }
    
}
