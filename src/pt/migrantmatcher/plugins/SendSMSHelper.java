package pt.migrantmatcher.plugins;

import pt.migrantmatcher.domain.MigrantConfiguration;
import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;

public abstract class SendSMSHelper {

	protected void sendSMS(String filename, String message, int tel) throws PropertiesLoadingException, NoFileNameException{
		
		MigrantConfiguration smsSender = MigrantConfiguration.getInstance(filename);
		SenderType sender;
		
		if(filename == null || filename.isEmpty()) {
			System.err.println("No file available, a default value will be used!");
			sender = new PidgeonSMSSenderAdapter();
			sender.sendSMS(tel, message);
			throw new NoFileNameException();
		}
		else {
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
}
