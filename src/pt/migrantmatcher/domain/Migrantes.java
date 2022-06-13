package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.plugins.PidgeonSMSSenderAdapter;
import pt.migrantmatcher.plugins.SenderType;
import utils.observer.DetetarNotifEvent;
import utils.observer.Observer;

public abstract class Migrantes implements Observer<DetetarNotifEvent>{
	
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
	
	public void receiveEvent(DetetarNotifEvent e) {
		MigrantConfiguration smsSender = MigrantConfiguration.getInstance();
		SenderType sender = smsSender.getClass("senderType", new PidgeonSMSSenderAdapter());
		sender.enviaSMS(this.tel, e.getMessage());
	}
}
