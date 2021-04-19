abstract class Reptile extends Animal{
	
	Reptile(String Name, float Weight) {
		super(Name, Weight);
	}

	abstract String getFood();
}