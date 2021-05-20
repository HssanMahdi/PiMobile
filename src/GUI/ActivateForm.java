/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceAdherent;
import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPSSLTransport;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author MediaStudio
 */
public class ActivateForm extends Form {

    Resources res;
    Form current;
    TextField email;

    public ActivateForm(Form previous) {
        res = UIManager.initFirstTheme("/theme");
        setLayout(BoxLayout.y());

        ImageViewer image = new ImageViewer(res.getImage("oublier.png"));
        email = new TextField("", "saisir votre email");
        // email.setSingleLineTextArea(false);

        Button valider = new Button("valider");

        Label lb = new Label("Retour se connecter?");
        Button signin = new Button("Renouveler votre mot de passe");
        signin.addActionListener(e -> new Login(previous).show());
        signin.setUIID("CenterLink");
        addAll(image, email, valider, lb, signin);
        valider.requestFocus();
        valider.addActionListener(e -> {
             
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDialog = ip.showInfiniteBlocking();
            sendMail(res);
           
            ipDialog.dispose();
          Dialog.show("Mot de passe", "Nous avons envoyé le mot de passe a votre e-mail.Veuilez vérifier votre boite de réception", new Command("OK"));
       new Login(previous).show();
            refreshTheme();
            
        }
        );

    }

//    public void sendMail(Resources res) {
//        try {
//            Properties props = new Properties();
//            props.put("mail.transport.protocol", "smtp.gmail.com"); //SMTP protocol
//            props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
//            props.put("mail.smtps.auth", "true"); //enable authentication
//            Session session = Session.getInstance(props,null);
//            MimeMessage msg = new MimeMessage(session);
//            
//            msg.setFrom(new InternetAddress("Reinitialisation mot de passe <monEmail@domaine.com>"));
//            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
//    
//            msg.setSubject("Application nom : Confirmation du");
//            msg.setSentDate(new Date(System.currentTimeMillis()));
//             
//            String mp = ServiceAdherent.getInstance().getPasswordByEmail(email.getText().toString() , res);
//            String txt = "Bienvenue sur AppNom : Tapez ce mot de passe : "+mp+" dans le champs requis et appuiez sur confirmer ";
//             msg.setText(txt, "utf-8");
//             SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
//            st.connect("smtp.gmail",465,"ghada.laaribi@esprit.tn","203JMT1888");
//            st.sendMessage(msg, msg.getAllRecipients());
//            System.out.println("server response :"+st.getLastServerResponse());
//
//        } catch (Exception e) {
//             e.printStackTrace();
//        }
//    }
      public void sendMail( Resources res) {

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "stpm.gmail.com");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("Reinitialisation mot de passe <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
            msg.setSubject("Application nom : Confirmation du");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            String mp = ServiceAdherent.getInstance().getPasswordByEmail(email.getText().toString(), res);
            String txt = "Bienvenue : Tapez ce mot de passe : "+mp+" dans le champs requis et appuiez sur valider";
            msg.setText(txt);
            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "ligue1fantasy@gmail.com", "azerty123_");
            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response : " + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
