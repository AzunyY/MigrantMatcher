package pt.migrantmatcher.domain;

public class Item extends Aid {
	
	private String desc;
	
	protected Item(String desc) {
		super();
		this.desc = desc;
		setType(Type.ITEM);
		setInfo(this.desc);
	}

	public String getDesc() {
		return desc;
	}
}
