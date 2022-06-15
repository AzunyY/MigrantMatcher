package pt.migrantmatcher.plugins;

import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.exceptios.ThereIsNoValueInPropertiesException;

public abstract class SendSMSHelper {

	protected void sendSMS(String filename, String message, int tel) throws NoFileNameException, PropertiesLoadingException{

		if(filename.isBlank())
			throw new NoFileNameException();
		
		else {

			MigrantConfiguration smsSender = MigrantConfiguration.getInstance(filename);
			SenderType sender;
			try {
				sender = smsSender.getClass("senderType");
				sender.sendSMS(tel, message);
			} catch (ThereIsNoValueInPropertiesException | PropertiesLoadingException e) {
				sender = new PidgeonSMSSenderAdapter();
				sender.sendSMS(tel, message);
				throw new PropertiesLoadingException();
			}	
		}


	}
}
