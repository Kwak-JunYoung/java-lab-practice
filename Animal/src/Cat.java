final class Cat extends Mammal{
	
	private String nameOfSlave;
	
	Cat(String Name, float Weight) {
		super(Name, Weight);
	}

	void bark() {
		System.out.println("Meow");
	}
	
	String getFood() {
		return "Fish";
	}

	public String getNameOfSlave() {
		return nameOfSlave;
	}

	public void setNameOfSlave(String nameOfSlave) {
		this.nameOfSlave = nameOfSlave;
	}
}