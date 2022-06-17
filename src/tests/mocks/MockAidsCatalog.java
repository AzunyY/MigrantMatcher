package tests.mocks;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import pt.migrantmatcher.domain.Aid;
import pt.migrantmatcher.domain.AidsCatalog;
import pt.migrantmatcher.domain.Housing;
import pt.migrantmatcher.domain.Item;
import pt.migrantmatcher.domain.Region;
import pt.migrantmatcher.domain.Voluntary;
import pt.migrantmatcher.facade.DTO.AidDTO;


/**
 * Pure Fabrication - classe artificial catalogo de ajudas
 * @author Ana Luis FC53563
 */
public class MockAidsCatalog extends AidsCatalog {

	public MockAidsCatalog () {
		super();
	}

	public Housing createHousing(int nPessoas) {
		return super.createHousing(nPessoas);
	}
	
	public Item getNewItem(String desc) {
		return super.getNewItem(desc);
	}

	public void addAid(Aid currAid, Voluntary volCurr, List <String> regList) {
		 super.addAid(currAid, volCurr, regList);
	}

	public Map<Integer,Aid> filterByReg(String reg) {
		return super.filterByReg(reg);
	}

	public Aid getAid(AidDTO aidDTO) {
		return super.getAid(aidDTO);
	}

	public Optional<Boolean> aidWasAdded(Aid currAid) {
		return super.aidWasAdded(currAid);
	}
	
	public boolean isNotifyingCorrectly() {
		return super.isNotifyingCorrectly();
	}

	public void insertReg(Aid currAid, Region region) {
		super.insertReg(currAid, region);
		
	}
	
}
