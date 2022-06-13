package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import utils.observer.DetetarAjudaEvent;
import utils.observer.DetetarEvent;

public class CatalogoAjudas{

	private List <Ajuda> listAj;

	public CatalogoAjudas() {
		listAj = new ArrayList<>();
	}

	public Alojamento criarAloj(int nPessoas) {
		return new Alojamento(nPessoas); //1.1
	}

	public void insereReg (Ajuda ajCurr, Regiao regiao) {

		((Alojamento) ajCurr).setRegiao(regiao); //1.1
	}

	public Item novoItem(String desc) {
		return new Item(desc);
	}

	public void addAj(Ajuda ajCurr, Voluntario volCurr) {
		ajCurr.setVol(volCurr); //2.1
		listAj.add(ajCurr); //2.2
	}

	public List<Ajuda> filterByReg(Regiao reg) {

		List <Ajuda> listAjudasReg = new ArrayList<>();//1.1

		listAj.stream()
		.forEach(x -> {
			if(x instanceof Alojamento) {
				if(((Alojamento) x).getRegiao().equals(reg) && x.isLivre()) //1.2 
					listAjudasReg.add(x); //1.3
			}else if (x.isLivre()) //1.4
				listAjudasReg.add(x); //1.5
		});

		return listAjudasReg;
	}

	/*vou ter de implementar o equals depois*/
	public Ajuda getAjuda(Ajuda aj) {

		for(Ajuda a: listAj)
			if(a.equals(aj))
				return a;

		return null;
	}
}
