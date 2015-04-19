package twitter4Plants.Logic;

import twitter4Plants.DAO.*;
import twitter4Plants.Plants.PlantCheck;
import twitter4Plants.Plants.PlantMeta;
import twitter4Plants.Plants.PlantStatus;
import twitter4Plants.Plants.PlantType;
import twitter4Plants.TwitterSender.Sender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Core {

    private Sender twitterSender;
    private SensorDAO sensordao;
    private ServerDAO serverdao;

    private int plantUpdateMins;

    Core(int plantUpdateMins, String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {

        this.plantUpdateMins = plantUpdateMins;

        this.twitterSender = Sender.getInstance(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.sensordao = new SensorDAOImpl();
        this.serverdao = new DummyServerDAO();
    }

    public void run() {

        //TODO: configurar y lanzar el timer

        //TODO: mantener la lista de plantas a actualizar
        // - La lee de serverdao

        // La operacion del timer será:
        // - leer la información de sensordao y serverdao
        // - comprobar los parámetros
        // - postear en twitter los resultados
        twitterSender.connect();
        Timer timer = new Timer(plantUpdateMins, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Timestamp time = new Timestamp(System.currentTimeMillis());
                String today = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time);
                PlantStatus plantStatus = sensordao.getPlantStatus(0);
                PlantMeta plantMeta = serverdao.getPlantMeta(0);
                PlantType plantType = serverdao.getPlantType(plantMeta.getTypeId());
                PlantCheck plantCheck = new PlantCheck(plantStatus, plantType);
                String messageTemp = "#".concat(plantMeta.getPlantName())
                        .concat(" says: ").concat(plantCheck.temperatureStatus())
                        .concat(" @").concat(plantMeta.getOwnerTwitter()).concat(" ").concat(today);
                //twitterSender.toTweet(messageTemp);
                String messageHumidity = "#".concat(plantMeta.getPlantName())
                        .concat(" says: ").concat(plantCheck.humidityStatus())
                        .concat(" @").concat(plantMeta.getOwnerTwitter()).concat(" ").concat(today);
                //twitterSender.toTweet(messageHumidity);
                String messageLight = "#".concat(plantMeta.getPlantName())
                        .concat(" says: ").concat(plantCheck.lightStatus())
                        .concat(" @").concat(plantMeta.getOwnerTwitter()).concat(" ").concat(today);
                //twitterSender.toTweet(messageLight);
            }
        });

        timer.start();
        while(true) {}
    }
}
