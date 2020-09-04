public abstract class Beverage {
	private String description = "Unknown Beverage";

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public abstract double cost();
}
