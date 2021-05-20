/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author PC
 */
public class HomeForm extends Form {
    
    Form current;

    public HomeForm() {
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
         Button btnAjoutAdherent=new Button ("Inscription");
      
         Button btnLogin= new Button (" Login");
        
        
        btnAjoutAdherent.addActionListener(e->new AddAdherent(current).show() );
        btnLogin.addActionListener(e->new Login(current).show() );
        
        addAll(btnAjoutAdherent,btnLogin);
                
    }

}
