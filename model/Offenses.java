package safety.model;

public class Offenses {
	protected int offenceCode;
	protected String description;
	protected String codeGroup;
	
	public Offenses(int offenceCode, String description, String codeGroup) {
		this.offenceCode = offenceCode;
		this.description = description;
		this.codeGroup = codeGroup;
	}
	
	public Offenses(int offenceCode) {
		this.offenceCode = offenceCode;
	}
	
	public Offenses(String description, String codeGroup) {
		this.description = description;
		this.codeGroup = codeGroup;
	}
	
	public int getOffenceCode() {
		return offenceCode;
	}

	public void setOffenceCode(int offenceCode) {
		this.offenceCode = offenceCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodeGroup() {
		return codeGroup;
	}

	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

}
