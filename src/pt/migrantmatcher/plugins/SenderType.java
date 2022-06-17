package pt.migrantmatcher.plugins;

/**
 * Padrao adapter - Plugins, para se conseguir adicionar outras implementacoes
 * @author Ana Luis FC53563
 */
public interface SenderType {
	
	public void sendSMS(int num, String cod);
}
