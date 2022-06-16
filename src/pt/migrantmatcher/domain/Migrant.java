package pt.migrantmatcher.domain;

import pt.migrantmatcher.exceptions.NoFileNameException;
import pt.migrantmatcher.exceptions.PropertiesLoadingException;
import pt.migrantmatcher.plugins.SendSMSHelper;
import utils.observer.DetectAidEvent;
import utils.observer.Observer;


/**
 * Esta classe vao ser as representacoes dos Migrantes 
 * E absrtacta pois estou a utilizar o padrao Template method uma vez que 
 * ha pormenores para os tipos de migrantes que sao diferentes.
 * Oserver porque os migrantes querem ser notificados de quando houver uma ajuda disponivel
 **/
public abstract class Migrant extends SendSMSHelper implements Observer<DetectAidEvent>{

	private int tel;
	private String name;

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
	
	/**
	 * Recebe a notificacao de se ter adicionada uma nova ajuda
	 * Ver @SendSMSHelper 
	 **/
	public void receiveEvent (String filename, DetectAidEvent e) {
		try {
			sendSMS(filename, e.getMessage(), this.tel);
		} catch (NoFileNameException e1) {
			System.err.println("The filename is empty!");
		} catch (PropertiesLoadingException e1) {
			System.err.println("There was an error loading the file!");
		}
	}

}
