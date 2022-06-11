package pt.migrantmatcher.domain;

import java.util.List;


public class Voluntario {

	private int tel;
	private List <Ajuda> listAjudas;
	private String cod;
	
	public Voluntario(int tel) {
		this.tel = tel;
	}
	
	public int getTel() {
		return tel;
	}
	
	public void addAjuda(Ajuda ajuda) {
		listAjudas.add(ajuda);
	}

	/**
	 *  Polimorfismo
	 **/
	public void enviaCodigo() {
		MigrantConfiguration codSender = MigrantConfiguration.getInstance();
		this.cod = codSender.enviaCod(tel);	
	}
	
	
	public boolean checkValidCod(String cod) {
		return this.cod.equals(cod);
	}

	public void enviaSMS(String string) {
		MigrantConfiguration codSender = MigrantConfiguration.getInstance();
		this.cod = codSender.enviaSMS(tel, string);	
	}

}
