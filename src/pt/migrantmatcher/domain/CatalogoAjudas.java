package pt.migrantmatcher.domain;

import java.util.ArrayList;
import java.util.List;

import utils.observer.Observable;
import utils.observer.SucessoAddAjudaEvent;

public class CatalogoAjudas extends Observable<SucessoAddAjudaEvent> {

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
		this.emitEvent(new SucessoAddAjudaEvent("Foi addicionada uma nova ajuda!"));
	}
	
	public List<Ajuda> filterByReg(Regiao reg) {
		
		List <Ajuda> listAjudasReg = new ArrayList<>();
		
		listAj.stream()
			  .forEach(x -> {
				  if(x instanceof Alojamento) {
					  if(((Alojamento) x).getRegiao().equals(reg))
						  listAjudasReg.add(x);
				  }	else
					  listAjudasReg.add(x);
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

	public List<Ajuda> getListAjudas() {
		return listAj;
	}
}
