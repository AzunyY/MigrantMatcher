package pt.migrantmatcher.plugins;

import telegramsms.TelegramSMSSender;

public class TelegramSMSSenderAdapter implements SenderType{

	@Override
	public void enviaSMS(int num, String cod) {
		TelegramSMSSender sender = new TelegramSMSSender();
		sender.setNumber(Integer.toString(num));
		sender.setText(cod);
		sender.send();
	}

}
