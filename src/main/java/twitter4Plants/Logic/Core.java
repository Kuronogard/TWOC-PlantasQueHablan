package twitter4Plants.Logic;

import twitter4Plants.DAO.DummySensorDAO;
import twitter4Plants.DAO.DummyServerDAO;
import twitter4Plants.DAO.SensorDAO;
import twitter4Plants.DAO.ServerDAO;
import twitter4Plants.TwitterSender.Sender;

public class Core {

	private Sender twitterSender;
	private SensorDAO sensordao;
	private ServerDAO serverdao;
	
	private int plantUpdateMins;
	
	Core(int plantUpdateMins, String consumerKey, String consumerSecret, String accessToken, String tokenSecret){
		
		this.plantUpdateMins = plantUpdateMins;
		
		this.twitterSender = Sender.getInstance(consumerKey, consumerSecret, accessToken, tokenSecret);
		this.sensordao = new DummySensorDAO();
		this.serverdao = new DummyServerDAO();
	}
	
	public void run(){
		
		//TODO: configurar y lanzar el timer
		
		//TODO: mantener la lista de plantas a actualizar
		// - La lee de serverdao
		
		// La operacion del timer será:
		// - leer la información de sensordao y serverdao
		// - comprobar los parámetros
		// - postear en twitter los resultados
	}
}
