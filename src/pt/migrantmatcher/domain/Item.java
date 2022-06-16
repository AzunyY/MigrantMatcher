package pt.migrantmatcher.domain;

/**
* Esta classe e subclasse de Aid
**/
public class Item extends Aid {
	private final TYPE type = TYPE.ITEM;
	private String desc;
	
	protected Item(String desc) {
		super();
		this.desc = desc;
	}
	
	/**
	 * Ver {@Aid}
	 **/
	@Override
	public String toString() {
		return "Tipo - " + this.type + "| Item: - " + this.desc;
	}

	/**
	 * Ver {@Aid}
	 **/
	@Override
	public String getInfo() {
		return this.desc;
	}

	/**
	 * Ver {@Aid}
	 **/
	@Override
	public String getType() {
		return this.type.name();
	}
}
