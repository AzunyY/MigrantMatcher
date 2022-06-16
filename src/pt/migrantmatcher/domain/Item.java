package pt.migrantmatcher.domain;

public class Item extends Aid {
	private final TYPE type = TYPE.ITEM;
	private String desc;
	
	protected Item(String desc) {
		super();
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Tipo - " + this.type + "| Item: - " + this.desc;
	}

	@Override
	public String getInfo() {
		return this.desc;
	}

	@Override
	public TYPE getType() {
		return this.type;
	}
}
