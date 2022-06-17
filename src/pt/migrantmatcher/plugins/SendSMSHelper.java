package pt.migrantmatcher.plugins;

import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;

/**
 * Classe que trata de notificar - 	High Coehesion
 * @author Ana Luis FC53563
 */
public abstract class SendSMSHelper {

	/**
	 * Envia um sms para um determinado numero de telemovel
	 * @param filename - ficheiro properties
	 * @param tel - nr de telemovel do destino
	 * @throws PropertiesLoadingException
	 */
	protected void sendSMS(String message, int tel) throws PropertiesLoadingException{

		MigrantConfiguration smsSender = MigrantConfiguration.getInstance("defaults.propertie");
		SenderType sender;

		try {
			sender = smsSender.getClass("senderType");
			sender.sendSMS(tel, message);
		} catch (PropertiesLoadingException e) {
			System.err.println("Parameters misssing in file, a default value will be used!");
			sender = new PidgeonSMSSenderAdapter();
			sender.sendSMS(tel, message);
			throw new PropertiesLoadingException();
		}	
	}
}
