package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class Voluntario {

	private int tel;
	private boolean existsInCatalogo;
	private List <Ajuda> listAjudas;
	private String cod;

	public Voluntario(int tel) {
		this.listAjudas = new ArrayList<>();
		this.tel = tel;
		this.existsInCatalogo = false;
	}

	public int getTel() {
		return tel;
	}

	public void addAjuda(Ajuda ajuda) {
		listAjudas.add(ajuda); //1.4.1
	}

	public boolean checkValidCod(String cod) {
		return this.cod.equals(cod);
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public boolean exists() {
		return existsInCatalogo;
	}

	public void adicionadoAoCatalogo() {
		this.existsInCatalogo = true;
	}
}
