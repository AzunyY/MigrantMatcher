package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

public class CatalogoAjudas {

	private List <Ajuda> listAj;
	
	public CatalogoAjudas() {
		listAj = new ArrayList<>();
	}
	
	public Alojamento criarAloj(int nPessoas) {
		return new Alojamento(nPessoas);
	}
	
	public void insereReg (Ajuda ajCurr, Regiao reg) {
		((Alojamento) ajCurr).setRegiao(reg);
	}
	
	public Item novoItem(String desc) {
		return new Item(desc);
	}

	public void addAj(Ajuda ajCurr) {
		listAj.add(ajCurr);
	}
	
}
