package pt.migrantmatcher.plugins;

import com.pidgeonsmssender.sdk.PidgeonSMSSender;

public class PidgeonSMSSenderAdapter implements SenderType {
	
	@Override
	public void enviaSMS(int num, String cod) {
		new PidgeonSMSSender().send(Integer.toString(num), cod);
	}
}
