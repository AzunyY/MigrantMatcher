package pt.migrantmatcher.plugins;

import com.telegramsms.TelegramSMSSender;

public class TelegramSMSSenderAdapter implements SenderType{

	public void sendSMS(int num, String cod) {
		
		TelegramSMSSender sender = new TelegramSMSSender();
		
		sender.setNumber(Integer.toString(num));
		sender.setText(cod);
		sender.send();
	}

}
