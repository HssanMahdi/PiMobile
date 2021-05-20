/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceGroupe;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entites.Groupe;

/**
 *
 * @author Mahdi
 */
public class QuitGroupe extends Form{
    Resources res1;
    public QuitGroupe(Groupe g,int idu){
        setTitle("Question ?");
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
        Button btnn = new Button("Non");
        Button btno = new Button("Oui");
         btnn.addActionListener((e) -> {
             ServiceGroupe.getInstance().leaveGroupe(idu, g,"n");
                Dialog.show("Success","Vous avez quitter"+g.getNom(),new Command("OK"));
                  new DisplayGroupes().show();
            });
          btno.addActionListener((e) -> {
              if(ServiceGroupe.getInstance().leaveGroupe(idu, g,"o")){
                Dialog.show("Success","Vous avez quitter "+g.getNom()+"définivement",new Command("OK"));
                  new DisplayGroupes().show();
              }else{
                  Dialog.show("ERROR", "Server error", new Command("OK"));
              }
            });
         SpanLabel mb = new SpanLabel("VOULEZ-VOUS QUITTER "+g.getNom()+" DÉFINITIVEMENT ?");
        Label l= new Label("(En acceptant les membres ne peut pas vous");
        Label l1= new Label("invitez !)");
        this.addAll(mb,l,l1,btno,btnn);
    }
}
