package pt.migrantmatcher.domain;

public class Item extends Ajuda {
	
	private String desc;
	
	protected Item(String desc) {
		super();
		this.desc = desc;
		setTipo(Tipo.ITEM);
		setInfo(this.desc);
	}

	public String getDesc() {
		return desc;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Alojamento) {
			Item other = (Item) obj;
			return other != null || this == other || this.desc.equals(other.desc);
		}
		return false;
	}
}
