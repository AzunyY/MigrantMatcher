package pt.migrantmatcher.domain;

public class Item extends Ajuda {
	
	private String desc;
	
	protected Item(String desc) {
		super();
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
