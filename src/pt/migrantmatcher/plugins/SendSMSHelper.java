package pt.migrantmatcher.plugins;

import pt.migrantmatcher.domain.MigrantConfiguration;

public abstract class SendSMSHelper {

	protected void sendSMS(String filename, String message, int tel){
			MigrantConfiguration smsSender = MigrantConfiguration.getInstance(filename);
			SenderType sender = smsSender.getClass("senderType", new PidgeonSMSSenderAdapter());
			sender.sendSMS(tel, message);
	}
}
