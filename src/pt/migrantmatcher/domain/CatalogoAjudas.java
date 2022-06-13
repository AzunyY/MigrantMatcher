package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import utils.observer.DetetarAjudaEvent;
import utils.observer.Observable;

public class CatalogoAjudas extends Observable<DetetarAjudaEvent>{

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

	public void addAj(Ajuda ajCurr, Voluntario volCurr, List <String> regList) {
		ajCurr.setVol(volCurr); //2.1
		listAj.add(ajCurr); //2.2
		
		String reg = ajCurr instanceof Alojamento ? ((Alojamento) ajCurr).getRegiao().getName() : "ALL";

		if(reg.equals("ALL")) {
			for(String s : regList)
				notifyAllObservers(new DetetarAjudaEvent(s));
		} else
			notifyAllObservers(new DetetarAjudaEvent(reg));

	}

	public List<Ajuda> filterByReg(String reg) {

		List <Ajuda> listAjudasReg = new ArrayList<>();//1.1

		listAj.stream()
		.forEach(x -> {
			if(x instanceof Alojamento) {
				if(((Alojamento) x).getRegiao().getName().equals(reg) && x.isLivre()) //1.2 
					listAjudasReg.add(x); //1.3
			}else if (x.isLivre()) //1.4
				listAjudasReg.add(x); //1.5
		});

		return listAjudasReg;
	}

	/*vou ter de implementar o equals depois*/
	public Ajuda getAjuda(Ajuda aj) {

		for(Ajuda a: this.listAj)
			if(a.equals(aj))
				return a;

		return null;
	}

	public List<Ajuda> getListAjudas() {
		return this.listAj;
	}
}
