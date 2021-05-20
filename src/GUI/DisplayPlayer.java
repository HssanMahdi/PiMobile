/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Joueur;
import Entities.RatingJoueur;
import Services.ServiceJoueur;
import Services.ServiceRating;
import com.codename1.components.ImageViewer;
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
import com.codename1.ui.Slider;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DisplayPlayer extends Form {

    Font smallPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
    Font mediumPlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
    Font largePlainSystemFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
    Resources res;
    ServiceJoueur SE = new ServiceJoueur();
    ServiceRating SR = new ServiceRating();
    Form current;

    public DisplayPlayer(Form previous) {
        current = this;
       
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        ArrayList<Joueur> lg = SE.getAllJoueur();
        setTitle("Fantasy Ligue One");
        Label lab = new Label("Liste Des Joueurs");
        lab.getAllStyles().setFont(mediumPlainSystemFont);
        lab.getAllStyles().setFgColor(0x990000);
        Image ico = null;
        int mm = Display.getInstance().convertToPixels(3);
        try {
            ico = Image.createImage("/font.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        ico.scale(mm * 2, mm * 2);
        lab.setIcon(ico);
        Image img = null;
        try {
            img = Image.createImage("/jee.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        img.scale(mm * 20, mm * 10);
        ImageViewer imp = new ImageViewer(img);
        list.addAll(FlowLayout.encloseCenter(lab), imp);

        this.setLayout(new BorderLayout());

        for (int i = 0; i < lg.size(); i++) {
            list.add(addItem(lg.get(i)));

        }

        this.add(CENTER, list);
    }

    private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }

    private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);

        starRank.setMaxValue(5);
        Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xda2a2a, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;

    }

    public Container addItem(Joueur p) {

        res = UIManager.initFirstTheme("/theme");
        Container cell = new Container(BoxLayout.x());
        Container rat = new Container(BoxLayout.x());
        Container labelsCtn = new Container(BoxLayout.y());
        String nomJ = p.getNomJoueur() ;
        String pren = p.getPrenomJoueur();
        String posJ = p.getPosition();
        Label nomLB = new Label(nomJ+ " " + pren);
        nomLB.getAllStyles().setFont(mediumPlainSystemFont);
        int id = p.getIdJoueur();
        int sc = p.getScoreJoueur();
        String log = p.getLogoJoueur();
        int prix = p.getPrixJoueur();
        String nomEq = p.getNomEquipe();
        nomLB.addPointerPressedListener((e) -> {

                new InfoJoueur(id,nomJ, pren, posJ, sc, log, prix, nomEq).show();

            });

        Slider slide = createStarRankSlider();
        int idJ = p.getIdJoueur();
        int val = SR.displayRates(idJ);
        slide.setProgress(val);
        Button valid = new Button();
        int mm = Display.getInstance().convertToPixels(3);
        Image icon = null;
        try {
            icon = Image.createImage("/valid.png");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        icon.scale(mm * 1, mm * 1);
        valid.setIcon(icon);

        valid.addActionListener((e) -> {

            RatingJoueur eq = new RatingJoueur(15, slide.getProgress(), idJ);

            if (SR.addRates(eq)) {
                String st = "vous avez attribué " + slide.getProgress() + " comme rating";
                Dialog.show("Confirmation", st, "OK", "Cancel");
                
            } else {
                Dialog.show("Alerte", "Connection failed", new Command("OK"));
            }

        });
        String urlimage = "http://127.0.0.1/FantasyWeb/public/uploads/" + p.getLogoJoueur();
        EncodedImage enc3 = EncodedImage.createFromImage(res.getImage("dog.png"), true);
        URLImage image = URLImage.createToStorage(enc3, "joueur" + p.getIdJoueur() + ".png", urlimage);
        ImageViewer img = new ImageViewer(image.scaled(300, 320));
        Label stade = new Label("Position :" + posJ);
        stade.getAllStyles().setFgColor(0xe50003);
        Label esp = new Label(" ");
        Label esp1 = new Label(" ");
        rat.addAll(slide, valid);
        labelsCtn.addAll(nomLB, rat, stade, esp);
        cell.addAll(img, esp1, labelsCtn);

        return cell;
    }
}
