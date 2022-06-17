package pt.migrantmatcher.plugins;

/**
 * Padrao adapter - Plugins, para se conseguir adicionar outras implementacoes
 * @author azuny
 *
 */
public interface SenderType {
	
	public void sendSMS(int num, String cod);
}
