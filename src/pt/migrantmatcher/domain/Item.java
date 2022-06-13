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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (desc != other.desc)
			return false;
		return true;
	}
}
