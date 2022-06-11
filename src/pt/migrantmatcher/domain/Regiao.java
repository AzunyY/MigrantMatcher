package pt.migrantmatcher.domain;

import java.util.List;

import pt.migrantmatcher.plugins.PidgeonSMSSenderAdapter;
import pt.migrantmatcher.plugins.SenderType;
import utils.observer.Observer;
import utils.observer.SucessoAddAjudaEvent;

public class Regiao implements Observer<SucessoAddAjudaEvent> {
	
	private String name;
	private List <Migrantes> listMigToNotify;
	
	public Regiao(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public void addNotifList(Migrantes migCurr) {
		listMigToNotify.add(migCurr);
	}

	@Override
	public void processEvent(SucessoAddAjudaEvent e) {
		MigrantConfiguration smsSender = MigrantConfiguration.getInstance();
		SenderType sender = smsSender.getClass(smsSender.getProperty("SENDERTYPE"), new PidgeonSMSSenderAdapter());
		
		for(int i = 0; i < listMigToNotify.size(); i++)
			sender.enviaSMS(listMigToNotify.get(i).getTel(),e.getMessage());
	}
}
