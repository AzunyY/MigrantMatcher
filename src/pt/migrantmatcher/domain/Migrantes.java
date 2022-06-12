package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.plugins.PidgeonSMSSenderAdapter;
import pt.migrantmatcher.plugins.SenderType;
import utils.observer.Observer;
import utils.observer.SucessoAddAjudaEvent;

public abstract class Migrantes implements Observer<SucessoAddAjudaEvent>{
	
	private List <Ajuda> aj;
	private int tel;
	
	protected Migrantes() {
		aj = new ArrayList <>();
	}

	public void addAjuda(Ajuda ajCurr) {
		aj.add(ajCurr);
	}
	
	public void setTel(int tel) {
		this.tel = tel;
	}

	public int getTel() {
		return tel;
	}
	
	public void receiveEvent(SucessoAddAjudaEvent e) {
		MigrantConfiguration smsSender = MigrantConfiguration.getInstance();
		SenderType sender = smsSender.getClass(smsSender.getProperty("SENDERTYPE"), new PidgeonSMSSenderAdapter());
		sender.enviaSMS(this.tel,e.getMessage());
	}
}
