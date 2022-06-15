package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import pt.migrantmatcher.plugins.SendSMSHelper;
import utils.observer.DetectAidEvent;
import utils.observer.Observer;

public abstract class Migrant extends SendSMSHelper implements Observer<DetectAidEvent>{
	
	private List <Aid> aj;
	private int tel;
	private String name;
	
	protected Migrant() {
		aj = new ArrayList <>();
	}

	public void addAid(Aid currAid) {
		aj.add(currAid);
	}
	
	protected void setTel(int tel) {
		this.tel = tel;
	}
	
	protected void setName(String name) {
		this.name = name;
	}

	public int getTel() {
		return tel;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void receiveEvent (DetectAidEvent e) {
		sendSMS(e.getMessage(), this.tel);
	}
	
}
