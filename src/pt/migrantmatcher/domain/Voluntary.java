package pt.migrantmatcher.domain;

public class Voluntary  {

	private int tel;
	private String cod;

	public Voluntary(int tel) {
		this.tel = tel;
	}

	public int getTel() {
		return tel;
	}

	public boolean checkValidCod(String cod) {
		return this.cod.equals(cod);
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

}
