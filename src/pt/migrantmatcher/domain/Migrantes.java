package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.plugins.PidgeonSMSSenderAdapter;
import pt.migrantmatcher.plugins.SenderType;
import utils.observer.DetetarAjudaEvent;
import utils.observer.Observer;

public abstract class Migrantes implements Observer<DetetarAjudaEvent>{
	
	private List <Ajuda> aj;
	private int tel;
	private String nome;
	
	protected Migrantes() {
		aj = new ArrayList <>();
	}

	public void addAjuda(Ajuda ajCurr) {
		aj.add(ajCurr);
	}
	
	protected void setTel(int tel) {
		this.tel = tel;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;
	}

	public int getTel() {
		return tel;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void receiveEvent(DetetarAjudaEvent e) {
		MigrantConfiguration smsSender = MigrantConfiguration.getInstance();
		SenderType sender = smsSender.getClass("senderType", new PidgeonSMSSenderAdapter());
		sender.enviaSMS(this.tel, e.getMessage());
	}
	
}
