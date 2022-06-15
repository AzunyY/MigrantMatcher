package pt.migrantmatcher.plugins;

import pt.migrantmatcher.domain.MigrantConfiguration;

public abstract class SendSMSHelper {

	protected void sendSMS(String message, int tel){
			MigrantConfiguration smsSender = MigrantConfiguration.getInstance();
			SenderType sender = smsSender.getClass("senderType", new PidgeonSMSSenderAdapter());
			sender.sendSMS(tel, message);
	}
}
