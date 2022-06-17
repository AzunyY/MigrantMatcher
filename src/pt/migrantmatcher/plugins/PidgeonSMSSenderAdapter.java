package pt.migrantmatcher.plugins;

import com.pidgeonsmssender.sdk.PidgeonSMSSender;
/** 
 * Adaptador do PidgeonSMSSender
 * @author Ana Luis FC53563
 */
public class PidgeonSMSSenderAdapter implements SenderType {
	
	@Override
	public void sendSMS(int num, String cod) {
		new PidgeonSMSSender().send(Integer.toString(num), cod);
	}
}
