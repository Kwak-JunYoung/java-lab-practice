final class Dog extends Mammal{
	
	private String nameOfMaster;
	
	Dog(String Name, float Weight) {
		super(Name, Weight);
	}

	void bark() {
		System.out.println("Bowbow");
	}
	
	String getFood() {
		return "Apple";
	}

	public String getNameOfMaster() {
		return nameOfMaster;
	}

	public void setNameOfMaster(String nameOfMaster) {
		this.nameOfMaster = nameOfMaster;
	}
}