/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServiceMatchEvent {

    public boolean resultOK;
    private final ConnectionRequest req;
    int moy;

    public ServiceMatchEvent() {
        req = new ConnectionRequest();
    }

    public int parseNotif(String json) {
        int e = 0;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> equipeJson = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println(equipeJson);
            for (Map.Entry<String, Object> entry : equipeJson.entrySet()) {

                float id = Float.parseFloat(entry.getValue().toString());
                e = (int) id;

            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return e;
    }

    public int displayNotif() {

        String url = MyConnection.BASE_URL + "/DisplayNotif";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                moy = parseNotif(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return moy;

    }
}
