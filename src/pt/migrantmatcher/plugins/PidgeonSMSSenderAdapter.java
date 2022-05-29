package pt.migrantmatcher.plugins;

import java.security.SecureRandom;
import java.lang.StringBuilder;

import pidgeonsmssender.sdk.PidgeonSMSSender;

public class PidgeonSMSSenderAdapter implements SenderType {
	
	@Override
	public String enviaSMS(int num, String cod) {
		new PidgeonSMSSender().send(Integer.toString(num), cod);
		return cod;
	}
}
