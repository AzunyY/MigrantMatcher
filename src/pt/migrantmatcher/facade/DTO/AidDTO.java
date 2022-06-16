package pt.migrantmatcher.facade.DTO;

public class AidDTO {

	private int ref;
	private String info;
	private String type;
	private boolean availability;
	private int tel;

	public AidDTO(int ref, String info, boolean availability, int tel, String type) {
		this.ref = ref;
		this.info = info;
		this.availability = availability;
		this.tel = tel;
		this.type = type;
	}

	public String getAidInfo() {

		return "Aid - " + this.type + " | Info - " + this.info;

	}

	public String getType() {
		return this.type;
	}

	public String getInfo() {
		return this.info;
	}

	public boolean getAvailability() {
		return this.availability;
	}

	public int getTel() {
		return this.tel;
	}

	public int getRef() {
		return ref;
	}

	public void set(boolean availability) {
		this.availability = availability;		
	}
}

