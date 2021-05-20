/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Equipe;
import Services.ServiceEquipe;
import Services.ServiceJoueur;
import Services.ServiceMatchEvent;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
 * @author PC
 */
public class DisplayEquipe extends Form {

    private Resources res;
    ServiceEquipe SE = new ServiceEquipe();
    Form current;
    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);

    public DisplayEquipe(Form previous) {
        current = this;

        Container list = new Container(BoxLayout.y());
       
        setTitle("Fantasy Ligue One");
        Label lab = new Label("Liste Des Equipes");
        
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
        Image ico = null;
        int mm = Display.getInstance().convertToPixels(3);
        try {
            ico = Image.createImage("/ball.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        ico.scale(mm * 4, mm * 2);
        lab.setIcon(ico);
        Image img = null;
        try {
            img = Image.createImage("/stage.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        img.scale(mm * 20, mm * 10);
        ImageViewer imp = new ImageViewer(img);
        list.addAll(FlowLayout.encloseCenter(lab), imp);
        ArrayList<Equipe> lg = ServiceEquipe.getInstance().getAllEquipes();

        this.setLayout(new BorderLayout());

        list.setScrollableY(true);
        for (int i = 0; i < lg.size(); i++) {

            list.add(addItem(lg.get(i)));

        }
        ServiceMatchEvent SJ = new ServiceMatchEvent();
        int c = SJ.displayNotif();
        if (c!=0){
        String message = c+" Match(s) sera(ont) joué(s) aujourd hui";

        this.addShowListener(e -> ToastBar.showMessage(message, FontImage.MATERIAL_INFO));}

        this.add(CENTER, list);
    }

    public Container addItem(Equipe p) {

        res = UIManager.initFirstTheme("/theme");
        Container cell = new Container(BoxLayout.x());
        Container labelsCtn = new Container(BoxLayout.y());
        Label nomLB = new Label(p.getNom());
        nomLB.getAllStyles().setFont(mediumPlainSystemFont);
        nomLB.getAllStyles().setFgColor(0xe50003);
        Button btnJ = new Button("Les Joueurs");
        btnJ.addActionListener((e)->{
        new DisplayPlayerEquipe(p).show();
        });
        
        
        int mm = Display.getInstance().convertToPixels(3);
        Image icon = null;
        try {
            icon = Image.createImage("/ico.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       icon.scale(mm * 1, mm * 1);
        btnJ.setIcon(icon);
        String urlimage = "http://127.0.0.1/FantasyWeb/public/uploads/" + p.getLogo_equipe();
        EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("dog.png"), true);
        URLImage image = URLImage.createToStorage(enc3, "equipe" + p.getId() + ".png", urlimage);
        ImageViewer img = new ImageViewer(image.scaled(300, 320));
        Label stade = new Label("Stade :" + p.getStade());
        Label esp = new Label(" ");
        Label esp1 = new Label(" ");
        labelsCtn.addAll(nomLB, stade,btnJ, esp);
        cell.addAll(img,esp1, labelsCtn);

        return cell;
    }
    
  
}
